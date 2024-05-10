package ru.muradyan.ui.task2.tests.base;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.muradyan.ui.managers.DriverManager;
import ru.muradyan.ui.managers.InitManager;

public class BaseTest {
    private final DriverManager driverManager = DriverManager.getInstance();

    @BeforeClass
    public static void beforeClass(){
        InitManager.initFramework();
    }

    @Before
    public void before(){
        driverManager.getDriver().get("https://mospolytech.ru/");
    }
    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }

}
