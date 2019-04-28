/**
 * Yobi, Project Hosting SW
 *
 * Copyright 2012 NAVER Corp.
 * http://yobi.io
 *
 * @Author Ahn Hyeok Jun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ukweli.news.domain;

import com.ukweli.news.domain.enumeration.UserState;
import com.ukweli.news.domain.enumeration.UserRole;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name="users")
public class User extends AbstractModel{

    public static final String LOGIN_ID_PATTERN = "^[a-zA-Z0-9-]+([_.][a-zA-Z0-9-]+)*$";

    private String name;

    @Column(unique = true, nullable = false)
    @NotEmpty
    @Pattern(regexp = LOGIN_ID_PATTERN, message = "user.wrongloginId.alert")
    private String loginId;

    @Column(nullable = false)
    @NotEmpty
    private String password;

    @Column(unique = true)
    @Email
    private String email;

    private String lang;

    private String url;

    @Enumerated(EnumType.STRING)
    private UserState userState;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    @OneToMany(mappedBy = "writer" ,fetch = FetchType.LAZY)
    private Set<Article> writtenArticles;

    @OneToMany(mappedBy = "editor" ,fetch = FetchType.LAZY)
    private Set<Article> editedArticles;

    @OneToOne(mappedBy = "user" , fetch = FetchType.LAZY)
    private Profile profile;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<Article> getWrittenArticles() {
        return writtenArticles;
    }

    public void setWrittenArticles(Set<Article> writtenArticles) {
        this.writtenArticles = writtenArticles;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Article> getEditedArticles() {
        return editedArticles;
    }

    public void setEditedArticles(Set<Article> editedArticles) {
        this.editedArticles = editedArticles;
    }
}
