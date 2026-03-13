package ge.gitgud.testautomation.api.data.models.response.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerResponse {

    @JsonProperty("correct")
    private Boolean correct;

    @JsonProperty("quizCompleted")
    private Boolean quizCompleted;

    @JsonProperty("nextQuestion")
    private QuizQuestionResponse nextQuestion;
}