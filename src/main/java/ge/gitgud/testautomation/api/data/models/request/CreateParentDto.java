package ge.gitgud.testautomation.api.data.models.request;

public record CreateParentDto(String username,
                              String firstname,
                              String lastname,
                              double balance) {}