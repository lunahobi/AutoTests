package ru.muradyan.ui.task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewsPage extends BasePage{

    private static final Logger logger = Logger.getLogger(NewsPage.class);

    @FindBy(xpath = "//div[@class='top-wrapper']//a[contains(@class, 'leftside-bar-item')]")
    private List<WebElement> menuItemsList;

    @FindBy(xpath = "//header/div[1]")
    private WebElement title;

    @FindBy(id = "Поиск новостей")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@class='content']/div/div/div/div[position()>1]/div[2]/div/div/div[2]/div")
    private List<WebElement> newsTitleList;


    @Step("Проверка открытия страницы 'Новости'")
    public NewsPage checkOpenPage(){
        checkOpenPage("Новости", title);
        logger.info("Проверка открытия страницы");
        return this;
    }

    @Step("Поиск слова '{word}'")
    public NewsPage searchNews(String word) {
        waitUntilElementToBeClickable(searchInput).click();
        searchInput.sendKeys(word);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (WebElement title: newsTitleList) {
            moveToElement(title);

            if (!title.getText().toLowerCase().contains(word.toLowerCase())) {
                Assert.fail("Новость '" + title.getText() +  "' не содержит '" + word + "'");
            }
        }
        logger.info("Поиск слова '" + word + "'");
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
