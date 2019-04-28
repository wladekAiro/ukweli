package com.ukweli.news.repository;

import com.ukweli.news.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wladek
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginId(String loginId);

    User findByEmail(String email);
}
