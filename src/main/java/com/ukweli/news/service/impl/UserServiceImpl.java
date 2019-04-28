package com.ukweli.news.service.impl;

import com.ukweli.news.domain.enumeration.UserRole;
import com.ukweli.news.domain.enumeration.UserState;
import com.ukweli.news.domain.User;
import com.ukweli.news.repository.UserRepository;
import com.ukweli.news.service.UserService;
import com.ukweli.news.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Keeun Baik
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User addNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(Collections.singletonList(UserRole.WRITER)));
        user.setUserState(UserState.ACTIVE);
        return repository.save(user);
    }

    @Override
    public void login(User user) {
        UserDetailsImpl ud = new UserDetailsImpl(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(ud, ud.getPassword(), ud.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
