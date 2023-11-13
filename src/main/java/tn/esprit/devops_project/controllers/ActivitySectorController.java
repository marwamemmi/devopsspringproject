package tn.esprit.devops_project.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.services.Iservices.IActivitySector;

import java.util.List;

@RestController
@AllArgsConstructor
public class ActivitySectorController {

    IActivitySector activitySectorService;

    @GetMapping("/activitySector")
    List<ActivitySector> retrieveAllActivitySectors(){
        return activitySectorService.retrieveAllActivitySectors();
    }



    @DeleteMapping("/activitySector/{id}")
    void deleteActivitySector(@PathVariable Long id){
        activitySectorService.deleteActivitySector(id);
    }

 
    @GetMapping("/activitySector/{id}")
    ActivitySector retrieveActivitySector(@PathVariable Long id){
        return activitySectorService.retrieveActivitySector(id);
    }
}
