package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findAllByParentOrderByOrderNumber(Menu parent);
}