package ir.ac.sbu.ms_proposal.repository;

import ir.ac.sbu.ms_proposal.common.entity.Proposal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProposalRepository extends MongoRepository<Proposal, String> {
    List<Proposal> getByTeacherIdNumber(String teacherIdNumber);

    Optional<Proposal> getByTeacherIdNumberAndId(String teacherIdNumber, String proposalId);

    Optional<Proposal> getById(String proposalId);
}
