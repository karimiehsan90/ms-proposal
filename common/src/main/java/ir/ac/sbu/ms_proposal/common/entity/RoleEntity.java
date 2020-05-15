package ir.ac.sbu.ms_proposal.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "roles")
@Getter
@Setter
public class RoleEntity {
    @Id
    private String id;
    private String name;
    private List<Permission> permissions;
}
