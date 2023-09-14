package org.pratikum.pages;
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
    final By orderButtonHeader = By.className("Button_Button__ra12g");

    // Кнопка "заказать" в середине страницы
    final By orderButtonBody = By.xpath("//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button");

    // Кнопка принятия куков
    private final By cookiesButton = By.id("rcc-confirm-button");

    // Общий локатор для каждого элемента дропдауна FAQ
    private final By faq = By.className("accordion__button");


    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    // Константа урла
    public final String URL = "https://qa-scooter.praktikum-services.ru/";

    // Хоть шаги не через allure.step писать, еще куки эти ваши
    public void clickOnCookieButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(cookiesButton)));
        driver.findElement(cookiesButton).click();
    }

    // Возвращает список кнопок FAQ в виде массива WebElement
    public WebElement[] getDropdownButtons() {
        driver.get(URL);
        List<WebElement> dropdownElements = driver.findElements(faq);
        WebElement[] faqButtons = new WebElement[8];
        int i = 0;
        for (WebElement element : dropdownElements) {
            faqButtons[i] = element;
            i++;
        }
        return faqButtons;
    }

    // Получаем локатор для нужного текста
    public WebElement getXpathFortext(int num) {
        String xpath = "//*[@id='accordion__panel-" + num + "']";
        return driver.findElement(By.xpath(xpath));
    }

    // Получаем нужный текст
    public String getText(int num) {
        return getXpathFortext(num).getText();
    }

    // Собираем все кнопки заказа на главной
    public WebElement getOrderButtonLocators(int number) {
        driver.get(URL);
        WebElement[] buttons = new WebElement[2];
        buttons[0] = driver.findElement(orderButtonHeader);
        buttons[1] = driver.findElement(orderButtonBody);
        return buttons[number];
    }

    // Тыкаем в нужную кнопку заказа на главной
    public void clickOnOrderButton(int number) {
        getOrderButtonLocators(number).click();
    }

    // Возвращает список текстов в ответах FAQ в массиве строк
    public String[] getDropdownTexts(int num) {
        driver.get(URL);
        WebElement[] buttons = getDropdownButtons();
        String[] faqTexts = new String[8];
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", buttons[num]);
        buttons[num].click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(getXpathFortext(num)));
        faqTexts[num] = getText(num);
        return faqTexts;
    }
}
