package net.mike.project.spend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.mike.project.spend.dao.DailySpendRepository;
import net.mike.project.spend.model.DailySpend;
import net.mike.project.user.dao.UserRepository;
import net.mike.project.user.detail.CustomUserDetails;
import net.mike.project.user.model.User;
import net.mike.project.util.UserUtil;

@RestController
public class SpendController {
	
	@Autowired
	DailySpendRepository dailySpendRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserUtil userUtil;
	
	@GetMapping("/getDailySpend")
	public List< DailySpend> getUserDailySpend(Principal principal) {
		
		Long userId=userUtil.getLoginUserId(principal);
		
		List< DailySpend> userDailySpendList = dailySpendRepository.findByUserId( userId);
		
		return userDailySpendList;
		
	}
	
	 @GetMapping("/getLoginUserPrincipal")
	 public Principal user(Principal principal) {
	     return principal;
	 }
	 
	 @PostMapping("/saveDailySpend")
	 public DailySpend saveDailySpend(    Principal principal) {
		return null;
		 
		 
	 }
}
