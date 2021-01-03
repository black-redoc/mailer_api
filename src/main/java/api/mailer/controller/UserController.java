package api.mailer.controller;

import api.mailer.model.User;
import api.mailer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository repository;

    @PostMapping(value = "/api/user/login")
    public ResponseEntity<User> login(@RequestBody User user){
        ResponseEntity<User> response;
        User newUser;
        newUser = repository.findByEmail(user.getEmail());
        if (newUser == null)
           newUser = repository.save(user);
        else {
            if (!newUser.getPassword().equalsIgnoreCase(user.getPassword())) {
                newUser.setPassword(user.getPassword());
                newUser = repository.save(newUser);
            }
        }
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.FOUND);
    }
}
