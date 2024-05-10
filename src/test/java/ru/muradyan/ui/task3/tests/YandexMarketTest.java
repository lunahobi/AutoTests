package ru.muradyan.ui.task3.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.muradyan.ui.task3.pages.StartPage;
import ru.muradyan.ui.task3.tests.base.BaseTest;

public class YandexMarketTest extends BaseTest {

    @Test
    @DisplayName("Яндекс Маркет: проверка фильтра по уцененным товарам")
    public void test(){
        StartPage startPage  = new StartPage();
        startPage.checkOpenPage()
                .clickOnCatalog()
                .moveToCategory("Ноутбуки и компьютеры")
                .clickOnMenuItem("Ноутбуки")
                .logProducts()
                .setDiscount();
    }
}
