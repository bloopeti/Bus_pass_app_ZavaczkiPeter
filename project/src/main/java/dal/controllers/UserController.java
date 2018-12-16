package dal.controllers;

import dal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import bll.crud.UserBll;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserBll userBll;

    @GetMapping(value = "/getAll")
    public List<User> getAllUsers() {
        return userBll.getAllUsers();
    }

    @GetMapping(value = "/get/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userBll.getUserById(id);
    }

    @PostMapping(value = "/add")
    public void addUser(@RequestBody User user) {
        userBll.addUser(user);
    }

    @PostMapping(value = "/update")
    public String updateUser(@RequestBody User user) {
        return userBll.updateUser(user);
    }

    @PostMapping(value = "/delete")
    public void deleteUser(@RequestBody int userId) {
        userBll.deleteUser(userId);
    }
}
