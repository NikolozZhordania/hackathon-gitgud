package ge.gitgud.testautomation.api.client;

import ge.gitgud.testautomation.api.data.constants.BankConstants;
import ge.gitgud.testautomation.api.data.models.request.ApprovalDto;
import ge.gitgud.testautomation.api.data.models.request.CreateRequestDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BankAPI extends BaseAPIClient {

    public Response getPendingRequests(int parentId) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .pathParam(BankConstants.Params.PARENT_ID, parentId)
                .get(BankConstants.Paths.BASE + BankConstants.Endpoints.GET_PENDING_REQUESTS);
    }

    public Response approveMoney(ApprovalDto dto) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .contentType(ContentType.JSON)
                .body(dto)
                .post(BankConstants.Paths.BASE + BankConstants.Endpoints.APPROVE);
    }

    public Response requestMoney(CreateRequestDto dto) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .contentType(ContentType.JSON)
                .body(dto)
                .post(BankConstants.Paths.BASE + BankConstants.Endpoints.REQUEST_MONEY);
    }
}
