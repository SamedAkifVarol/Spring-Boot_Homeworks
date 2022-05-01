package org.samedakifvarol.restaurant.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.samedakifvarol.restaurant.data.RestaurantEntity;
import org.samedakifvarol.restaurant.data.RestaurantRepository;
import org.samedakifvarol.restaurant.exception.RestaurantNotFoundException;
import org.samedakifvarol.restaurant.model.UpdateRestaurantModel;
import org.samedakifvarol.restaurant.shared.RestaurantDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{

    RestaurantRepository restaurantRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public RestaurantDto add(RestaurantDto restaurantDetails) {

        restaurantDetails.setRestaurantId(UUID.randomUUID().toString());
        restaurantDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(restaurantDetails.getPassword()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RestaurantEntity restaurantEntity = modelMapper.map(restaurantDetails, RestaurantEntity.class);

        restaurantRepository.save(restaurantEntity);

        RestaurantDto returnValue = modelMapper.map(restaurantEntity,RestaurantDto.class);

        return returnValue;
    }

    @Override
    public RestaurantDto update(UpdateRestaurantModel restaurantDetails ,Long id) {
        if (restaurantRepository.findById(id).isPresent()){
            RestaurantEntity existingRestaurant = restaurantRepository.findById(id).get();

            existingRestaurant.setName(restaurantDetails.getName());
            existingRestaurant.setMenu(restaurantDetails.getMenu());
            existingRestaurant.setItem(restaurantDetails.getItem());
            existingRestaurant.setDistrict(restaurantDetails.getDistrict());
            existingRestaurant.setCity(restaurantDetails.getCity());

            RestaurantEntity updatedRestaurant = restaurantRepository.save(existingRestaurant);

            return new RestaurantDto(updatedRestaurant.getName(),
                    updatedRestaurant.getCity(),
                    updatedRestaurant.getDistrict(),
                    updatedRestaurant.getMenu(),
                    updatedRestaurant.getItem(),
                    updatedRestaurant.getRestaurantId(),
                    updatedRestaurant.getEncryptedPassword());
        }else {
            return null;
        }
    }


    @Override
    public List<RestaurantEntity> gets() {
        return (List<RestaurantEntity>) restaurantRepository.findAll();
    }

    @Override
    public RestaurantDto getRestaurant(Long id) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
        RestaurantEntity restaurantError = restaurantEntity.orElseThrow(() -> new RestaurantNotFoundException("Restaurant Id : " + id));
        RestaurantDto restaurantDto = new ModelMapper().map(restaurantEntity,RestaurantDto.class);
        return restaurantDto;
    }

    @Override
    public void delete(Long id) {
        boolean exists = restaurantRepository.existsById(id);
        if (!exists) {
            throw new RestaurantNotFoundException("Restaurant Id : " + id);
        }
        restaurantRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String restaurantId) throws UsernameNotFoundException {
        RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(restaurantId);
        if (restaurantEntity == null) throw new RestaurantNotFoundException("RestaurantId or Password are not valid");

        return  new User(restaurantEntity.getRestaurantId(), restaurantEntity.getEncryptedPassword() ,
                true,true,true,true, new ArrayList<>());
    }
}
