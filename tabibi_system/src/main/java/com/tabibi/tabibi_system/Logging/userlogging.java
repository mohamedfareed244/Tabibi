package com.tabibi.tabibi_system.Logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.RedirectView;

import com.tabibi.tabibi_system.Repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class userlogging {
    @Pointcut("execution ( com.tabibi.tabibi_system.Controllers.UserController.loginprocess(..))")
    public void UserLoginPointCut(){

    }
    @AfterReturning(
        pointcut = "execution(* your.package.loginprocess(..)) && args(email, pass, session)",
        returning = "redirectView")
public void logSuccessfulLogin(JoinPoint joinPoint, RedirectView redirectView, String email, String pass, HttpSession session) {
    // Check if redirection is to DoctorHomePage
    if (redirectView != null && "/User/DoctorHomePage".equals(redirectView.getUrl())) {
        // Extract user ID
        String userId = (String) session.getAttribute("uid");
        // Log the successful login event with user ID
        // You can use your preferred logging mechanism here
        System.out.println("User with ID " + userId + " signed in and redirected to DoctorHomePage at: " + java.time.LocalDateTime.now());
    }
}
}
