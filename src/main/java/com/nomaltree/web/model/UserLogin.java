package com.nomaltree.web.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLogin {
	@NotEmpty(message="아이디를 입력하세요.")
	String id;

	@NotEmpty(message="비밀번호를 입력하세요")
	String password;

}
