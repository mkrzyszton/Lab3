package com.example.lab3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
    public class UserController {
        static ObjectMapper objectMapper = new ObjectMapper();

        public UserController() throws IOException {

            User u1 = new User(1, "Mariusz", "mariusz@gmail.com");
            User u2 = new User(2, "Bartosz", "bartosz@gmail.com");
            User u3 = new User(3, "Krystyna", "krystyna@gmail.com");

            //objectMapper.writeValue(new File("target/user.json"), u1);
            //objectMapper.writeValue(new File("target/user.json"), u2);
            //objectMapper.writeValue(new File("target/user.json"), u3);

            //User user = objectMapper.readValue(json, User.class);
            //String userJson = objectMapper.writeValueAsString(u1);
            //String userJson2 = objectMapper.writeValueAsString(u2);
            //String userJson3 = objectMapper.writeValueAsString(u3);
        }

        @RequestMapping(value = "/users", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public String page(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "1") int pageSize, @RequestBody String userJson,
                           @RequestBody User user) throws IOException {
            objectMapper.writeValue(new File("target/user.json"), user);
            userJson = objectMapper.writeValueAsString(user);
            if ((pageNumber < 1 || pageNumber > 100) || (pageSize < 1 || pageSize > 100)) {
                return "Conajmniej jeden niewłaściwy parametr";
            }

            return "Page number: " + pageNumber + "\nPage size: " + pageSize + "\n" + userJson;
        }

        @RequestMapping(value = "users/get", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public User getUser(@RequestBody User user) {
            return user;
        }

        @RequestMapping(value = "users/get/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public User getUser(@PathVariable long id, @RequestBody User user) {
            if(user.id == id) {
                return user;
            }
            else {
                return null;
            }
        }

        @RequestMapping(value = "users/update/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public User updateUser(@PathVariable long id, @RequestBody User user) {
            if(user.id == id) {
                return user;
            }
            else {
                return null;
            }
        }

        @RequestMapping(value = "users/delete/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public ResponseEntity<User> deleteUser(@PathVariable long id, @RequestBody User user) {
            if(user.id == id) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return null;
            }
        }

        @RequestMapping(value = "/users/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public User createUser(@RequestBody User user) {
            return user;
        }
    }