package com.kalvin.kvf.modules.test.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.config.ChromeConfig;
import com.kalvin.kvf.gui.HomePageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.webdriver.core.capability.impl.desktop.ChromeCapabilities;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.test.entity.Tcase;
import com.kalvin.kvf.modules.test.service.TcaseService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.zebrunner.agent.core.webdriver.RemoteWebDriverFactory.getDriver;


/**
 * <p>
 * 测试用例表 前端控制器
 * </p>
 * @since 2024-03-26 23:57:32
 */
@RestController
@RequestMapping("test/tcase")
public class TcaseController extends BaseController implements IAbstractTest {

    @Autowired
    private TcaseService tcaseService;

    @Autowired
    private ChromeConfig capabilities;


    @FindBy(id = "su")
    private ExtendedWebElement fileUploadingArea;

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

    @PostMapping(value = "run/{id}")
    public R run(String id) throws IOException, InterruptedException {
        Tcase tcase;
        if (id == null) {
            tcase = new Tcase();
        } else {
            tcase = tcaseService.getById(id);
        }


        System.setProperty("webdriver.chrome.driver", "/Users/sondin/Documents/git/java/kvf-admin/chromedriver");

        // 创建一个新的Chrome浏览器实例
        WebDriver driver = new ChromeDriver();

        // 打开网页
        driver.get("http://localhost/");


        WebDriverWait wait = new WebDriverWait(driver, 10); // 超时时长为10秒

        WebElement displayInfoLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("kUserLoginUsername")));

        displayInfoLabel.sendKeys("admin");

        WebElement submitBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submitBtn")));


        WebElement input  = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("kUserLoginPassword")));

        input.sendKeys("admin");

        WebElement check  = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(".layui-icon layui-icon-ok")));


        check.click();

        Thread.sleep(3000);

        check.click();


        submitBtn.click();

        Thread.sleep(3000);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(screenshot, new File("screenshot.png")); // 需要Apache Commons IO库来复制文件到指定路径

        WebElement kAppLeftNavBox  = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("kAppLeftNavBox")));

        if (kAppLeftNavBox != null) {

            return R.ok();

        }

        return R.fail("fail");

    }

}

