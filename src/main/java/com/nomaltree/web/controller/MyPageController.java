package com.nomaltree.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.nomaltree.web.service.MyPageService;

import jakarta.servlet.http.HttpSession;

public class MyPageController {

	@Autowired MyPageService myPageService;

	@PostMapping("deleteUser")
	public String deleteUser(HttpSession session) {
		String id = (String) session.getAttribute("userId");
		if(id != null) {
			myPageService.deleteUser(id);
		}
		session.invalidate();
		return "redirect:index";
	}
}
