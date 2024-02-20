package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Page à tester : https://www.saucedemo.com/
public class LoginPage {

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    //private By errorMessageBox = By.xpath("//h3[@data-test='error']");
    private WebDriver driver;
    private WebDriverWait wait;

    private String url = "https://www.saucedemo.com/";



    public LoginPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
        this.driver.get(url);
    }

    public void enterUsername(String username) {
        WebElement emailWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        emailWebElement.clear();
        emailWebElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordWebElement.clear();
        passwordWebElement.sendKeys(password);
    }

    public void clickButtonLogin() {
        WebElement buttonLoginWebElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        buttonLoginWebElement.click();
    }

    public void performLogin(String username, String password) {
        this.enterUsername(username);
        this.enterPassword(password);
        this.clickButtonLogin();
    }

    public boolean checkInventoryIsDisplayed() {

        //System.out.println("Url de la page : " + driver.getCurrentUrl());
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html") ;
    }
}
