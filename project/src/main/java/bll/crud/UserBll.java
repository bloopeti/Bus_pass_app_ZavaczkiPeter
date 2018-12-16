package bll.crud;

import bll.PassExpirationNotifier;
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        if (userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();
        else
            return null;
    }

    public User getUserByEmailAddress(User user) {
        if (userRepository.findByEmailAddress(user.getEmailAddress()).isPresent())
            return userRepository.findByEmailAddress(user.getEmailAddress()).get();
        else
            return null;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public String updateUser(User user) {
        User updatedUser;
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

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}
