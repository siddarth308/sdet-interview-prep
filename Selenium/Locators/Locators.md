package Selenium.Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorPractice {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.get("https://example.com");

        // 1. ID
        driver.findElement(By.id("username"));

        // 2. Name
        driver.findElement(By.name("username"));

        // 3. Class Name
        driver.findElement(By.className("login-button"));

        // 4. Tag Name
        driver.findElement(By.tagName("input"));

        // 5. Link Text
        driver.findElement(By.linkText("Login"));

        // 6. Partial Link Text
        driver.findElement(By.partialLinkText("Log"));

        // 7. CSS Selector
        driver.findElement(
                By.cssSelector("input[name='username']")
        );

        // 8. XPath
        driver.findElement(
                By.xpath("//input[@id='username']")
        );

        driver.quit();
    }
}