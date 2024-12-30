package com.fooddelivery.fooddeliveryapp.controllers;

import com.fooddelivery.fooddeliveryapp.entities.Dish;
import com.fooddelivery.fooddeliveryapp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@CrossOrigin(origins = "http://localhost:4200")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    // Ajouter un plat
    @PostMapping
    public ResponseEntity<Dish> addDish(@RequestBody Dish dish) {
        Dish createdDish = dishService.addDish(dish);
        return new ResponseEntity<>(createdDish, HttpStatus.CREATED);
    }

    // Mettre à jour un plat
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update/{name}")
    public ResponseEntity<Dish> updateDish(@PathVariable String name, @RequestBody Dish dishDetails) {
        try {
            Dish updatedDish = dishService.updateDish(name, dishDetails);
            return new ResponseEntity<>(updatedDish, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Si le plat n'est pas trouvé
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Autres erreurs
        }
    }

    // Supprimer un plat
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteDish(@PathVariable String name) {
        try {
            dishService.deleteDish(name);
            return new ResponseEntity<>("Dish deleted successfully", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Dish not found", HttpStatus.NOT_FOUND);
        }
    }


    // Récupérer tous les plats (avec filtres et tri)
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<Iterable<Dish>> getAllDishes(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String category) {
        Iterable<Dish> dishes = dishService.getAllDishes(filter, sortBy, category);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Iterable<Dish>> getDishesByName(@RequestParam String name) {
        Iterable<Dish> dishes = dishService.findByName(name);  // Trouver tous les plats avec le même nom
        if (dishes != null && ((Collection<?>) dishes).size() > 0) {
            return ResponseEntity.ok(dishes);  // Retourne tous les plats trouvés
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
