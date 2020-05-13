package ir.ac.sbu.ms_proposal.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
public class User {
    @Id
    private String token;
    private String username;
    @JsonIgnore
    private String password;
    private Role role;
}
