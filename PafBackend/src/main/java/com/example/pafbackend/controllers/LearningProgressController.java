package com.example.pafbackend.controllers;

import com.example.pafbackend.models.LearningProgress;
import com.example.pafbackend.repositories.LearningProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/workoutPlans")
public class LearningProgressController {

    private final LearningProgressRepository workoutPlanRepository;

    @Autowired
    public LearningProgressController(LearningProgressRepository workoutPlanRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
    }

    @GetMapping
    public ResponseEntity<List<LearningProgress>> getWorkoutPlans() {
        List<LearningProgress> workoutPlans = workoutPlanRepository.findAll();
        return new ResponseEntity<>(workoutPlans, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<LearningProgress>> getWorkoutPlansByUserId(@PathVariable String userId) {
        List<LearningProgress> workoutPlans = workoutPlanRepository.findByUserId(userId);
        return new ResponseEntity<>(workoutPlans, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LearningProgress> createWorkoutPlan(@RequestBody LearningProgress workoutPlan) {
        LearningProgress savedWorkoutPlan = workoutPlanRepository.save(workoutPlan);
        return new ResponseEntity<>(savedWorkoutPlan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{workoutPlanId}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable String workoutPlanId) {
        workoutPlanRepository.deleteById(workoutPlanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{workoutPlanId}")
    public ResponseEntity<LearningProgress> updateWorkoutPlan(@PathVariable String workoutPlanId, @RequestBody LearningProgress updatedWorkoutPlan) {
        Optional<LearningProgress> existingWorkoutPlanOptional = workoutPlanRepository.findById(workoutPlanId);
        if (existingWorkoutPlanOptional.isPresent()) {
            LearningProgress existingWorkoutPlan = existingWorkoutPlanOptional.get();
            // Update the existing Learning Progress with the new details
            existingWorkoutPlan.setUserId(updatedWorkoutPlan.getUserId());
            existingWorkoutPlan.setRoutines(updatedWorkoutPlan.getRoutines());
            existingWorkoutPlan.setPlanName(updatedWorkoutPlan.getPlanName());
            existingWorkoutPlan.setDescription(updatedWorkoutPlan.getDescription());
            existingWorkoutPlan.setGoal(updatedWorkoutPlan.getGoal());

            // Save the updated Learning Progress
            LearningProgress savedWorkoutPlan = workoutPlanRepository.save(existingWorkoutPlan);
            return new ResponseEntity<>(savedWorkoutPlan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
