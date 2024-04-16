package com.nomaltree.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.nomaltree.web.mapper.MyPageMapper;

public class MyPageService {

	@Autowired MyPageMapper myPageMapper;

	//회원탈퇴 메소드
	public void deleteUser(String id) {
		myPageMapper.deleteUser(id);
	}
}
