package com.pocturkcell.model.pages;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.pocturkcell.util.Browser;
import com.pocturkcell.util.DriverManager;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class TestBase {

    @BeforeMethod(
            alwaysRun = true
    )
    public void beforeMethod(Method method) throws CloneNotSupportedException, UnsupportedEncodingException {
        try {
            DriverManager.setDriver(Browser.prepareDriver());
            DriverManager.getDriver().navigate().to("http://192.168.43.186:8080/user");
            DriverManager.getDriver().manage().timeouts().pageLoadTimeout((long)60, TimeUnit.SECONDS);
        } catch (Exception ignore) {
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (DriverManager.getDriver() != null && !"null".contains(DriverManager.getDriver().toString())) {
            DriverManager.getDriver().quit();
        }
    }
}
