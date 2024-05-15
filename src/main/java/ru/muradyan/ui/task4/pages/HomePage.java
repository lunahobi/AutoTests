package ru.muradyan.ui.task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{

    private static final Logger logger = Logger.getLogger(HomePage.class);

    @FindBy(xpath = "//h2/div")
    private WebElement title;

    @FindBy(xpath = "//button[contains(@class, 'close-button') and @width]")
    private WebElement closeButtonNewInfo;

    @FindBy(xpath = "//div[@class='top-wrapper']//a[contains(@class, 'leftside-bar-item')]")
    private List<WebElement> menuItemsList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[3]/div/div[3]/div[1]/h4/div/a/button")
    private WebElement moreButton;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[3]/div[1]/div/div/div[2]/div[2]/a")
    private List<WebElement> horizontalMenuItemList;

    @Step("Проверка открытия главной страницы")
    public HomePage checkOpenPage(){
        checkOpenPage("Главная", title);
        logger.info("Проверка открытия страницы");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Step("Закрыть окно 'Что нового'")
    public HomePage closeNew() {
        if (closeButtonNewInfo.isDisplayed()) {
            clickJS(closeButtonNewInfo);
            logger.info("Закрытие окна 'Что нового'");
        }
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
            for (WebElement item: horizontalMenuItemList) {
                if (item.findElement(By.xpath("./span")).getText().equalsIgnoreCase(menuItem)) {
                    clickJS(item);
                    logger.info("Клик на '" + menuItem + "'");
                    found = true;
                    break; // После нахождения элемента, выходим из цикла
                }
                if (found) break;
            }
        }

        if (!found) {
            Assert.fail("Не найден пункт меню '" + menuItem + "'");
        }
    }


    @Step("Нажать на кнопку 'Ещё'")
    public NewsPage clickOnMore() {
        waitUntilElementToBeClickable(moreButton).click();
        logger.info("Клик на 'Ещё' для перехода на страницу с новостями");
        return pageManager.getNewsPage();
    }
}
