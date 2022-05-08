package org.samedakifvarol.restaurant.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.samedakifvarol.restaurant.converter.MenuConverter;
import org.samedakifvarol.restaurant.data.MenuEntity;
import org.samedakifvarol.restaurant.data.MenuRepository;
import org.samedakifvarol.restaurant.data.RestaurantEntity;
import org.samedakifvarol.restaurant.exception.MenuNotFoundException;
import org.samedakifvarol.restaurant.exception.RestaurantNotFoundException;
import org.samedakifvarol.restaurant.model.MenuRequest;
import org.samedakifvarol.restaurant.shared.MenuDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    MenuRepository menuRepository;

    //Menu Oluştur
    @Override
    public MenuDto add(MenuDto menuDto) {
        //ModelMapper Objesi Oluşturuldu
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        MenuEntity menu = modelMapper.map(menuDto,MenuEntity.class);

        menuRepository.save(menu);

        MenuDto returnValue = modelMapper.map(menu,MenuDto.class);

        return returnValue;
    }
    //Menü Güncelle ---------------------------------------------
    @Override
    public MenuDto update(MenuRequest menuRequest, Long id) {
        Optional<MenuEntity> optionalMenuEntity = menuRepository.findById(id);
        if (optionalMenuEntity.isPresent()){
            MenuEntity menu = optionalMenuEntity.get();

            menu.setAnaYemekler(menuRequest.getAnaYemekler());
            menu.setIcecekler(menuRequest.getIcecekler());
            menu.setTatlilar(menuRequest.getTatlilar());
            menu.setCorbalar(menuRequest.getCorbalar());

            menuRepository.save(menu);
            return MenuConverter.convert(menu);
        }
        throw new MenuNotFoundException("Menu Not Found");
    }

    //Menu Sil --------------------------------------------------------------------
    @Override
    public void delete(Long id) {
        boolean exists = menuRepository.existsById(id);
        if (!exists) {
            throw new MenuNotFoundException("Menu Id : " + id);
        }
        menuRepository.deleteById(id);
    }

    //Menu Getir -------------------------------------------------------------------------------------------------------
    @Override
    public MenuDto getMenu(Long id) {
        Optional<MenuEntity> menuEntity = menuRepository.findById(id);
        MenuEntity menuError = menuEntity.orElseThrow(() ->
                new MenuNotFoundException("Menu Id : " + id));
        MenuDto menuDto = new ModelMapper().map(menuEntity,MenuDto.class);
        return menuDto;
    }
}
