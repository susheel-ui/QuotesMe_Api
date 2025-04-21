package com.example.QuotesMe.Services;

import com.example.QuotesMe.Entities.User;
import com.example.QuotesMe.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo usrRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usrRepo.findByUsername(username);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }
        throw  new UsernameNotFoundException("username is not found"+username);
    }
}
