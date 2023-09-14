import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pratikum.pages.*;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
    public class OrderTest {
    private static WebDriver driver;

    // Больше параметров богу параметров!
    private final int buttonNumber;
    private final String name;
    private final String surname;
    private final String address;
    private final String subway;
    private final String phoneNumber;
    private final String date;
    private final String rentDuration;
    private final String color;
    private final String comment;

    public OrderTest(int buttonNumber, String name, String surname, String address, String subway, String phoneNumber,
                     String date, String rentDuration, String color, String comment) {
        this.buttonNumber = buttonNumber;
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
                {0, "Роман", "Романов", "Адрес",
                        "Черкизовская", "88005553535", "21.12.2023", "сутки", "чёрный жемчуг",
                        "Закинуть в реку"},
                {1, "Олег", "Олегов",
                        "Другой адрес", "Сокольники", "89991234567", "26.10.2023", "двое суток",
                        "серая безысходность", "без комментариев"},
        };
    }

    @Test
    public void orderSamokatTest() {
        // Открываем браузер
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);

        // Открываем урл
        MainPage objMainPage = new MainPage(driver);
        driver.get(objMainPage.URL);

        // Ждем нужную кнопку заказа в зависимости от параметра и жмем на нее
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                objMainPage.getOrderButtonLocators(buttonNumber));
        objMainPage.clickOnOrderButton(buttonNumber);

        // Заполняем все поля и переходим на следующую страницу
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.orderSamokat(name, surname, address, subway, phoneNumber);
        RentPage objRentPage = new RentPage(driver);

        // Вводим инфу по аренде и жмем "далее"
        objRentPage.fillRentInfo(date, rentDuration, color, comment);

        // Идем в окошко подтверждения и подтверждаем аренду
        ConfirmationPage objConfirmationPage = new ConfirmationPage(driver);
        objConfirmationPage.confirmRent();

        // Проверяем, что аренда оформена
        SuccessPage objSuccessPage = new SuccessPage(driver);
        MatcherAssert.assertThat("Не удалось подтвердить аренду", objSuccessPage.getSuccessHeader(),
                CoreMatchers.is("Заказ оформен"));
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}