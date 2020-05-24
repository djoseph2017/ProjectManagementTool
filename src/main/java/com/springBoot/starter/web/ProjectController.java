package com.springBoot.starter.web;

import com.springBoot.starter.domain.Project;
import com.springBoot.starter.services.ProjectService;
import com.springBoot.starter.services.ProjectValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectValidationService projectValidationService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorsMap = projectValidationService.validateProject(result);
        if (errorsMap != null)
            return errorsMap;

        Project project1 = projectService.saveProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);

    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/allProjects")
    public Iterable<Project> getAllProject() {
        return projectService.findAllProjects();
    }

    @PutMapping("")
    public ResponseEntity<?> updateProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorsMap = projectValidationService.validateProject(result);
        if (errorsMap != null)
            return errorsMap;

        projectService.updateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.OK);

    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProjectById(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<String>("Project Id:" + projectId + " was deleted from Db", HttpStatus.OK);
    }


}
