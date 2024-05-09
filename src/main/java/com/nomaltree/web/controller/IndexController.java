package com.nomaltree.web.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nomaltree.web.dto.Notice;
import com.nomaltree.web.dto.User;
import com.nomaltree.web.model.UserLogin;
import com.nomaltree.web.model.UserSignUp;
import com.nomaltree.web.service.NoticeService;
import com.nomaltree.web.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
		UserLogin userLogin = new UserLogin();
		model.addAttribute("userLogin", userLogin);
		return "view/home/login";
	}
	//로그인 Post요청
	@PostMapping("login")
	public String login(HttpSession session, @Valid UserLogin userLogin, BindingResult bindingResult){
		try {
			String userId = userService.login(userLogin, bindingResult);
			session.setMaxInactiveInterval(60*60*8);
			session.setAttribute("userId", userId);
			return "redirect:index";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.rejectValue("", null, "로그인 할 수 없습니다.");
			return "view/home/login";
		}
	}
	//로그아웃 Request요청
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index";
	}
	//회원가입화면 Get요청
	@GetMapping("signup")
	public String Signup(Model model) {
		UserSignUp userSignUp = new UserSignUp();
		model.addAttribute("userSignUp", userSignUp);
		return "view/home/signup";
	}
	//회원가입 Post요청
	@PostMapping("signup")
	public String signup(Model model, @Valid UserSignUp userSignUp, BindingResult bindingResult) {
		try {
			userService.signup(userSignUp, bindingResult);
			return "redirect:login";
		}catch (Exception e) {
			e.printStackTrace();
			bindingResult.rejectValue("", null, "등록할 수 없습니다.");
			return "view/home/signup";
		}
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
