package ir.ac.sbu.ms_proposal.repository;

import ir.ac.sbu.ms_proposal.common.entity.Proposal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends MongoRepository<Proposal, String> {
    List<Proposal> getByTeacherIdNumber(String teacherIdNumber);
}
