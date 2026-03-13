package ge.gitgud.testautomation.api.tests;

import ge.gitgud.testautomation.api.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static ge.gitgud.testautomation.api.data.constants.BankConstants.TestData.*;

@Feature("Parent CRUD Flow")
public class ParentCrudFlowTests extends BaseTest {

    @Story("Parent Lifecycle")
    @Description("POST /api/Parent")
    @Test(description = "Create Parent", priority = 1)
    public void createParent() {
        parentSteps
                .createParent("newparent", "New", "Parent", 500.0)
                .validateStatusCode(201)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000)
                .validateParentIdIsNotNull()
                .validateParentUsernameIsNotEmpty()
                .validateParentBalanceIsNotNegative();
    }

    @Story("Parent Lifecycle")
    @Description("GET /api/Parent/by-id/{id}")
    @Test(description = "Get Created Parent by ID", priority = 2,
            dependsOnMethods = "createParent")
    public void getCreatedParentById() {
        int createdId = parentSteps.getLastCreatedId();
        parentSteps
                .getParentById(createdId)
                .validateStatusCode(200)
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000)
                .validateParentIdEquals(createdId)
                .validateParentUsernameEquals("newparent")
                .validateParentBalanceIsNotNegative();
    }

    @Story("Parent Lifecycle")
    @Description("GET /api/Parent/{username}")
    @Test(description = "Get Created Parent by Username", priority = 3,
            dependsOnMethods = "createParent")
    public void getCreatedParentByUsername() {
        parentSteps
                .getParentByUsername("newparent")
                .validateStatusCode(200)
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000)
                .validateParentUsernameEquals("newparent")
                .validateParentBalanceIsNotNegative();
    }

    @Story("Parent Lifecycle")
    @Description("PUT /api/Parent/{id}")
    @Test(description = "Update Created Parent", priority = 4,
            dependsOnMethods = "getCreatedParentById")
    public void updateCreatedParent() {
        int createdId = parentSteps.getLastCreatedId();
        parentSteps
                .updateParent(createdId, "newparent", "Updated", "Parent", 1000.0)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000)
                .validateParentFirstnameIsNotEmpty()
                .validateParentLastnameIsNotEmpty()
                .validateParentBalanceIsNotNegative();
    }

    @Story("Parent Lifecycle")
    @Description("DELETE /api/Parent/{id}")
    @Test(description = "Delete Created Parent", priority = 5,
            dependsOnMethods = "updateCreatedParent")
    public void deleteCreatedParent() {
        int createdId = parentSteps.getLastCreatedId();
        parentSteps
                .deleteParent(createdId)
                .validateStatusCode(204)
                .validateResponseTimeIsUnder(3000);
    }

    @Story("Parent Lifecycle")
    @Description("GET /api/Parent/by-id/{id}")
    @Test(description = "Verify Parent No Longer Exists", priority = 6,
            dependsOnMethods = "deleteCreatedParent")
    public void verifyParentNoLongerExists() {
        int createdId = parentSteps.getLastCreatedId();
        parentSteps
                .getParentById(createdId)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty()
                .validateResponseTimeIsUnder(3000);
    }
}