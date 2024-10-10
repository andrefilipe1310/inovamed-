package com.inovamed.clinical_study_system.service;

import com.inovamed.clinical_study_system.model.user.User;
import com.inovamed.clinical_study_system.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    @Autowired
   private UserRepository userRepository;

    public User create(User user){
        return userRepository.save(user);
    };
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()->{
            return new RuntimeException("Deu erro");
        });
    }
}
