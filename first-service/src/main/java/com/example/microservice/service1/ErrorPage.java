package com.example.microservice.service1;

import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ErrorPage implements ErrorController{

	@RequestMapping("/error")
	public ModelAndView getError(HttpServletResponse response) {
		ModelAndView modelAndView  = new ModelAndView();
		if(response.getStatus()  == 404){
			modelAndView.setViewName("404");	
		}
		else if(response.getStatus()  == 500) {
			modelAndView.setViewName("error/500");	
		}else if(response.getStatus()  == 503) {
			modelAndView.setViewName("error/503");	
		}
		else {
			modelAndView.setViewName("error/error");		
		}
		return modelAndView;
	}
	
	@Override
	public String getErrorPath() {	
		return "/error";
	}

}
