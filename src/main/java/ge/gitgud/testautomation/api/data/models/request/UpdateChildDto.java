package ge.gitgud.testautomation.api.data.models.request;

public record UpdateChildDto(int id, String username,
                             String firstname,
                             String lastname,
                             int parentId,
                             double balance) {}