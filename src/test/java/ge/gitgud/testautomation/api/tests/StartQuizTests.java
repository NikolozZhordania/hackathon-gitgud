package ge.gitgud.testautomation.api.tests;

import ge.gitgud.testautomation.api.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.gitgud.testautomation.api.data.constants.BankConstants.TestData.*;

@Feature("Start Quiz")
public class StartQuizTests extends BaseTest {

    private int startQuizChildId;

    @BeforeClass
    public void createFreshChild() {
        startQuizChildId = childSteps.createChildAndGetId(
                "start_quiz_child", "StartQuiz", "Child", VALID_PARENT_ID);
    }

    @Story("Successful Quiz Start")
    @Description("POST /api/Quiz/start/{childId} — status, body, content-type")
    @Test(description = "Child's Successful Quiz Session Initiation", priority = 1)
    public void childSuccessfulQuizSessionInitiation() {
        quizSteps
                .startQuiz(startQuizChildId)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000);
    }

    @Story("Successful Quiz Start")
    @Description("POST /api/Quiz/start/{childId} — response body verification")
    @Test(description = "Child's Quiz Session Response Body Verification", priority = 2,
            dependsOnMethods = "childSuccessfulQuizSessionInitiation")
    public void childQuizSessionResponseBodyVerification() {
        // Quiz already started in test 1 — validate the stored response body
        quizSteps
                .validateResponseBodyIsNotEmpty();
    }

    @Story("Successful Quiz Start")
    @Description("POST /api/Quiz/start/{childId} — content type verification")
    @Test(description = "Child's Quiz Session Content Type Verification", priority = 3,
            dependsOnMethods = "childSuccessfulQuizSessionInitiation")
    public void childQuizSessionContentTypeVerification() {
        quizSteps
                .validateContentTypeIsJson();
    }

    @Story("Successful Quiz Start")
    @Description("POST /api/Quiz/start/{childId} — response time verification")
    @Test(description = "Child's Quiz Session Response Time Verification", priority = 4,
            dependsOnMethods = "childSuccessfulQuizSessionInitiation")
    public void childQuizSessionResponseTimeVerification() {
        quizSteps
                .validateResponseTimeIsUnder(3000);
    }

    // ── Negative tests — INVALID_ID never creates a quiz, safe to call repeatedly ──

    @Story("Invalid Quiz Start")
    @Description("POST /api/Quiz/start/{childId} — non-existent child returns 404")
    @Test(description = "Child's Quiz Initiation with Non-Existent Child", priority = 5)
    public void childQuizInitiationWithNonExistentChild() {
        quizSteps
                .startQuiz(INVALID_ID)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty();
    }

    @Story("Invalid Quiz Start")
    @Description("POST /api/Quiz/start/{childId} — error detail on non-existent child")
    @Test(description = "Non-Existent Child Quiz Error Detail Verification", priority = 6)
    public void nonExistentChildQuizErrorDetailVerification() {
        quizSteps
                .startQuiz(INVALID_ID)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty();
    }
}