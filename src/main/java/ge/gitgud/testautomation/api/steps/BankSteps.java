package ge.gitgud.testautomation.api.steps;

import ge.gitgud.testautomation.api.client.BankAPI;
import ge.gitgud.testautomation.api.data.models.request.ApprovalDto;
import ge.gitgud.testautomation.api.data.models.request.CreateRequestDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static ge.gitgud.testautomation.api.client.BaseAPIClient.waitBetweenRequests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BankSteps {

    private final BankAPI api = new BankAPI();
    private Response rawResponse;
    private int lastCreatedRequestId;

    @Step("Request money: childId={childId}, amount={amount}")
    public BankSteps requestMoney(int childId, double amount) {
        waitBetweenRequests();
        this.rawResponse = api.requestMoney(new CreateRequestDto(childId, amount));
        return this;
    }

    @Step("Request money and store request ID: childId={childId}, amount={amount}")
    public BankSteps requestMoneyAndStoreId(int childId, double amount) {
        waitBetweenRequests();
        this.rawResponse = api.requestMoney(new CreateRequestDto(childId, amount));
        System.out.println("REQUEST MONEY RESPONSE: " + rawResponse.getBody().asString());
        if (rawResponse.statusCode() == 200) {
            Integer id = rawResponse.jsonPath().get("id");
            if (id == null) id = rawResponse.jsonPath().get("requestId");
            assertThat("Created request ID must not be null", id, notNullValue());
            this.lastCreatedRequestId = id;
        }
        return this;
    }

    @Step("Get pending requests for parentId={parentId}")
    public BankSteps getPendingRequests(int parentId) {
        waitBetweenRequests();
        this.rawResponse = api.getPendingRequests(parentId);
        return this;
    }

    @Step("Approve request: requestId={requestId}")
    public BankSteps approveRequest(int requestId) {
        waitBetweenRequests();
        this.rawResponse = api.approveMoney(new ApprovalDto(requestId, true));
        return this;
    }

    @Step("Reject request: requestId={requestId}")
    public BankSteps rejectRequest(int requestId) {
        waitBetweenRequests();
        this.rawResponse = api.approveMoney(new ApprovalDto(requestId, false));
        return this;
    }

    @Step("Approve last created request")
    public BankSteps approveLastRequest() {
        waitBetweenRequests();
        this.rawResponse = api.approveMoney(new ApprovalDto(lastCreatedRequestId, true));
        return this;
    }

    @Step("Reject last created request")
    public BankSteps rejectLastRequest() {
        waitBetweenRequests();
        this.rawResponse = api.approveMoney(new ApprovalDto(lastCreatedRequestId, false));
        return this;
    }

    @Step("Validate status code is {expectedCode}")
    public BankSteps validateStatusCode(int expectedCode) {
        assertThat("Status code", rawResponse.statusCode(), equalTo(expectedCode));
        return this;
    }

    @Step("Validate Content-Type is application/json")
    public BankSteps validateContentTypeIsJson() {
        assertThat("Content-Type", rawResponse.contentType(), containsString("application/json"));
        return this;
    }

    @Step("Validate response time is under {maxMillis}ms")
    public BankSteps validateResponseTimeIsUnder(long maxMillis) {
        assertThat(
                String.format("Response time %dms exceeds limit of %dms", rawResponse.getTime(), maxMillis),
                rawResponse.getTime(), lessThan(maxMillis)
        );
        return this;
    }

    @Step("Validate response body is not empty")
    public BankSteps validateResponseBodyIsNotEmpty() {
        assertThat("Response body must not be empty",
                rawResponse.getBody().asString(), not(emptyString()));
        return this;
    }

    @Step("Validate error detail is not empty")
    public BankSteps validateErrorDetailIsNotEmpty() {
        String detail = rawResponse.jsonPath().getString("detail");
        assertThat("Error detail must not be null or empty", detail, not(emptyOrNullString()));
        return this;
    }
}