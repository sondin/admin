package com.kalvin.kvf.modules.test.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.test.entity.Tcase;
import com.kalvin.kvf.modules.test.service.TcaseService;

import java.util.List;


/**
 * <p>
 * 测试用例表 前端控制器
 * </p>
 * @since 2024-03-26 23:57:32
 */
@RestController
@RequestMapping("test/tcase")
public class TcaseController extends BaseController {

    @Autowired
    private TcaseService tcaseService;

    @RequiresPermissions("test:tcase:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("test/tcase");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("test/tcase_edit");
        Tcase tcase;
        if (id == null) {
            tcase = new Tcase();
        } else {
            tcase = tcaseService.getById(id);
        }
        mv.addObject("editInfo", tcase);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(Tcase tcase) {
        Page<Tcase> page = tcaseService.listTcasePage(tcase);
        return R.ok(page);
    }

    @RequiresPermissions("test:tcase:add")
    @PostMapping(value = "add")
    public R add(Tcase tcase) {
        tcaseService.save(tcase);
        return R.ok();
    }

    @RequiresPermissions("test:tcase:del")
    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        tcaseService.removeByIds(ids);
        return R.ok();
    }

    @RequiresPermissions("test:tcase:edit")
    @PostMapping(value = "edit")
    public R edit(Tcase tcase) {
        tcaseService.updateById(tcase);
        return R.ok();
    }

    @RequiresPermissions("test:tcase:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        tcaseService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(tcaseService.getById(id));
    }

}

