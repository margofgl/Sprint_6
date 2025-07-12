package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FaqTests {
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
            "0, Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "1, Пока что нет! Но скоро появится.",
            "2, Допустимы только черный и серый. Самокат приезжает в том цвете, который есть в наличии.",
            "3, Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "4, Пока что привозим только по Москве и Московской области.",
            "5, Да, обязательно. Всем самокатам прописываем дату возврата, чтобы успеть подготовить их для следующего клиента.",
            "6, Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "7, Самокат приезжает к тебе с полной зарядкой. Этого хватает на восемь суток — даже если будешь кататься без остановки."
    })
    void testFaqAnswerByIndex(int index, String expectedAnswer) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickQuestion(index);
        String actualAnswer = mainPage.getAnswerText(index);
        assertEquals(expectedAnswer, actualAnswer);
    }
}