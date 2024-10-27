package com.kalvin.kvf;

import com.kalvin.kvf.modules.generator.utils.VelocityKit;
import org.apache.velocity.VelocityContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create by Kalvin on 2019/06/13 20:21
 */
public class MyTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyTest.class);

    public static void main(String[] args) throws Exception {
        velocity();
    }

    public static void velocity() throws InterruptedException {
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

        WebElement check = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".layui-icon.layui-icon-ok")));


        check.click();

        Thread.sleep(3000);

        check.click();


        submitBtn.click();
    }
}
