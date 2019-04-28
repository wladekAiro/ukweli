package com.ukweli.news.web;

import com.ukweli.news.service.impl.UserDetailsImpl;
import com.ukweli.news.domain.User;
import com.ukweli.news.domain.enumeration.UserRole;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wladek on 7/6/15.
 */
@Controller
public class PageDirectorController {

    @RequestMapping(value = "/url-processor" , method = RequestMethod.GET)
    public String redirect(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userDetails.getUser();

        if (user.getRoles().contains(UserRole.EDITOR)){
            return "redirect:/editor/home" ;
        }

        if (user.getRoles().contains(UserRole.WRITER)){
            return "redirect:/writer/home" ;
        }

        return "redirect:/";

    }
}
