package com.eleven.finalweb.controller;

import com.eleven.finalweb.model.User;
import com.eleven.finalweb.service.UserService;
import com.eleven.finalweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // @RestController가 아닌 @Controller 사용
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // 템플릿 파일 이름 반환
    }

    @PostMapping("/register")
    public String processRegistration(User user) {
        userService.saveUserWithGameData(user);
        return "redirect:/api/register?success"; // 리다이렉트 URL 수정
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String userId, @RequestParam String userPassword) {
        boolean authenticated = userService.authenticate(userId, userPassword);
        if (authenticated) {
            User user = userRepository.findByUserId(userId);
            return ResponseEntity.ok(new LoginResponse(true, "Login successful", user.getUserNumber()));
        } else {
            return ResponseEntity.status(401).body(new LoginResponse(false, "Invalid credentials", null));
        }
    }

    @GetMapping("/nickname")
    public @ResponseBody NicknameResponse getNickname(@RequestParam Long userNum) {
        String nickname = userService.getNickname(userNum);
        return nickname != null ? new NicknameResponse(true, nickname) : new NicknameResponse(false, "User not found");
    }

    static class LoginResponse {
        private boolean success;
        private String message;
        private Long userNum;

        public LoginResponse(boolean success, String message, Long userNum) {
            this.success = success;
            this.message = message;
            this.userNum = userNum;
        }

        // Getters and setters
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Long getUserNum() {
            return userNum;
        }

        public void setUserNum(Long userNum) {
            this.userNum = userNum;
        }
    }

    static class NicknameResponse {
        private boolean success;
        private String nickname;

        public NicknameResponse(boolean success, String nickname) {
            this.success = success;
            this.nickname = nickname;
        }

        // Getters and setters
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }

}