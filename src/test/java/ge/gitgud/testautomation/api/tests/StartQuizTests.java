package ge.gitgud.testautomation.api.tests;

import ge.gitgud.testautomation.api.base.BaseTest;
import ge.gitgud.testautomation.api.steps.QuizSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static ge.gitgud.testautomation.api.data.constants.BankConstants.TestData.*;

@Feature("Start Quiz")
public class StartQuizTests extends BaseTest {

    private QuizSteps quizSteps;

    @BeforeMethod
    public void setUp() {
        quizSteps = new QuizSteps();
    }

    @Story("Successful Quiz Start")
    @Description("POST /api/Quiz/start/{childId}")
    @Test(description = "Child's Successful Quiz Session Initiation")
    public void childSuccessfulQuizSessionInitiationTest() {
        quizSteps
                .startQuiz(VALID_CHILD_ID)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson();
    }

    @Story("Successful Quiz Start")
    @Description("POST /api/Quiz/start/{childId}")
    @Test(description = "Child's Quiz Session Response Body Verification")
    public void childQuizSessionResponseBodyVerificationTest() {
        quizSteps
                .startQuiz(VALID_CHILD_ID)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty();
    }

    @Story("Successful Quiz Start")
    @Description("POST /api/Quiz/start/{childId}")
    @Test(description = "Child's Quiz Session Content Type Verification")
    public void childQuizSessionContentTypeVerificationTest() {
        quizSteps
                .startQuiz(VALID_CHILD_ID)
                .validateStatusCode(200)
                .validateContentTypeIsJson();
    }

    @Story("Successful Quiz Start")
    @Description("POST /api/Quiz/start/{childId}")
    @Test(description = "Child's Quiz Session Response Time Verification")
    public void childQuizSessionResponseTimeVerificationTest() {
        quizSteps
                .startQuiz(VALID_CHILD_ID)
                .validateResponseTimeIsUnder(3000);
    }

    @Story("Invalid Quiz Start")
    @Description("POST /api/Quiz/start/{childId}")
    @Test(description = "Child's Quiz Initiation with Non-Existent Child")
    public void childQuizInitiationWithNonExistentChildTest() {
        quizSteps
                .startQuiz(INVALID_ID)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty();
    }

    @Story("Invalid Quiz Start")
    @Description("POST /api/Quiz/start/{childId}")
    @Test(description = "Non-Existent Child Quiz Error Detail Verification")
    public void nonExistentChildQuizErrorDetailVerificationTest() {
        quizSteps
                .startQuiz(INVALID_ID)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty();
    }
}