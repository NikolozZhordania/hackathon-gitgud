package ge.gitgud.testautomation.api.tests;

import ge.gitgud.testautomation.api.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static ge.gitgud.testautomation.api.data.constants.BankConstants.TestData.*;

@Feature("Child CRUD Flow")
public class ChildCrudFlowTests extends BaseTest {

    @Story("Child Lifecycle")
    @Description("POST /api/Child")
    @Test(description = "Create Child", priority = 1)
    public void createChild() {
        childSteps
                .createChild("newchild", "New", "Child", VALID_PARENT_ID)
                .validateStatusCode(201)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000)
                .validateChildIdIsNotNull()
                .validateChildUsernameIsNotEmpty()
                .validateChildParentIdIsNotNull();
    }

    @Story("Child Lifecycle")
    @Description("GET /api/Child/by-id/{id}")
    @Test(description = "Get Created Child by ID", priority = 2,
            dependsOnMethods = "createChild")
    public void getCreatedChildById() {
        int createdId = childSteps.getLastCreatedId();
        childSteps
                .getChildById(createdId)
                .validateStatusCode(200)
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000)
                .validateChildIdEquals(createdId)
                .validateChildUsernameEquals("newchild")
                .validateChildParentIdEquals(VALID_PARENT_ID);
    }

    @Story("Child Lifecycle")
    @Description("GET /api/Child/{username}")
    @Test(description = "Get Created Child by Username", priority = 3,
            dependsOnMethods = "createChild")
    public void getCreatedChildByUsername() {
        childSteps
                .getChildByUsername("newchild")
                .validateStatusCode(200)
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000)
                .validateChildUsernameEquals("newchild")
                .validateChildParentIdEquals(VALID_PARENT_ID);
    }

    @Story("Child Lifecycle")
    @Description("PUT /api/Child/{id}")
    @Test(description = "Update Created Child", priority = 4,
            dependsOnMethods = "getCreatedChildById")
    public void updateCreatedChild() {
        int createdId = childSteps.getLastCreatedId();
        childSteps
                .updateChild(createdId, "newchild", "Updated", "Child", VALID_PARENT_ID)
                .validateStatusCode(200)
                .validateResponseBodyIsNotEmpty()
                .validateContentTypeIsJson()
                .validateResponseTimeIsUnder(3000)
                .validateChildFirstnameIsNotEmpty()
                .validateChildLastnameIsNotEmpty();
    }

    @Story("Child Lifecycle")
    @Description("DELETE /api/Child/{id}")
    @Test(description = "Delete Created Child", priority = 5,
            dependsOnMethods = "updateCreatedChild")
    public void deleteCreatedChild() {
        int createdId = childSteps.getLastCreatedId();
        childSteps
                .deleteChild(createdId)
                .validateStatusCode(204)
                .validateResponseTimeIsUnder(3000);
    }

    @Story("Child Lifecycle")
    @Description("GET /api/Child/by-id/{id}")
    @Test(description = "Verify Child No Longer Exists", priority = 6,
            dependsOnMethods = "deleteCreatedChild")
    public void verifyChildNoLongerExists() {
        int createdId = childSteps.getLastCreatedId();
        childSteps
                .getChildById(createdId)
                .validateStatusCode(404)
                .validateErrorDetailIsNotEmpty()
                .validateResponseTimeIsUnder(3000);
    }
}