package com.nomaltree.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
			+ "where writerId = #{writerId} "
			+ "order by regdate desc")
	List<Notice> getMyNotice(String writerId);

	//내가 쓴 댓글 불러오기
	@Select("SELECT * FROM comment "
			+ "where nickname=#{nickname} "
			+ "order by regdate desc")
	List<Comment> getMyComment(String nickname);

	//회원닉네임 수정
	@Update("UPDATE user SET "
			+ "nickname = #{nickname} "
			+ "where id = #{id}")
	void editNickName(@Param("id")String id, @Param("nickname")String nickname);

	//회원비밀번호 수정
	@Update("UPDATE user SET "
			+ "password = #{password} "
			+ "where id = #{id}")
	void editPwd(@Param("password")String password, @Param("id")String id);

	//즐겨찾기 한 게시글 불러오기
	@Select("select bmv.id, bmv.title, bmv.nickname, mybook.regdate, bmv.hit, bmv.comment from "
			+ "(select * from bookmark "
			+ "where userId = '${userId}') mybook left join bookmarkview bmv "
			+ "on mybook.noticeId = bmv.id "
			+ "order by mybook.regdate desc")
	List<BookMark> getMyBookMark(String userId);

	//즐겨찾기 등록 메소드
	@Insert("insert into bookmark (noticeId, userId) "
			+ "values ( "
			+ "#{noticeId}, "
			+ "#{userId} "
			+ ")")
	void insertBookmark(@Param("noticeId")int noticeId, @Param("userId")String userId);

	//즐겨찾기 해제 메소드
	@Delete("delete from bookmark "
			+ "where noticeId = #{noticeId} && "
			+ "userId = #{userId}")
	void deleteBookmark(@Param("noticeId")int noticeId, @Param("userId")String userId);

	//즐겨찾기 확인 메소드
	@Select("select * from bookmark "
			+ "where noticeId = #{noticeId} && "
			+ "userId = #{userId}")
	BookMark hasBookmark(@Param("noticeId")int noticeId, @Param("userId")String userId);

}
