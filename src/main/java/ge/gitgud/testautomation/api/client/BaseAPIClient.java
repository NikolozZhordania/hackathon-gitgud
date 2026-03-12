package ge.gitgud.testautomation.api.client;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseAPIClient {
    static {
        RestAssured.filters(new AllureRestAssured());
    }

    public static void waitBetweenRequests() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
