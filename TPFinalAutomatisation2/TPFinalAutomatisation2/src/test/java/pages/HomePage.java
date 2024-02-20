package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void Recherche_Element(){
        List<WebElement> ListLesProduits = driver.findElements(By.tagName("a"));
        int k= 0;
        for(int i= 0; i < ListLesProduits.size() - 1; i++){
            String a = ListLesProduits.get(i).getText();
            String b = ListLesProduits.get(i+1).getText();
            k= k+(a.compareTo(b));
        }
        System.out.println("Le resultat est : " + k);
        Assert.assertTrue(k<0,"Les produits ne sont pas ordonné par ordre croissant ");
        System.out.println("L'ordre des produits est croissant");
        for(WebElement link:ListLesProduits){
            if(!link.getText().equals("Twitter")& !link.getText().equals("Facebook")
                    &!link.getText().equals("LinkedIn")) {
                System.out.println(link.getText());
            }
        }
    }
}

