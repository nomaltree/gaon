package com.nomaltree.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.nomaltree.web.dto.User;
import com.nomaltree.web.mapper.UserMapper;
import com.nomaltree.web.model.UserLogin;
import com.nomaltree.web.model.UserSignUp;

@Service
public class UserService {

	@Autowired UserMapper userMapper;

	//로그인 메소드
	public String login(UserLogin userLogin, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {
			throw new Exception("로그인 할 수 없습니다.");
		}
		User user = getUserById(userLogin.getId());
		if(user == null) {
			bindingResult.rejectValue("id", null, "아이디가 존재하지 않습니다.");
			throw new Exception("아이디가 존재하지 않습니다.");
		}
		if(!user.getPassword().equals(userLogin.getPassword())) {
			bindingResult.rejectValue("password", null, "아이디와 비밀번호가 일치하지 않습니다.");
			throw new Exception("아이디와 비밀번호가 일치하지 않습니다.");
		}
		return userLogin.getId();
	}
	//회원가입 메소드
	public void signup(UserSignUp userSignUp, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {
			throw new Exception("사용자를 등록할 수 없습니다.");
		}
		User user2 = getUserById(userSignUp.getId());
		if(user2 != null) {
			bindingResult.rejectValue("id", null, "아이디가 중복됩니다.");
			throw new Exception("아이디가 중복됩니다.");
		}
		User user3 = getUserBynickname(userSignUp.getNickname());
		if(user3 != null) {
			bindingResult.rejectValue("nickname", null, "닉네임이 중복됩니다.");
			throw new Exception("닉네임이 중복됩니다.");
		}
		if(!userSignUp.getPassword().equals(userSignUp.getChkpassword())) {
			bindingResult.rejectValue("chkpassword", null, "비밀번호가 일치하지 않습니다.");
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		userMapper.insertUser(toUser(userSignUp));
	}

	//id로 특정 회원정보를 얻는 메소드
	public User getUserById(String id) {
		return userMapper.getUserByid(id);
	}

	//닉네임으로 특정 회원정보를 얻는 메소드
	public User getUserBynickname(String nickname) {
		return userMapper.getUserBynickname(nickname);
	}


	//user dto 객체를 userLS객체로 바꾸는 메소드
	public UserSignUp toUserLS(User user) {
		UserSignUp userLS = new UserSignUp();
		userLS.setId(user.getId());
		userLS.setNickname(user.getNickname());
		userLS.setPassword(user.getPassword());
		return userLS;
	}

	//userLS model 객체를 user객체로 바꾸는 메소드
	public User toUser(UserSignUp userLS) {
		User user = new User();
		user.setId(userLS.getId());
		user.setNickname(userLS.getNickname());
		user.setPassword(userLS.getPassword());
		return user;
	}
}
