package com.mealscheduler.meal.controller;

import com.mealscheduler.meal.exception.MealNotFoundException;
import com.mealscheduler.meal.exception.MealsNotFoundException;
import com.mealscheduler.meal.exception.ScheduleNotFoundException;
import com.mealscheduler.meal.model.Meal;
import com.mealscheduler.meal.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping("/meals")
    public ResponseEntity<List<Meal>> getAllMeals() {
        return ResponseEntity.ok(mealService.getAllMeals());
    }

    @GetMapping("/meals/{id}")
    public ResponseEntity<Meal> getMeal(@PathVariable Long id) throws MealNotFoundException {
        return ResponseEntity.ok(mealService.getMeal(id));
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<Meal>> getSchedule() throws ScheduleNotFoundException {
        return ResponseEntity.ok(mealService.getSchedule());
    }

    @GetMapping("/schedule/set")
    public ResponseEntity<List<Meal>> addSchedule() throws MealsNotFoundException {
        return ResponseEntity.ok(mealService.addSchedule());
    }

    @PostMapping("/meals")
    public ResponseEntity<Meal> addMeal(@RequestBody Meal meal) {
        return new ResponseEntity<>(mealService.addMeal(meal), HttpStatus.CREATED);
    }

    @PutMapping("meals/{id}")
    public ResponseEntity<Meal> updateMeal(@RequestBody Meal meal, @PathVariable Long id) {
        return ResponseEntity.ok(mealService.updateMeal(meal, id));
    }

    @DeleteMapping("/meals/{id}")
    public void deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
    }

    @DeleteMapping("/meals")
    public void deleteAllMeals() {
        mealService.deleteAllMeals();
    }

    @DeleteMapping("/schedule")
    public void deleteSchedule() {
        mealService.deleteSchedule();
    }
}
