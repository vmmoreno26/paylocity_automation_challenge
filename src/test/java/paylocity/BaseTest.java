package paylocity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.pages.*;

public class BaseTest {
    public LoginPage loginPage;
    public BenefitsPage benefitsPage;

	public WebDriver driver; 

	@BeforeClass(alwaysRun = true)
	public void setUp(){
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		initializePages();
    }
	
    @AfterClass(alwaysRun = true)
    public void afterClass () {
        driver.close();
		driver.quit();
    }
    
    
    public void initializePages() {
    	loginPage = new LoginPage(driver);
    	benefitsPage = new BenefitsPage(driver);
    }
}
