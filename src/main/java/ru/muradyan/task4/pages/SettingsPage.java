package ru.muradyan.task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;

public class SettingsPage extends BasePage{

    private static final Logger logger = Logger.getLogger(SettingsPage.class);
    @FindBy(xpath = "//a[@title='Настройки']")
    private WebElement menuItem;

    @FindBy(xpath = "//b[@title='Меню']/../../..")
    private WebElement menuButton;

    @FindBy(xpath = "//div[@direction='vertical']//div[@class='sc-kAyceB grEoze']")
    private WebElement titleMenu;

    @FindBy(xpath = "//div[@class='sc-JrDLc cJgRsg']")
    private WebElement addButton;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[4]/div/div[2]/div/div[position() > 1]")
    private List<WebElement> itemsList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[5]")
    private WebElement message;

    @FindBy(xpath = "//div[@class='top-wrapper']//a[contains(@class, 'leftside-bar-item')]")
    private List<WebElement> menuItemsList;


    @Step("Проверка открытия страницы с настройками")
    public SettingsPage checkOpenPage(){
        wait.until(attributeContains(menuItem, "class", "hQdpCM"));
        logger.info("Проверка открытия страницы");
        return this;
    }

    @Step("Нажать на кнопку 'Меню'")
    public SettingsPage clickOnMenuButton() {
        waitUntilElementToBeClickable(menuButton).click();
        Assert.assertEquals("Настройки меню не открылись", "Настройка меню", titleMenu.getText());
        logger.info("Переход во вкладку с настройками меню");
        return this;
    }

    @Step("Добавить пункт меню '{nameOfItem}'")
    public SettingsPage addMenuItem(String nameOfItem) {
        waitUntilElementToBeClickable(addButton).click();
        for (WebElement item: itemsList) {
            waitUntilElementToBeVisible(item);
            moveToElement(item);
            String title = item.findElement(By.xpath(".//b")).getText();
            if (title.equalsIgnoreCase(nameOfItem)) {
                item.click();
                wait.until(ExpectedConditions.attributeContains(message, "class", "gZaFur"));
                Assert.assertEquals("Сообщение в окне не корректное", "Элемент добавлен в навигационное меню", message.findElement(By.xpath(".//div[@class='info-text']")).getText());
                WebElement checkbox = item.findElement(By.xpath("./div[@class='buttons']/*/div/*"));
                wait.until(ExpectedConditions.attributeContains(checkbox, "style", "color: var(--blue);"));
                WebElement closeButton = driverManager.getDriver().findElement(By.xpath("//button[contains(@class, 'kGDcM close-button')]"));
                moveToElement(closeButton);
                closeButton.click();
                for (WebElement menu: menuItemsList) {
                    if (menu.getAttribute("title").equalsIgnoreCase(nameOfItem)) {
                        logger.info("Успешно добавлен новый пункт меню '" + nameOfItem + "'");
                        return this;
                    }
                }
                Assert.fail("Пункт '" + nameOfItem + "' не добавился в меню");
                return this;
            }
        }
        Assert.fail("Не найдено название пункта меню '" + nameOfItem + "'");
        return this;
    }

    @Step("Нажать на пункт меню '{menuItem}'")
    public void clickOnMenuItem(String menuItem) {
        boolean found = false;
        for (WebElement item : menuItemsList) {
            if (item.getAttribute("title").equalsIgnoreCase(menuItem)) {
                clickJS(item);
                logger.info("Клик на '" + menuItem + "'");
                found = true;
                break; // После нахождения элемента, выходим из цикла
            }
            if (found) break;
        }

        if (!found) {
            Assert.fail("Не найден пункт меню '" + menuItem + "'");
        }
    }


}
