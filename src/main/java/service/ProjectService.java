package service;

import entity.Project;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    Project get(Long projectId);
    List<Project> list();
    Project create(Project newProject);
    Project edit(Long projectId, Project project);
    void delete(Long projectId);

    Project finish(Long id, Date date);
    long countActive();
    long countFinished();
}
