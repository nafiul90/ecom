//package com.ecom.ecom.service;
//
//import com.ecom.ecom.model.User;
//import com.ecom.ecom.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = userRepository.findByUsername(username);
//        if(user == null)
//            throw new UsernameNotFoundException("User not found");
//        return new UserPrinciple(user);
//    }
//}
