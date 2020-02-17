package com.pocturkcell.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class Browser {
    public Browser() {
    }


    public static WebDriver prepareDriver() {
        WebDriver driver;
        loadBrowsers();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private static void loadBrowsers() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/setup/chromedriver_2_41.exe");
    }
}


