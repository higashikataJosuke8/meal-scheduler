package com.mealscheduler.meal.service;

import com.mealscheduler.meal.exception.MealNotFoundException;
import com.mealscheduler.meal.exception.MealsNotFoundException;
import com.mealscheduler.meal.exception.ScheduleNotFoundException;
import com.mealscheduler.meal.model.Meal;
import com.mealscheduler.meal.repository.MealRepository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    private List<Meal> mealSchedule = new ArrayList<>();

    public List<Meal> getAllMeals() {
        return (List<Meal>) mealRepository.findAll();
    }

    public Meal getMeal(Long id) throws MealNotFoundException {
        Meal meal = mealRepository.findById(id).orElse(null);
        if(meal != null) {
            return meal;
        } else {
            throw new MealNotFoundException("Meal not found with id: " + id);
        }
    }

    public List<Meal> getSchedule() throws ScheduleNotFoundException {
        if(!mealSchedule.isEmpty()) {
            return mealSchedule;
        } else {
            throw new ScheduleNotFoundException("Schedule not found");
        }
    }

    public Meal addMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public List<Meal> addSchedule() throws MealsNotFoundException {
        mealSchedule = getAllMeals();

        if(mealSchedule.size() != 0) {
            Collections.shuffle(mealSchedule);
            return mealSchedule;
        }
        else {
            throw new MealsNotFoundException("Meals not found");
        }
    }

    public Meal updateMeal(Meal meal, Long id) {
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    public void deleteAllMeals() {
        mealRepository.deleteAll();
    }

    public void deleteSchedule() {
        mealSchedule = new ArrayList<>();
    }
}
