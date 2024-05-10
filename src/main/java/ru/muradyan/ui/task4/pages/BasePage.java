package ru.muradyan.ui.task4.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.muradyan.ui.managers.DriverManager;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class BasePage {

    protected DriverManager driverManager = DriverManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(10), Duration.ofMillis(1000));


    protected PageManager pageManager = PageManager.getInstance();

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverManager.getDriver();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void clickJS(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driverManager.getDriver();
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    protected WebElement waitUntilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitUntilElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitUntilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void moveToElement(WebElement element) {
        Actions action = new Actions(driverManager.getDriver());
        action.moveToElement(element).build().perform();
    }

    protected void moveToNewTab() {
        for (String tab : driverManager.getDriver().getWindowHandles()) {
            driverManager.getDriver().switchTo().window(tab);
        }
    }

    protected String getCurrentDayOfWeek() {
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

        switch (dayOfWeek) {
            case MONDAY:
                return "Пн";
            case TUESDAY:
                return "Вт";
            case WEDNESDAY:
                return "Ср";
            case THURSDAY:
                return "Чт";
            case FRIDAY:
                return "Пт";
            case SATURDAY:
                return "Сб";
            case SUNDAY:
                return "Вс";
            default:
                return "Неизвестный день недели";
        }
    }

    protected String findGroup(List<WebElement> list, String number) {
        for (WebElement group : list) {
            if (group.getAttribute("id").equals(number)) {
                return number;
            }
        }
        return "0";
    }

    protected void checkOpenPage(String pageName, WebElement title) {
        waitUntilElementToBeVisible(title);
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                pageName,
                title.getText());
    }
}

