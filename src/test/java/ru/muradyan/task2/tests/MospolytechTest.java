package ru.muradyan.task2.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.muradyan.task2.pages.StartPage;
import ru.muradyan.task2.tests.base.BaseTest;

public class MospolytechTest extends BaseTest {

    @Test
    @DisplayName("Тестирование страницы расписания на сайте Мосполитеха")
    public void test(){
        StartPage startPage  = new StartPage();
        startPage.clickOnHamburgerMenu()
                .mouseOnMenuItem("Обучающимся")
                .clickOnMenuItem("Расписания")
                .checkOpenPage()
                .clickOnButton()
                .checkOpenPage()
                .inputGroupNumber("221-361")
                .clickOnGroup("221-361");
    }
}
