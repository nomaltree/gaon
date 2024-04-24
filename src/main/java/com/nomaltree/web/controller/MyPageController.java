package com.nomaltree.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nomaltree.web.dto.User;
import com.nomaltree.web.service.MyPageService;
import com.nomaltree.web.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("gaon")
public class MyPageController {

	@Autowired MyPageService myPageService;
	@Autowired UserService userService;

	//마이페이지 홈 화면 불러오기 메소드
	@GetMapping("myPage")
	public String myPage(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");
		if(userId != null) {
			User user = userService.getUserById(userId);
			model.addAttribute("user", user);
			return "view/mypage/myPageHome";
		}
		else {
			return "view/home/index";
		}
	}
	//회원탈퇴 메소드
	@PostMapping("deleteUser")
	public String deleteUser(HttpSession session) {
		String id = (String) session.getAttribute("userId");
		session.invalidate();
		if(id != null) {
			myPageService.deleteUser(id);
		}
		return "redirect:index";
	}
}
