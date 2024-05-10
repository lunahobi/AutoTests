package ru.muradyan.ui.task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.muradyan.ui.managers.TestPropManager;

import static ru.muradyan.ui.utils.PropConst.LOGIN;
import static ru.muradyan.ui.utils.PropConst.PASSWORD;

public class StartPage extends BasePage {

    private static final TestPropManager props = TestPropManager.getInstance();

    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//h3")
    private WebElement title;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(@class, 'submit')]")
    private WebElement logInButton;

    @Step("Проверка открытия страницы авторизации")
    public StartPage checkOpenPage(){
        checkOpenPage("Личный кабинет", title);
        logger.info("Проверка открытия страницы");
        return this;
    }

    @Step("Авторизоваться под аккаунтом студента")
    public HomePage logInStudent() {
        System.out.println("logInStudent() method called");
        waitUntilElementToBeClickable(loginInput).click();
        loginInput.sendKeys(props.getProperty(LOGIN));

        waitUntilElementToBeClickable(passwordInput).click();
        passwordInput.sendKeys(props.getProperty(PASSWORD));

        logInButton.click();
        logger.info("Авторизация под учетной записью студента");
        return pageManager.getHomePage();
    }


}
