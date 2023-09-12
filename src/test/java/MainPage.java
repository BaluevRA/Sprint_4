import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;


public class MainPage {
    private final WebDriver driver;
    // Кнопка "заказать" в хедере
    private final By orderButtonHeader = By.className("Button_Button__ra12g");
    // Кнопка "заказать" в середине страницы
    private final By orderButtonBody = By.className("Button_Button__ra12g Button_Middle__1CSJM");
    // Кнопка принятия куков
    private final By cookiesButton = By.id("rcc-confirm-button");
    // Общий локатор для каждого элемента дропдауна FAQ
    private final By faq = By.className("accordion__button");
    // Общий локатор для каждого текста в дропдаунах FAQ
    private final By faqText = By.cssSelector(".accordion__panel > p");
    // Локатор для удобного скролла страницы
    private final By lastFaqButton = By.cssSelector("#accordion__heading-0");
    // Второй локатор для удобного скролла страницы
    private final By fourthFaqButton = By.cssSelector("#accordion__heading-3");


    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickOnUpperOrderButton() {
        driver.findElement(orderButtonHeader).click();
    }
    public void clickOnMiddleOrderButton() {
        driver.findElement(orderButtonBody).click();
    }
    public WebElement[] getDropdownButtons() {
        List<WebElement> dropdownElements = driver.findElements(faq);
        WebElement[] faqButtons = new WebElement[8];
        int i = 0;
        for (WebElement element : dropdownElements) {
            faqButtons[i] = element;
            i++;
        }
        return faqButtons;
    }

    public void clickOnCookieButton() {
        driver.findElement(cookiesButton).click();
    }

    public String[] getDropdownText() {
        WebElement element2 = driver.findElement(lastFaqButton);
        WebElement element3 = driver.findElement(fourthFaqButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element2);
        WebElement[] faqButtons = getDropdownButtons();
        List<WebElement> dropdownTexts = driver.findElements(faqText);
        String[] faqTexts = new String[8];
        int i = 0;
        for (WebElement element : dropdownTexts) {
            if (i < 4) {
                faqButtons[i].click();
            } else {
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element3);
                faqButtons[i].click();
            }
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOf(element));
            faqTexts[i] = element.getText();
            i++;
        }
        return faqTexts;
    }
}
