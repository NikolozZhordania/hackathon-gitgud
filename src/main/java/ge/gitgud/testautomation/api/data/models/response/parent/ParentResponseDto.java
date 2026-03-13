package ge.gitgud.testautomation.api.data.models.response.parent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ge.gitgud.testautomation.api.data.models.response.child.ChildResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParentResponseDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("children")
    private List<ChildResponseDto> children;
}
