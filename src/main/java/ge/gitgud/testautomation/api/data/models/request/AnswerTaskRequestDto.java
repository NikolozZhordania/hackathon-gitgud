package ge.gitgud.testautomation.api.data.models.request;

public record AnswerTaskRequestDto(int childId,
                                   int taskId,
                                   int selectedOptionId) {}
