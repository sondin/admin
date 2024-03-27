package com.kalvin.kvf.modules.test.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.test.entity.Project;
import com.kalvin.kvf.modules.test.mapper.ProjectMapper;

import java.util.List;

/**
 * <p>
 * 项目表 服务实现类
 * </p>
 * @since 2024-03-26 23:57:32
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    public Page<Project> listProjectPage(Project project) {
        Page<Project> page = new Page<>(project.getCurrent(), project.getSize());
        List<Project> projects = baseMapper.selectProjectList(project, page);
        return page.setRecords(projects);
    }

}
