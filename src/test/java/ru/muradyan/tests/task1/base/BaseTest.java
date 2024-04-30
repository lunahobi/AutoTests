package ru.muradyan.tests.task1.base;
import org.junit.*;

import ru.muradyan.managers.DriverManager;
import ru.muradyan.managers.InitManager;
import ru.muradyan.managers.TestPropManager;

public class BaseTest {
    private final DriverManager driverManager = DriverManager.getInstance();
    private final TestPropManager propManager = TestPropManager.getInstance();

    @BeforeClass
    public static void beforeClass(){
        InitManager.initFramework();
    }

    @Before
    public void before(){
        driverManager.getDriver().get("https://lambdatest.github.io/sample-todo-app/");
    }
    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }

}
