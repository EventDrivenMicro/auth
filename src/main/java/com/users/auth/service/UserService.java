package com.users.auth.service;

import com.users.auth.dao.entity.User;
import com.users.auth.dao.repository.UserRepository;
import dto.UserDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO findById(Long id){
        User user = userRepository.findById(id).orElse(null);

        if(user == null){
            throw new EntityNotFoundException();
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setRoles(user.getRoles());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }
}
