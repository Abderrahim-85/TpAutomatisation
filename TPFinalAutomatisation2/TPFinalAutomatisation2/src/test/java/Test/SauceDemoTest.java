package Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.User;

import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class SauceDemoTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private List<User> userList;
    HomePage PageElement;
    String UserName = "standard_user";
    String Password = "secret_sauce";
    public String url = "https://www.saucedemo.com/";

    @BeforeClass
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();

    }

    @Test(priority = 2)
    public void validConnection() throws IOException {

        //lire le fichier
        //1-Crée une instance ObjectMapper : mapper les objets JSON vers des objets Java
        ObjectMapper objectMapper = new ObjectMapper();

        //2-Ou trouver ce fichier userslist.json et mapper en fonction de la classe User et la Liste
        userList = objectMapper.readValue(getClass().getClassLoader().getResourceAsStream("userslist.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));

        //Connecter à la page de Login
        loginPage = new LoginPage(driver);

        User userValid = null;
        //on va faire une boucle sur la liste d'utilisateurs : Pour chaque user de ma liste userList
        for (User u : userList) {
            if(u.isValid()==true){
                userValid = u;
                break;
            }
        }

        loginPage.performLogin(userValid.getUsername(),userValid.getPassword());
        Assert.assertTrue(loginPage.checkInventoryIsDisplayed());
    }

    @Test(priority = 1)
    public void invalidConnection() throws IOException {

        //lire le fichier
        //1-Crée une instance OnjectMapper : mapper les objets JSON vers des objets Java
        ObjectMapper objectMapper = new ObjectMapper();

        //2-Ou trouver ce fichier userslist.json et mapper en fonction de la classe User et la Liste
        userList = objectMapper.readValue(getClass().getClassLoader().getResourceAsStream("userslist.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));

        //Connecter à la page de Login
        loginPage = new LoginPage(driver);

        User userInvalid = null;
        //on va faire une boucle sur la liste d'utilisateurs : Pour chaque user de ma liste userList
        for (User u : userList) {
            if(u.isValid()==false){
                userInvalid = u;

            }
        }

        loginPage.performLogin(userInvalid.getUsername(),userInvalid.getPassword());
        Assert.assertFalse(loginPage.checkInventoryIsDisplayed());
    }

    @Test(priority = 3)
    public void RechercheElement() throws InterruptedException {
        System.out.println(" Début de test");
        loginPage = new LoginPage(driver);
        loginPage.enterUsername(UserName);
        loginPage.enterPassword(Password);
        loginPage.clickButtonLogin();
        loginPage.checkInventoryIsDisplayed();
        PageElement = new HomePage(driver);
        PageElement.Recherche_Element();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}