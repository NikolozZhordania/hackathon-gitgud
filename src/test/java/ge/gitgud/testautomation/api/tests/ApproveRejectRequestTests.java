package ge.gitgud.testautomation.api.tests;

import ge.gitgud.testautomation.api.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static ge.gitgud.testautomation.api.data.constants.BankConstants.TestData.*;

@Feature("Approve / Reject Request")
public class ApproveRejectRequestTests extends BaseTest {

    @Story("Successful Approval")
    @Description("POST /api/bank/approve")
    @Test(description = "Parent's Successful Approval of Child's Money Request")
    public void parentSuccessfulApprovalOfChildMoneyRequest() {
        bankSteps
                .requestMoneyAndStoreId(VALID_CHILD_ID, VALID_AMOUNT)
                .validateStatusCode(200)
                .approveLastRequest()
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson();
    }

    @Story("Successful Approval")
    @Description("POST /api/bank/approve")
    @Test(description = "Parent's Approval Response Content Type Verification")
    public void parentApprovalResponseContentTypeVerification() {
        bankSteps
                .requestMoneyAndStoreId(VALID_CHILD_ID, VALID_AMOUNT)
                .validateStatusCode(200)
                .approveLastRequest()
                .validateStatusCode(200)
                .validateContentTypeIsJson();
    }

    @Story("Successful Rejection")
    @Description("POST /api/bank/approve")
    @Test(description = "Parent's Successful Rejection of Child's Money Request")
    public void parentSuccessfulRejectionOfChildMoneyRequest() {
        bankSteps
                .requestMoneyAndStoreId(VALID_CHILD_ID, VALID_AMOUNT)
                .validateStatusCode(200)
                .rejectLastRequest()
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson();
    }

    @Story("Successful Rejection")
    @Description("POST /api/bank/approve")
    @Test(description = "Parent's Rejection Response Content Type Verification")
    public void parentRejectionResponseContentTypeVerification() {
        bankSteps
                .requestMoneyAndStoreId(VALID_CHILD_ID, VALID_AMOUNT)
                .validateStatusCode(200)
                .rejectLastRequest()
                .validateStatusCode(200)
                .validateContentTypeIsJson();
    }

    @Story("Invalid Approval")
    @Description("POST /api/bank/approve")
    @Test(description = "Parent's Approval Attempt with Non-Existent Request")
    public void parentApprovalAttemptWithNonExistentRequest() {
        bankSteps
                .approveRequest(INVALID_ID)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty();
    }

    @Story("Invalid Rejection")
    @Description("POST /api/bank/approve")
    @Test(description = "Parent's Rejection Attempt with Non-Existent Request")
    public void parentRejectionAttemptWithNonExistentRequest() {
        bankSteps
                .rejectRequest(INVALID_ID)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty();
    }

    @Story("Invalid Approval")
    @Description("POST /api/bank/approve")
    @Test(description = "Non-Existent Request Approval Error Detail Verification")
    public void nonExistentRequestApprovalErrorDetailVerification() {
        bankSteps
                .approveRequest(INVALID_ID)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty();
    }

    @Story("Invalid Rejection")
    @Description("POST /api/bank/approve")
    @Test(description = "Non-Existent Request Rejection Error Detail Verification")
    public void nonExistentRequestRejectionErrorDetailVerification() {
        bankSteps
                .rejectRequest(INVALID_ID)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty();
    }
}