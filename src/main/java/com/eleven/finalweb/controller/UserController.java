package com.eleven.finalweb.controller;

import com.eleven.finalweb.model.GameData;
import com.eleven.finalweb.model.User;
import com.eleven.finalweb.service.UserService;
import com.eleven.finalweb.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    // Main Page rendering
    @GetMapping("/main")
    public String showMainPage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
            System.out.println("메인 페이지에서 세션 사용자 확인: " + loggedInUser.getUserId());
        } else {
            System.out.println("세션에 저장된 사용자 정보가 없습니다.");
        }
        return "Main"; // resources/templates/Main.html
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // resources/templates/login.html
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User()); // User 객체를 모델에 추가
        return "register"; // resources/templates/register.html 반환
    }
    // Userinfor
    @GetMapping("/userinfor")
    public String showUserInformationPage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);

            // 게임 데이터 가져오기
            GameData gameData = userService.findGameDataByUser(loggedInUser);
            model.addAttribute("gameData", gameData);
        }
        return "userinfor"; // resources/templates/userinfor.html
    }

    // Card Page rendering
    @GetMapping("/card")
    public String showCardPage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        }
        return "card"; // resources/templates/card.html
    }

    // Gameinfor Page rendering
    @GetMapping("/gameinfor")
    public String showGameInformationPage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        }
        return "gameinfor"; // resources/templates/gameinfor.html
    }

    // User registration
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute User user, Model model) {
        userService.saveUserWithGameData(user);
        model.addAttribute("user", user); // 회원 정보 추가
        return "register_success"; // 회원가입 성공 페이지로 이동
    }

    // User login (for API request)
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

    // User login (for web)
    @PostMapping("/web-login")
    public String webLogin(@RequestParam String userId, @RequestParam String userPassword, Model model, HttpSession session) {
        boolean authenticated = this.userService.authenticate(userId, userPassword);
        if (authenticated) {
            // 로그인 성공 시 세션에 사용자 정보 저장
            User user = this.userRepository.findByUserId(userId);
            session.setAttribute("loggedInUser", user);
            return "redirect:/api/main";  // 메인 페이지로 리다이렉트
        } else {
            // 로그인 실패 시 에러 메시지를 모델에 추가하고 로그인 페이지로 이동
            model.addAttribute("loginError", "아이디 및 비밀번호를 확인해주세요");
            return "login";
        }
    }

    // User logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/api/login"; // 로그인 페이지로 리다이렉트
    }

    // Get user nickname
    @GetMapping("/nickname")
    @ResponseBody
    public NicknameResponse getNickname(@RequestParam Long userNum) {
        String nickname = userService.getNickname(userNum);
        return nickname != null ? new NicknameResponse(true, nickname) : new NicknameResponse(false, "User not found");
    }

    // Check if userId exists
    @GetMapping("/check-userId")
    public ResponseEntity<Boolean> checkUserId(@RequestParam String userId) {
        boolean isUserIdExists = userService.isUserIdExists(userId);
        return ResponseEntity.ok(isUserIdExists);
    }

    // Response classes
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
