package ge.gitgud.testautomation.api.data.models.request;

public record AnswerTaskRequestDto(int childId, int parentId, int taskId, int selectedOptionId) {}
