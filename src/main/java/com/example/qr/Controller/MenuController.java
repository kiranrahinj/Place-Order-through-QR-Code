package com.example.qr.Controller;



import com.example.qr.Entity.MenuItem;
import com.example.qr.Service.MenuService;
import com.example.qr.Service.QRCodeService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private QRCodeService qrCodeService;

    // Get all menu items
    @GetMapping("/menus")
    public List<MenuItem> getAllMenus() {
        return menuService.getAllMenuItems();
    }

    // Add a new menu item
    @PostMapping("/menus")
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
        return menuService.addMenuItem(menuItem);
    }

    // Delete a menu item
    @DeleteMapping("/menus/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuService.deleteMenuItem(id);
    }

    // Generate QR code with table number
    @GetMapping("/generate-qr")
    public ResponseEntity<byte[]> generateQRCode(@RequestParam String tableNumber) throws WriterException, IOException {
        List<MenuItem> menuItems = menuService.getAllMenuItems();
        String menuData = menuItems.stream().map(MenuItem::getName).collect(Collectors.joining(", "));
        System.out.println(menuData);
        String qrData = String.format("Table Number: %s\nMenu: %s", tableNumber, menuData);

        byte[] qrCodeImage = qrCodeService.generateQRCode(qrData, 300, 300);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return ResponseEntity.ok()
                .headers(headers)
                .body(qrCodeImage);
    }
}
