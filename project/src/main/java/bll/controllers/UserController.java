package bll.controllers;

import bll.crud.UserBll;
import bll.dtos.UserDTO;
import bll.wrappers.IdWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserBll userBll;

    @GetMapping(value = "/getAll")
    public List<UserDTO> getAllUsers() {
        return userBll.getAllUsers();
    }

    @GetMapping(value = "/get/{id}")
    public UserDTO getUserById(@PathVariable("id") int id) {
        return userBll.getUserById(id);
    }

    @PostMapping(value = "/add")
    public String addUser(@RequestBody UserDTO user) {
         return userBll.addUser(user);
    }

    @PostMapping(value = "/update")
    public String updateUser(@RequestBody UserDTO user) {
        return userBll.updateUser(user);
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestBody IdWrapper idWrapper) {
        return userBll.deleteUser(idWrapper.getId());
    }

    @PostMapping(value = "/login")
    public int login(@RequestBody UserDTO user) {
        return userBll.login(user);
    }
}
