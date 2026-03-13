package ge.gitgud.testautomation.api.client;

import ge.gitgud.testautomation.api.data.constants.BankConstants;
import ge.gitgud.testautomation.api.data.models.request.CreateChildDto;
import ge.gitgud.testautomation.api.data.models.request.UpdateChildDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ChildAPI extends BaseAPIClient {

    public Response getAllChildren() {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .get(BankConstants.Paths.BASE + BankConstants.Endpoints.GET_ALL_CHILDREN);
    }

    public Response getChildById(int id) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .pathParam("id", id)
                .get(BankConstants.Paths.BASE + BankConstants.Endpoints.GET_CHILD_BY_ID);
    }

    public Response getChildByUsername(String username) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .pathParam("username", username)
                .get(BankConstants.Paths.BASE + BankConstants.Endpoints.GET_CHILD_BY_USERNAME);
    }

    public Response createChild(CreateChildDto dto) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .contentType(ContentType.JSON)
                .body(dto)
                .post(BankConstants.Paths.BASE + BankConstants.Endpoints.GET_ALL_CHILDREN);
    }

    public Response createChildWithMissingParent() {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .contentType(ContentType.JSON)
                .body("{\"username\": \"bad_child\"}")
                .post(BankConstants.Paths.BASE + BankConstants.Endpoints.GET_ALL_CHILDREN);
    }

    public Response updateChild(int id, UpdateChildDto dto) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(dto)
                .put(BankConstants.Paths.BASE + BankConstants.Endpoints.UPDATE_CHILD);
    }

    public Response updateChildWithMismatchedId(int pathId) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .contentType(ContentType.JSON)
                .pathParam("id", pathId)
                .body("{\"id\": 99999, \"username\": \"mismatch\"}")
                .put(BankConstants.Paths.BASE + BankConstants.Endpoints.UPDATE_CHILD);
    }

    public Response deleteChild(int id) {
        return given()
                .baseUri(BankConstants.URI.BASE)
                .pathParam("id", id)
                .delete(BankConstants.Paths.BASE + BankConstants.Endpoints.DELETE_CHILD);
    }
}