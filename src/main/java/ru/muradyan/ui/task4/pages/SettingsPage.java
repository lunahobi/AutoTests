package ru.muradyan.ui.task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SettingsPage extends BasePage{

    private static final Logger logger = Logger.getLogger(SettingsPage.class);
    @FindBy(xpath = "//a[@title='Настройки']")
    private WebElement menuItem;

    @FindBy(xpath = "//b[@title='Меню']/../../..")
    private WebElement menuButton;

    @FindBy(xpath = "//header/div[1]")
    private WebElement titleMenu;

    @FindBy(xpath = "//div[contains(@style, 'margin-left: 8px;')]/parent::div/div[2]/div")
    private WebElement addButton;

    @FindBy(xpath = "//button[contains(@class, 'close-button') and @width]/parent::div/div[2]/div/div[position()>1]")
    private List<WebElement> itemsList;

    @FindBy(xpath = "//div[@class='top-wrapper']//a[contains(@class, 'leftside-bar-item')]")
    private List<WebElement> menuItemsList;


    @Step("Проверка открытия страницы с настройками")
    public SettingsPage checkOpenPage(){
        Assert.assertEquals("Не открылась страница", "rgba(64, 197, 197, 1)", menuItem.getCssValue("color"));
        logger.info("Проверка открытия страницы");
        return this;
    }

    @Step("Нажать на кнопку 'Меню'")
    public SettingsPage clickOnMenuButton() {
        waitUntilElementToBeClickable(menuButton).click();
        Assert.assertEquals("Настройки меню не открылись", "Меню", titleMenu.getText());
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
                WebElement checkbox = item.findElement(By.xpath("./div[@class='buttons']/*/div/*"));
                wait.until(ExpectedConditions.attributeContains(checkbox, "style", "color: var(--blue);"));
                WebElement closeButton = driverManager.getDriver().findElement(By.xpath("//button[contains(@class, 'close-button') and @width]"));
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
