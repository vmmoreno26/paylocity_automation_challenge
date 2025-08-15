package paylocity;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DashboardTest extends BaseTest {

	private final String domain = "https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/Account/Login";
    private final String user = "TestUser775";
    private final String password = "J8dl@9+ZPod'";
    String fname = "Victor";
    String lname = "Moreno";
    String dependents = "0";
    SoftAssert softAssertion;

    @BeforeClass
    public void login() throws Exception {
        softAssertion = new SoftAssert();

    	loginPage.goToLoginPage(domain)
    		.login(user, password);
    	Assert.assertTrue(benefitsPage.isBenefitsPageDisplayed(), "Benefits page was not displayed");
    }

	@Test(description="Add Employee information", priority = 0)
    public void add_employee() throws Exception {
		benefitsPage.clickAddEmployeeBtn()
			.fillEmployeeInformation(fname, lname, dependents)
			.clickAddBtn();
		Assert.assertTrue(benefitsPage.isNoEmployeesMessageNotDisplayed(), "'No employees found.' message is still displayed");
	}
	
	@Test(description="Get Employee information", priority = 1)
    public void get_employee_information() throws Exception {
		//This test will fail, because we have a bug in columns 
		//First name is saved under Last name
		//Last name is saved under First name
		
		int index = benefitsPage.getHeaderIndex("first name");
		List<String> obtainedData = benefitsPage.getColumnData(index);
		softAssertion.assertTrue(obtainedData.contains(fname), ""
				+ "Obtained data under First Name column " + obtainedData+ " does not contains used first name to create employee '" + fname);
		
		index = benefitsPage.getHeaderIndex("last name");
		obtainedData = benefitsPage.getColumnData(index);
		softAssertion.assertTrue(obtainedData.contains(fname), 
				"Obtained data under Last Name column " + obtainedData+ " does not contains used last name to create employee '" + lname);
		
		index = benefitsPage.getHeaderIndex("dependents");
		obtainedData = benefitsPage.getColumnData(index);
		softAssertion.assertTrue(obtainedData.contains(fname), "Employee Dependents '" + dependents +"' was not found under header Dependents column");
		
        softAssertion.assertAll();
	}
	
	@Test(description="Update Employee information", priority = 2)
    public void update_employee_information() throws Exception {
		String newDependentValue = "3";
		String oldDependentValue = benefitsPage.clickEditBtnFirstRow()
				.getDependentsValue();
		
		benefitsPage.sendDependentsValue(newDependentValue)
			.clickUpdateBtn();
		int index = benefitsPage.getHeaderIndex("dependents");
		List<String> obtainedData = benefitsPage.getColumnData(index);
		Assert.assertNotEquals(obtainedData.get(0), oldDependentValue,
				"Obtained data from Dependents column " +obtainedData.get(0)+ " is equals to initial Employee value " + oldDependentValue);
		Assert.assertEquals(obtainedData.get(0), newDependentValue, 
				"Obtained data from Dependents column " +obtainedData.get(0)+ " is not equals to value used to update Employee " + newDependentValue);
	}
	
	@Test(description="Delete Employee information", priority = 3)
    public void delete_employee_information() throws Exception {
		benefitsPage.clickDeleteBtnFirstRow()
			.confirmDelete();		
		Assert.assertTrue(benefitsPage.isNoEmployeesMessageDisplayed(), "'No employees found.' message was not displayed");
	}
}
