package org.pratikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessPage {
    private final WebDriver driver;
    private final By header = By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[1]/text()");

    public SuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSuccessHeader() {
        return driver.findElement(header).getText();
    }
}
