package dao;

import entity.Project;
import java.util.List;

public interface ProjectDao {
    Project get(Long projectId);
    List<Project> list();
    Project create(Project newProject);
    void edit(Project project);
    void delete(Project project);
    long countActive();
    long countFinished();
}
