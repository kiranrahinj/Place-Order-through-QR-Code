package com.example.qr.Repository;


import com.example.qr.Entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Long> {
}
