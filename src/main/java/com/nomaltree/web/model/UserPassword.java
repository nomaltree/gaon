package com.nomaltree.web.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPassword {

	@NotEmpty(message="비밀번호를 입력하세요")
	String prePwd;

	@NotEmpty(message="비밀번호를 입력하세요")
	@Size(min=4, max=12, message="4자리 이상 12자리 이하여야 합니다.")
	String newPwd;
}
