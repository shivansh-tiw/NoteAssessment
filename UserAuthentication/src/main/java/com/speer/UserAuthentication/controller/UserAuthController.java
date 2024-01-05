package com.speer.UserAuthentication.controller;

import com.speer.UserAuthentication.entity.User;
import com.speer.UserAuthentication.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.ServletException;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/auth")
public class UserAuthController {
	long expireTime = 1200000;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User user) {
		boolean bool = userService.addUser(user);
			if(bool) {
				return new ResponseEntity<String>("ok", HttpStatus.CREATED);
			}
			else return new ResponseEntity<String>("no", HttpStatus.CONFLICT);
	}
	 
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		 String jwtToken = "";
		 Map<String, String> map = new HashMap<>();
		 try {
			 jwtToken = getToken(user.getUsername(), user.getPassword());
			 map.clear();
			 map.put("message", "User loggedIn successfully");
			 map.put("Token", jwtToken);
		 } catch(Exception e) {
			 map.clear();
			 map.put("message", e.getMessage());
			 map.put("Token", null);
			 return new ResponseEntity<>(map,HttpStatus.CONFLICT);
		 }
		 return new ResponseEntity<>(map, HttpStatus.OK);
	}
	 
	public String getToken(String username, String password) throws Exception{
		 if(username==null || password==null) {
			 throw new ServletException("Please fill credentials");
		 }
		 
		 boolean status = userService.validate(username, password);
		 
		 if(!status) {
			 throw new ServletException("Invalid credentials");
		 }
		 
		 String jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
				 			.setExpiration(new Date(System.currentTimeMillis()+ expireTime))
				 			.signWith(SignatureAlgorithm.HS256, "secretKey").compact();
		 return jwtToken;
	}
}

