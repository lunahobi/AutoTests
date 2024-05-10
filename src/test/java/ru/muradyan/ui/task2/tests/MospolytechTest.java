package ru.muradyan.ui.task2.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.muradyan.ui.task2.tests.base.BaseTest;
import ru.muradyan.ui.task2.pages.*;

public class MospolytechTest extends BaseTest {

    @Test
    @DisplayName("Тестирование страницы расписания на сайте Мосполитеха")
    public void test(){
        StartPage startPage  = new StartPage();
        startPage.checkOpenPage().
                clickOnHamburgerMenu()
                .mouseOnMenuItem("Обучающимся")
                .clickOnMenuItem("Расписания")
                .checkOpenPage()
                .clickOnButton()
                .checkOpenPage()
                .inputGroupNumber("221-361")
                .clickOnGroup("221-361");
    }
}
