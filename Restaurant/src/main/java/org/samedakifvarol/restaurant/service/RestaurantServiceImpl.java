package org.samedakifvarol.restaurant.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.samedakifvarol.restaurant.controller.request.AddRestaurantRequest;
import org.samedakifvarol.restaurant.controller.response.AddRestaurantResponse;
import org.samedakifvarol.restaurant.model.entity.RestaurantEntity;
import org.samedakifvarol.restaurant.repository.MenuRepository;
import org.samedakifvarol.restaurant.repository.RestaurantRepository;
import org.samedakifvarol.restaurant.exception.RestaurantNotFoundException;
import org.samedakifvarol.restaurant.controller.response.GetRestaurantResponse;
import org.samedakifvarol.restaurant.controller.response.UpdateRestaurant;
import org.samedakifvarol.restaurant.model.dto.RestaurantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{

    private RestaurantRepository restaurantRepository;
    private MenuRepository menuRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AddRestaurantResponse add(AddRestaurantRequest restaurantDetails) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RestaurantDto restaurantDto = modelMapper.map(restaurantDetails,RestaurantDto.class);

        restaurantDto.setRestaurantId(UUID.randomUUID().toString());
        restaurantDto.setEncryptedPassword(bCryptPasswordEncoder.encode(restaurantDetails.getPassword()));

        RestaurantEntity restaurantEntity = modelMapper.map(restaurantDto,RestaurantEntity.class);

        return null;
    }

    @Override
    public RestaurantMenuDto update(UpdateRestaurant restaurantDetails, Long id) {
        return null;
    }

//    @Override
//    public RestaurantMenuDto update(UpdateRestaurant restaurantDetails , Long id) {
//        Optional<RestaurantEntity> optionalRestaurantEntity = restaurantRepository.findById(id);
//        if (optionalRestaurantEntity.isPresent()){
//            RestaurantEntity restaurant = optionalRestaurantEntity.get();

            //Optional<MenuEntity> menuEntity = menuRepository.findById(restaurant.getMenu().getId());
            //MenuEntity menu = menuEntity.get();// id 10 olan menü

//            if (restaurantDetails.getSoups().equals(menu.getSoups()) &&
//                restaurantDetails.getDrinks().equals(menu.getDrinks()) &&
//                restaurantDetails.getDeserts().equals(menu.getDeserts()) &&
//                restaurantDetails.getMainDishes().equals(menu.getMainDishes())) {
//
//                //Restaurant Bilgileri -----
//                restaurant.setName(restaurantDetails.getName());
//                restaurant.setItem(restaurantDetails.getItem());
//                restaurant.setDistrict(restaurantDetails.getDistrict());
//                restaurant.setCity(restaurantDetails.getCity());
//                //restaurant.setMenu(menu);
//
//                restaurantRepository.save(restaurant);
//
//                return RestaurantConverter.convert(restaurant);
//
//            } else {
//                MenuDto menuDto = new MenuDto();
//                menuDto.setMainDishes(restaurantDetails.getMainDishes());
//                menuDto.setDeserts(restaurantDetails.getDeserts());
//                menuDto.setDrinks(restaurantDetails.getDrinks());
//                menuDto.setSoups(restaurantDetails.getSoups());
//
//                menuService.add(menuDto);
//
//                MenuEntity yeniMenu = menuRepository.findTopByOrderByIdDesc();
//
//                restaurant.setName(restaurantDetails.getName());
//                restaurant.setItem(restaurantDetails.getItem());
//                restaurant.setDistrict(restaurantDetails.getDistrict());
//                restaurant.setCity(restaurantDetails.getCity());
//                restaurantRepository.save(restaurant);
//
//                return RestaurantConverter.convert(restaurant);
//            }
//        }
//        throw new RestaurantNotFoundException("Restaurant not Found");
//    }


    // Hepsini Getir --------------------------------------------------------------------------------
    @Override
    public Page<GetRestaurantResponse> gets(Pageable page) {
        Page<GetRestaurantResponse> restaurantEntity = restaurantRepository.findAll(page).map(GetRestaurantResponse::new);
        return  restaurantEntity;
    }

    //Birini Getir -----------------------------------------------------------------------------------------------------
    @Override
    public RestaurantDto getRestaurant(Long id) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
        RestaurantEntity restaurantError = restaurantEntity.orElseThrow(() ->
                new RestaurantNotFoundException("Restaurant Id : " + id));
        RestaurantDto restaurantDto = new ModelMapper().map(restaurantEntity,RestaurantDto.class);
        return restaurantDto;
    }

    //Restaurant Sil ---------------------------------------------------------------------------------------------------
    @Override
    public void delete(Long id) {
        boolean exists = restaurantRepository.existsById(id);
        if (!exists) {
            throw new RestaurantNotFoundException("Restaurant Id : " + id);
        }
        restaurantRepository.deleteById(id);
    }

    @Override
    public RestaurantDto getRestaurantDetailsbyId(String Id) {
        RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(Id);

        if (restaurantEntity == null) throw new UsernameNotFoundException(Id);
        return new ModelMapper().map(restaurantEntity,RestaurantDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String restaurantId) throws UsernameNotFoundException {
        RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(restaurantId);
        if (restaurantEntity == null) throw new RestaurantNotFoundException("RestaurantId or Password are not valid");

        return  new User(restaurantEntity.getRestaurantId(), restaurantEntity.getEncryptedPassword() ,
                true,true,true,true, new ArrayList<>());
    }
}
