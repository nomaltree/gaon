package com.nomaltree.web.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriUtils;

import com.nomaltree.web.dto.BookMark;
import com.nomaltree.web.dto.Comment;
import com.nomaltree.web.dto.Notice;
import com.nomaltree.web.dto.User;
import com.nomaltree.web.logic.NoticeLogic;
import com.nomaltree.web.model.NoticeReg;
import com.nomaltree.web.model.Pagination;
import com.nomaltree.web.service.MyPageService;
import com.nomaltree.web.service.NoticeService;
import com.nomaltree.web.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("gaon")
public class NoticeController {

	@Autowired
	NoticeService noticeService;
	@Autowired
	UserService userService;
	@Autowired
	MyPageService myPageService;

	String board; // 게시판 종류를 저장할 멤버 변수
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss"); // 날짜 포맷을 변경할 멤버 변수

	// 게시글 검색 메소드
	@GetMapping("search")
	public String search(Model model, String keyword, String query, HttpSession session, Pagination pagination,
			HttpServletRequest request) throws ParseException {
		String userId = (String) session.getAttribute("userId");
		board = (String) session.getAttribute("board");
		board = new NoticeLogic().board(board);
		int count = noticeService.searchNoticeCount(keyword, query, board);
		List<Notice> notices = noticeService.searchNotice(keyword, query, board, pagination);
		pagination.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
		pagination.setQuery(query);
		for (Notice ntc : notices) {
			String date = simpleDateFormat.format(ntc.getRegdate());
			ntc.setStrRegDate(date);
		}

		board = "'" + query + "' " + "검색 결과: " + Integer.toString(count) + "건";
		model.addAttribute("notice", notices);
		model.addAttribute("board", board);
		model.addAttribute("orders", noticeService.getOrders());
		if (userId != null) {
			User user = userService.getUserById(userId);
			model.addAttribute("user", user);
			return "view/notice/customerList";
		} else {
			return "view/notice/list";
		}
	}

	// 자유 게시판 호출 메소드
	@GetMapping("freelist")
	public String freelist(Model model, HttpSession session, Pagination pagination, HttpServletRequest request)
			throws ParseException {
		String board = "자유";
		List<Notice> notice = noticeService.getListView(board, pagination);
		for (Notice ntc : notice) {
			String date = simpleDateFormat.format(ntc.getRegdate());
			ntc.setStrRegDate(date);
		}
		pagination.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
		String userId = (String) session.getAttribute("userId");
		String engBoard = new NoticeLogic().board(board);
		model.addAttribute("engBoard", engBoard);
		session.setAttribute("board", board);
		model.addAttribute("board", board + " 게시판");
		model.addAttribute("notice", notice);
		model.addAttribute("orders", noticeService.getOrders());
		if (userId != null) {
			User user = userService.getUserById(userId);

			model.addAttribute("user", user);
			return "view/notice/customerList";
		} else {
			return "view/notice/list";
		}
	}

	// 장터 게시판 호출 메소드
	@GetMapping("marketlist")
	public String marketlist(Model model, HttpSession session, Pagination pagination, HttpServletRequest request) {
		String board = "장터";
		List<Notice> notice = noticeService.getListView(board, pagination);
		for (Notice ntc : notice) {
			String date = simpleDateFormat.format(ntc.getRegdate());
			ntc.setStrRegDate(date);
		}
		pagination.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
		String userId = (String) session.getAttribute("userId");
		String engBoard = new NoticeLogic().board(board);
		model.addAttribute("engBoard", engBoard);
		session.setAttribute("board", board);
		model.addAttribute("board", board + " 게시판");
		model.addAttribute("notice", notice);
		model.addAttribute("orders", noticeService.getOrders());
		if (userId != null) {
			User user = userService.getUserById(userId);

			model.addAttribute("user", user);
			return "view/notice/customerList";
		} else {
			return "view/notice/list";
		}
	}

	// 입문 게시판 호출 메소드
	@GetMapping("beginlist")
	public String beginlist(Model model, HttpSession session, Pagination pagination, HttpServletRequest request) {
		String board = "입문";
		List<Notice> notice = noticeService.getListView(board, pagination);
		for (Notice ntc : notice) {
			String date = simpleDateFormat.format(ntc.getRegdate());
			ntc.setStrRegDate(date);
		}
		pagination.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
		String userId = (String) session.getAttribute("userId");
		String engBoard = new NoticeLogic().board(board);
		model.addAttribute("engBoard", engBoard);
		session.setAttribute("board", board);
		model.addAttribute("board", board + " 게시판");
		model.addAttribute("notice", notice);
		model.addAttribute("orders", noticeService.getOrders());
		if (userId != null) {
			User user = userService.getUserById(userId);

			model.addAttribute("user", user);
			return "view/notice/customerList";
		} else {
			return "view/notice/list";
		}
	}

	// 기타 게시판 호출 메소드
	@GetMapping("guitarlist")
	public String guitarlist(Model model, HttpSession session, Pagination pagination, HttpServletRequest request) {
		String board = "기타";
		List<Notice> notice = noticeService.getListView(board, pagination);
		for (Notice ntc : notice) {
			String date = simpleDateFormat.format(ntc.getRegdate());
			ntc.setStrRegDate(date);
		}
		pagination.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
		String userId = (String) session.getAttribute("userId");
		String engBoard = new NoticeLogic().board(board);
		model.addAttribute("engBoard", engBoard);
		session.setAttribute("board", board);
		model.addAttribute("board", board + " 게시판");
		model.addAttribute("notice", notice);
		model.addAttribute("orders", noticeService.getOrders());
		if (userId != null) {
			User user = userService.getUserById(userId);

			model.addAttribute("user", user);
			return "view/notice/customerList";
		} else {
			return "view/notice/list";
		}
	}

	// 베이스 게시판 호출 메소드
	@GetMapping("basslist")
	public String basslist(Model model, HttpSession session, Pagination pagination, HttpServletRequest request) {
		String board = "베이스";
		List<Notice> notice = noticeService.getListView(board, pagination);
		for (Notice ntc : notice) {
			String date = simpleDateFormat.format(ntc.getRegdate());
			ntc.setStrRegDate(date);
		}
		pagination.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
		String userId = (String) session.getAttribute("userId");
		String engBoard = new NoticeLogic().board(board);
		model.addAttribute("engBoard", engBoard);
		session.setAttribute("board", board);
		model.addAttribute("board", board + " 게시판");
		model.addAttribute("notice", notice);
		model.addAttribute("orders", noticeService.getOrders());
		if (userId != null) {
			User user = userService.getUserById(userId);

			model.addAttribute("user", user);
			return "view/notice/customerList";
		} else {
			return "view/notice/list";
		}
	}

	// 건반 게시판 호출 메소드
	@GetMapping("pianolist")
	public String pianolist(Model model, HttpSession session, Pagination pagination, HttpServletRequest request) {
		String board = "건반";
		List<Notice> notice = noticeService.getListView(board, pagination);
		for (Notice ntc : notice) {
			String date = simpleDateFormat.format(ntc.getRegdate());
			ntc.setStrRegDate(date);
		}
		pagination.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
		String userId = (String) session.getAttribute("userId");
		String engBoard = new NoticeLogic().board(board);
		model.addAttribute("engBoard", engBoard);
		session.setAttribute("board", board);
		model.addAttribute("board", board + " 게시판");
		model.addAttribute("notice", notice);
		model.addAttribute("orders", noticeService.getOrders());
		if (userId != null) {
			User user = userService.getUserById(userId);

			model.addAttribute("user", user);
			return "view/notice/customerList";
		} else {
			return "view/notice/list";
		}
	}

	// 드럼 게시판 호출 메소드
	@GetMapping("drumlist")
	public String drumlist(Model model, HttpSession session, Pagination pagination, HttpServletRequest request) {
		String board = "드럼";
		List<Notice> notice = noticeService.getListView(board, pagination);
		for (Notice ntc : notice) {
			String date = simpleDateFormat.format(ntc.getRegdate());
			ntc.setStrRegDate(date);
		}
		pagination.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
		String userId = (String) session.getAttribute("userId");
		String engBoard = new NoticeLogic().board(board);
		model.addAttribute("engBoard", engBoard);
		session.setAttribute("board", board);
		model.addAttribute("board", board + " 게시판");
		model.addAttribute("notice", notice);
		model.addAttribute("orders", noticeService.getOrders());
		if (userId != null) {
			User user = userService.getUserById(userId);

			model.addAttribute("user", user);
			return "view/notice/customerList";
		} else {
			return "view/notice/list";
		}
	}

	// 보컬 게시판 호출 메소드
	@GetMapping("vocallist")
	public String vocallist(Model model, HttpSession session, Pagination pagination, HttpServletRequest request) {
		String board = "보컬";
		List<Notice> notice = noticeService.getListView(board, pagination);
		for (Notice ntc : notice) {
			String date = simpleDateFormat.format(ntc.getRegdate());
			ntc.setStrRegDate(date);
		}
		pagination.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
		String userId = (String) session.getAttribute("userId");
		String engBoard = new NoticeLogic().board(board);
		model.addAttribute("engBoard", engBoard);
		session.setAttribute("board", board);
		model.addAttribute("board", board + " 게시판");
		model.addAttribute("notice", notice);
		model.addAttribute("orders", noticeService.getOrders());
		if (userId != null) {
			User user = userService.getUserById(userId);

			model.addAttribute("user", user);
			return "view/notice/customerList";
		} else {
			return "view/notice/list";
		}
	}

	// 게시글 호출 메소드
	@GetMapping("detail")
	public String detail(Model model, int id, HttpSession session, Pagination pagination, HttpServletRequest request) {
		Notice notice = noticeService.getDetailView(id);
		noticeService.upHit(id);
		List<Comment> comments = noticeService.getComment(id);
		for (Comment cmt : comments) {
			String date = simpleDateFormat.format(cmt.getRegdate());
			cmt.setStrRegDate(date);
		}
		String date = simpleDateFormat.format(notice.getRegdate());
		notice.setStrRegDate(date);

		String userId = (String) session.getAttribute("userId");
		pagination.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
		board = notice.getBoard();
		BookMark hasBookmark = myPageService.hasBookmark(id, userId);

		if (userId != null) {
			User user = userService.getUserById(userId);
			model.addAttribute("notice", notice);
			model.addAttribute("comments", comments);
			model.addAttribute("user", user);
			model.addAttribute("board", board);
			model.addAttribute("hasBookmark", hasBookmark);
			return "view/notice/customerDetail";
		} else {
			model.addAttribute("user", userId);
			model.addAttribute("notice", notice);
			model.addAttribute("comments", comments);
			model.addAttribute("board", board);
			model.addAttribute("hasBookmark", hasBookmark);
			return "view/notice/detail";
		}

	}

	// 게시글 작성 화면 호출 메소드
	@GetMapping("reg")
	public String reg(Model model, HttpSession session, Pagination pagination) {
		String userId = (String) session.getAttribute("userId");
		String board = (String) session.getAttribute("board");
		board = new NoticeLogic().board(board);
		if (userId != null) {
			User user = userService.getUserById(userId);
			NoticeReg noticeReg = new NoticeReg();

			model.addAttribute("board", board);
			model.addAttribute("user", user);
			model.addAttribute("noticeReg", noticeReg);
			return "view/notice/reg";
		} else {

			return "redirect:" + board + "list?" + pagination.getQueryString();
		}

	}

	// 게시글 등록 호출 메소드
	@PostMapping("reg")
	public String reg(@Valid NoticeReg noticeReg, BindingResult bindingResult, Model model, HttpSession session, Pagination pagination)
			throws IllegalStateException, IOException {
		String userId = (String) session.getAttribute("userId");
		String board = (String) session.getAttribute("board");
		board = new NoticeLogic().board(board);
		noticeReg.setWriterId(userId);
		try {
			board = noticeService.insertNotice(noticeReg, bindingResult, pagination);
			return "redirect:" + board + "list?" + pagination.getQueryString();
		} catch (Exception e) {
			e.printStackTrace();
			User user = userService.getUserById(userId);

			model.addAttribute("board", board);
			model.addAttribute("user", user);
			return "view/notice/reg";
		}
	}

	// 게시글 수정화면 불러오기 메소드
	@GetMapping("edit")
	public String edit(Model model, int id, HttpSession session, Pagination pagination) {
		Notice notice = noticeService.getEditNotice(id);
		String userId = (String) session.getAttribute("userId");
		User user = userService.getUserById(userId);

		if (userId.equals(notice.getWriterId())) {
			model.addAttribute("notice", notice);
			model.addAttribute("user", user);
			model.addAttribute("board", new NoticeLogic().board(notice.getBoard()));
			return "view/notice/edit";
		}
		return "redirect:detail";
	}

	// 게시글 수정 메소드
	@PostMapping(value = "edit", params = "cmd=수정")
	public String edit(Notice notice, Pagination pagination) throws IllegalStateException, IOException {
		if (notice.getBoard().equals("")) {
			return "redirect:edit";
		}
		board = noticeService.updateNotice(notice);
		// return "redirect:" + board + "list?" + pagination.getQueryString();
		return "redirect:detail?id=" + notice.getId();
	}

	// 게시글 삭제 메소드
	@PostMapping(value = "detail", params = "cmd=삭제")
	public String delete(Notice notice, HttpSession session, Pagination pagination) {
		System.out.println(notice.getId());
		String userId = (String) session.getAttribute("userId");
		Notice noticeDelete = noticeService.getDetailView(notice.getId());
		// 삭제한 글의 해당 게시판으로 이동할 수 있게 하는 로직
		String board = new NoticeLogic().board(noticeDelete.getBoard());

		if (userId.equals(noticeDelete.getWriterId()) || userId.equals("admin")) {
			noticeService.deleteNotice(notice.getId());
			return "redirect:" + board + "list?" + pagination.getQueryString();
		} else {
			return "redirect:" + board + "list?" + pagination.getQueryString();
		}

	}

	// 파일 다운로드 메소드
	@GetMapping("download")
	public ResponseEntity<Object> download(String file) throws MalformedURLException {
		UrlResource resource = new UrlResource(
				"file:" + System.getProperty("user.home") + "\\GaonDummyData" + "\\" + file);
		String encodedFileName = UriUtils.encode(file, StandardCharsets.UTF_8);
		String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
	}

	// 댓글 등록 메소드
	@PostMapping(value = "detail", params = "cmd=댓글")
	public String insertComment(String content, HttpSession session, Notice notice) {
		String writerId = (String) session.getAttribute("userId");
		User user = userService.getUserById(writerId);
		int noticeId = notice.getId();
		if (content.equals("") || content == null) {
			return "redirect:detail?id=" + noticeId;
		} else {
			Comment comment = new Comment();
			comment.setContent(content);
			comment.setNickname(user.getNickname());
			comment.setNoticeId(noticeId);

			noticeService.insertComment(comment);
			noticeService.upComment(noticeId);

			return "redirect:detail?id=" + noticeId;
		}
	}

	// 댓글 삭제하기 메소드
	@GetMapping("deleteComment")
	public String deleteComment(int id) {
		Comment comment = noticeService.getCommentById(id);
		noticeService.downComment(comment.getNoticeId());
		noticeService.deleteComment(id);
		return "redirect:detail?id=" + comment.getNoticeId();
	}
}