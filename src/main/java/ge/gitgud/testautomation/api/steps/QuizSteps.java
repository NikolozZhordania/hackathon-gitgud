package ge.gitgud.testautomation.api.steps;

import ge.gitgud.testautomation.api.client.QuizAPI;
import ge.gitgud.testautomation.api.data.models.request.AnswerTaskRequestDto;
import ge.gitgud.testautomation.api.data.models.response.quiz.AnswerResponse;
import ge.gitgud.testautomation.api.data.models.response.quiz.StreakResponseDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static ge.gitgud.testautomation.api.client.BaseAPIClient.waitBetweenRequests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class QuizSteps {

    private final QuizAPI api = new QuizAPI();
    private Response rawResponse;

    @Step("Get streak for childId={childId}")
    public QuizSteps getStreak(int childId) {
        waitBetweenRequests();
        this.rawResponse = api.getStreak(childId);
        return this;
    }

    @Step("Start quiz for childId={childId}")
    public QuizSteps startQuiz(int childId) {
        waitBetweenRequests();
        this.rawResponse = api.startQuiz(childId);
        return this;
    }

    @Step("Submit answer: childId={childId}, taskId={taskId}, optionId={selectedOptionId}")
    public QuizSteps submitAnswer(int childId, int taskId, int selectedOptionId) {
        waitBetweenRequests();
        this.rawResponse = api.submitAnswer(
                new AnswerTaskRequestDto(childId, taskId, selectedOptionId));
        return this;
    }

    @Step("Submit answer with missing fields: childId={childId}")
    public QuizSteps submitAnswerWithMissingFields(int childId) {
        waitBetweenRequests();
        this.rawResponse = api.submitAnswerWithMissingFields(childId);
        return this;
    }

    @Step("Print raw response body")
    public QuizSteps printRawResponse() {
        System.out.println("RAW RESPONSE: " + rawResponse.getBody().asString());
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
                rawResponse.getTime(), lessThan(maxMillis));
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


    @Step("Validate answer correct field is not null")
    public QuizSteps validateAnswerCorrectFieldIsNotNull() {
        AnswerResponse answer = rawResponse.as(AnswerResponse.class);
        assertThat("correct field must not be null",
                answer.getCorrect(), notNullValue());
        return this;
    }

    @Step("Validate answer is correct")
    public QuizSteps validateAnswerIsCorrect() {
        AnswerResponse answer = rawResponse.as(AnswerResponse.class);
        assertThat("Answer must be correct",
                answer.getCorrect(), equalTo(true));
        return this;
    }

    @Step("Validate answer is incorrect")
    public QuizSteps validateAnswerIsIncorrect() {
        AnswerResponse answer = rawResponse.as(AnswerResponse.class);
        assertThat("Answer must be incorrect",
                answer.getCorrect(), equalTo(false));
        return this;
    }

    @Step("Validate quizCompleted field is not null")
    public QuizSteps validateQuizCompletedIsNotNull() {
        AnswerResponse answer = rawResponse.as(AnswerResponse.class);
        assertThat("quizCompleted field must not be null",
                answer.getQuizCompleted(), notNullValue());
        return this;
    }

    @Step("Validate quiz is not yet completed")
    public QuizSteps validateQuizIsNotCompleted() {
        AnswerResponse answer = rawResponse.as(AnswerResponse.class);
        assertThat("Quiz must not be completed yet",
                answer.getQuizCompleted(), equalTo(false));
        return this;
    }

    @Step("Validate quiz is completed")
    public QuizSteps validateQuizIsCompleted() {
        AnswerResponse answer = rawResponse.as(AnswerResponse.class);
        assertThat("Quiz must be completed",
                answer.getQuizCompleted(), equalTo(true));
        return this;
    }

    @Step("Validate next question is not null")
    public QuizSteps validateNextQuestionIsNotNull() {
        AnswerResponse answer = rawResponse.as(AnswerResponse.class);
        assertThat("Next question must not be null",
                answer.getNextQuestion(), notNullValue());
        return this;
    }

    @Step("Validate next question ID is not null")
    public QuizSteps validateNextQuestionIdIsNotNull() {
        AnswerResponse answer = rawResponse.as(AnswerResponse.class);
        assertThat("Next question ID must not be null",
                answer.getNextQuestion().getId(), notNullValue());
        return this;
    }

    @Step("Validate next question text is not empty")
    public QuizSteps validateNextQuestionTextIsNotEmpty() {
        AnswerResponse answer = rawResponse.as(AnswerResponse.class);
        assertThat("Next question text must not be empty",
                answer.getNextQuestion().getQuestion(), not(emptyOrNullString()));
        return this;
    }

    @Step("Validate next question has options")
    public QuizSteps validateNextQuestionHasOptions() {
        AnswerResponse answer = rawResponse.as(AnswerResponse.class);
        assertThat("Next question must have options",
                answer.getNextQuestion().getOptions(), not(empty()));
        return this;
    }


    @Step("Validate streak childId is not null")
    public QuizSteps validateStreakChildIdIsNotNull() {
        StreakResponseDto streak = rawResponse.as(StreakResponseDto.class);
        assertThat("Streak childId must not be null",
                streak.getChildId(), notNullValue());
        return this;
    }

    @Step("Validate streak childId matches expected={expectedChildId}")
    public QuizSteps validateStreakChildIdEquals(int expectedChildId) {
        StreakResponseDto streak = rawResponse.as(StreakResponseDto.class);
        assertThat("Streak childId must match",
                streak.getChildId(), equalTo(expectedChildId));
        return this;
    }

    @Step("Validate currentStreak is not null")
    public QuizSteps validateCurrentStreakIsNotNull() {
        StreakResponseDto streak = rawResponse.as(StreakResponseDto.class);
        assertThat("CurrentStreak must not be null",
                streak.getCurrentStreak(), notNullValue());
        return this;
    }

    @Step("Validate currentStreak is not negative")
    public QuizSteps validateCurrentStreakIsNotNegative() {
        StreakResponseDto streak = rawResponse.as(StreakResponseDto.class);
        assertThat("CurrentStreak must not be negative",
                streak.getCurrentStreak(), greaterThanOrEqualTo(0));
        return this;
    }

    @Step("Validate bestStreak is not null")
    public QuizSteps validateBestStreakIsNotNull() {
        StreakResponseDto streak = rawResponse.as(StreakResponseDto.class);
        assertThat("BestStreak must not be null",
                streak.getBestStreak(), notNullValue());
        return this;
    }

    @Step("Validate bestStreak is not negative")
    public QuizSteps validateBestStreakIsNotNegative() {
        StreakResponseDto streak = rawResponse.as(StreakResponseDto.class);
        assertThat("BestStreak must not be negative",
                streak.getBestStreak(), greaterThanOrEqualTo(0));
        return this;
    }

    @Step("Validate bestStreak is greater than or equal to currentStreak")
    public QuizSteps validateBestStreakIsGreaterThanOrEqualToCurrentStreak() {
        StreakResponseDto streak = rawResponse.as(StreakResponseDto.class);
        assertThat("BestStreak must be >= currentStreak",
                streak.getBestStreak(), greaterThanOrEqualTo(streak.getCurrentStreak()));
        return this;
    }

    @Step("Complete full quiz for childId={childId}")
    public QuizSteps completeFullQuiz(int childId) {
        submitAnswer(childId, 1, 3);
        submitAnswer(childId, 2, 6);
        submitAnswer(childId, 3, 10);
        submitAnswer(childId, 4, 13);
        submitAnswer(childId, 5, 17);
        return this;
    }

    public int getRawStatusCode() {
        return rawResponse.statusCode();
    }
}