package ir.ac.sbu.ms_proposal.service;

import ir.ac.sbu.ms_proposal.common.entity.Permission;
import ir.ac.sbu.ms_proposal.common.entity.RoleEntity;
import ir.ac.sbu.ms_proposal.common.entity.User;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.repository.RoleRepository;
import ir.ac.sbu.ms_proposal.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorizationService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public AuthorizationService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public ActionResult<Boolean> hasPermission(String token, Permission permission) {
        Optional<User> userOptional = userRepository.getByToken(token);
        if (userOptional.isPresent()) {
            Optional<RoleEntity> roleOptional = roleRepository
                    .findByNameAndPermissionsContains(userOptional.get().getRole().name(), permission);
            return new ActionResult<>(roleOptional.isPresent(), null, roleOptional.isPresent());
        }
        return new ActionResult<>(false, null, false);
    }
}
