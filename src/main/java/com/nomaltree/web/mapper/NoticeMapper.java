package com.nomaltree.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nomaltree.web.dto.Comment;
import com.nomaltree.web.dto.Notice;

@Mapper
public interface NoticeMapper {

	//최신 게시글 10개 불러오기 메소드
	@Select("SELECT n.id ,n.title, n.regdate, n.hit, n.comment, u.nickname "
			+ "FROM notice n LEFT JOIN user u on n.writerId=u.id "
			+ "order by regdate desc "
			+ "limit 10")
	List<Notice> getNewestList();

	//게시글 검색 메소드
	@Select("SELECT sl.id ,sl.title, sl.regdate, sl.hit, sl.comment, u.nickname "
			+ "FROM ${board}listview sl LEFT JOIN user u on sl.writerId=u.id "
			+ "where ${keyword} like '%${query}%' "
			+ "order by regdate desc "
			+ "limit ${firstRecordIndex}, ${sz}")
	List<Notice> searchNotice(String keyword, String query, String board, int firstRecordIndex, int sz);

	//검색한 게시글 갯수를 구하는 메소드
	@Select("SELECT count(id) FROM "
			+ "(SELECT sl.id ,sl.title, sl.regdate, sl.hit, sl.comment, u.nickname "
			+ "FROM ${board}listview sl LEFT JOIN user u on sl.writerId=u.id "
			+ "where ${keyword} like '%${query}%' "
			+ "order by regdate desc) searchview")
	int getSearchCount(String keyword, String query, String board);

	//게시판 목록 불러오기 메소드
	@Select("SELECT lv.id, lv.title, lv.regdate, lv.hit, lv.comment, u.nickname "
			+ "FROM ${board}listview lv LEFT JOIN user u on lv.writerId=u.id "
			+ "order by regdate desc "
			+ "limit ${firstRecordIndex}, ${sz}")
	List<Notice> getListView(String board, int firstRecordIndex, int sz);

	//게시글 갯수를 구하는 메소드
	@Select("SELECT count(id) FROM ${board}listview")
	int getCount(String board);

	//게시글 번호로 게시글 정보 불러오기 메소드
	@Select("SELECT n.*, u.nickname "
			+ "FROM notice n left JOIN user u on n.writerId=u.id "
			+ "where n.id=#{id}")
	Notice getDetailView(int id);

	//조회수 증가 메소드
	@Update("UPDATE notice SET "
			+ "hit = hit + 1 "
			+ "where id=#{id}")
	void upHit(int id);

	//게시글 저장 메소드
	@Insert("INSERT INTO notice "
			+ "("
			+ "title, "
			+ "content, "
			+ "board, "
			+ "file, "
			+ "writerId "
			+ ") "
			+ "VALUES "
			+ "("
			+ "#{title}, "
			+ "#{content}, "
			+ "#{board},"
			+ "#{file}, "
			+ "#{writerId} "
			+ ")")
	void insertNotice(Notice notice);

	//게시글 수정 정보 불러오기 메소드
	@Select("SELECT * from notice "
			+ "where id = ${id}")
	Notice getEditNotice(int id);

	//게시글 수정 메소드
	@Update("UPDATE notice SET "
			+ "title = #{title}, "
			+ "content = #{content}, "
			+ "file = #{file}, "
			+ "board = #{board} "
			+ "WHERE id=#{id}")
	void updateNotice(Notice notice);

	//게시글 삭제 메소드
	@Delete("DELETE FROM notice WHERE id = #{id}")
	void deleteNotice(int id);

	//댓글 등록 메소드
	@Insert("INSERT INTO comment "
			+ "("
			+ "content, "
			+ "noticeId, "
			+ "nickname "
			+ ") "
			+ "VALUES "
			+ "("
			+ "#{content}, "
			+ "#{noticeId}, "
			+ "#{nickname} "
			+ ")")
	void insertComment(Comment comment);

	//댓글 수 증가 메소드
	@Update("UPDATE notice SET "
			+ "comment = comment + 1 "
			+ "where id=#{id}")
	void upComment(int id);

	//댓글 수 감소 메소드
	@Update("UPDATE notice SET "
			+ "comment = comment - 1 "
			+ "where id=#{id}")
	void downComment(int id);

	//댓글 불러오기 메소드
	@Select("SELECT * FROM comment "
			+ "where noticeId = #{id}")
	List<Comment> getComment(int id);

	//댓글 삭제하기 메소드
	@Delete("DELETE FROM comment "
			+ "where id = #{id}")
	void deleteComment(int id);

	//아이디로 댓글 불러오기 메소드
	@Select("SELECT * FROM comment "
			+ "where id=#{id}")
	Comment getCommentById(int id);
}
