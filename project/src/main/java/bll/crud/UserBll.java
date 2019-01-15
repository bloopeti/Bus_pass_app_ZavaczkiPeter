package bll.crud;

import bll.PassExpirationNotifier;
import bll.dtos.CartDTO;
import bll.dtos.PassDTO;
import bll.dtos.PurchasedPassDTO;
import bll.dtos.UserDTO;
import bll.dtos.converters.UserConverter;
import bll.mailing.Mailer;
import bll.wrappers.IdWrapper;
import dal.entities.User;
import dal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ReportAsSingleViolation;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserBll {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartBll cartBll;
    @Autowired
    PurchasedPassBll purchasedPassBll;
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

    public UserDTO login(UserDTO user) {
        UserDTO dbUser = getUserByEmailAddress(user);
        if (!(dbUser == null)) {
            if (user.getPassword().equals(dbUser.getPassword())) {
                return dbUser;
            }
        }
        dbUser = new UserDTO();
        dbUser.setIsAdmin(-10);
        return dbUser;
    }

    public String register(UserDTO user) {
        UserDTO userToRegister = getUserByEmailAddress(user);
        if (userToRegister == null) {
            userToRegister = new UserDTO();
            userToRegister.setIsAdmin(0);
            userToRegister.setEmailAddress(user.getEmailAddress());
            userToRegister.setPassword(user.getPassword());
            addUser(user);
            CartDTO usersCart = new CartDTO();
            usersCart.setUserId(getUserByEmailAddress(user).getId());
            usersCart.setPasses(new ArrayList<PassDTO>());
            cartBll.addCart(usersCart);
            return "USER REGISTER SUCCESSFUL";
        }
        return "USER REGISTER FAILED: THIS EMAIL ADDRESS ALREADY EXISTS";
    }

    public void notifyAllUsers() {
        PassExpirationNotifier passExpirationNotifier = new PassExpirationNotifier(new Mailer("peter.zavaczki.tucn@gmail.com", "P4$$word"));
        List<UserDTO> users = getAllUsers();
        passExpirationNotifier.notifyList(users);
    }

    // Just an ID in the user field is necessary, every pass it has in its cart (if it exists) will be bought
    public String buyPassesInCart(UserDTO user) {
        String reason = "User doesn't exist";
        UserDTO dbUser = getUserById(user.getId());
        if (!(dbUser == null)) {
            reason = "Cart doesn't exist";
            CartDTO cart = dbUser.getCart();
            if (!(cart == null)) {
                List<PassDTO> passList = cart.getPasses();
                reason = "The cart is empty";
                if (!passList.isEmpty()) {
                    for (PassDTO passDTO : passList) {
                        PurchasedPassDTO purchasedPassDTO = new PurchasedPassDTO();
                        purchasedPassDTO.setUserId(dbUser.getId());
                        purchasedPassDTO.setPass(passDTO);
                        long thirtyOneDaysMillis = Long.parseLong("2678400000"); // one month
                        purchasedPassDTO.setExpirationDate(Long.toString(System.currentTimeMillis() + thirtyOneDaysMillis));
                        dbUser.getPurchasedPasses().add(purchasedPassDTO);
                        purchasedPassBll.addPurchasedPass(purchasedPassDTO);
                    }
                }
                cart.getPasses().clear();
                reason = cartBll.updateCart(cart);
                if (reason.contains("SUCCESSFUL")) {
                    reason = updateUser(dbUser);
                    if (reason.contains("SUCCESSFUL")) {
                        return "PASSES PURCHASED SUCCESSFULLY FOR THIS USER";
                    }
                }
            }
        }

        return "PASS PURCHASE FAILED: " + reason;
    }
}
