package ge.gitgud.testautomation.api.data.models.response.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoneyRequestResponse {

    @JsonProperty("requestId")
    private Integer requestId;

    @JsonProperty("childId")
    private Integer childId;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("status")
    private String status;
}
