package ge.gitgud.testautomation.api.data.models.response.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizOptionResponse {

    @JsonProperty("optionId")
    private Integer optionId;

    @JsonProperty("text")
    private String text;
}
