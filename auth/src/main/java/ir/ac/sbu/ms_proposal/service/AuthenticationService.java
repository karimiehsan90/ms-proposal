package ir.ac.sbu.ms_proposal.service;

import ir.ac.sbu.ms_proposal.common.entity.User;
import ir.ac.sbu.ms_proposal.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationService {
    private final UserRepository repository;
    private final HashUtils hashUtils;

    public AuthenticationService(UserRepository repository, HashUtils hashUtils) {
        this.repository = repository;
        this.hashUtils = hashUtils;
    }

    public Optional<User> login(String username, String password) {
        return repository.findByUsernameAndPassword(username, hashUtils.hashPassword(password));
    }

    public Optional<User> getByToken(String token) {
        return repository.getByToken(token);
    }
}
