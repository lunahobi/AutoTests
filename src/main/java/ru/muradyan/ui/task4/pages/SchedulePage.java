package ru.muradyan.ui.task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SchedulePage extends BasePage{

    private static final Logger logger = Logger.getLogger(SchedulePage.class);
    @FindBy(xpath = "//header/div[1]")
    private WebElement title;

    @FindBy(xpath = "//div[@class='content']/div/div/div[3]/div[1]/div[3]//a")
    private List<WebElement> buttonsList;

    @FindBy(xpath = "//div[@class='content']/div/div/div[3]/div[2]/div[1]/div//span[1]")
    private List<WebElement> days;

    @Step("Проверка открытия страницы 'Расписание'")
    public SchedulePage checkOpenPage(){
        checkOpenPage("Расписание", title);
        logger.info("Проверка открытия страницы");
        return this;
    }

    @Step("Нажать на кнопку '{buttonName}'")
    public SchedulePage clickOnButton(String buttonName) {
        for (WebElement item : buttonsList) {
            if (item.getAttribute("title").equalsIgnoreCase(buttonName)) {
                clickJS(item);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                logger.info("Клик на '" + buttonName + "'");
                String  url;
                if (buttonName.equalsIgnoreCase("Семестр")) {
                    url = "https://e.mospolytech.ru/#/schedule/semestr";
                }
                else if (buttonName.equalsIgnoreCase("Сессия")) {
                    url = "https://e.mospolytech.ru/#/schedule/session";
                }
                else {
                    url = "";
                }
                Assert.assertEquals("Страница не открылась", url,driverManager.getDriver().getCurrentUrl());
                if (getCurrentDayOfWeek() != "Вс") {
                    for (WebElement day: days) {
                        if (day.getCssValue("background").contains("rgb(86, 125, 255)")) {
                            Assert.assertEquals("Открылось расписание не текущего дня", getCurrentDayOfWeek(), day.getText());
                            return this;
                        }
                    }
                }

                return this;
            }
        }
        Assert.fail("Не найдена кнопка '" + buttonName + "'");
        return this;
    }


}
