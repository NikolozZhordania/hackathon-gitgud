package ge.gitgud.testautomation.api.tests;

import ge.gitgud.testautomation.api.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static ge.gitgud.testautomation.api.data.constants.BankConstants.TestData.*;

@Feature("Get Pending Requests")
public class GetPendingRequestsTests extends BaseTest {

    @Story("Successful Retrieval")
    @Description("GET /api/bank/parent/{parentId}/requests")
    @Test(description = "Parent's Successful Retrieval of Pending Requests")
    public void parentSuccessfulRetrievalOfPendingRequests() {
        bankSteps
                .getPendingRequests(VALID_PARENT_ID)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson();
    }

    @Story("Successful Retrieval")
    @Description("GET /api/bank/parent/{parentId}/requests")
    @Test(description = "Parent's Pending Requests Response Body Verification")
    public void parentPendingRequestsResponseBodyVerification() {
        bankSteps
                .getPendingRequests(VALID_PARENT_ID)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty();
    }

    @Story("Successful Retrieval")
    @Description("GET /api/bank/parent/{parentId}/requests")
    @Test(description = "Parent's Pending Requests Content Type Verification")
    public void parentPendingRequestsContentTypeVerification() {
        bankSteps
                .getPendingRequests(VALID_PARENT_ID)
                .validateStatusCode(200)
                .validateContentTypeIsJson();
    }

    @Story("Invalid Retrieval")
    @Description("GET /api/bank/parent/{parentId}/requests")
    @Test(description = "Parent's Retrieval of Requests with Non-Existent Parent")
    public void parentRetrievalOfRequestsWithNonExistentParent() {
        bankSteps
                .getPendingRequests(INVALID_ID)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty();
    }

    @Story("Invalid Retrieval")
    @Description("GET /api/bank/parent/{parentId}/requests")
    @Test(description = "Parent's Not Found Error Response Detail Verification")
    public void parentNotFoundErrorResponseDetailVerification() {
        bankSteps
                .getPendingRequests(INVALID_ID)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty();
    }
}