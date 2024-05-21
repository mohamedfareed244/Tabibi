package com.tabibi.tabibi_system.Logging;

import java.sql.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.RedirectView;

import com.tabibi.tabibi_system.Models.UserLog;
import com.tabibi.tabibi_system.Repositories.UserLogRepository;
import com.tabibi.tabibi_system.Repositories.UserAccRepository;

import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class userlogging {
    @Autowired
    UserLogRepository userlog;

    @Pointcut("execution(* com.tabibi.tabibi_system.Controllers.UserController.loginprocess(..))")
    public void UserLoginPointCut(){

    }
    @AfterReturning(
        pointcut = "UserLoginPointCut()",
        returning = "redirectView")
        public void logSuccessfulLogin(JoinPoint joinPoint, RedirectView redirectView) {
    // Check if redirection is to DoctorHomePage
    if (redirectView != null && ( "/User/DoctorHomePage".equals(redirectView.getUrl()) || "/User/clinicHomepage".equals(redirectView.getUrl()) || "/User/patientHomepage".equals(redirectView.getUrl()) ) ) {

        Object[] args = joinPoint.getArgs();
        HttpSession session = (HttpSession) args[2];
        int userId = (int) session.getAttribute("uid");
     
       
       Date currentDate = new Date(System.currentTimeMillis());
       UserLog NewLog=new UserLog();
   NewLog.setDate(currentDate);
   NewLog.setUserId(userId);
    userlog.save(NewLog);

   
    }
 
}
}
