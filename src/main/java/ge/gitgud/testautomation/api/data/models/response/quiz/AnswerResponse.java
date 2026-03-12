package ge.gitgud.testautomation.api.data.models.response.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerResponse {

    @JsonProperty("isCorrect")
    private Boolean isCorrect;

    @JsonProperty("message")
    private String message;

    @JsonProperty("rewardAmount")
    private Integer rewardAmount;
}
