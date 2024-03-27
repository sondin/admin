package com.kalvin.kvf.modules.test.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.test.entity.Project;

/**
 * <p>
 * 项目表 服务类
 * </p>
 * @since 2024-03-26 23:57:32
 */
public interface ProjectService extends IService<Project> {

    /**
     * 获取列表。分页
     * @param project 查询参数
     * @return page
     */
    Page<Project> listProjectPage(Project project);

}
