import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RentPage {
    private final WebDriver driver;
    // Заголовок страницы
    private final By header = By.className("Order_Header__BZXOb");
    // Поле ввода даты доставки
    private final By deliveryDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Дропдаун выбора длительности аренды
    private final By rentLengthDropdownButton = By.className("Dropdown-root");
    // Заголовок поля выбора цвета
    private final By colorChooseFieldTitle = By.className("Order_Title__3EKne");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By nextButton = By.cssSelector("div.Order_Buttons__1xGrp > button:nth-child(2)");

    public RentPage(WebDriver driver){
        this.driver = driver;
    }

    public String checkHeader() {
        return driver.findElement(header).getText();
    }

    public void fillRentInfo(String date, String duration, String scooterColor, String comment) {
        driver.findElement(deliveryDate).sendKeys(date);
        driver.findElement(header).click();
        driver.findElement(rentLengthDropdownButton).click();
        driver.findElement(By.xpath(".//div[text()='" + duration + "']")).click();
        driver.findElement(colorChooseFieldTitle).click();
        driver.findElement(By.xpath(".//label[text()='" + scooterColor + "']")).click();
        driver.findElement(commentField).sendKeys(comment);
        driver.findElement(nextButton).click();
    }
}
