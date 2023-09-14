package org.pratikum.pages;

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

    // Поле комментариев
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Кнопка далее
    private final By nextButton = By.cssSelector("div.Order_Buttons__1xGrp > button:nth-child(2)");

    public RentPage(WebDriver driver){
        this.driver = driver;
    }


    // Заполняем поля для аренды
    public void fillRentInfo(String date, String duration, String scooterColor, String comment) {
        // Заполняем дату
        driver.findElement(deliveryDate).sendKeys(date);

        // Убираем всплывающий календарь (с Днём костыля!)
        driver.findElement(header).click();

        // Выбираем вариант длительности аренды
        driver.findElement(rentLengthDropdownButton).click();
        driver.findElement(By.xpath(".//div[text()='" + duration + "']")).click();

        // Выбираем цвет
        driver.findElement(colorChooseFieldTitle).click();
        driver.findElement(By.xpath(".//label[text()='" + scooterColor + "']")).click();

        // Пишем коммент
        driver.findElement(commentField).sendKeys(comment);

        // Переходим дальше
        driver.findElement(nextButton).click();
    }
}
