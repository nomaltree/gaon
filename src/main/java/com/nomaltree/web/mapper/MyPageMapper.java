package com.nomaltree.web.mapper;

import org.apache.ibatis.annotations.Delete;

public interface MyPageMapper {

	//회원탈퇴
	@Delete("DELETE FROM user "
			+ "where id=#{id}")
	void deleteUser(String id);
}
