package ir.ac.sbu.ms_proposal.repository;

import ir.ac.sbu.ms_proposal.common.entity.Proposal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends MongoRepository<Proposal, String> {
}
