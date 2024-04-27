package com.nomaltree.web.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nomaltree.web.dto.BookMark;
import com.nomaltree.web.dto.Comment;
import com.nomaltree.web.dto.Notice;
import com.nomaltree.web.dto.User;
import com.nomaltree.web.service.MyPageService;
import com.nomaltree.web.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("gaon")
public class MyPageController {

	@Autowired MyPageService myPageService;
	@Autowired UserService userService;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss"); //날짜 포맷을 변경할 멤버 변수

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

	//내가 쓴 게시글 목록 불러오기 메소드
	@GetMapping("myNotice")
	public String myNotice(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");
		if(userId != null) {
			User user = userService.getUserById(userId);
			List<Notice> myNotice = myPageService.getMyNotice(user.getId());
			for(Notice mnt : myNotice) {
				String date = simpleDateFormat.format(mnt.getRegdate());
				mnt.setStrRegDate(date);
			}
			model.addAttribute("myNotice", myNotice);
			model.addAttribute("user", user);
			return "view/mypage/myNotice";
		}
		else {
			return "view/home/index";
		}
	}

	//내가 쓴 댓글 목록 불러오기 메소드
	@GetMapping("myComment")
	public String myComment(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");
		if(userId != null) {
			User user = userService.getUserById(userId);
			List<Comment> myComment = myPageService.getMyComment(user.getNickname());
			for(Comment mcm : myComment) {
				String date = simpleDateFormat.format(mcm.getRegdate());
				mcm.setStrRegDate(date);
			}
			model.addAttribute("myComment", myComment);
			model.addAttribute("user", user);
			return "view/mypage/myComment";
		}
		else {
			return "view/home/index";
		}
	}

	//즐겨찾기 목록 불러오기 메소드
	@GetMapping("myBookmark")
	public String myBookmark(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");
		if(userId != null) {
			User user = userService.getUserById(userId);
			List<BookMark> myBookmark = myPageService.getMyBookMark(user.getId());
			model.addAttribute("myBookmark", myBookmark);
			model.addAttribute("user", user);
			return "view/mypage/myBookmark";
		}
		else {
			return "view/home/index";
		}
	}

	//회원탈퇴 메소드
	@PostMapping("deleteUser")
	public String deleteUser(HttpSession session) {
		String id = (String) session.getAttribute("userId");
		System.out.println(id);
		session.invalidate();
		if(id != null) {
			myPageService.deleteUser(id);
			return "redirect:index";
		}
		return "redirect:index";
	}
}
