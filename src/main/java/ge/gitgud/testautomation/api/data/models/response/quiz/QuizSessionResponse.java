package ge.gitgud.testautomation.api.data.models.response.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizSessionResponse {

    @JsonProperty("sessionId")
    private Integer sessionId;

    @JsonProperty("childId")
    private Integer childId;

    @JsonProperty("taskId")
    private Integer taskId;

    @JsonProperty("question")
    private String question;

    @JsonProperty("options")
    private List<QuizOptionResponse> options;
}
