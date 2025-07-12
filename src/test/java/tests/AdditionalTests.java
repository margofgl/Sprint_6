package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.MainPage;

import static org.junit.jupiter.api.Assertions.*;

public class AdditionalTests {
    private WebDriver driver;

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testClickScooterLogoReturnsToMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderTop();
        mainPage.clickScooterLogo();
        assertTrue(driver.getCurrentUrl().contains("scooter"));
    }

    @Test
    void testClickYandexLogoOpensNewTab() {
        MainPage mainPage = new MainPage(driver);
        String originalTab = driver.getWindowHandle();
        mainPage.clickYandexLogo();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalTab)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        assertTrue(driver.getCurrentUrl().contains("yandex"));
    }

    @Test
    void testInvalidOrderNumberShowsNotFound() {
        driver.get("https://qa-scooter.praktikum-services.ru/track");
        driver.findElement(By.xpath("//input[@placeholder='Введите номер заказа']")).sendKeys("000000");
        driver.findElement(By.className("Button_Button__ra12g")).click();
        boolean notFound = driver.getPageSource().contains("Заказ не найден");
        assertTrue(notFound);
    }
}