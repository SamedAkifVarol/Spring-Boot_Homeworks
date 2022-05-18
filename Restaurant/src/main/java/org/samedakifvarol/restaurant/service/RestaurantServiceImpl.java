package org.samedakifvarol.restaurant.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.samedakifvarol.restaurant.controller.request.AddRestaurantRequest;
import org.samedakifvarol.restaurant.controller.response.AddRestaurantResponse;
import org.samedakifvarol.restaurant.converter.RestaurantConverter;
import org.samedakifvarol.restaurant.exception.MenuNotFoundException;
import org.samedakifvarol.restaurant.model.dto.MenuDto;
import org.samedakifvarol.restaurant.model.dto.MenuItemDto;
import org.samedakifvarol.restaurant.model.entity.MenuEntity;
import org.samedakifvarol.restaurant.model.entity.MenuItemEntity;
import org.samedakifvarol.restaurant.model.entity.RestaurantEntity;
import org.samedakifvarol.restaurant.repository.MenuItemRepository;
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
    private MenuItemRepository menuItemRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AddRestaurantResponse add(AddRestaurantRequest restaurantDetails) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RestaurantDto restaurantDto = modelMapper.map(restaurantDetails,RestaurantDto.class);

        restaurantDto.setRestaurantId(UUID.randomUUID().toString());
        restaurantDto.setEncryptedPassword(bCryptPasswordEncoder.encode(restaurantDetails.getPassword()));

        RestaurantEntity restaurantEntity = modelMapper.map(restaurantDto,RestaurantEntity.class);
        restaurantRepository.save(restaurantEntity);

        Optional<MenuEntity> menuEntity = menuRepository.findById(restaurantDetails.getMenu_id());
        if (menuEntity.isPresent()){
            // Hata:  Found shared references to a collection: org.samedakifvarol.restaurant.model.entity.MenuEntity.menuItem
            menuRepository.save(menuEntity.get());
        }
        else {
            MenuEntity menuError = menuEntity.orElseThrow(() ->
                    new MenuNotFoundException("Menu Id: " + restaurantDetails.getMenu_id()));
        }

        AddRestaurantResponse addRestaurantResponse = modelMapper.map(restaurantEntity,AddRestaurantResponse.class);
        return addRestaurantResponse;
    }

    @Override
    public UpdateRestaurant update(UpdateRestaurant restaurantDetails , Long id) {
        Optional<RestaurantEntity> optionalRestaurantEntity = restaurantRepository.findById(id);
        if (optionalRestaurantEntity.isPresent()){
            RestaurantEntity restaurant = optionalRestaurantEntity.get();

            Optional<MenuEntity> menuEntity = menuRepository.findById(restaurantDetails.getMenu_id());
            menuEntity.get().setRestaurant(restaurant);
            menuRepository.save(menuEntity.get());

            List<MenuEntity> menuEntityList = new ModelMapper().map(menuEntity,new TypeToken<List<MenuEntity>>(){}.getType());

            restaurant.setName(restaurantDetails.getName());
            restaurant.setCity(restaurantDetails.getCity());
            restaurant.setDistrict(restaurantDetails.getDistrict());
            restaurant.setItem(restaurantDetails.getItem());
            restaurant.setMenu(menuEntityList);

            return RestaurantConverter.updatingConvert(restaurant);
        }
        throw new RestaurantNotFoundException("Restaurant not Found");
    }


    @Override
    public Page<GetRestaurantResponse> gets(Pageable page) {
        Page<GetRestaurantResponse> restaurantEntity = restaurantRepository.findAll(page).map(GetRestaurantResponse::new);
        return  restaurantEntity;
    }

    @Override
    public GetRestaurantResponse getRestaurant(Long id) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
        RestaurantEntity restaurantError = restaurantEntity.orElseThrow(() ->
                new RestaurantNotFoundException("Restaurant Id : " + id));
        GetRestaurantResponse getRestaurantResponse = new ModelMapper().map(restaurantEntity,GetRestaurantResponse.class);

        List<MenuEntity> menuEntity = menuRepository.findByRestaurantId(id);
        List<MenuItemEntity> menuItemEntity = menuItemRepository.findByMenuId(menuEntity.stream().findAny().get().getId());

        List<MenuItemDto> menuItemDto = new ModelMapper().map(menuItemEntity,new TypeToken<List<MenuItemDto>>(){}.getType());

        MenuDto menuDto = new MenuDto();
        menuDto.setMenuItem(menuItemDto);
        menuDto.setName(menuEntity.stream().findAny().get().getName());
        menuDto.setId(menuEntity.stream().findAny().get().getId());

        getRestaurantResponse.setMenu(menuDto);

        return getRestaurantResponse;
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
    public RestaurantDto getRestaurantDetailsbyId(String Id) {
        RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(Id);

        if (restaurantEntity == null) throw new UsernameNotFoundException(Id);
        return new ModelMapper().map(restaurantEntity,RestaurantDto.class);
    }

    //HATA: Converter org.modelmapper.internal.converter.MergingCollectionConverter@1f8c4555 failed to convert org.hibernate.collection.internal.PersistentBag to java.util.List.
    @Override
    public UserDetails loadUserByUsername(String restaurantId) throws UsernameNotFoundException {
        RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantId(restaurantId);
        if (restaurantEntity == null) throw new RestaurantNotFoundException("RestaurantId or Password are not valid");

        return  new User(restaurantEntity.getRestaurantId(), restaurantEntity.getEncryptedPassword() ,
                true,true,true,true, new ArrayList<>());
    }
}
