import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    private final WebDriver driver;
    // Кнопка подтверждения аренды
    private final By yesButton = By.cssSelector("div.Order_Buttons__1xGrp > button:nth-child(2)");

    public ConfirmationPage(WebDriver driver){
        this.driver = driver;
    }

    public void confirmRent() {
        driver.findElement(yesButton).click();
    }
}
