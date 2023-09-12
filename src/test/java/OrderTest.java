import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
    public class OrderTest {
    private WebDriver driver;
    private final By locator;
    private final String name;
    private final String surname;
    private final String address;
    private final String subway;
    private final String phoneNumber;
    private final String date;
    private final String rentDuration;
    private final String color;
    private final String comment;

    public OrderTest(By locator, String name, String surname, String address, String subway, String phoneNumber,
                     String date, String rentDuration, String color, String comment) {
        this.locator = locator;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subway = subway;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentDuration = rentDuration;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getUserData() {
        return new Object[][] {
                {By.cssSelector("div.Header_Nav__AGCXC > button.Button_Button__ra12g"), "Роман", "Романов", "Адрес",
                        "Черкизовская", "88005553535", "21.12.2023", "сутки", "чёрный жемчуг",
                        "Закинуть в реку"},
                {By.xpath("//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button"), "Олег", "Олегов",
                        "Другой адрес", "Сокольники", "89991234567", "26.10.2023", "двое суток",
                        "серая безысходность", "без комментариев"},
        };
    }

    @Test
    public void OrderSamokatTest() {
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
        this.driver.get("https://qa-scooter.praktikum-services.ru/");
        // Заходим на главную страницу
        // MainPage objMainPage = new MainPage(this.driver);
        // Переходим на страницу заказа через верхнюю кнопку
        // objMainPage.clickOnUpperOrderButton();
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
        driver.findElement(locator).click();
        // Заполняем все поля и переходим на следующую страницу
        OrderPage objOrderPage = new OrderPage(this.driver);
        objOrderPage.orderSamokat(name, surname, address, subway, phoneNumber);
        // Проверяем, что следующая страница открылась, через ее заголовок
        RentPage objRentPage = new RentPage(this.driver);
        MatcherAssert.assertThat(objRentPage.checkHeader(), CoreMatchers.is("Про аренду"));
        // Вводим инфу по аренде и жмем "далее"
        objRentPage.fillRentInfo(date, rentDuration, color, comment);
        // Идем в окошко подтверждения и подтверждаем аренду
        ConfirmationPage objConfirmationPage = new ConfirmationPage(this.driver);
        objConfirmationPage.confirmRent();
        // Проверяем, что аренда оформена
        SuccessPage objSuccessPage = new SuccessPage(driver);
        MatcherAssert.assertThat(objSuccessPage.getSuccessHeader(), CoreMatchers.is("Заказ оформен"));


    }
    @After
    public void tearDown() {
        this.driver.quit();
    }
}