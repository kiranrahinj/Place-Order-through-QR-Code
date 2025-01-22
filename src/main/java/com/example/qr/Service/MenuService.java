package com.example.qr.Service;

import com.example.qr.Entity.MenuItem;
import com.example.qr.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<MenuItem> getAllMenuItems() {
        return menuRepository.findAll();
    }

    public MenuItem addMenuItem(MenuItem menuItem) {
        return menuRepository.save(menuItem);
    }

    public void deleteMenuItem(Long id) {
        menuRepository.deleteById(id);
    }
}

