package ir.ac.sbu.ms_proposal.common.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.ac.sbu.ms_proposal.common.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestEntity {
    private String username;
    private String password;
    private Role role;
    @JsonProperty("id_number")
    private String identificationNumber;
    private String field;
    private String name;
}
