package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.MainPage;
import page.OrderPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTests {
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

    @ParameterizedTest
    @CsvSource({
            "Иван, Иванов, Москва, Сокольники, 89991112233, Коммент 1, 10.07.2025",
            "Ольга, Петрова, Питер, Технологический, 89993334455, Коммент 2, 11.07.2025"
    })
    void testOrderFromTop(String name, String surname, String address, String metro, String phone, String comment, String date) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderTop();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillStepOne(name, surname, address, metro, phone);
        orderPage.fillStepTwo(date, comment);

        assertTrue(orderPage.isSuccessModalVisible());
    }

    @ParameterizedTest
    @CsvSource({
            "Алексей, Сидоров, Казань, Кремлёвская, 89990001111, Коммент 3, 12.07.2025",
            "Мария, Иванова, Новосибирск, Площадь Ленина, 89998887766, Коммент 4, 13.07.2025"
    })
    void testOrderFromBottom(String name, String surname, String address, String metro, String phone, String comment, String date) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderBottom();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillStepOne(name, surname, address, metro, phone);
        orderPage.fillStepTwo(date, comment);

        assertTrue(orderPage.isSuccessModalVisible());
    }
}