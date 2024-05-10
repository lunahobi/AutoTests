package ru.muradyan.ui.task4.tests.base;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import ru.muradyan.ui.managers.DriverManager;
import ru.muradyan.ui.managers.InitManager;

public class BaseTest {
    private static final DriverManager driverManager = DriverManager.getInstance();

    @BeforeClass
    public static void beforeClass(){
        InitManager.initFramework();
        driverManager.getDriver().get("https://e.mospolytech.ru");
    }
    @AfterClass
    public static void after() {
        InitManager.quitFramework();
    }
}
