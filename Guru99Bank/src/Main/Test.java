package Main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
	public static WebDriver driver;
	public static String customerID = null;
	public static String accountID = null;
	
	public Test() {
		driver = new ChromeDriver();
	}
	
	public static void main(String[] args) throws InterruptedException {
		String exePath = "D:\\SourceCode\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		Test test= new Test();
		driver.get("http://demo.guru99.com/v4/");
		login();
		
		// create Customer
		createCustomer();		
		//verify customer just created
		verifyCustomer();
		// create new account
		craeteNewAccount();
		// verify new account just created
		verifyNewAccount();
		// check deposit function
		createAmountDeposit();
		verifyAmountDeposit();
				
	}
	
	public static void input(String xpath, String ele) {
		driver.findElement(By.xpath(xpath)).sendKeys(ele);
	}
	
	public static void click(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public static String getTextByXpath(String xpath) {
		return driver.findElement(By.xpath(xpath)).getText();
	}
	
	public static void compareText(String fieldName, String xpath, String ele) {
		boolean flag = getTextByXpath(xpath).equals(ele);
		if(!flag) {
			System.err.println(fieldName + ": Faild");
		}
	}
	public static void login() throws InterruptedException {
		System.out.println("Login: ");
		input("//input[@name='uid']", "mngr160614");
		input("//input[@name='password']", "uvapAmU");
		click("//input[@name='btnLogin']");
		Thread.sleep(1000);
		System.out.println("done");
	}
	
	public static void createCustomer() throws InterruptedException {
		System.out.println("Create New customer: ");
		click("//a[text()='New Customer']");
		input("//input[@name='name']", "Hien");
		click("//input[@name='rad1'][@value='f']");
		input("//input[@name='dob']", "24/10/2000");
		input("//textarea[@name='addr']", "12");
		input("//input[@name='city']", "tphcm");
		input("//input[@name='state']", "hcm");
		input("//input[@name='pinno']", "232434");
		input("//input[@name='telephoneno']", "23215556");
		input("//input[@name='emailid']", "phamthikimhien2991@gmail.com");
		input("//input[@name='password']", "uvapAmU");
		Thread.sleep(1000);
		click("//input[@name='sub']");
		System.out.println("done");
	}
	
	public static void verifyCustomer() {
		System.out.println("Verify new customer information: ");
		customerID = getTextByXpath("//td[text()='Customer ID']/following-sibling::td[1]");
		compareText("CustomerName", "//td[text()='Customer Name']/following-sibling::td[1]", "Hien");
		compareText("Gender", "//td[text()='Gender']/following-sibling::td[1]", "female");
		compareText("Birthdate", "//td[text()='Birthdate']/following-sibling::td[1]", "2018-10-24");
		compareText("Address", "//td[text()='Address']/following-sibling::td[1]", "12");
		compareText("City", "//td[text()='City']/following-sibling::td[1]", "tphcm");
		compareText("State", "//td[text()='State']/following-sibling::td[1]", "hcm");
		compareText("Pin", "//td[text()='Pin']/following-sibling::td[1]", "232434");
		compareText("Mobile", "//td[text()='Mobile No.']/following-sibling::td[1]", "23215556");
		compareText("Email", "//td[text()='Email']/following-sibling::td[1]", "phamthikimhienit@gmail.com");
		System.out.println("done");
	}
	
	public static void craeteNewAccount() throws InterruptedException {
		System.out.println("Create New Account: ");
		click("//a[text()='New Account']");
		input("//input[@name='cusid']", customerID);
		click("//select[@name='selaccount']");
		click("//option[@value='Current']");
		input("//input[@name='inideposit']", "7000000");
		click("//input[@name='button2']");
		Thread.sleep(1000);
		System.out.println("done");
	}
	
	public static void verifyNewAccount() throws InterruptedException {
		System.out.println("Verify New Account: ");
		compareText("CustomerID", "//td[text()='Customer ID']/following-sibling::td[1]", "70837");
		accountID = getTextByXpath("//td[text()='Account ID']/following-sibling::td[1]"); //50377
		System.out.println("done");
	}
	
	public static void createAmountDeposit() throws InterruptedException {
		System.out.println("Create Amount Deposit: ");
		click("//a[text()='Deposit']");
		input("//input[@name='accountno']", accountID);
		input("//input[@name='ammount']", "7000000");
		input("//input[@name='desc']", "Saving");
		Thread.sleep(1000);
		click("//input[@name='AccSubmit']");
		System.out.println("done");
	}
	
	public static void verifyAmountDeposit() throws InterruptedException {
		System.out.println("Verify Amount Deposit: ");
		compareText("Account No", "//td[text()='Account No']/following-sibling::td[1]", "70837");
		compareText("Description", "//td[text()='Description']/following-sibling::td[1]", "Saving");
		compareText("Amount", "//td[text()='Amount Credited']/following-sibling::td[1]", "700000");
		System.out.println("done");
	}

}
