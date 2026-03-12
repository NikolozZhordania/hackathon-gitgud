package ge.gitgud.testautomation.api.tests;

import ge.gitgud.testautomation.api.base.BaseTest;
import ge.gitgud.testautomation.api.steps.BankSteps;
import ge.gitgud.testautomation.api.steps.QuizSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.gitgud.testautomation.api.data.constants.BankConstants.TestData.*;

@Feature("Full Parent-Child Bank & Quiz Flow")
public class BankQuizFlowTest extends BaseTest {

    @Story("End-to-End Flow")
    @Description("POST /api/bank/request-money")
    @Test(description = "Child's Money Request Submission to Parent")
    public void childMoneyRequestSubmissionToParent() {
        bankSteps
                .requestMoneyAndStoreId(VALID_CHILD_ID, VALID_AMOUNT)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000);
    }

    @Story("End-to-End Flow")
    @Description("GET /api/bank/parent/{parentId}/requests")
    @Test(description = "Parent's Retrieval of Incoming Money Requests",
            dependsOnMethods = "childMoneyRequestSubmissionToParent")
    public void parentRetrievalOfIncomingMoneyRequests() {
        bankSteps
                .getPendingRequests(VALID_PARENT_ID)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000);
    }

    @Story("End-to-End Flow")
    @Description("POST /api/bank/approve")
    @Test(description = "Parent's Approval of Child's Money Request",
            dependsOnMethods = "parentRetrievalOfIncomingMoneyRequests")
    public void parentApprovalOfChildMoneyRequest() {
        bankSteps
                .approveLastRequest()
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000);
    }

    @Story("End-to-End Flow")
    @Description("POST /api/Quiz/start/{childId}")
    @Test(description = "Child's Initiation of Financial Literacy Quiz",
            dependsOnMethods = "parentApprovalOfChildMoneyRequest")
    public void childInitiationOfFinancialLiteracyQuiz() {
        quizSteps
                .startQuiz(VALID_CHILD_ID)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000);
    }

    @Story("End-to-End Flow")
    @Description("POST /api/Quiz/answer")
    @Test(description = "Child's Submission of Quiz Answer for Reward",
            dependsOnMethods = "childInitiationOfFinancialLiteracyQuiz")
    public void childSubmissionOfQuizAnswerForReward() {
        quizSteps
                .submitAnswer(VALID_CHILD_ID, VALID_PARENT_ID, VALID_TASK_ID, VALID_OPTION_ID)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000);
    }

    @Story("End-to-End Flow")
    @Description("POST /api/Quiz/answer")
    @Test(description = "System's Evaluation and Issuance of Answer Result",
            dependsOnMethods = "childSubmissionOfQuizAnswerForReward")
    public void systemEvaluationAndIssuanceOfAnswerResult() {
        quizSteps
                .submitAnswer(VALID_CHILD_ID, VALID_PARENT_ID, VALID_TASK_ID, VALID_OPTION_ID)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson();
    }

    @Story("End-to-End Flow")
    @Description("Response time validation across all steps")
    @Test(description = "System's Response Time Compliance Verification",
            dependsOnMethods = "systemEvaluationAndIssuanceOfAnswerResult")
    public void systemResponseTimeComplianceVerification() {
        bankSteps
                .requestMoney(VALID_CHILD_ID, VALID_AMOUNT)
                .validateResponseTimeIsUnder(3000);
        quizSteps
                .startQuiz(VALID_CHILD_ID)
                .validateResponseTimeIsUnder(3000);
    }
}