package com.nomaltree.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nomaltree.web.dto.BookMark;
import com.nomaltree.web.dto.Comment;
import com.nomaltree.web.dto.Notice;
import com.nomaltree.web.mapper.MyPageMapper;

@Service
public class MyPageService {

	@Autowired MyPageMapper myPageMapper;

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
	public void editId(String id, String nickname) {
		myPageMapper.editNickName(id, nickname);
	}

	//회원비밀번호 수정 메소드
	public void editPwd(String id, String password) {
		myPageMapper.editPwd(password, id);
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
