package com.eleven.finalweb.service;


import com.eleven.finalweb.model.GameData;
import com.eleven.finalweb.repository.GameDataRepository;
import com.eleven.finalweb.model.User;
import com.eleven.finalweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameDataRepository gameDataRepository;

    public boolean isUserIdExists(String userId) { // 회원가입시 아이디 중복체크
        return userRepository.findByUserId(userId) != null;
    }

    public boolean authenticate(String userid, String password) {
        return userRepository.findByUserIdAndUserPassword(userid, password).isPresent();
    }

    public String getNickname(Long userNum) {
        User user = userRepository.findByUserNumber(userNum);
        return user != null ? user.getNickname() : null;
    }

    public void saveUserWithGameData(User user) {
        // Save the user
        userRepository.save(user);

        // Create and save game data associated with the user
        GameData gameData = new GameData();
        gameData.setUser(user);
        gameData.setPlaytime(0); // Initialize playtime or any other default values
        gameData.setClearCount(0); // Initialize clear count or any other default values
        gameDataRepository.save(gameData);
    }
}
