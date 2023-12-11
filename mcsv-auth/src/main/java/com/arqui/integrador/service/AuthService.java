package com.arqui.integrador.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arqui.integrador.dto.CommonUserCreateDto;
import com.arqui.integrador.dto.SpecialUserCreateDto;
import com.arqui.integrador.dto.UserCreateDto;
import com.arqui.integrador.dto.UserDto;
import com.arqui.integrador.exception.NoRoleException;
import com.arqui.integrador.exception.UserNotFoundException;
import com.arqui.integrador.feign.IUserFeignClient;
import com.arqui.integrador.model.Role;
import com.arqui.integrador.model.UserAuth;
import com.arqui.integrador.repository.IAuthRepository;

@Service
public class AuthService implements UserDetailsService{
	
	private IAuthRepository authRepository;
	
	private IUserFeignClient userFeignClient;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public AuthService(IAuthRepository authRepository, IUserFeignClient userFeignClient) {
		this.authRepository = authRepository;
		this.bCryptPasswordEncoder =  new BCryptPasswordEncoder();
		this.userFeignClient = userFeignClient;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UserNotFoundException {

		return authRepository.findByUsername(username)
				.map(user -> new User(user.getUsername(), user.getPassword(), getAuthorities(user.getRole())))
				.orElseThrow(() -> new UserNotFoundException("User not found","User not found with username: " + username));
	}
	
	public void registerSpecialUser(SpecialUserCreateDto user) {
		saveUser(user, findRole(user.getRole()));
	}
	
	public void register(CommonUserCreateDto user) {
		saveUser(user, findRole("user"));
		this.userFeignClient.create(
				UserDto.builder()
						.username(user.getUsername())
						.cellphone(user.getCellphone())
						.email(user.getEmail())
						.firstname(user.getFirstname())
						.surname(user.getSurname())
						.build());
	}
	
	private void saveUser(UserCreateDto user, Role role) {
		String bCryptedPassword = encodePassword(user.getPassword());
		
		this.authRepository.save(UserAuth.builder()
				.username(user.getUsername())
				.password(bCryptedPassword)
				.role(role)
				.build());
	}
	
	private String encodePassword(String password) {
		
		return bCryptPasswordEncoder.encode(password);
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Role role){
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.getRole()));
	    return authorities;
	}
	
	private Role findRole(String role) {
		return this.authRepository.findRoleByRoleName(role).orElseThrow(() ->
			new NoRoleException("Role not exists", "Role provided does not exists"));
	}

}
