package ge.gitgud.testautomation.api.tests;

import ge.gitgud.testautomation.api.base.BaseTest;
import ge.gitgud.testautomation.api.steps.BankSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static ge.gitgud.testautomation.api.data.constants.BankConstants.TestData.*;

@Feature("Request Money")
public class RequestMoneyTests extends BaseTest {

    @Story("Successful Money Request")
    @Description("POST /api/bank/request-money")
    @Test(description = "Child's Successful Money Request Submission")
    public void childSuccessfulMoneyRequestSubmission() {
        bankSteps
                .requestMoney(VALID_CHILD_ID, VALID_AMOUNT)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson();
    }

    @Story("Successful Money Request")
    @Description("POST /api/bank/request-money")
    @Test(description = "Child's Money Request Response Body Verification")
    public void childMoneyRequestResponseBodyVerification() {
        bankSteps
                .requestMoney(VALID_CHILD_ID, VALID_AMOUNT)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty();
    }

    @Story("Successful Money Request")
    @Description("POST /api/bank/request-money")
    @Test(description = "Child's Money Request Content Type Verification")
    public void childMoneyRequestContentTypeVerification() {
        bankSteps
                .requestMoney(VALID_CHILD_ID, VALID_AMOUNT)
                .validateStatusCode(200)
                .validateContentTypeIsJson();
    }

    @Story("Successful Money Request")
    @Description("POST /api/bank/request-money")
    @Test(description = "Child's Money Request Response Time Verification")
    public void childMoneyRequestResponseTimeVerification() {
        bankSteps
                .requestMoney(VALID_CHILD_ID, VALID_AMOUNT)
                .validateResponseTimeIsUnder(3000);
    }

    @Story("Invalid Money Request")
    @Description("POST /api/bank/request-money")
    @Test(description = "Child's Money Request Submission with Negative Amount")
    public void childMoneyRequestSubmissionWithNegativeAmount() {
        bankSteps
                .requestMoney(VALID_CHILD_ID, INVALID_AMOUNT)
                .validateStatusCode(400)
                .validateErrorDetailIsNotEmpty();
    }

    @Story("Invalid Money Request")
    @Description("POST /api/bank/request-money")
    @Test(description = "Child's Money Request Submission with Non-Existent Child")
    public void childMoneyRequestSubmissionWithNonExistentChild() {
        bankSteps
                .requestMoney(INVALID_ID, VALID_AMOUNT)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty();
    }

    @Story("Invalid Money Request")
    @Description("POST /api/bank/request-money")
    @Test(description = "Child's Money Request Error Response Detail Verification")
    public void childMoneyRequestErrorResponseDetailVerification() {
        bankSteps
                .requestMoney(VALID_CHILD_ID, INVALID_AMOUNT)
                .validateStatusCode(400)
                .validateErrorDetailIsNotEmpty();
    }
}