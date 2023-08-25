package com.ceyentra.last.service.impl;


import com.ceyentra.last.advice.UserException;
import com.ceyentra.last.dao.UserDao;
import com.ceyentra.last.entity.User;
import com.ceyentra.last.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			User user = userDao.findByUsername(username);
			System.out.println(user);
			if(user == null){
				throw new UsernameNotFoundException("Invalid username or password.");
			}
//		System.out.println(user.getPassword());
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));

		}catch (Exception e){
			throw e;
		}
	}

//	private List<SimpleGrantedAuthority> getAuthority() {
//		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//	}

	private List<SimpleGrantedAuthority> getAuthority(User user) {
		System.out.println(user.getRole());
		if (user.getRole().equals("ROLE_ADMIN")){
			return Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
		}
		if (user.getRole().equals("ROLE_USER")){
			return Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
		}
		throw new UsernameNotFoundException("Access Denied");
	}

	public User Update(User user) {
		try {
			Optional<User> byId = userDao.findById(user.getId());
			if(!byId.isPresent())
				throw new UserException("Admin user not found");
			Optional<User> byUsername = Optional.ofNullable(userDao.findByUsername(user.getUsername()));

			if(byUsername.isPresent())
				throw new UserException("Username already exist",409 );
			User userEntity =byId.get();


			userEntity.setId(user.getId());
			userEntity.setUsername( user.getUsername() );
			userEntity.setPassword(encoder.encode(user.getPassword()));
			userEntity.setRole(user.getRole());

			return userDao.save(userEntity);
		}catch (Exception e){
			throw e;
		}


	}

	public List<User> findAll() {

		List<User> list = new ArrayList<>();
		System.out.println(list);
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(Long id) {
		userDao.deleteAllById(Collections.singleton(id));
	}

	@Override
    public User save(User user) {

		try {
			Optional<User> byUsername = Optional.ofNullable(userDao.findByUsername(user.getUsername()));
			System.out.println(byUsername);
			if(byUsername.isPresent())
				throw new UserException("Username already exist",409 );
			user.setPassword(encoder.encode(user.getPassword()));
			System.out.println(user);
			return userDao.save(user);

		}catch (Exception e){
			throw  e;
		}
    }
}
