package ge.gitgud.testautomation.api.base;

import ge.gitgud.testautomation.api.steps.BankSteps;
import ge.gitgud.testautomation.api.steps.QuizSteps;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected BankSteps bankSteps;
    protected QuizSteps quizSteps;

    @BeforeClass
    public void setUp() {
        bankSteps = new BankSteps();
        quizSteps = new QuizSteps();
    }
}
