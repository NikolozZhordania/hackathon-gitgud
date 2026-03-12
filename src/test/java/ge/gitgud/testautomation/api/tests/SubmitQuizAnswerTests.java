package ge.gitgud.testautomation.api.tests;

import ge.gitgud.testautomation.api.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static ge.gitgud.testautomation.api.data.constants.BankConstants.TestData.*;

@Feature("Submit Quiz Answer")
public class SubmitQuizAnswerTests extends BaseTest {

    @Story("Successful Answer Submission")
    @Description("POST /api/Quiz/answer")
    @Test(description = "Child's Successful Quiz Answer Submission")
    public void childSuccessfulQuizAnswerSubmissionTest() {
        quizSteps
                .submitAnswer(VALID_CHILD_ID, VALID_PARENT_ID, VALID_TASK_ID, VALID_OPTION_ID)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson();
    }

    @Story("Successful Answer Submission")
    @Description("POST /api/Quiz/answer")
    @Test(description = "Child's Quiz Answer Response Body Verification")
    public void childQuizAnswerResponseBodyVerificationTest() {
        quizSteps
                .submitAnswer(VALID_CHILD_ID, VALID_PARENT_ID, VALID_TASK_ID, VALID_OPTION_ID)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty();
    }

    @Story("Successful Answer Submission")
    @Description("POST /api/Quiz/answer")
    @Test(description = "Child's Quiz Answer Content Type Verification")
    public void childQuizAnswerContentTypeVerificationTest() {
        quizSteps
                .submitAnswer(VALID_CHILD_ID, VALID_PARENT_ID, VALID_TASK_ID, VALID_OPTION_ID)
                .validateStatusCode(200)
                .validateContentTypeIsJson();
    }

    @Story("Successful Answer Submission")
    @Description("POST /api/Quiz/answer")
    @Test(description = "Child's Quiz Answer Response Time Verification")
    public void childQuizAnswerResponseTimeVerificationTest() {
        quizSteps
                .submitAnswer(VALID_CHILD_ID, VALID_PARENT_ID, VALID_TASK_ID, VALID_OPTION_ID)
                .validateResponseTimeIsUnder(3000);
    }

    @Story("Invalid Answer Submission")
    @Description("POST /api/Quiz/answer")
    @Test(description = "Child's Answer Submission with Non-Existent Task")
    public void childAnswerSubmissionWithNonExistentTaskTest() {
        quizSteps
                .submitAnswer(VALID_CHILD_ID, VALID_PARENT_ID, INVALID_TASK_ID, VALID_OPTION_ID)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty();
    }

    @Story("Invalid Answer Submission")
    @Description("POST /api/Quiz/answer")
    @Test(description = "Child's Answer Submission with Missing Fields")
    public void childAnswerSubmissionWithMissingFieldsTest() {
        quizSteps
                .submitAnswerWithMissingFields(VALID_CHILD_ID)
                .validateStatusCode(400)
                .validateErrorDetailIsNotEmpty();
    }

    @Story("Invalid Answer Submission")
    @Description("POST /api/Quiz/answer")
    @Test(description = "Invalid Task Answer Error Detail Verification")
    public void invalidTaskAnswerErrorDetailVerificationTest() {
        quizSteps
                .submitAnswer(VALID_CHILD_ID, VALID_PARENT_ID, INVALID_TASK_ID, VALID_OPTION_ID)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty();
    }
}