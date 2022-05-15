package com.example.lab3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.AudioFormat;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

@RestController
    public class UserController {

        public UserController() throws IOException {

            User u1 = new User(1, "Mariusz", "mariusz@gmail.com");
            User u2 = new User(2, "Bartosz", "bartosz@gmail.com");
            User u3 = new User(3, "Krystyna", "krystyna@gmail.com");

            ArrayList<User> userList = new ArrayList<>();
            userList.add(u1);
            userList.add(u2);
            userList.add(u3);

//            objectMapper.writeValue(new File("target/user.json"), u1);
//            objectMapper.writeValue(new File("target/user.json"), u2);
//            objectMapper.writeValue(new File("target/user.json"), u3);
//
//            User user = objectMapper.readValue(json, User.class);
//            String userJson = objectMapper.writeValueAsString(u1);
//            String userJson2 = objectMapper.writeValueAsString(u2);
//            String userJson3 = objectMapper.writeValueAsString(u3);
//
//            ArrayList<String> array = new ArrayList<String>();

            ObjectMapper objectMapper = new ObjectMapper();

            String userJson = objectMapper.writeValueAsString(userList);

            try (FileOutputStream stream = new FileOutputStream("users.json");
                 Writer writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8)) {
                writer.write(userJson);
                writer.flush();
            }


//            System.out.println(userJson); // {"name":"John","age":21}
        }

        @RequestMapping(value = "/users", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public String page(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "1") int pageSize, @RequestBody String userJson){
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