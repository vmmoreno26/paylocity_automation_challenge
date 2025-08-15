package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
	private final By inputUsername = By.id("Username");
	private final By inputPassword = By.id("Password");
	private final By btnLogin = By.xpath("//button[text()='Log In']");
	
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public LoginPage goToLoginPage(String url)  {
        driver.get(url);
        waitForElementVisibility(inputUsername, 10);
        return this;
    }
    
    public void login(String username, String password) throws Exception {
    	clearAndSendkeys(inputUsername, username);
    	clearAndSendkeys(inputPassword, password);
        clickElement(btnLogin);
    }
}
