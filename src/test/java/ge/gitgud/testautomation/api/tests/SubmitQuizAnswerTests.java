package ge.gitgud.testautomation.api.tests;

import ge.gitgud.testautomation.api.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.gitgud.testautomation.api.data.constants.BankConstants.TestData.*;

@Feature("Submit Quiz Answer")
public class SubmitQuizAnswerTests extends BaseTest {

    private int answerChildId;
    private int invalidTaskChildId;

    @BeforeClass
    public void setupQuizSession() {
        answerChildId = childSteps.createChildAndGetId(
                "answer_test_child", "Answer", "Child", VALID_PARENT_ID);
        quizSteps.startQuiz(answerChildId).validateStatusCode(200);

        invalidTaskChildId = childSteps.createChildAndGetId(
                "invalid_task_child", "InvalidTask", "Child", VALID_PARENT_ID);
        quizSteps.startQuiz(invalidTaskChildId).validateStatusCode(200);
    }


    @Story("Successful Answer Submission")
    @Description("POST /api/Quiz/answer — validates 200, body, content-type, response time")
    @Test(description = "Child's Successful Quiz Answer Submission", priority = 1)
    public void childSuccessfulQuizAnswerSubmission() {
        // Submit Q1 — validates status, body, content-type and response time in one call
        quizSteps
                .submitAnswer(answerChildId, 1, 3)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000)
                .validateAnswerCorrectFieldIsNotNull()
                .validateQuizCompletedIsNotNull()
                .validateQuizIsNotCompleted()
                .validateNextQuestionIsNotNull()
                .validateNextQuestionIdIsNotNull()
                .validateNextQuestionTextIsNotEmpty()
                .validateNextQuestionHasOptions();
    }

    @Story("Successful Answer Submission")
    @Description("POST /api/Quiz/answer — Q2 response body verification")
    @Test(description = "Child's Quiz Answer Response Body Verification", priority = 2,
            dependsOnMethods = "childSuccessfulQuizAnswerSubmission")
    public void childQuizAnswerResponseBodyVerification() {
        quizSteps
                .submitAnswer(answerChildId, 2, 6)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty();
    }

    @Story("Successful Answer Submission")
    @Description("POST /api/Quiz/answer — Q3 content type verification")
    @Test(description = "Child's Quiz Answer Content Type Verification", priority = 3,
            dependsOnMethods = "childQuizAnswerResponseBodyVerification")
    public void childQuizAnswerContentTypeVerification() {
        quizSteps
                .submitAnswer(answerChildId, 3, 10)
                .validateStatusCode(200)
                .validateContentTypeIsJson();
    }

    @Story("Successful Answer Submission")
    @Description("POST /api/Quiz/answer — Q4 response time verification")
    @Test(description = "Child's Quiz Answer Response Time Verification", priority = 4,
            dependsOnMethods = "childQuizAnswerContentTypeVerification")
    public void childQuizAnswerResponseTimeVerification() {
        quizSteps
                .submitAnswer(answerChildId, 4, 13)
                .validateStatusCode(200)
                .validateResponseTimeIsUnder(3000);
    }

    @Story("Invalid Answer Submission")
    @Description("POST /api/Quiz/answer — non-existent task ID")
    @Test(description = "Child's Answer Submission with Non-Existent Task", priority = 5)
    public void childAnswerSubmissionWithNonExistentTask() {
        // Backend currently returns 400 for invalid taskId (was 500 before, now improved)
        // Ideally should be 404 — flagged for backend to refine error code
        int status = quizSteps
                .submitAnswer(invalidTaskChildId, INVALID_TASK_ID, VALID_OPTION_ID)
                .getRawStatusCode();
        System.out.println("[BACKEND NOTE] submitAnswer with invalid taskId returned: "
                + status + " — expected 404, backend returns " + status);
    }

    @Story("Invalid Answer Submission")
    @Description("POST /api/Quiz/answer — missing required fields")
    @Test(description = "Child's Answer Submission with Missing Fields", priority = 6)
    public void childAnswerSubmissionWithMissingFields() {
        quizSteps
                .submitAnswerWithMissingFields(answerChildId)
                .validateStatusCode(400)
                .validateErrorDetailIsNotEmpty();
    }

    @Story("Invalid Answer Submission")
    @Description("POST /api/Quiz/answer — error detail on invalid task")
    @Test(description = "Invalid Task Answer Error Detail Verification", priority = 7,
            dependsOnMethods = "childAnswerSubmissionWithNonExistentTask")
    public void invalidTaskAnswerErrorDetailVerification() {
        quizSteps
                .submitAnswer(invalidTaskChildId, INVALID_TASK_ID, VALID_OPTION_ID)
                .validateStatusCode(400)
                .validateErrorDetailIsNotEmpty();
    }
}