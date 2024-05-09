package com.nomaltree.web.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSignUp {

	@NotEmpty(message="아이디를 입력하세요.")
	String id;

	@NotEmpty(message="닉네임을 입력하세요")
	String nickname;

	@NotEmpty(message="비밀번호를 입력하세요")
	@Size(min=4, max=12, message="4 자리 이상 12 자리 이하여야 합니다.")
	String password;

	@NotEmpty(message="비밀번호를 한번 더 입력하세요.")
	String chkpassword;
}
