package ru.muradyan.ui.task5.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.muradyan.ui.managers.TestPropManager;

import java.util.List;

public class StartPage extends BasePage {

    private static final TestPropManager props = TestPropManager.getInstance();

    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "/html/body/div[1]/main/div/h2[1]")
    private WebElement title;

    @FindBy(xpath = "//li[@data-id]")
    private List<WebElement> buttonList;

    @FindBy(xpath = "//pre[@data-key='output-response']")
    private WebElement outputResponse;

    @FindBy(xpath = "//pre[@data-key='output-request']")
    private WebElement outputRequest;

    @Step("Проверка открытия страницы авторизации")
    public StartPage checkOpenPage(){
        checkOpenPage("Test your front-end against a real API", title);
        logger.info("Проверка открытия страницы");
        return this;
    }

    @Step("Нажать на кнопку {nameButton} и проверить ответ с ответом api")
    public StartPage clickOnButtonAndCheckAPI(String nameButton, String httpMethod) {
        for (WebElement button: buttonList) {
            WebElement request = button.findElement(By.xpath("./a"));
            if (request.getText().equalsIgnoreCase(nameButton) && button.getAttribute("data-http").equals(httpMethod)) {
                moveToElement(button);
                button.click();
                waitUntilElementToBeVisible(outputResponse);
                Assert.assertEquals("Не нажалась кнопка","active", button.getAttribute("class"));
                switch (httpMethod){
                    case ("get"):
                        Assert.assertEquals("Результат не совпал с результатом API", get(request.getAttribute("href")), outputResponse.getText());
                        break;
                    case ("post"):
                        compareResponses(post(request.getAttribute("href"), outputRequest.getText()), outputResponse.getText());
                        break;
                    case ("delete"):
                        Assert.assertEquals("Результат не совпал с результатом API", delete(request.getAttribute("href")), outputResponse.getText());
                        break;
                    case ("put"):
                        compareResponses(put(request.getAttribute("href"), outputRequest.getText()), outputResponse.getText());
                        break;
                    case ("patch"):
                        compareResponses(patch(request.getAttribute("href"), outputRequest.getText()), outputResponse.getText());
                        break;
                    default:
                        Assert.fail("Произошла ошибка");
                        break;
                }
                logger.info("Проверка ответа '" + nameButton + "' на соответствии с API");
                return this;
            }
        }

        Assert.fail("Не найдена кнопка '" + nameButton + "'");
        return this;
    }
}
