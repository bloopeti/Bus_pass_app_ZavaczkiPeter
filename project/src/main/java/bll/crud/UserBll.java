package bll.crud;

import bll.PassExpirationNotifier;
import bll.dtos.UserDTO;
import bll.dtos.converters.UserConverter;
import bll.mailing.Mailer;
import dal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dal.repositories.UserRepository;

import java.util.List;

@Service
public class UserBll {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserConverter converter;// = new UserConverter();

    public List<UserDTO> getAllUsers() {
        return converter.entityListToDtoList(userRepository.findAll());
    }

    public UserDTO getUserById(int id) {
        if (userRepository.findById(id).isPresent())
            return converter.entityToDto(userRepository.findById(id).get());
        else
            return null;
    }

    public UserDTO getUserByEmailAddress(UserDTO user) {
        if (userRepository.findByEmailAddress(user.getEmailAddress()).isPresent())
            return converter.entityToDto(userRepository.findByEmailAddress(user.getEmailAddress()).get());
        else
            return null;
    }

    public String addUser(UserDTO user) {
        userRepository.save(converter.dtoToEntity(user));
        return "USER ADD SUCCESSFUL";
    }

    public String updateUser(UserDTO user) {
        User updatedUser;
        String reason = "User";
        if (userRepository.findById(user.getId()).isPresent()) {
            updatedUser = userRepository.findById(user.getId()).get();
            updatedUser.setEmailAddress(user.getEmailAddress());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setIsAdmin(user.getIsAdmin());
            userRepository.save(updatedUser);
            return "USER UPDATE SUCCESSFUL";
        } else
            return "USER UPDATE FAILED";
    }

    public String deleteUser(int userId) {

        String reason = "User";
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return "User DELETE SUCCESSFUL";
        }
        return "USER DELETE FAILED: " + reason + " with this ID doesn't exist";
    }

    public int login(UserDTO user) {
        UserDTO dbUser = getUserByEmailAddress(user);
        if (!(dbUser == null))
            if (user.getPassword().equals(dbUser.getPassword()))
                return dbUser.getIsAdmin();
        return -10;
    }
    public void notifyAllUsers() {
        PassExpirationNotifier passExpirationNotifier = new PassExpirationNotifier(new Mailer("peter.zavaczki.tucn@gmail.com", "P4$$word"));
        List<UserDTO> users = getAllUsers();
        passExpirationNotifier.notifyList(users);
    }
}
