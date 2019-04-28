package com.ukweli.news.web.front.support;

import com.ukweli.news.domain.User;
import com.ukweli.news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author wladek
 */
@Component
public class UserValidator {

    @Autowired
    UserRepository repository;

    public boolean validateNewUser(User user, BindingResult result) {
        User existingUser = repository.findByEmail(user.getEmail());
        if(existingUser != null) {
            result.rejectValue("email", "user.email.duplicate", "user.email.duplicate");
        }

        existingUser = repository.findByLoginId(user.getLoginId());
        if(existingUser != null) {
            result.rejectValue("loginId", "user.loginId.duplicate", "user.loginId.duplicate");
        }

        return result.hasErrors();
    }
}
