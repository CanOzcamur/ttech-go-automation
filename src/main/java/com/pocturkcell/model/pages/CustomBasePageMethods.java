package com.pocturkcell.model.pages;

import java.time.Duration;
import java.util.Iterator;

import com.pocturkcell.util.DriverManager;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class CustomBasePageMethods
{
    protected WebDriver driver = DriverManager.getDriver();

    protected boolean checkElementIsDisplayedByLocator(By locator)
    {
        try
        {
            return this.waitUntilPresenceOfElementByLocator(locator).isDisplayed();
        }
        catch (Exception var3)
        {
            return false;
        }
    }

    protected WebElement waitUntilPresenceOfElementByLocator(By locator)
    {
        WebElement element = null;

        try
        {
            Wait<WebDriver> wait = (new FluentWait(DriverManager.getDriver())).withTimeout(Duration.ofSeconds(30L)).pollingEvery(Duration.ofMillis(100L)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }
        catch (Exception var4)
        {
        }

        return element;
    }

    public WebElement waitUntilClickableByLocator(By locator)
    {
        WebElement element = null;

        try
        {
            Wait<WebDriver> wait = (new FluentWait(DriverManager.getDriver())).withTimeout(Duration.ofSeconds(30L)).pollingEvery(Duration.ofMillis(100L)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
        catch (Exception var4)
        {
        }

        return element;
    }

    public void hoverElementWithActions(WebElement webElement)
    {
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(webElement).perform();
    }

    public WebElement waitUntilVisibleByLocator(By locator)
    {
        WebElement element = null;

        try
        {
            Wait<WebDriver> wait = (new FluentWait(DriverManager.getDriver())).withTimeout(Duration.ofSeconds(60L)).pollingEvery(Duration.ofMillis(100L)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        catch (Exception e)
        {
        }

        return element;
    }

    public void clickWebElement(By locator)
    {
        WebElement webElement = this.waitUntilVisibleByLocator(locator);
        webElement.click();
    }

    protected String getText(By byLocator)
    {
        return this.waitUntilPresenceOfElementByLocator(byLocator).getText();
    }

    public void clickWebElement(WebElement element)
    {
        element.click();
    }

    public boolean checkElementIsExistsByLocator(By locator)
    {
        try
        {
            this.driver.findElement(locator);
            return true;
        }
        catch (Exception var3)
        {
            return false;
        }
    }

    public void sendKeyToElement(By locator, String text)
    {
        WebElement webElement = this.waitUntilVisibleByLocator(locator);
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected boolean waitUntilOKResponse()
    {
        LogEntries entries = DriverManager.getDriver().manage().logs().get("performance");
        int status = -1;
        String currentURL = "http://46.101.71.151/order-management/create";
        for (Iterator<LogEntry> it = entries.iterator(); it.hasNext(); )
        {
            LogEntry entry = it.next();
            try
            {
                JSONObject json = new JSONObject(entry.getMessage());

                JSONObject message = json.getJSONObject("message");
                String method = message.getString("method");

                if (method != null
                        && "Network.responseReceived".equals(method))
                {
                    JSONObject params = message.getJSONObject("params");

                    JSONObject response = params.getJSONObject("response");
                    String messageUrl = response.getString("url");

                    if (currentURL.equals(messageUrl))
                    {
                        status = response.getInt("status");

                        System.out.println(
                                "---------- bingo !!!!!!!!!!!!!! returned response for "
                                        + messageUrl + ": " + status);

                        System.out.println(
                                "---------- bingo !!!!!!!!!!!!!! headers: "
                                        + response.get("headers"));
                    }
                }
                if ("Order has been created successfully.".equals(method))
                {
                    return true;
                }

            }
            catch (Exception ex)
            {

            }
        }
        return false;
    }

}
