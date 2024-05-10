package ru.muradyan.ui.task2.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ScheduleGroupPage extends BasePage{

    private static final Logger logger = Logger.getLogger(ScheduleGroupPage.class);

    @FindBy(tagName = "h4")
    private WebElement title;

    @FindBy(xpath = "//input[@placeholder='группа ...']")
    private WebElement inputGroup;

    @FindBy(xpath = "//div[contains(@class, 'found-groups')]/*")
    private List<WebElement> groupsList;

    @FindBy(xpath = "//div[contains(@class, 'schedule-day_today')]/div[contains(@class, 'title')]")
    private WebElement dayToday;

    @Step("Проверка открытия страницы 'Расписание занятий'")
    public ScheduleGroupPage checkOpenPage(){
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Расписание занятий",
                title.getText());
        logger.info("Проверка открытия страницы");
        return this;
    }

    @Step("Ввести группу {numberOfGroup}")
    public ScheduleGroupPage inputGroupNumber(String numberOfGroup){
        waitUntilElementToBeClickable(inputGroup).click();
        inputGroup.sendKeys(numberOfGroup);
        inputGroup.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals("В результатах поиска отображается больше одной группы или вовсе не отображается", 1, groupsList.size());
        Assert.assertEquals("В результатах поиска не отображается искомая группа", numberOfGroup, findGroup(groupsList, numberOfGroup));
        logger.info("Ввод группы " + numberOfGroup);
        return this;
    }

    @Step("Нажать на найденную группу {numberOfGroup}")
    public ScheduleGroupPage clickOnGroup(String numberOfGroup) {
        for (WebElement group: groupsList) {
            if (group.getAttribute("id").equals(numberOfGroup)) {
                group.click();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Assert.assertEquals("Не открылось расписание выбранной группы", "Расписание " + numberOfGroup,driverManager.getDriver().getTitle());
                if (getCurrentDayOfWeek() != "Воскресенье") {
                    Assert.assertEquals("Текущий день недели не выделен цветом", getCurrentDayOfWeek(), dayToday.getText());
                }
                logger.info("Клик по найденной группе " + numberOfGroup);
                return this;
            }
        }
        Assert.fail("Не нашли группу");
        return this;
    }
}
