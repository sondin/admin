package com.kalvin.kvf.common.config;

import org.openqa.selenium.Capabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ChromeConfig {

//    chrome:
//    capabilities:
//    url: https://www.gsmarena.com
//    browserName: chrome
//    browserVersion: 115.0
    @Value("${chrome.capabilities.browserName}")
    private String browserName;

    @Value("${chrome.capabilities.browserVersion}")
    private String browserVersion;

    @Bean(name = "chrome")
    public Capabilities runChrome()  {

        Capabilities capabilities = new Capabilities() {
            @Override
            public Map<String, Object> asMap() {
                return null;
            }

            @Override
            public Object getCapability(String s) {
                return null;
            }
        };
        return capabilities;
    }
}
