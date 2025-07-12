package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private final WebDriver driver;

    private final By firstName = By.xpath("//input[@placeholder='* Имя']");
    private final By lastName = By.xpath("//input[@placeholder='* Фамилия']");
    private final By address = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStation = By.className("select-search__input");
    private final By phone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    private final By date = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriod = By.className("Dropdown-control");
    private final By periodOption = By.xpath("//div[text()='сутки']");
    private final By colorBlack = By.id("black");
    private final By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[text()='Заказать']");
    private final By confirmButton = By.xpath("//button[text()='Да']");
    private final By successModal = By.xpath("//div[contains(text(),'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillStepOne(String name, String surname, String addr, String metro, String phoneNum) {
        driver.findElement(firstName).sendKeys(name);
        driver.findElement(lastName).sendKeys(surname);
        driver.findElement(address).sendKeys(addr);
        driver.findElement(metroStation).sendKeys(metro);
        driver.findElement(By.className("select-search__option")).click();
        driver.findElement(phone).sendKeys(phoneNum);
        driver.findElement(nextButton).click();
    }

    public void fillStepTwo(String dateValue, String commentText) {
        driver.findElement(date).sendKeys(dateValue);
        driver.findElement(rentalPeriod).click();
        driver.findElement(periodOption).click();
        driver.findElement(colorBlack).click();
        driver.findElement(comment).sendKeys(commentText);
        driver.findElement(orderButton).click();
        driver.findElement(confirmButton).click();
    }

    public boolean isSuccessModalVisible() {
        return !driver.findElements(successModal).isEmpty();
    }
}