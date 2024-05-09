package com.nomaltree.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.nomaltree.web.dto.BookMark;
import com.nomaltree.web.dto.Comment;
import com.nomaltree.web.dto.Notice;
import com.nomaltree.web.dto.User;
import com.nomaltree.web.mapper.MyPageMapper;
import com.nomaltree.web.mapper.UserMapper;
import com.nomaltree.web.model.UserNickname;
import com.nomaltree.web.model.UserPassword;

@Service
public class MyPageService {

	@Autowired MyPageMapper myPageMapper;
	@Autowired UserMapper userMapper;

	//회원탈퇴 메소드
	public void deleteUser(String id) {
		myPageMapper.deleteUser(id);
	}

	//내가 쓴 게시글 불러오기 메소드
	public List<Notice> getMyNotice(String writerId) {
		List<Notice> myNotice = myPageMapper.getMyNotice(writerId);
		return myNotice;
	}

	//내가 쓴 댓글 불러오기 메소드
	public List<Comment> getMyComment(String nickname) {
		List<Comment> myComment = myPageMapper.getMyComment(nickname);
		return myComment;
	}

	//회원닉네임 수정 메소드
	public void editId(UserNickname userNickname, BindingResult bindingResult, String id) throws Exception {
		if(bindingResult.hasErrors()) {
			throw new Exception("변경할 수 없습니다.");
		}
		User user2 = userMapper.getUserByid(id);
		if(user2.getNickname().equals(userNickname.getNickname())) {
			bindingResult.rejectValue("nickname", null, "이전 닉네임과 같습니다.");
			throw new Exception("이전 닉네임과 같습니다");
		}
		User user3 = userMapper.getUserBynickname(userNickname.getNickname());
		if(user3 != null) {
			bindingResult.rejectValue("nickname", null, "닉네임이 중복됩니다.");
			throw new Exception("닉네임이 중복됩니다.");
		}
		myPageMapper.editNickName(id, userNickname.getNickname());
	}

	//회원비밀번호 수정 메소드
	public void editPwd(UserPassword userPassword, BindingResult bindingResult, String id) throws Exception {
		if(bindingResult.hasErrors()) {
			throw new Exception("변경할 수 없습니다.");
		}
		User user2 = userMapper.getUserByid(id);
		if(!user2.getPassword().equals(userPassword.getPrePwd())) {
			bindingResult.rejectValue("prePwd", null, "현재 비밀번호가 일치하지 않습니다.");
			throw new Exception("현재 비밀번호가 일치하지 않습니다.");
		}
		if(userPassword.getPrePwd().equals(userPassword.getNewPwd())) {
			bindingResult.rejectValue("newPwd", null, "현재 비밀번호와 똑같습니다.");
			throw new Exception("현재 비밀번호와 똑같습니다.");
		}
		myPageMapper.editPwd(userPassword.getNewPwd(), id);
	}

	//즐겨찾기 한 게시글 불러오기 메소드
	public List<BookMark> getMyBookMark(String userId) {
		List<BookMark> myBookMark = myPageMapper.getMyBookMark(userId);
		return myBookMark;
	}

	//즐겨찾기 등록 메소드
	public void insertBookmark(int noticeId, String userId) {
		myPageMapper.insertBookmark(noticeId, userId);
	}

	//즐겨찾기 해제 메소드
	public void deleteBookmark(int noticeId, String userId) {
		myPageMapper.deleteBookmark(noticeId, userId);
	}

	//즐겨찾기 확인 메소드
	public BookMark hasBookmark(int noticeId, String userId) {
		return myPageMapper.hasBookmark(noticeId, userId);
	}
}
