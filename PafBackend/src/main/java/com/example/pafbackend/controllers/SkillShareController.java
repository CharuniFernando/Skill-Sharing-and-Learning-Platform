package com.example.pafbackend.controllers;

import com.example.pafbackend.models.SkillShare;
import com.example.pafbackend.repositories.SkillShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //return data
@RequestMapping("/api/mealPlans") //base URL or base path
public class SkillShareController {
    
    //autowired repository and inject constructor
    private final SkillShareRepository mealPlanRepository;
    
    @Autowired
    public SkillShareController(SkillShareRepository mealPlanRepository) {
        this.mealPlanRepository = mealPlanRepository;
    }
    

    //API endpoints
    // GET: Retrieve all Post with User details populated
    @GetMapping
    public ResponseEntity<List<SkillShare>> getMealPlans() {
        List<SkillShare> mealPlans = mealPlanRepository.findAll();
        return new ResponseEntity<>(mealPlans, HttpStatus.OK); //HttpStatus.OK 200 - success
    }
    
    // GET: Retrieve a Post by ID with User details populated
    @GetMapping("/{userId}")
    public ResponseEntity<List<SkillShare>> getMealPlansByUserId(@PathVariable String userId) {
        List<SkillShare> mealPlans = mealPlanRepository.findByUserId(userId);
        return new ResponseEntity<>(mealPlans, HttpStatus.OK);
    }
    
    // POST: Create a new Post
    @PostMapping
    public ResponseEntity<SkillShare> createMealPlan(@RequestBody SkillShare mealPlan) {
        SkillShare savedMealPlan = mealPlanRepository.save(mealPlan);
        return new ResponseEntity<>(savedMealPlan, HttpStatus.CREATED); //HttpStatus.CREATED 201 - response
    }
    
    @DeleteMapping("/{mealPlanId}")
    public ResponseEntity<Void> deleteMealPlan(@PathVariable String mealPlanId) {
        mealPlanRepository.deleteById(mealPlanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //HttpStatus.NO_CONTENT 204 - send status without response body
    }
    
    // DELETE: Delete a Post by ID
    @PutMapping("/{mealPlanId}")
    public ResponseEntity<SkillShare> updateMealPlan(@PathVariable String mealPlanId, @RequestBody SkillShare updatedMealPlan) {
        // Check if the Skill Share with the given ID exists
        if (!mealPlanRepository.existsById(mealPlanId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        // Set the ID of the updated Skill Share
        updatedMealPlan.setId(mealPlanId);
        
        // Update the Skill Share
        SkillShare savedMealPlan = mealPlanRepository.save(updatedMealPlan);
        
        return new ResponseEntity<>(savedMealPlan, HttpStatus.OK);
    }
}