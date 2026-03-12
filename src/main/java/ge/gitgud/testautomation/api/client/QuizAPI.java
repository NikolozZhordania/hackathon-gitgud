package ge.gitgud.testautomation.api.client;

import ge.gitgud.testautomation.api.data.constants.BankConstants;
import ge.gitgud.testautomation.api.data.models.request.AnswerTaskRequestDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class QuizAPI extends BaseAPIClient {

    public Response startQuiz(int childId) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .pathParam(BankConstants.Params.CHILD_ID, childId)
                .post(BankConstants.Paths.BASE + BankConstants.Endpoints.START_QUIZ);
    }

    public Response submitAnswer(AnswerTaskRequestDto dto) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .contentType(ContentType.JSON)
                .body(dto)
                .post(BankConstants.Paths.BASE + BankConstants.Endpoints.ANSWER_QUIZ);
    }

    public Response submitAnswerWithMissingFields(int childId) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .contentType(ContentType.JSON)
                .body("{\"childId\": " + childId + "}")
                .post(BankConstants.Paths.BASE + BankConstants.Endpoints.ANSWER_QUIZ);
    }
}
