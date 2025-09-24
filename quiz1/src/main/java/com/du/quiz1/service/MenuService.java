package com.du.quiz1.service;


import com.du.quiz1.domain.Menu;
import com.du.quiz1.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuMapper menuMapper;

    public List<Menu> findAll() { return menuMapper.findAll(); }

    public Menu findById(Long id) { return menuMapper.findById(id); }

    public void save(Menu menu) {
        if (menu.getId() == null) menuMapper.insert(menu);
        else menuMapper.update(menu);
    }

    public void delete(Long id) { menuMapper.delete(id); }
}