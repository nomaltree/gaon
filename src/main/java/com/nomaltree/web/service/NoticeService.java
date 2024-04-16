package com.nomaltree.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nomaltree.web.dto.Comment;
import com.nomaltree.web.dto.Notice;
import com.nomaltree.web.logic.NoticeLogic;
import com.nomaltree.web.mapper.NoticeMapper;
import com.nomaltree.web.model.Pagination;

@Service
public class NoticeService {

	@Autowired NoticeMapper noticeMapper;

	//최신 게시글 10개 불러오기 메소드
	public List<Notice> getNewestList() {
		List<Notice> notice = noticeMapper.getNewestList();
		return notice;
	}
	//각 게시판 게시글 목록 불러오기 메소드
	public List<Notice> getListView(String board, Pagination pagination) {
		board = new NoticeLogic().board(board);
		pagination.setRecordCount(noticeMapper.getCount(new NoticeLogic().board(board)));
		List<Notice> notice = noticeMapper.getListView(board, pagination.getFirstRecordIndex(), pagination.getSz());
		return notice;
	}
	//게시글 검색 메소드
	public List<Notice> searchNotice(String keyword, String query, String board, Pagination pagination) {
		pagination.setRecordCount(noticeMapper.getSearchCount(keyword, query, board));
		List<Notice> notice = noticeMapper.searchNotice(keyword, query, board, pagination.getFirstRecordIndex(), pagination.getSz());
		return notice;
	}
	//게시글 갯수 불러오기 메소드
	public int searchNoticeCount(String keyword, String query, String board) {
		int count = noticeMapper.getSearchCount(keyword, query, board);
		return count;
	}
	//게시글 불러오기 메소드
	public Notice getDetailView(int id) {
		Notice notice = noticeMapper.getDetailView(id);
		return notice;
	}
	//조회수 증가 메소드
	public void upHit(int id) {
		noticeMapper.upHit(id);
	}
	//게시글 저장 메소드
	public String insertNotice(Notice notice, Pagination pagination) throws IllegalStateException, IOException {
		String board;
		board = new NoticeLogic().board(notice.getBoard());
		//int lastPage = (int)Math.ceil((double)noticeMapper.getCount(board) / pagination.getSz());
        pagination.setPg(1);
		//파일 업로드 구현 로직
		if(notice.getFiles().getOriginalFilename() != null && notice.getFiles().getOriginalFilename() != "") {
		MultipartFile files = notice.getFiles();
		notice.setFile(files.getOriginalFilename());
		String savePath = System.getProperty("user.home") + "\\GaonDummyData";
		if(!new File(savePath).exists()) {
			try {
				new File(savePath).mkdir();
			} catch(Exception e) {
				e.getStackTrace();
			}
		}
		String filePath = savePath + "\\" + notice.getFile();
		files.transferTo(new File(filePath));
		}
		//작성한 글의 해당 게시판으로 이동할 수 있게 하는 로직
		board = new NoticeLogic().board(notice.getBoard());
		noticeMapper.insertNotice(notice);
		return board;
	}
	//게시글 수정 정보 불러오기 메소드
	public Notice getEditNotice(int id) {
		Notice notice = noticeMapper.getEditNotice(id);
		return notice;
	}
	//게시글 수정 메소드
	public String updateNotice(Notice notice) throws IllegalStateException, IOException {
		String board;
		//파일 업로드 구현 로직
		if(notice.getFiles().getOriginalFilename() != null && notice.getFiles().getOriginalFilename() != "") {
			MultipartFile files = notice.getFiles();
			notice.setFile(files.getOriginalFilename());
			String savePath = System.getProperty("user.home") + "\\GaonDummyData";
			if(!new File(savePath).exists()) {
				try {
					new File(savePath).mkdir();
				} catch(Exception e) {
					e.getStackTrace();
				}
			}
			String filePath = savePath + "\\" + notice.getFile();
			files.transferTo(new File(filePath));
		}
		//작성한 글의 해당 게시판으로 이동할 수 있게 하는 로직
		board = new NoticeLogic().board(notice.getBoard());
		noticeMapper.updateNotice(notice);
		return board;
	}
	//게시글 삭제 메소드
	public void deleteNotice(int id) {
		noticeMapper.deleteNotice(id);
	}
	//댓글 수 증가 메소드
	public void upComment(int id) {
		noticeMapper.upComment(id);
	}
	//댓글 수 감소 메소드
	public void downComment(int id) {
		noticeMapper.downComment(id);
	}
	//댓글 등록 메소드
	public void insertComment(Comment comment) {
		noticeMapper.insertComment(comment);
	}
	//댓글 불러오기 메소드
	public List<Comment> getComment(int id) {
		List<Comment> comment = noticeMapper.getComment(id);
		return comment;
	}
	//댓글 삭제하기 메소드
	public void deleteComment(int id) {
		noticeMapper.deleteComment(id);
	}
	//아이디로 댓글 정보 불러오기 메소드
	public Comment getCommentById(int id) {
		Comment comment = noticeMapper.getCommentById(id);
		return comment;
	}
}
