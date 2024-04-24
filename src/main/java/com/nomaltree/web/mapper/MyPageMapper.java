package com.nomaltree.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nomaltree.web.dto.BookMark;
import com.nomaltree.web.dto.Comment;
import com.nomaltree.web.dto.Notice;

@Mapper
public interface MyPageMapper {

	//회원탈퇴
	@Delete("DELETE FROM user "
			+ "where id=#{id}")
	void deleteUser(String id);

	//내가 쓴 게시글 불러오기
	@Select("SELECT * FROM notice "
			+ "where writerId = #{writerId}")
	List<Notice> getMyNotice(String writerId);

	//내가 쓴 댓글 불러오기
	@Select("SELECT * FROM comment "
			+ "where nickname=#{nickname}")
	List<Comment> getMyComment(String nickname);

	//회원닉네임 수정
	@Update("UPDATE user SET "
			+ "nickname = #{nickname} "
			+ "where id = #{id}")
	void editId(@Param("id")String id, @Param("nickname")String nickname);

	//즐겨찾기 한 게시글 불러오기
	@Select("select bmv.id, bmv.title, bmv.nickname, mybook.regdate, bmv.hit, bmv.comment from "
			+ "(select * from bookmark "
			+ "where userId = '${userId}') mybook left join bookmarkview bmv "
			+ "on mybook.noticeId = bmv.id "
			+ "order by mybook.regdate desc")
	List<BookMark> getMyBookMark(String userId);

}
