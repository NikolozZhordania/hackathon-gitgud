package ge.gitgud.testautomation.api.client;

import ge.gitgud.testautomation.api.data.constants.BankConstants;
import ge.gitgud.testautomation.api.data.models.request.CreateParentDto;
import ge.gitgud.testautomation.api.data.models.request.UpdateParentDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ParentAPI extends BaseAPIClient {

    public Response getAllParents() {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .get(BankConstants.Paths.BASE + BankConstants.Endpoints.GET_ALL_PARENTS);
    }

    public Response getParentById(int id) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .pathParam("id", id)
                .get(BankConstants.Paths.BASE + BankConstants.Endpoints.GET_PARENT_BY_ID);
    }

    public Response getParentByUsername(String username) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .pathParam("username", username)
                .get(BankConstants.Paths.BASE + BankConstants.Endpoints.GET_PARENT_BY_USERNAME);
    }

    public Response createParent(CreateParentDto dto) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .contentType(ContentType.JSON)
                .body(dto)
                .post(BankConstants.Paths.BASE + BankConstants.Endpoints.GET_ALL_PARENTS);
    }

    public Response createParentWithMissingFields() {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .contentType(ContentType.JSON)
                .body("{}")
                .post(BankConstants.Paths.BASE + BankConstants.Endpoints.GET_ALL_PARENTS);
    }

    public Response updateParent(int id, UpdateParentDto dto) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(dto)
                .put(BankConstants.Paths.BASE + BankConstants.Endpoints.UPDATE_PARENT);
    }

    public Response updateParentWithMismatchedId(int pathId) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .contentType(ContentType.JSON)
                .pathParam("id", pathId)
                .body("{\"id\": 99999, \"username\": \"mismatch\"}")
                .put(BankConstants.Paths.BASE + BankConstants.Endpoints.UPDATE_PARENT);
    }

    public Response deleteParent(int id) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .pathParam("id", id)
                .delete(BankConstants.Paths.BASE + BankConstants.Endpoints.DELETE_PARENT);
    }
}