package org.pratikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class OrderPage {
    private final WebDriver driver;

    // Поле ввода имени
    private final By nameInputField = By.xpath(".//input[@placeholder='* Имя']");

    // Поле ввода фамилии
    private final By surnameInputField = By.xpath(".//input[@placeholder='* Фамилия']");

    // Поле ввода адреса
    private final By addressInputField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // Дропдаун выбора станции метро
    private final By subwayDropdownButton = By.className("select-search__input");

    // Поле ввода телефона
    private final By phoneInputField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка куков
    private final By cookiesButton = By.id("rcc-confirm-button");

    // Кнопка далее
    private final By nextButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void orderSamokat(String name, String surname, String address, String subway, String phoneNumber) {
        driver.findElement(cookiesButton).click();
        driver.findElement(nameInputField).sendKeys(name);
        driver.findElement(surnameInputField).sendKeys(surname);
        driver.findElement(addressInputField).sendKeys(address);
        driver.findElement(subwayDropdownButton).click();
        driver.findElement(By.xpath(".//*[text()='"+ subway +"']")).click();
        driver.findElement(phoneInputField).sendKeys(phoneNumber);
        driver.findElement(nextButton).click();
    }
}
