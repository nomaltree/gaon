package com.nomaltree.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.nomaltree.web.dto.User;
import com.nomaltree.web.mapper.UserMapper;

@Service
public class UserService {

	@Autowired UserMapper userMapper;

	//로그인 메소드
	public String login(String id, String password) {
		User user = userMapper.getUserId(id);
		if(user == null) {
			return null;
		}
		if(user.getPassword().equals(password)) {
			return user.getId();
		}
		else {
			return null;
		}
	}
	//회원가입 메소드
	public void signup(User user) {
		//User user = toUserDto(userLS);
		userMapper.insertUser(user);
	}
	//에러확인 메소드
	public boolean hasError(User user, BindingResult bindingResult) {
		if(userMapper.getUserId(user.getId()) == null) {
			bindingResult.rejectValue("id", null, "id가 존재하지않습니다");
			return true;
		}
		else if(user.getPassword() != userMapper.getUserId(user.getId()).getPassword()) {
			bindingResult.rejectValue("password", null, "패스워드가 일치하지않습니다");
			return true;
		}
		return false;
	}
	//특정 회원정보를 얻는 메소드
	public User getUserById(String id) {
		return userMapper.getUserByid(id);
	}
}
