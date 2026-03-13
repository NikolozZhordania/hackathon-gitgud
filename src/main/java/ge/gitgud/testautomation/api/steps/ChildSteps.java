package ge.gitgud.testautomation.api.steps;

import ge.gitgud.testautomation.api.client.ChildAPI;
import ge.gitgud.testautomation.api.data.models.request.CreateChildDto;
import ge.gitgud.testautomation.api.data.models.request.UpdateChildDto;
import ge.gitgud.testautomation.api.data.models.response.child.ChildResponseDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static ge.gitgud.testautomation.api.client.BaseAPIClient.waitBetweenRequests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ChildSteps {

    private final ChildAPI api = new ChildAPI();
    private Response rawResponse;
    private int lastCreatedId;

    public int getLastCreatedId() {
        return lastCreatedId;
    }

    @Step("Get all children")
    public ChildSteps getAllChildren() {
        waitBetweenRequests();
        this.rawResponse = api.getAllChildren();
        return this;
    }

    @Step("Get child by id={id}")
    public ChildSteps getChildById(int id) {
        waitBetweenRequests();
        this.rawResponse = api.getChildById(id);
        return this;
    }

    @Step("Get child by username={username}")
    public ChildSteps getChildByUsername(String username) {
        waitBetweenRequests();
        this.rawResponse = api.getChildByUsername(username);
        return this;
    }

    @Step("Create child: username={username}")
    public ChildSteps createChild(String username, String firstname, String lastname, int parentId) {
        waitBetweenRequests();
        this.rawResponse = api.createChild(new CreateChildDto(username, firstname, lastname, parentId));
        if (rawResponse.statusCode() == 201) {
            Integer id = rawResponse.jsonPath().get("id");
            assertThat("Created child ID must not be null", id, notNullValue());
            this.lastCreatedId = id;
        }
        return this;
    }

    @Step("Create child with missing parent")
    public ChildSteps createChildWithMissingParent() {
        waitBetweenRequests();
        this.rawResponse = api.createChildWithMissingParent();
        return this;
    }

    @Step("Create child and get ID: username={username}")
    public int createChildAndGetId(String username, String firstname, String lastname, int parentId) {
        waitBetweenRequests();
        this.rawResponse = api.createChild(new CreateChildDto(username, firstname, lastname, parentId));
        Integer id = rawResponse.jsonPath().get("id");
        assertThat("Created child ID must not be null", id, notNullValue());
        this.lastCreatedId = id;
        return id;
    }

    @Step("Update child: id={id}")
    public ChildSteps updateChild(int id, String username, String firstname, String lastname, int parentId) {
        waitBetweenRequests();
        this.rawResponse = api.updateChild(id,
                new UpdateChildDto(id, username, firstname, lastname, parentId, 0.0));
        return this;
    }

    @Step("Update child with mismatched ID: pathId={pathId}")
    public ChildSteps updateChildWithMismatchedId(int pathId) {
        waitBetweenRequests();
        this.rawResponse = api.updateChildWithMismatchedId(pathId);
        return this;
    }

    @Step("Delete child: id={id}")
    public ChildSteps deleteChild(int id) {
        waitBetweenRequests();
        this.rawResponse = api.deleteChild(id);
        return this;
    }

    @Step("Validate status code is {expectedCode}")
    public ChildSteps validateStatusCode(int expectedCode) {
        assertThat("Status code", rawResponse.statusCode(), equalTo(expectedCode));
        return this;
    }

    @Step("Validate Content-Type is application/json")
    public ChildSteps validateContentTypeIsJson() {
        assertThat("Content-Type", rawResponse.contentType(), containsString("application/json"));
        return this;
    }

    @Step("Validate response time is under {maxMillis}ms")
    public ChildSteps validateResponseTimeIsUnder(long maxMillis) {
        assertThat(
                String.format("Response time %dms exceeds limit of %dms", rawResponse.getTime(), maxMillis),
                rawResponse.getTime(), lessThan(maxMillis));
        return this;
    }

    @Step("Validate response body is not empty")
    public ChildSteps validateResponseBodyIsNotEmpty() {
        assertThat("Response body must not be empty",
                rawResponse.getBody().asString(), not(emptyString()));
        return this;
    }

    @Step("Validate error detail is not empty")
    public ChildSteps validateErrorDetailIsNotEmpty() {
        String detail = rawResponse.jsonPath().getString("detail");
        assertThat("Error detail must not be null or empty", detail, not(emptyOrNullString()));
        return this;
    }

    @Step("Validate child ID is not null")
    public ChildSteps validateChildIdIsNotNull() {
        ChildResponseDto child = rawResponse.as(ChildResponseDto.class);
        assertThat("Child ID must not be null", child.getId(), notNullValue());
        return this;
    }

    @Step("Validate child ID matches expected={expectedId}")
    public ChildSteps validateChildIdEquals(int expectedId) {
        ChildResponseDto child = rawResponse.as(ChildResponseDto.class);
        assertThat("Child ID must match", child.getId(), equalTo(expectedId));
        return this;
    }

    @Step("Validate child username is not null or empty")
    public ChildSteps validateChildUsernameIsNotEmpty() {
        ChildResponseDto child = rawResponse.as(ChildResponseDto.class);
        assertThat("Child username must not be null or empty",
                child.getUsername(), not(emptyOrNullString()));
        return this;
    }

    @Step("Validate child username matches expected={expectedUsername}")
    public ChildSteps validateChildUsernameEquals(String expectedUsername) {
        ChildResponseDto child = rawResponse.as(ChildResponseDto.class);
        assertThat("Child username must match",
                child.getUsername(), equalTo(expectedUsername));
        return this;
    }

    @Step("Validate child firstname is not null or empty")
    public ChildSteps validateChildFirstnameIsNotEmpty() {
        ChildResponseDto child = rawResponse.as(ChildResponseDto.class);
        assertThat("Child firstname must not be null or empty",
                child.getFirstname(), not(emptyOrNullString()));
        return this;
    }

    @Step("Validate child lastname is not null or empty")
    public ChildSteps validateChildLastnameIsNotEmpty() {
        ChildResponseDto child = rawResponse.as(ChildResponseDto.class);
        assertThat("Child lastname must not be null or empty",
                child.getLastname(), not(emptyOrNullString()));
        return this;
    }

    @Step("Validate child parentId is not null")
    public ChildSteps validateChildParentIdIsNotNull() {
        ChildResponseDto child = rawResponse.as(ChildResponseDto.class);
        assertThat("Child parentId must not be null",
                child.getParentId(), notNullValue());
        return this;
    }

    @Step("Validate child parentId matches expected={expectedParentId}")
    public ChildSteps validateChildParentIdEquals(int expectedParentId) {
        ChildResponseDto child = rawResponse.as(ChildResponseDto.class);
        assertThat("Child parentId must match",
                child.getParentId(), equalTo(expectedParentId));
        return this;
    }

    @Step("Validate child balance is not null")
    public ChildSteps validateChildBalanceIsNotNull() {
        ChildResponseDto child = rawResponse.as(ChildResponseDto.class);
        assertThat("Child balance must not be null",
                child.getBalance(), notNullValue());
        return this;
    }

    @Step("Validate child balance is not negative")
    public ChildSteps validateChildBalanceIsNotNegative() {
        ChildResponseDto child = rawResponse.as(ChildResponseDto.class);
        assertThat("Child balance must not be negative",
                child.getBalance(), greaterThanOrEqualTo(0.0));
        return this;
    }

    @Step("Validate child bestStreak is not null")
    public ChildSteps validateChildBestStreakIsNotNull() {
        ChildResponseDto child = rawResponse.as(ChildResponseDto.class);
        assertThat("Child bestStreak must not be null",
                child.getBestStreak(), notNullValue());
        return this;
    }

    @Step("Validate child bestStreak is not negative")
    public ChildSteps validateChildBestStreakIsNotNegative() {
        ChildResponseDto child = rawResponse.as(ChildResponseDto.class);
        assertThat("Child bestStreak must not be negative",
                child.getBestStreak(), greaterThanOrEqualTo(0));
        return this;
    }

    @Step("Validate children list is not empty")
    public ChildSteps validateChildrenListIsNotEmpty() {
        List<ChildResponseDto> children = rawResponse.jsonPath()
                .getList(".", ChildResponseDto.class);
        assertThat("Children list must not be empty", children, not(empty()));
        return this;
    }

    @Step("Validate children list size is greater than zero")
    public ChildSteps validateChildrenListSizeIsGreaterThanZero() {
        List<ChildResponseDto> children = rawResponse.jsonPath()
                .getList(".", ChildResponseDto.class);
        assertThat("Children list size must be greater than zero",
                children.size(), greaterThan(0));
        return this;
    }

    @Step("Validate every child in list has non-null ID")
    public ChildSteps validateEveryChildHasId() {
        List<ChildResponseDto> children = rawResponse.jsonPath()
                .getList(".", ChildResponseDto.class);
        children.forEach(c ->
                assertThat("Each child must have a non-null ID", c.getId(), notNullValue()));
        return this;
    }

    @Step("Validate every child in list has non-empty username")
    public ChildSteps validateEveryChildHasUsername() {
        List<ChildResponseDto> children = rawResponse.jsonPath()
                .getList(".", ChildResponseDto.class);
        children.forEach(c ->
                assertThat("Each child must have a non-empty username",
                        c.getUsername(), not(emptyOrNullString())));
        return this;
    }
}