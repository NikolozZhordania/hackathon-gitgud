package ge.gitgud.testautomation.api.steps;

import ge.gitgud.testautomation.api.client.ParentAPI;
import ge.gitgud.testautomation.api.data.models.request.CreateParentDto;
import ge.gitgud.testautomation.api.data.models.request.UpdateParentDto;
import ge.gitgud.testautomation.api.data.models.response.parent.ParentResponseDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static ge.gitgud.testautomation.api.client.BaseAPIClient.waitBetweenRequests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ParentSteps {

    private final ParentAPI api = new ParentAPI();
    private Response rawResponse;
    private int lastCreatedId;

    public int getLastCreatedId() {
        return lastCreatedId;
    }

    @Step("Get all parents")
    public ParentSteps getAllParents() {
        waitBetweenRequests();
        this.rawResponse = api.getAllParents();
        return this;
    }

    @Step("Get parent by id={id}")
    public ParentSteps getParentById(int id) {
        waitBetweenRequests();
        this.rawResponse = api.getParentById(id);
        return this;
    }

    @Step("Get parent by username={username}")
    public ParentSteps getParentByUsername(String username) {
        waitBetweenRequests();
        this.rawResponse = api.getParentByUsername(username);
        return this;
    }

    @Step("Create parent: username={username}")
    public ParentSteps createParent(String username, String firstname, String lastname, double balance) {
        waitBetweenRequests();
        this.rawResponse = api.createParent(new CreateParentDto(username, firstname, lastname, balance));
        if (rawResponse.statusCode() == 201) {
            Integer id = rawResponse.jsonPath().get("id");
            assertThat("Created parent ID must not be null", id, notNullValue());
            this.lastCreatedId = id;
        }
        return this;
    }

    @Step("Create parent and get ID: username={username}")
    public int createParentAndGetId(String username, String firstname, String lastname, double balance) {
        waitBetweenRequests();
        this.rawResponse = api.createParent(new CreateParentDto(username, firstname, lastname, balance));
        Integer id = rawResponse.jsonPath().get("id");
        assertThat("Created parent ID must not be null", id, notNullValue());
        this.lastCreatedId = id;
        return id;
    }

    @Step("Create parent with missing fields")
    public ParentSteps createParentWithMissingFields() {
        waitBetweenRequests();
        this.rawResponse = api.createParentWithMissingFields();
        return this;
    }


    @Step("Update parent: id={id}")
    public ParentSteps updateParent(int id, String username, String firstname, String lastname, double balance) {
        waitBetweenRequests();
        this.rawResponse = api.updateParent(id,
                new UpdateParentDto(id, username, firstname, lastname, balance));
        return this;
    }

    @Step("Update parent with mismatched ID: pathId={pathId}")
    public ParentSteps updateParentWithMismatchedId(int pathId) {
        waitBetweenRequests();
        this.rawResponse = api.updateParentWithMismatchedId(pathId);
        return this;
    }

    @Step("Delete parent: id={id}")
    public ParentSteps deleteParent(int id) {
        waitBetweenRequests();
        this.rawResponse = api.deleteParent(id);
        return this;
    }

    @Step("Validate status code is {expectedCode}")
    public ParentSteps validateStatusCode(int expectedCode) {
        assertThat("Status code", rawResponse.statusCode(), equalTo(expectedCode));
        return this;
    }

    @Step("Validate Content-Type is application/json")
    public ParentSteps validateContentTypeIsJson() {
        assertThat("Content-Type", rawResponse.contentType(), containsString("application/json"));
        return this;
    }

    @Step("Validate response time is under {maxMillis}ms")
    public ParentSteps validateResponseTimeIsUnder(long maxMillis) {
        assertThat(
                String.format("Response time %dms exceeds limit of %dms", rawResponse.getTime(), maxMillis),
                rawResponse.getTime(), lessThan(maxMillis));
        return this;
    }

    @Step("Validate response body is not empty")
    public ParentSteps validateResponseBodyIsNotEmpty() {
        assertThat("Response body must not be empty",
                rawResponse.getBody().asString(), not(emptyString()));
        return this;
    }

    @Step("Validate error detail is not empty")
    public ParentSteps validateErrorDetailIsNotEmpty() {
        String detail = rawResponse.jsonPath().getString("detail");
        assertThat("Error detail must not be null or empty", detail, not(emptyOrNullString()));
        return this;
    }

    @Step("Validate parent ID is not null")
    public ParentSteps validateParentIdIsNotNull() {
        ParentResponseDto parent = rawResponse.as(ParentResponseDto.class);
        assertThat("Parent ID must not be null", parent.getId(), notNullValue());
        return this;
    }

    @Step("Validate parent ID matches expected={expectedId}")
    public ParentSteps validateParentIdEquals(int expectedId) {
        ParentResponseDto parent = rawResponse.as(ParentResponseDto.class);
        assertThat("Parent ID must match", parent.getId(), equalTo(expectedId));
        return this;
    }

    @Step("Validate parent username is not null or empty")
    public ParentSteps validateParentUsernameIsNotEmpty() {
        ParentResponseDto parent = rawResponse.as(ParentResponseDto.class);
        assertThat("Parent username must not be null or empty",
                parent.getUsername(), not(emptyOrNullString()));
        return this;
    }

    @Step("Validate parent username matches expected={expectedUsername}")
    public ParentSteps validateParentUsernameEquals(String expectedUsername) {
        ParentResponseDto parent = rawResponse.as(ParentResponseDto.class);
        assertThat("Parent username must match",
                parent.getUsername(), equalTo(expectedUsername));
        return this;
    }

    @Step("Validate parent firstname is not null or empty")
    public ParentSteps validateParentFirstnameIsNotEmpty() {
        ParentResponseDto parent = rawResponse.as(ParentResponseDto.class);
        assertThat("Parent firstname must not be null or empty",
                parent.getFirstname(), not(emptyOrNullString()));
        return this;
    }

    @Step("Validate parent lastname is not null or empty")
    public ParentSteps validateParentLastnameIsNotEmpty() {
        ParentResponseDto parent = rawResponse.as(ParentResponseDto.class);
        assertThat("Parent lastname must not be null or empty",
                parent.getLastname(), not(emptyOrNullString()));
        return this;
    }

    @Step("Validate parent balance is not null")
    public ParentSteps validateParentBalanceIsNotNull() {
        ParentResponseDto parent = rawResponse.as(ParentResponseDto.class);
        assertThat("Parent balance must not be null",
                parent.getBalance(), notNullValue());
        return this;
    }

    @Step("Validate parent balance is not negative")
    public ParentSteps validateParentBalanceIsNotNegative() {
        ParentResponseDto parent = rawResponse.as(ParentResponseDto.class);
        assertThat("Parent balance must not be negative",
                parent.getBalance(), greaterThanOrEqualTo(0.0));
        return this;
    }

    @Step("Validate parent children list is not null")
    public ParentSteps validateParentChildrenListIsNotNull() {
        ParentResponseDto parent = rawResponse.as(ParentResponseDto.class);
        assertThat("Parent children list must not be null",
                parent.getChildren(), notNullValue());
        return this;
    }

    @Step("Validate parents list is not empty")
    public ParentSteps validateParentsListIsNotEmpty() {
        List<ParentResponseDto> parents = rawResponse.jsonPath()
                .getList(".", ParentResponseDto.class);
        assertThat("Parents list must not be empty", parents, not(empty()));
        return this;
    }

    @Step("Validate parents list size is greater than zero")
    public ParentSteps validateParentsListSizeIsGreaterThanZero() {
        List<ParentResponseDto> parents = rawResponse.jsonPath()
                .getList(".", ParentResponseDto.class);
        assertThat("Parents list size must be greater than zero",
                parents.size(), greaterThan(0));
        return this;
    }

    @Step("Validate every parent in list has non-null ID")
    public ParentSteps validateEveryParentHasId() {
        List<ParentResponseDto> parents = rawResponse.jsonPath()
                .getList(".", ParentResponseDto.class);
        parents.forEach(p ->
                assertThat("Each parent must have a non-null ID", p.getId(), notNullValue()));
        return this;
    }

    @Step("Validate every parent in list has non-empty username")
    public ParentSteps validateEveryParentHasUsername() {
        List<ParentResponseDto> parents = rawResponse.jsonPath()
                .getList(".", ParentResponseDto.class);
        parents.forEach(p ->
                assertThat("Each parent must have a non-empty username",
                        p.getUsername(), not(emptyOrNullString())));
        return this;
    }
}