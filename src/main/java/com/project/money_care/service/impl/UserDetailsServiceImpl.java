package com.project.money_care.service.impl;

import com.project.money_care.model.MyUserDetails;
import com.project.money_care.model.Users;
import com.project.money_care.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmail(userEmail);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found " + userEmail));
        return new MyUserDetails(user.get());
        //return user.map( MyUserDetails::new).get();
    }
}
