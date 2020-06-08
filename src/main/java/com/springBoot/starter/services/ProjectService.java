package com.springBoot.starter.services;

import com.springBoot.starter.domain.Project;
import com.springBoot.starter.execption.ProjectIdExecption;
import com.springBoot.starter.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdExecption("Project ID ->" + project.getProjectIdentifier().toUpperCase() + " already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null)
            throw new ProjectIdExecption("Project ID is null");
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Project updateProject(Project project) {
        try {
            if (project.getId() == null) {
                throw new ProjectIdExecption("Need to have Project Id in the request");
            }
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdExecption("Project ID ->" + project.getId() + " not found");
        }

    }


    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdExecption("Cannot find the project : " + projectId + " in db");
        }
        projectRepository.delete(project);

    }


}
