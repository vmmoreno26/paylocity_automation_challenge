package com.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BenefitsPage extends BasePage {
	private final By title = By.cssSelector("a.navbar-brand");
	private final By btnAddEmployee = By.id("add");
	private final By firstNameModal = By.id("firstName");
	private final By lastNameModal = By.id("lastName");
	private final By dependantsModal = By.id("dependants");
	private final By btnAdd = By.id("addEmployee");
	private final By btnUpdate = By.id("updateEmployee");
	private final By noEmployeesText = By.xpath("//td[text()='No employees found.']");
	private final By employeeTableHeaders = By.cssSelector("#employeesTable thead th");
	private final By employeeTableRows = By.cssSelector("table tbody td");
	private By employeeColumnByIndex(int index) {
		return By.cssSelector("#employeesTable tbody td:nth-child("+index+")");
	}
	private final By iconEdit = By.cssSelector("i.fa-edit");
	private final By iconDelete = By.cssSelector("i.fa-times");
	private final By confirmDelete = By.id("deleteEmployee");
	
    public BenefitsPage(WebDriver driver) {
        super(driver);
    }
    
    public void waitTableReload() {
    	waitForElementInvisibility(employeeTableRows, 5);
    	waitForElementVisibility(employeeTableRows, 5);
    }
    
    public boolean isBenefitsPageDisplayed()  {
        waitForElementVisibility(title, 5);
        return getText(title).equals("Paylocity Benefits Dashboard");
    }
    
    public BenefitsPage clickAddEmployeeBtn() {
    	clickElement(btnAddEmployee);
    	return this;
    }
    
    public BenefitsPage fillEmployeeInformation(String fname, String lname, String dependants) {
    	clearAndSendkeys(firstNameModal, fname);
    	clearAndSendkeys(lastNameModal, lname);
    	clearAndSendkeys(dependantsModal, dependants);
    	return this;
    }
    
    public BenefitsPage clickAddBtn() {
    	clickElement(btnAdd);
    	waitTableReload();
    	return this;
    }
    
    public BenefitsPage clickUpdateBtn() {
    	clickElement(btnUpdate);
    	waitTableReload();
    	return this;
    }
    
    public boolean isNoEmployeesMessageNotDisplayed()  {
        return waitForElementInvisibility(noEmployeesText, 5);
    }
    
    public boolean isNoEmployeesMessageDisplayed()  {
        return waitForElementVisibility(noEmployeesText, 5);
    }
    
    public int getHeaderIndex(String header) {
    	List<WebElement> listElems = getWebElementList(employeeTableHeaders);
    	int index =1;
    	
    	for(WebElement wElem : listElems) {
    		System.out.println(wElem.getText());
    		if(wElem.getText().equalsIgnoreCase(header)) {
    			break;
    		}
    		index++;
    	}
    	return index;
    }
    
    public List<String> getColumnData(int index) {
    	return getWebElementList(employeeColumnByIndex(index)).stream().map(this::getText).collect(Collectors.toList());
    }
    
    public BenefitsPage clickEditBtnFirstRow() {
    	WebElement wElem = getWebElementList(iconEdit).get(0);
    	wElem.click();
    	return this;
    }
    
    public String getDependentsValue() {
    	return getValue(dependantsModal);
    }
    
    public BenefitsPage sendDependentsValue(String value) {
    	clearAndSendkeys(dependantsModal, value);
    	return this;
    }
    
    public BenefitsPage clickDeleteBtnFirstRow() {
    	WebElement wElem = getWebElementList(iconDelete).get(0);
    	wElem.click();
    	return this;
    }
    
    public BenefitsPage confirmDelete() {
    	clickElement(confirmDelete);
    	return this;
    }
    
}
