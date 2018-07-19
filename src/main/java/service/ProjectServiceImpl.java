package service;

import dao.ProjectDao;
import entity.Project;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Stateless
public class ProjectServiceImpl implements ProjectService {

    @Inject
    private ProjectDao projectDao;

    @Override
    public Project get(Long projectId) {
        return projectDao.get(projectId);
    }

    @Override
    public List<Project> list() {
        return projectDao.list();
    }

    @Override
    public Project create(Project newProject) {
        return projectDao.create(newProject);
    }

    @Override
    public Project edit(Long projectId, Project project) {
        projectDao.edit(project);
        return project;
    }

    @Override
    public void delete(Long projectId) {
        Project project = projectDao.get(projectId);
        projectDao.delete(project);
    }

    @Override
    public Project finish(Long id, Date date) {
        Project project = projectDao.get(id);
        project.setEndDate(date);
        projectDao.edit(project);
        return  project;
    }

    @Override
    public long countActive() {
        return projectDao.countActive();
    }

    @Override
    public long countFinished() {
        return projectDao.countFinished();
    }
}
