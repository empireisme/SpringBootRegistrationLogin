package net.mike.project.util;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mike.project.user.dao.UserRepository;
import net.mike.project.user.model.User;
@Service
public class UserUtil {
	
	@Autowired
	UserRepository userRepository;
	
	public Long getLoginUserId(Principal principal) {
		
		String email=principal.getName();
		User user = userRepository.findByEmail(email);
		Long userId= user.getId();
		return userId;
	}
}
