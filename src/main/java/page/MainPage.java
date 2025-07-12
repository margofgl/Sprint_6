package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;

    private final By orderButtonTop = By.className("Button_Button__ra12g");

    private final By orderButtonBottom = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button");

    private final By scooterLogo = By.className("Header_LogoScooter__3lsAR");
    private final By yandexLogo = By.cssSelector("a.Header_LogoYandex__3TSOI");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public void clickQuestion(int index) {
        String questionByIndex = "//div[@id='accordion__heading-%d']";
        driver.findElement(By.xpath(String.format(questionByIndex, index))).click();
    }

    public String getAnswerText(int index) {
        String answerByIndex = "//div[@id='accordion__panel-%d']/p";
        return driver.findElement(By.xpath(String.format(answerByIndex, index))).getText();
    }

    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }
}