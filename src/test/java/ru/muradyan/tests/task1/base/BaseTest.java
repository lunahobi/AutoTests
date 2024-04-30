package ru.muradyan.tests.task1.base;
import org.junit.*;

import ru.muradyan.task1.managers.DriverManager;
import ru.muradyan.task1.managers.InitManager;
import ru.muradyan.task1.managers.TestPropManager;

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
