package com.workintech.sprint18d1.controller;

import com.workintech.sprint18d1.dao.BurgerDaoImpl;
import com.workintech.sprint18d1.entity.Burger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/burgers")
@CrossOrigin(origins = "*")
public class BurgerController {
    private final BurgerDaoImpl burgerDao;

    @Autowired
    public BurgerController(BurgerDaoImpl burgerDao) {
        this.burgerDao = burgerDao;
    }

    @GetMapping
    public List<Burger> getAllBurgers() {
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger getBurgerById(@PathVariable Integer id) {
        return burgerDao.findById(id);
    }

    @PostMapping
    public void createBurger(@RequestBody Burger burger) {
        burgerDao.save(burger);
    }


    @DeleteMapping("/{id}")
    public void deleteBurger(@PathVariable Integer id) {
        burgerDao.remove(id);
    }

    @GetMapping("/findByPrice")
    public List<Burger> findBurgersByPrice(@RequestParam double price) {
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/findByBreadType")
    public List<Burger> findBurgersByBreadType(@RequestParam String breadType) {
        return burgerDao.findByBreadType(breadType);
    }

    @GetMapping("/findByContent")
    public List<Burger> findBurgersByContent(@RequestParam String content) {
        return burgerDao.findByContent(content);
    }
}
