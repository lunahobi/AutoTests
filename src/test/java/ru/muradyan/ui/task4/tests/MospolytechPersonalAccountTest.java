package ru.muradyan.ui.task4.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.muradyan.ui.task4.tests.base.BaseTest;
import ru.muradyan.ui.task4.pages.*;

public class MospolytechPersonalAccountTest extends BaseTest {

    StartPage startPage  = new StartPage();
    SettingsPage settingsPage = new SettingsPage();
    HomePage homePage = new HomePage();
    NewsPage newsPage = new NewsPage();
    SchedulePage schedulePage = new SchedulePage();

    @Test
    @DisplayName("Проверка настройки меню")
    public void test(){
        startPage.checkOpenPage()
                .logInStudent()
                .checkOpenPage()
                .closeNew()
                .clickOnMenuItem("Настройки");

        settingsPage.checkOpenPage()
                .clickOnMenuButton()
                .addMenuItem("Расписание");
    }

    @Test
    @DisplayName("Проверка поиска новостей")
    public void test2(){
//        StartPage startPage  = new StartPage();
//        startPage.checkOpenPage()
//                .logInStudent()
//                .checkOpenPage()
//                .closeNew()
//                .clickOnMore()
//                .checkOpenPage()
//                .searchNews("при");
        settingsPage.clickOnMenuItem("Главная");
        homePage.checkOpenPage()
                .clickOnMore()
                .checkOpenPage()
                .searchNews("при");
    }

    @Test
    @DisplayName("Проверка расписания студента")
    public void test3(){
//        StartPage startPage  = new StartPage();
//        startPage.checkOpenPage()
//                .logInStudent()
//                .checkOpenPage()
//                .closeNew()
//                .clickOnMenuItem("Расписание");
//        SchedulePage schedulePage = new SchedulePage();
//        schedulePage.checkOpenPage()
//                .clickOnButtonAndCheckAPI("Семестр")
//                .clickOnButtonAndCheckAPI("Сессия");
        newsPage.clickOnMenuItem("Расписание");
        schedulePage.checkOpenPage()
                .clickOnButton("Семестр")
                .clickOnButton("Сессия");

    }


}
