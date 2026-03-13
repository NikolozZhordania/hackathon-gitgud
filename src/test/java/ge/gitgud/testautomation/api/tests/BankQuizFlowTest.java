package ge.gitgud.testautomation.api.tests;

import ge.gitgud.testautomation.api.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.gitgud.testautomation.api.data.constants.BankConstants.TestData.*;

@Feature("Full Parent-Child Bank & Quiz Flow")
public class BankQuizFlowTest extends BaseTest {

    private int flowQuizChildId;

    @BeforeClass
    public void createFreshQuizChild() {
        flowQuizChildId = childSteps.createChildAndGetId(
                "flow_quiz_child", "FlowQuiz", "Child", VALID_PARENT_ID);
    }

    @Story("End-to-End Flow")
    @Description("POST /api/bank/request-money")
    @Test(description = "Child's Money Request Submission to Parent", priority = 1)
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
            dependsOnMethods = "childMoneyRequestSubmissionToParent", priority = 2)
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
            dependsOnMethods = "parentRetrievalOfIncomingMoneyRequests", priority = 3)
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
            dependsOnMethods = "parentApprovalOfChildMoneyRequest", priority = 4)
    public void childInitiationOfFinancialLiteracyQuiz() {
        quizSteps
                .startQuiz(flowQuizChildId)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000);
    }

    @Story("End-to-End Flow")
    @Description("POST /api/Quiz/answer")
    @Test(description = "Child's Submission of Quiz Answer for Reward",
            dependsOnMethods = "childInitiationOfFinancialLiteracyQuiz", priority = 5)
    public void childSubmissionOfQuizAnswerForReward() {
        quizSteps
                .submitAnswer(flowQuizChildId, 1, 3)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000);
    }

    @Story("End-to-End Flow")
    @Description("POST /api/Quiz/answer")
    @Test(description = "System's Evaluation and Issuance of Answer Result",
            dependsOnMethods = "childSubmissionOfQuizAnswerForReward", priority = 6)
    public void systemEvaluationAndIssuanceOfAnswerResult() {
        quizSteps
                .submitAnswer(flowQuizChildId, 2, 6)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000);
    }

    @Story("End-to-End Flow")
    @Description("Response time validation across all steps")
    @Test(description = "System's Response Time Compliance Verification",
            dependsOnMethods = "systemEvaluationAndIssuanceOfAnswerResult", priority = 7)
    public void systemResponseTimeComplianceVerification() {
        bankSteps
                .requestMoney(VALID_CHILD_ID, VALID_AMOUNT)
                .validateResponseTimeIsUnder(3000);
        quizSteps
                .submitAnswer(flowQuizChildId, 3, 10)
                .validateStatusCode(200)
                .validateResponseTimeIsUnder(3000);
    }
}