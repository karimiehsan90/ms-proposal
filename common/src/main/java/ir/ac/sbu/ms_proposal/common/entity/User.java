package ir.ac.sbu.ms_proposal.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    @Field("national_id")
    @JsonProperty("id_number")
    private String identificationNumber;
    private String field;
    private String name;
}
