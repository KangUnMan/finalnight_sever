package com.eleven.finalweb.repository;

import com.eleven.finalweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserIdAndUserPassword(String userId, String userPassword);
    //Optional<User>는 결과가 있을 수도 있고 없을 수도 있는 User 객체를 나타냄
    User findByUserId(String userId); // userid로 사용자 닉네임 조회

    User findByUserNumber(Long userNum);
}