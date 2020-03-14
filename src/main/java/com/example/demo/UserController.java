package com.example.demo;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
@Api(value = "User API")
public class UserController {

    @Autowired
    UserRepository repo;

    //@GetMapping(value = "/{id}")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Optional<User>> getUser(@PathVariable(value = "id") String id){
        Optional<User> user = repo.findById(Integer.parseInt(id));
        if(!user.isPresent()) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //@PostMapping
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = repo.save(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }

    //@GetMapping
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<Iterable<User>> getUserList(){
        Iterable<User> userList = repo.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    //@DeleteMapping(value = "/{id}")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<User> removeUser(@PathVariable(value = "id") String id){
        User user = new User();
        user.setId(Integer.parseInt(id));
        repo.delete(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
