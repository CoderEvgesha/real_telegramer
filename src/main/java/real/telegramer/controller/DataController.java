package real.telegramer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import real.telegramer.db.model.Admin;
import real.telegramer.db.model.Message;
import real.telegramer.db.model.User;
import real.telegramer.db.repository.AdminRepository;
import real.telegramer.db.repository.MessageRepository;
import real.telegramer.db.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class DataController {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public DataController(@Autowired AdminRepository adminRepository,
                          @Autowired UserRepository userRepository,
                          @Autowired MessageRepository messageRepository) {
        this.adminRepository = adminRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/admin")
    public Admin getAdmins() {
        return adminRepository.findTopByOrderByIdDesc();
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return StreamSupport.stream(messageRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
