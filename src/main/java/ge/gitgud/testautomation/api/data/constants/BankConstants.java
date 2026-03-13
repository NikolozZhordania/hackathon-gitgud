package ge.gitgud.testautomation.api.data.constants;

public class BankConstants {
    public static class URI {
        public static final String BASE = "https://gitgood.cypherbloom.com";
    }

    public static class Paths {
        public static final String BASE = "/api";
    }

    public static class Endpoints {
        public static final String GET_PENDING_REQUESTS = "/bank/parent/{parentId}/requests";
        public static final String APPROVE                = "/bank/approve";
        public static final String REQUEST_MONEY          = "/bank/request-money";
        public static final String START_QUIZ             = "/Quiz/start/{childId}";
        public static final String ANSWER_QUIZ            = "/Quiz/answer";
        public static final String GET_STREAK        = "/Quiz/{childId}/streak";
        public static final String GET_ALL_CHILDREN  = "/Child";
        public static final String GET_CHILD_BY_ID   = "/Child/by-id/{id}";
        public static final String GET_CHILD_BY_USERNAME = "/Child/{username}";
        public static final String UPDATE_CHILD      = "/Child/{id}";
        public static final String DELETE_CHILD      = "/Child/{id}";
        public static final String GET_ALL_PARENTS   = "/Parent";
        public static final String GET_PARENT_BY_ID  = "/Parent/by-id/{id}";
        public static final String GET_PARENT_BY_USERNAME = "/Parent/{username}";
        public static final String UPDATE_PARENT     = "/Parent/{id}";
        public static final String DELETE_PARENT     = "/Parent/{id}";
    }

    public static class Params {
        public static final String PARENT_ID = "parentId";
        public static final String CHILD_ID  = "childId";
    }

    public static class TestData {
        public static final int    VALID_PARENT_ID   = 1;
        public static final int    VALID_CHILD_ID    = 1;
        public static final int    INVALID_ID        = 999999;
        public static final double VALID_AMOUNT      = 5.0;
        public static final double INVALID_AMOUNT    = -100.0;
        public static final int    VALID_TASK_ID     = 1;
        public static final int    VALID_OPTION_ID   = 3;
        public static final int    INVALID_TASK_ID   = 9999;
        public static final int VALID_CHILD_ID_2 = 2;
        public static final int VALID_CHILD_ID_3 = 3;
        public static final String VALID_CHILD_USERNAME   = "usrnm1";
        public static final String VALID_PARENT_USERNAME  = "DemoParent123";
        public static final String INVALID_USERNAME       = "nonexistent_user_xyz";
        public static final int    VALID_PARENT_ID_2      = 2;
    }
}
