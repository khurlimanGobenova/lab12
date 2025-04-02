package com.example.lab12.category1.task5;

import com.example.lab12.category1.task4.EmployeeRepository;
import com.example.lab12.category1.task4.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectsController {
    @Autowired
    private ProjectRepository repository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return repository.save(project);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found"));
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
        Project existingProject = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found"));

        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        existingProject.setStatus(project.getStatus());

        return repository.save(existingProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        Project project = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found"));
        repository.delete(project);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{projectId}/employees/{employeeId}")
    public Project assignEmployeeToProject(
            @PathVariable Long projectId,
            @PathVariable Long employeeId) {

        Project project = repository.findById(projectId).orElseThrow(() ->
                new RuntimeException("Project Not Found"));

        Employees employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new RuntimeException("Employee Not Found"));

        project.getAssignedEmployees().add(employee);
        return repository.save(project);
    }

    @DeleteMapping("/{projectId}/employees/{employeeId}")
    public Project removeEmployeeFromProject(
            @PathVariable Long projectId,
            @PathVariable Long employeeId) {

        Project project = repository.findById(projectId).orElseThrow(() ->
                new RuntimeException("Project Not Found"));

        Employees employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new RuntimeException("Employee Not Found"));

        project.getAssignedEmployees().remove(employee);
        return repository.save(project);
    }
}