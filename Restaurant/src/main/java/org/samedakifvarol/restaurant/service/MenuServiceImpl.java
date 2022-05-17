package org.samedakifvarol.restaurant.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.samedakifvarol.restaurant.controller.request.MenuItemRequest;
import org.samedakifvarol.restaurant.controller.response.MenuResponse;
import org.samedakifvarol.restaurant.converter.MenuConverter;
import org.samedakifvarol.restaurant.model.dto.MenuItemDto;
import org.samedakifvarol.restaurant.model.entity.MenuEntity;
import org.samedakifvarol.restaurant.model.entity.MenuItemEntity;
import org.samedakifvarol.restaurant.repository.MenuItemRepository;
import org.samedakifvarol.restaurant.repository.MenuRepository;
import org.samedakifvarol.restaurant.exception.MenuNotFoundException;
import org.samedakifvarol.restaurant.controller.request.MenuRequest;
import org.samedakifvarol.restaurant.model.dto.MenuDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;
    private MenuItemRepository menuItemRepository;

    @Override
    public MenuResponse add(MenuRequest menuRequest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        MenuEntity menu = modelMapper.map(menuRequest,MenuEntity.class);
        menuRepository.save(menu);

        for(int i = 0; i < menuRequest.getMenuItems().size(); i++) {
            MenuItemEntity menuItem = new MenuItemEntity();
            menuItem.setPrice(menuRequest.getMenuItems().get(i).getPrice());
            menuItem.setMealType(menuRequest.getMenuItems().get(i).getMealType());
            menuItem.setValue(menuRequest.getMenuItems().get(i).getValue());
            menuItem.setMenu(menu);
            menuItemRepository.save(menuItem);
        }

        MenuResponse returnValue = modelMapper.map(menu,MenuResponse.class);
        returnValue.setMenuItems(menuRequest.getMenuItems());
        return  returnValue;
    }

    @Override
    public MenuResponse update(MenuRequest menuRequest, Long id) {
        Optional<MenuEntity> optionalMenuEntity = menuRepository.findById(id);
        if (optionalMenuEntity.isPresent()){
            MenuEntity menu = optionalMenuEntity.get();

            List<MenuItemEntity> menuItemEntity = menuItemRepository.findByMenuId(id);
            for(int i = 0 ; i < menuRequest.getMenuItems().size();i++) {
                menuItemEntity.get(i).setMealType(menuRequest.getMenuItems().get(i).getMealType());
                menuItemEntity.get(i).setPrice(menuRequest.getMenuItems().get(i).getPrice());
                menuItemEntity.get(i).setValue(menuRequest.getMenuItems().get(i).getValue());
            }

            menu.setName(menuRequest.getName());
            menu.setMenuItem(menuItemEntity);
            menuRepository.save(menu);

            MenuResponse menuResponse = new MenuResponse();
            menuResponse.setName(menu.getName());
            menuResponse.setMenuItems(menuRequest.getMenuItems());

            return menuResponse;
      }
       throw new MenuNotFoundException("Menu Not Found");
    }

    @Override
    public void delete(Long id) {
        boolean exists = menuRepository.existsById(id);
        if (!exists) {
            throw new MenuNotFoundException("Menu Id : " + id);
        }
        menuRepository.deleteById(id);
    }

    @Override
    public MenuDto getMenu(Long id) {
        Optional<MenuEntity> menuEntity = menuRepository.findById(id);
        MenuEntity menuError = menuEntity.orElseThrow(() ->
                new MenuNotFoundException("Menu Id : " + id));
        MenuDto menuDto = new ModelMapper().map(menuEntity,MenuDto.class);
        List<MenuItemEntity> menuItemEntity = menuItemRepository.findByMenuId(id);
        List<MenuItemDto> menuItemDto = new ModelMapper().map(menuItemEntity,new TypeToken<List<MenuItemDto>>(){}.getType());

        menuDto.setMenuItem(menuItemDto);
        return menuDto;
    }

    @Override
    public Page<MenuDto> getMenus(Pageable page) {
        Page<MenuDto> menuDto = menuRepository.findAll(page).map(MenuDto::new);

        List<MenuItemEntity> menuItemEntity = menuItemRepository.findAll();
        List<MenuItemDto> menuItemDto = new ModelMapper().map(menuItemEntity,new TypeToken<List<MenuItemDto>>(){}.getType());
        menuDto.stream().findAny().get().setMenuItem(menuItemDto);
        return menuDto;
    }
}
