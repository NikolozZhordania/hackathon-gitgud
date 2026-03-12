package ge.gitgud.testautomation.api.steps;

import ge.gitgud.testautomation.api.client.QuizAPI;
import ge.gitgud.testautomation.api.data.models.request.AnswerTaskRequestDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static ge.gitgud.testautomation.api.client.BaseAPIClient.waitBetweenRequests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class QuizSteps {

    private final QuizAPI api = new QuizAPI();
    private Response rawResponse;

    @Step("Start quiz for childId={childId}")
    public QuizSteps startQuiz(int childId) {
        waitBetweenRequests();
        this.rawResponse = api.startQuiz(childId);
        return this;
    }

    @Step("Submit answer: childId={childId}, parentId={parentId}, taskId={taskId}, optionId={selectedOptionId}")
    public QuizSteps submitAnswer(int childId, int parentId, int taskId, int selectedOptionId) {
        waitBetweenRequests();
        this.rawResponse = api.submitAnswer(
                new AnswerTaskRequestDto(childId, parentId, taskId, selectedOptionId));
        return this;
    }

    @Step("Validate status code is {expectedCode}")
    public QuizSteps validateStatusCode(int expectedCode) {
        assertThat("Status code", rawResponse.statusCode(), equalTo(expectedCode));
        return this;
    }

    @Step("Validate Content-Type is application/json")
    public QuizSteps validateContentTypeIsJson() {
        assertThat("Content-Type", rawResponse.contentType(), containsString("application/json"));
        return this;
    }

    @Step("Validate response time is under {maxMillis}ms")
    public QuizSteps validateResponseTimeIsUnder(long maxMillis) {
        assertThat(
                String.format("Response time %dms exceeds limit of %dms", rawResponse.getTime(), maxMillis),
                rawResponse.getTime(), lessThan(maxMillis)
        );
        return this;
    }

    @Step("Validate response body is not empty")
    public QuizSteps validateResponseBodyIsNotEmpty() {
        assertThat("Response body must not be empty",
                rawResponse.getBody().asString(), not(emptyString()));
        return this;
    }

    @Step("Validate error detail is not empty")
    public QuizSteps validateErrorDetailIsNotEmpty() {
        String detail = rawResponse.jsonPath().getString("detail");
        assertThat("Error detail must not be null or empty", detail, not(emptyOrNullString()));
        return this;
    }

    @Step("Submit answer with missing fields: childId={childId}")
    public QuizSteps submitAnswerWithMissingFields(int childId) {
        waitBetweenRequests();
        this.rawResponse = api.submitAnswerWithMissingFields(childId);
        return this;
    }
}