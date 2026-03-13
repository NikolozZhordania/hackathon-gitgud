package ge.gitgud.testautomation.api.data.models.response.child;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChildResponseDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("bestStreak")
    private Integer bestStreak;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("parentId")
    private Integer parentId;
}
