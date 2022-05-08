package org.samedakifvarol.restaurant.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.samedakifvarol.restaurant.converter.RestaurantConverter;
import org.samedakifvarol.restaurant.data.MenuEntity;
import org.samedakifvarol.restaurant.data.MenuRepository;
import org.samedakifvarol.restaurant.data.RestaurantEntity;
import org.samedakifvarol.restaurant.data.RestaurantRepository;
import org.samedakifvarol.restaurant.exception.RestaurantNotFoundException;
import org.samedakifvarol.restaurant.model.UpdateRestaurant;
import org.samedakifvarol.restaurant.shared.MenuDto;
import org.samedakifvarol.restaurant.shared.RestaurantDto;
import org.samedakifvarol.restaurant.shared.RestaurantMenuDto;
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

    RestaurantRepository restaurantRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    MenuRepository menuRepository;
    MenuService menuService;
    //ModelMapper modelMapper = new ModelMapper();

    //Restaurant Ekleme
    @Override
    public RestaurantMenuDto add(RestaurantMenuDto restaurantDetails,Long id) {

        // RestaurantId ve EncryptedPassword Oluşturuluyor
        restaurantDetails.setRestaurantId(UUID.randomUUID().toString());
        restaurantDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(restaurantDetails.getPassword()));

        // ModelMapper Objesi Oluşturuluyor
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //MenuEntity class'ından istenilen id çekiliyor.
        Optional<MenuEntity> menuEntity = menuRepository.findById(id);

        //menuEntity Optional özelliğini devredışı bırakmak için "yeni obje" oluşturdum.
        MenuEntity menu = modelMapper.map(menuEntity,MenuEntity.class);

        //RestaurantEntity objesi oluşturulup,setMenu("yeni obje") eklendi
        RestaurantEntity restaurantEntity = modelMapper.map(restaurantDetails, RestaurantEntity.class);
        restaurantEntity.setMenu(menu);

        //DataBase'e eklendi.
        restaurantRepository.save(restaurantEntity);

        // Controller için uygun obje oluşturalım
        RestaurantMenuDto returnValue = new RestaurantMenuDto(
                restaurantEntity.getName(),
                restaurantEntity.getCity(),
                restaurantEntity.getDistrict(),
                restaurantEntity.getItem(),
                restaurantEntity.getRestaurantId(),
                restaurantEntity.getEncryptedPassword(),
                menu.getCorbalar(),
                menu.getAnaYemekler(),
                menu.getIcecekler(),
                menu.getIcecekler(),
                restaurantDetails.getPassword()

        );

        return returnValue;
    }

    // Restaurant Güncelle ---------------------------------------------------------

    /*
    1-) Girilen id doğru mu diye kontrol et +
    2-) Doğruysa; Restaurant ve menü bilgilerini değiştirebil. +
    3-) Menü bilgileri hali hazırda yoksa, yeni bir menü oluşturabil.
    4-) Bilgileri PostMan'de bastır.
     */
    @Override
    public RestaurantMenuDto update(UpdateRestaurant restaurantDetails , Long id) {
        Optional<RestaurantEntity> optionalRestaurantEntity = restaurantRepository.findById(id);
        if (optionalRestaurantEntity.isPresent()){
            RestaurantEntity restaurant = optionalRestaurantEntity.get();

            //Seçilen restorantın menu bilgileri elde edildi.
            Optional<MenuEntity> menuEntity = menuRepository.findById(restaurant.getMenu().getId());
            MenuEntity menu = menuEntity.get();// id 10 olan menü

            //Menu bilgileri Farklı mı, diye kontrol edildi.
            // Aynı ise ;
            if (restaurantDetails.getCorbalar().equals(menu.getCorbalar()) &&
                restaurantDetails.getIcecekler().equals(menu.getIcecekler()) &&
                restaurantDetails.getTatlilar().equals(menu.getTatlilar()) &&
                restaurantDetails.getAnaYemekler().equals(menu.getAnaYemekler())) {

                //Restaurant Bilgileri -----
                restaurant.setName(restaurantDetails.getName());
                restaurant.setItem(restaurantDetails.getItem());
                restaurant.setDistrict(restaurantDetails.getDistrict());
                restaurant.setCity(restaurantDetails.getCity());
                restaurant.setMenu(menu);

                //Menu kaydedildi
                restaurantRepository.save(restaurant);

                return RestaurantConverter.convert(restaurant);

            //Menü Bilgileri Farklı ise;
            } else {
                //Menuyu güncellemek için DTO objesi oluşturuldu
                MenuDto menuDto = new MenuDto();
                //Menu güncellendi
                menuDto.setAnaYemekler(restaurantDetails.getAnaYemekler());
                menuDto.setTatlilar(restaurantDetails.getTatlilar());
                menuDto.setIcecekler(restaurantDetails.getIcecekler());
                menuDto.setCorbalar(restaurantDetails.getCorbalar());

                //DataBase'e eklendi.
                menuService.add(menuDto);

                //Restaurant tablosundaki menü_id bilgisini değiştirmek için ;
                //Bunu çağırıyoruz
                MenuEntity yeniMenu = menuRepository.findTopByOrderByIdDesc();

                //Restaurant Bilgileri -----
                restaurant.setName(restaurantDetails.getName());
                restaurant.setItem(restaurantDetails.getItem());
                restaurant.setDistrict(restaurantDetails.getDistrict());
                restaurant.setCity(restaurantDetails.getCity());
                restaurant.setMenu(yeniMenu);

                //Menu kaydedildi
                restaurantRepository.save(restaurant);

                return RestaurantConverter.convert(restaurant);
            }
        }
        throw new RestaurantNotFoundException("Restaurant not Found");
    }

    // Hepsini Getir --------------------------------------------------------------------------------
    @Override
    public Page<RestaurantEntity> gets(Pageable page) {
        return restaurantRepository.findAll(page);
//        Page<GetRestaurantResponse> getRestaurantResponses = restaurants.stream().map(restaurant ->
//                new ModelMapper().map(restaurant,GetRestaurantResponse.class)).collect(Collectors.toList());
//        return getRestaurantResponses;
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
