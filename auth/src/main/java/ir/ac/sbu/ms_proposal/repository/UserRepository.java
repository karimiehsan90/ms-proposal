package ir.ac.sbu.ms_proposal.repository;

import ir.ac.sbu.ms_proposal.common.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
    Optional<User> getByToken(String token);
    List<User> findAll();
}
