package ge.gitgud.testautomation.api.data.models.request;

public record CreateChildDto(String username,
                             String firstname,
                             String lastname,
                             int parentId) {}