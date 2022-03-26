package com.apirest.videogame.controller;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IUserInvService;
import com.apirest.videogame.interfaces.IUserService;
import com.apirest.videogame.model.ResponseLogin;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.UserInv;
import com.apirest.videogame.model.Users;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/authentification")
public class LoginController {
	
	@Autowired
    IUserService userService;
	
	@Autowired
    IUserInvService userInvService;
	
	@Value("${jwt.secret}")
	private String SECRET;
	
	@PostMapping("/login")
	public ResponseLogin login(@RequestHeader("username") String username, @RequestHeader("password") String password) {
		Users user = new Users();
		ResponseLogin response = new ResponseLogin();
		username = decodeBase64(username);
		password = decodeBase64(password);
		user = userService.authenticate(username, password);
		if(user != null) {
			if(!user.getFirstname().equals("Error")) {
				String token = getJWTToken(user.getUsername());
				response.setIduser(user.getIduser());
				response.setIdrol(user.getIdrol());
				response.setFirstname(user.getFirstname());
				response.setUsername(user.getUsername());
				response.setDateborn(user.getDateborn());
				response.setToken(token);
			}else {
				response = new ResponseLogin();
				response.setUsername("Usuario o contraseña incorrecta");
			}			
		}else {
			response = new ResponseLogin();
			response.setUsername("Usuario o contraseña incorrecta");
		}
		return response;
	}
	
	@PostMapping("/loginInv")
	public ResponseObject login(@RequestBody Users userParam) {
		ResponseObject res = new ResponseObject();
		UserInv user = new UserInv();
		ResponseLogin response = new ResponseLogin();
		String username = decodeBase64(userParam.getUsername());
		String password = decodeBase64(userParam.getPass());
		user = userInvService.authenticate(username, password);
		if(user != null) {
			if(!user.getFirstname().equals("Error")) {
				String token = getJWTToken(user.getUsername());
				response.setIduser(user.getIduser());
				response.setIdrol(user.getIdrol());
				response.setFirstname(user.getFirstname());
				response.setUsername(user.getUsername());
				response.setDateborn(user.getDateborn());
				response.setIdsucursal(user.getIdsucursal());
				response.setRolname(user.getRolname());
				response.setToken(token);
				res.setState("Success");
				res.setMessage("Usuario autentificado exitosamente");
				res.setData(response);
			}else {
				res.setState("Error");
				res.setMessage("Usuario o contraseña incorrecta");
			}			
		}else {
			res.setState("Error");
			res.setMessage("Usuario o contraseña incorrecta");
		}
		return res;
	}
	
	public String decodeBase64(String valor) {
		try {
			Base64.Decoder decoder = Base64.getMimeDecoder();
	        byte[] decodedByteArray = decoder.decode(valor);
	        String decode_Value = new String(decodedByteArray);
	        return decode_Value;
		}catch(Exception e) {
			return e.getMessage();
		}        
    }
	
	@PostMapping("/passForget")
	public ResponseObject passForget(@RequestHeader("username") String username, @RequestHeader("email") String email, @RequestHeader("phone") String phone, @RequestHeader("password") String password) {
		return userService.passForget(username, email, phone, password);
	}
	
	@PostMapping("/saveUsers")
	public ResponseObject createUser(@RequestBody Users user) {
		return userService.createUser(user);
	}
	
	private String getJWTToken(String username) {
		String secretKey = SECRET;
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 18000000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
