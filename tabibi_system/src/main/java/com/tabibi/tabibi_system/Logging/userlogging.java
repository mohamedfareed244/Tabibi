package com.tabibi.tabibi_system.Logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class userlogging {
    @Pointcut("execution ( com.tabibi.tabibi_system.Controllers.UserController.loginprocess(..))")
    public void UserLoginPointCut(){

    }
    @After(value ="UserLoginPointCut()")
    public void UserLoginLogger(){
        
    }
}
