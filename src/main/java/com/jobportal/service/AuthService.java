//package com.jobportal.service;
//
//import com.jobportal.entity.User;
//import com.jobportal.dto.UserDTO;
//import com.jobportal.mapper.UserMapper;
//import com.jobportal.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public User register(UserDTO userDto) {
//        User user = UserMapper.toEntity(userDto);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    public User authenticate(String email, String rawPassword) {
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
//                return user;
//            }
//        }
//
//        return null;
//    }
//}



package com.jobportal.service;

import com.jobportal.dto.UserDTO;
import com.jobportal.entity.User;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if(user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public boolean register(UserDTO dto) {
        if(userRepository.findByUsername(dto.getUsername()).isPresent()) return false;
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        userRepository.save(user);
        return true;
    }
}
