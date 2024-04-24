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
		myPageMapper.editId(id, nickname);
	}

	//즐겨찾기 한 게시글 불러오기 메소드
	public List<BookMark> getMyBookMark(String userId) {
		List<BookMark> myBookMark = myPageMapper.getMyBookMark(userId);
		return myBookMark;
	}
}
