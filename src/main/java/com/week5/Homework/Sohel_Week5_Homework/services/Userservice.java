package com.week5.Homework.Sohel_Week5_Homework.services;

import com.week5.Homework.Sohel_Week5_Homework.dto.SignUpDto;
import com.week5.Homework.Sohel_Week5_Homework.dto.UserDto;
import com.week5.Homework.Sohel_Week5_Homework.entities.UserEntity;
import com.week5.Homework.Sohel_Week5_Homework.exceptions.ResourceNotFoundException;
import com.week5.Homework.Sohel_Week5_Homework.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Userservice implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(
                ()->new BadCredentialsException("User not Found with email "+ username)
        );
    }

    public UserDto signup(SignUpDto signUpDto) {
       Optional<UserEntity> user = userRepository.findByEmail(signUpDto.getEmail());

       if(user.isPresent()){
            throw new BadCredentialsException("User already present with email " + signUpDto.getEmail());
       }

       UserEntity userEntity = modelMapper.map(signUpDto,UserEntity.class);
       userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
       UserEntity toSave = userRepository.save(userEntity);

       return modelMapper.map(toSave,UserDto.class);



    }

    public UserEntity getUserById(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User not Found with id "+ userId)
        );

        return user;
    }
}
