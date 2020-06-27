package ir.ac.sbu.ms_proposal.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "proposal")
@Getter
@Setter
public class Proposal {
    @Id
    private String id;
    @Field("passed_credits")
    @JsonProperty("passed_credits")
    private int passedCredits;
    @Field("teacher_id_num")
    @JsonProperty("teacher_id_num")
    private String teacherIdNumber;
    private String title;
    private String description;
    private List<String> keywords;
    private String term;
    @Field("start_time")
    @JsonProperty("start_time")
    private Long startTime;
    @Field("submit_time")
    @JsonProperty("submit_time")
    private Long submitTime;
    @Field("end_time")
    @JsonProperty("end_time")
    private Long endTime;
    @Field("specific_facilities")
    @JsonProperty("specific_facilities")
    private String specificFacilities;
    @Field("owner_id")
    @JsonProperty("owner_id")
    private String ownerId;
    @Field("state")
    @JsonProperty("state")
    private ProposalState proposalState = ProposalState.STUDENT_SUBMITTED;
}
