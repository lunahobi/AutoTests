package ru.muradyan.ui.task3.tests.base;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Cookie;
import ru.muradyan.ui.managers.DriverManager;
import ru.muradyan.ui.managers.InitManager;

import java.util.Date;

public class BaseTest {
    private final DriverManager driverManager = DriverManager.getInstance();

    @BeforeClass
    public static void beforeClass(){
        InitManager.initFramework();
    }

    @Before
    public void before(){
        driverManager.getDriver().get("https://market.yandex.ru/");
        driverManager.getDriver().manage().addCookie(new Cookie("spravka",
                "dD0xNzE1NjAzMjY1O2k9MTk0LjE4Ni4yMjAuMTk2O0Q9MDlGRTE2RDQxQUI3OEEwRTRBODQ1RjcwMEE2QjU1Q0Q3NzIzNUExQkIxMEYzOUQwNkExMjMzRTBGMDA2Nzg0QTdFQTdBOThBNEI0Mzg3RjU4OTNEQTM3RDBCNjY4QkVFRTUxNzg1Q0M3RUU1QTM0MjY2RjkwNkM5Q0ZEQTQzMDM1M0UzM0E0RUYwQzVDNjYzMjgxQTEwMDUyMkE5N0I0MUNDNDRDNTYyQzdFNTEzQzlEQ0ZDOUNCMjBCM0FDODIyQzQ1RDc3O3U9MTcxNTYwMzI2NTEwNTA0MTc2MztoPWFhZjNjY2ZmY2Y5ODkyNzkwYWRiMjk5OGQ2YjY1MDk4",
                ".yandex.ru", "/", new Date(2024, 6, 12, 12, 27, 45)));
        driverManager.getDriver().get("https://market.yandex.ru/");

    }
    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }

}
