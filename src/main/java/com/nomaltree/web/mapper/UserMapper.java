package com.nomaltree.web.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.nomaltree.web.dto.User;

@Mapper
public interface UserMapper {

	//로그인 확인용 메소드
	@Select("SELECT * FROM user "
			+ "where id=#{id}")
	User getUserId(String id);

	//회원가입
	@Insert("INSERT INTO user "
			+ "("
			+ "id, "
			+ "nickname, "
			+ "password "
			+ ") "
			+ "VALUES "
			+ "("
			+ "#{id}, "
			+ "#{nickname}, "
			+ "#{password} "
			+ ")")
	void insertUser(User user);

	//특정 id를 가진 유저정보 검색메소드
	@Select("SELECT * FROM user "
			+ "where id = #{id}")
	User getUserByid(String id);

	//특정 nickname을 가진 유저정보 검색메소드
	@Select("SELECT * FROM user "
			+ "where nickname = #{nickname}")
	User getUserBynickname(String nickname);
}
