package com.kalvin.kvf.modules.test.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.test.entity.Project;
import com.kalvin.kvf.modules.test.service.ProjectService;

import java.util.List;


/**
 * <p>
 * 项目表 前端控制器
 * </p>
 * @since 2024-03-26 23:57:32
 */
@RestController
@RequestMapping("test/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @RequiresPermissions("test:project:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("test/project");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("test/project_edit");
        Project project;
        if (id == null) {
            project = new Project();
        } else {
            project = projectService.getById(id);
        }
        mv.addObject("editInfo", project);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(Project project) {
        Page<Project> page = projectService.listProjectPage(project);
        return R.ok(page);
    }

    @RequiresPermissions("test:project:add")
    @PostMapping(value = "add")
    public R add(Project project) {
        projectService.save(project);
        return R.ok();
    }

    @RequiresPermissions("test:project:del")
    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        projectService.removeByIds(ids);
        return R.ok();
    }

    @RequiresPermissions("test:project:edit")
    @PostMapping(value = "edit")
    public R edit(Project project) {
        projectService.updateById(project);
        return R.ok();
    }

    @RequiresPermissions("test:project:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        projectService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(projectService.getById(id));
    }

}

