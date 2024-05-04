package com.nomaltree.web.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nomaltree.web.dto.Notice;
import com.nomaltree.web.dto.User;
import com.nomaltree.web.model.UserLoginSignup;
import com.nomaltree.web.service.NoticeService;
import com.nomaltree.web.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("gaon")
public class IndexController {

	@Autowired UserService userService;
	@Autowired NoticeService noticeService;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");


	//홈화면 Get요청
	@GetMapping("index")
	public String index(Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		List<Notice> notice = noticeService.getNewestList();
		for(Notice ntc : notice) {
			String date = simpleDateFormat.format(ntc.getRegdate());
			ntc.setStrRegDate(date);
		}

		if(userId != null) {
			User user = userService.getUserById(userId);
			model.addAttribute("notice", notice);
			model.addAttribute("user", user);
			return "view/home/customerIndex";
		}
		else {
			model.addAttribute("notice", notice);
			return "view/home/index";
		}

	}
	//로그인화면 Get요청
	@GetMapping("login")
	public String login(Model model, HttpSession session) {
		User user = new User();
		model.addAttribute("user", user);
		return "view/home/login";
	}
	//로그인 Post요청
	@PostMapping("login")
	public String login(User user, HttpSession session, BindingResult bindingResult) {
		String userId = userService.login(user.getId(), user.getPassword());
		session.setMaxInactiveInterval(60*60*8);
		session.setAttribute("userId", userId);
		return "redirect:index";
	}
	//로그아웃 Request요청
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index";
	}
	//회원가입화면 Get요청
	@GetMapping("signup")
	public String toSignupPage(Model model) {
		UserLoginSignup user = new UserLoginSignup();
		model.addAttribute("user", user);
		return "view/home/signup";
	}
	//회원가입 Post요청
	@PostMapping("signup")
	public String signup(User user) {
		try {
			userService.signup(user);
		} catch (DuplicateKeyException e) {
			return "redriect:/signup?error_code=-1";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/signup?error_code=-99";
		}
		return "view/home/login";
	}
	//로그인 후 사용자 전용화면 Get요청
	@GetMapping("customerIndex")
	public String customerIndex(HttpSession session, Model model) {
		String id = (String) session.getAttribute("userId");
		if(id != null) {
			User user = userService.getUserById(id);
			model.addAttribute("user", user);
			return "view/home/customerIndex";
		}
		return "redirect:login";
	}
}
