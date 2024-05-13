package ru.muradyan.ui.task3.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LaptopPage extends BasePage{

    private static final Logger logger = Logger.getLogger(LaptopPage.class);

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//div[@data-auto-themename='listDetailed']")
    private List<WebElement> productList;

    @FindBy(xpath = "//div[@data-filter-value-id='resale_resale']//label")
    private WebElement resaleFilter;

    @FindBy(xpath = "//h2[text()='Популярные предложения']")
    private WebElement titleResale;

    @Step("Проверка открытия страницы 'Ноутбуки'")
    public LaptopPage checkOpenPage(){
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Ноутбуки",
                title.getText());
        logger.info("Проверка открытия страницы");
        return this;
    }

    @Step("Вывести в лог первые 5 найденных товаров")
    public LaptopPage logProducts() {
        for (int i = 0; i < 5 && i < productList.size(); i++) {
            WebElement product = productList.get(i);
            moveToElement(product);

            String title = product.findElement(By.xpath(".//h3")).getText();
            String price = product.findElement(By.xpath(".//span[@class='_1ArMm']")).getText();
            List<WebElement> discountElements = product.findElements(By.xpath(".//span[@class='_2Vt2k']"));
            boolean isDiscount = !discountElements.isEmpty();

            logger.info("Название: " + title + ". Цена: " + price + (isDiscount ? ". Уценка." : "."));
        }
        return this;
    }

    @Step("В меню фильтров “Состояние товара” выбрать “Уценка”")
    public LaptopPage setDiscount() {
        moveToElement(resaleFilter);
        resaleFilter.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        moveToElement(title);
        for (int i = 0; i < 10 && i < productList.size(); i++) {
            WebElement product = productList.get(i);
            moveToElement(product);
            List<WebElement> discountElements = product.findElements(By.xpath(".//span[@class='_2Vt2k']"));
            boolean isDiscount = !discountElements.isEmpty();
            if (!isDiscount) {
                Assert.fail("Не у каждого товара присутствует плашка 'Уценка'");
            }
        }
        logger.info("Показаны уценённые товары");
        return this;
    }



}
