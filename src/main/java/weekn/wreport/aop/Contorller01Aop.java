package weekn.wreport.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import weekn.wreport.model.ResponseModel;
import weekn.wreport.security.MyException_noLogin;

@Component  
@Aspect  
public class Contorller01Aop {
	
	
	@Pointcut("execution(* weekn.wreport.controller..*.*(..))")
	private void controller() {
	}

	@Around("execution(* weekn.wreport.controller..*.*(..))")
	public Object test(ProceedingJoinPoint pjp) throws Throwable {
		ResponseModel response=new ResponseModel();
		try {
			Object retVal = pjp.proceed();
			return retVal;
		}catch (MyException_noLogin e) {
			System.out.println("no login in aop");
			response.setMessage("no login");
			response.setStatus(-1);
			return response;
		}
		
		
	}
}
