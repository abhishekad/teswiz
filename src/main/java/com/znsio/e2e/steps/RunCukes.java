package com.znsio.e2e.steps;

import com.context.SessionContext;
import com.context.TestExecutionContext;
import com.znsio.e2e.tools.ScreenShotManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

public class RunCukes extends AbstractTestNGCucumberTests {
    private final TestExecutionContext context;
    private final ScreenShotManager screenShotManager;

    public RunCukes () {
        long threadId = Thread.currentThread().getId();
        System.out.println("RunCukes constructor: ThreadId: " + threadId);
        context = SessionContext.getTestExecutionContext(threadId);
        screenShotManager = new ScreenShotManager();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios () {
        System.out.printf("ThreadID: %d: in overridden scenarios%n", Thread.currentThread().getId());
        Object[][] scenarios = super.scenarios();
        System.out.println(scenarios);
        return scenarios;
    }

    @Before
    public void beforeScenario (Scenario scenario) {
        new Hooks().beforeScenario(scenario);
    }

    @After
    public void afterScenario (Scenario scenario) {
        new Hooks().afterScenario(scenario);
    }
}