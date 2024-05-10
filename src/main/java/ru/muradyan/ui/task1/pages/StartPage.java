package ru.muradyan.ui.task1.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;

import java.util.List;

public class StartPage extends BasePage{
    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//span[@class='ng-binding']")
    private WebElement numberOfRemainingItems;

    @FindBy(xpath = "//input[@type='checkbox']")
    private List<WebElement> listOfCheckboxes;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement inputAddElement;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement buttonAddElement;

    private int remaing = 5;
    private int total = 5;

    @Step("Проверить, что присутствует текст '5 of 5 remaining'")
    public StartPage checkText(){
        waitUntilElementToBeVisible(numberOfRemainingItems);
        String text = String.format("%s of %s remaining", remaing, total);
        Assert.assertEquals("Текст " + text + " не присутствует на странице", text, numberOfRemainingItems.getText().trim());
        logger.info("Проверка присутствия текста '5 of 5 remaining'");
        return this;
    }

    @Step("Проверить, что элемент списка '{nameOfItem}' не зачеркнут")
    public StartPage checkItem(String nameOfItem){
        for (WebElement item: listOfCheckboxes){
            WebElement checkbox = item.findElement(By.xpath("./..//span"));
            if (checkbox.getText().trim().equals(nameOfItem)) {
                Assert.assertEquals("Элемент списка " + nameOfItem + " зачеркнут","done-false", checkbox.getAttribute("class"));
                logger.info("Проверка того, что элемент списка '" + nameOfItem + "' не зачеркнут");
                return this;
            }
        }
        Assert.fail("Элемент списка '" + nameOfItem + "' не был найден на cтранице!");
        return this;
    }

    @Step("Поставить галочку у элемента списка '{nameOfItem}'")
    public StartPage checkboxByName(String nameOfItem){
        for (WebElement checkbox: listOfCheckboxes){
            WebElement item = checkbox.findElement(By.xpath("./..//span"));
            if (item.getText().trim().equals(nameOfItem)) {
                checkbox.click();
                remaing -= 1;
                String text = String.format("%s of %s remaining", remaing, total);
                Assert.assertEquals("Элемент списка " + nameOfItem + " не зачеркнут","done-true", item.getAttribute("class"));
                Assert.assertEquals("Число оставшихся элементов не уменьшилось на 1", text, numberOfRemainingItems.getText());
                logger.info("Поставить галочку у элемента списка '" + nameOfItem + "'");
                return this;
            }
        }
        Assert.fail("Элемент списка '" + nameOfItem + "' не был найден на cтранице!");
        return this;
    }

    @Step("Добавить новый элемент списка '{nameOfItem}'")
    public StartPage addNewItem(String nameOfItem){
        inputAddElement.click();
        inputAddElement.sendKeys(nameOfItem);
        buttonAddElement.click();
        total += 1;
        remaing += 1;
        for (WebElement item: listOfCheckboxes){
            WebElement checkbox = item.findElement(By.xpath("./..//span"));
            if (checkbox.getText().trim().equals(nameOfItem)) {
                Assert.assertEquals("Элемент списка " + nameOfItem + " зачеркнут","done-false", checkbox.getAttribute("class"));
            }
        }
        String text = String.format("%s of %s remaining", remaing, total);
        Assert.assertEquals("Число оставшихся и общее число элементов не увеличилось на 1", text, numberOfRemainingItems.getText());
        logger.info("Добавление нового элемента списка '" + nameOfItem + "'");
        return this;
    }

}
