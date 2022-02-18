package com.larcomlabs.lab1.Controllers;

import com.larcomlabs.lab1.Models.User;
import com.larcomlabs.lab1.Repos.UserRepository;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController
{
    private UserRepository repo;
    private PasswordEncoder encoder;
    private JavaMailSender sender;

    private UserRestController(UserRepository repo, PasswordEncoder encoder, JavaMailSender sender) {
        this.repo = repo;
        this.encoder = encoder;
        this.sender = sender;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        return repo.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        repo.deleteById(id);
    }

    @PatchMapping("/{username}")
    public void updateUser(@PathVariable String username, @RequestBody User u) {
        User current = repo.findById(username).get();
        //only allow update to address
        current.setStreetAddress(u.getStreetAddress());
        repo.save(current);
    }

    @GetMapping("/reset")
    public void resetPassword(@RequestParam String email) {
        //use email to send reset email to the user
        User u = repo.findByEmail(email);

        String newPass = generateTemporaryPassword();
        u.setPassword(encoder.encode(newPass));
        //persist the password change
        repo.save(u);
        System.out.println(newPass);

        emailPassword(u.getEmail(), newPass, u.getUsername());
    }

    //used to send an email to the user whos password has been reset.
    private void emailPassword(String email, String newPass, String username) {
        String msgBody = "Hello "+ username +"! \nYour new temporary password is: " + newPass;
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("larcomlabs@gmail.com");
        msg.setTo(email);
        msg.setSubject("Password Reset");
        msg.setText(msgBody);

        sender.send(msg);
    }

    private String generateTemporaryPassword() {
        PasswordGenerator generator = new PasswordGenerator();
        CharacterRule lowerRule = new CharacterRule(EnglishCharacterData.LowerCase);
        CharacterRule upperRule = new CharacterRule(EnglishCharacterData.UpperCase);
        CharacterRule digitRule = new CharacterRule(EnglishCharacterData.Digit);

        lowerRule.setNumberOfCharacters(3);
        upperRule.setNumberOfCharacters(3);
        digitRule.setNumberOfCharacters(3);

        return generator.generatePassword(12, lowerRule,upperRule,digitRule);
    }
}
