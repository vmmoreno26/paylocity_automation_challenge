package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    
    public BasePage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    public WebElement getWebElement(By locator) {
    	return driver.findElement(locator);
    }
    
    public List<WebElement> getWebElementList(By locator) {
    	waitForElementVisibilityAll(locator, 10);
    	return driver.findElements(locator);
    }
    
    public boolean waitForElementVisibility(By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean waitForElementInvisibility(By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public boolean waitForElementVisibilityAll(By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean waitForElementToBeClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clearAndSendkeys(By locator, String text) {
    	WebElement wElem = getWebElement(locator);
    	wElem.clear();
    	wElem.sendKeys(text);
    }
    
    public void clickElement(By locator) {
    	waitForElementToBeClickable(locator);
    	getWebElement(locator).click();
    }
    
    public String getText(By locator) {
    	waitForElementVisibility(locator, 5);
    	return getWebElement(locator).getText();
    }
    
    public String getText(WebElement wElem) {
    	return wElem.getText();
    }
    
    public String getValue(By locator) {
        return getWebElement(locator).getAttribute("value");
    }
}
