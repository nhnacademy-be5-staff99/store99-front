package com.nhnacademy.store99.front.quit.controller;

import static com.nhnacademy.store99.front.quit.service.QuitService.Id;

import com.nhnacademy.store99.front.auth.service.LoginService;
import com.nhnacademy.store99.front.quit.service.QuitService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuitController {

    private final QuitService quitService;
    private final LoginService loginService;

    public QuitController(QuitService quitService, LoginService loginService) {
        this.quitService = quitService;
        this.loginService = loginService;
    }


    @PostMapping("/quit")
    public Long quit(HttpServletRequest request, HttpServletResponse response) {
        quitService.quit();
        loginService.doLogout(request, response);
        return Id;
    }

}
