package ge.gitgud.testautomation.api.data.models.response.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizQuestionResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("question")
    private String question;

    @JsonProperty("options")
    private List<QuizOptionResponse> options;
}