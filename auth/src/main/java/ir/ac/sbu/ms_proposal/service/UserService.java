package ir.ac.sbu.ms_proposal.service;

import ir.ac.sbu.ms_proposal.common.entity.User;
import ir.ac.sbu.ms_proposal.common.entity.request.UserRequestEntity;
import ir.ac.sbu.ms_proposal.common.response.ActionResult;
import ir.ac.sbu.ms_proposal.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private final UserRepository userRepository;
    private final HashUtils hashUtils;

    public UserService(UserRepository userRepository, HashUtils hashUtils) {
        this.userRepository = userRepository;
        this.hashUtils = hashUtils;
    }

    public ActionResult<Boolean> addUser(UserRequestEntity userRequestEntity) {
        Optional<User> userOptional = userRepository.findByUsername(userRequestEntity.getUsername());
        if (userOptional.isPresent()) {
            return new ActionResult<>(false,"کاربر وجود دارد", false);
        }
        User user = new User();
        user.setUsername(userRequestEntity.getUsername());
        user.setPassword(hashUtils.hashPassword(userRequestEntity.getPassword()));
        user.setField(userRequestEntity.getField());
        user.setName(userRequestEntity.getName());
        user.setIdentificationNumber(userRequestEntity.getIdentificationNumber());
        user.setRole(userRequestEntity.getRole());
        userRepository.save(user);
        return new ActionResult<>(true, "با موفقیت افزوده شد", true);
    }

    public ActionResult<List<User>> getUsers(){
        List<User> users = userRepository.findAll();
        return new ActionResult<>(true, "", users);
    }
}
