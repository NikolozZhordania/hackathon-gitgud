package ge.gitgud.testautomation.api.data.models.request;

public record UpdateParentDto(int id,
                              String username,
                              String firstname,
                              String lastname,
                              double balance) {}