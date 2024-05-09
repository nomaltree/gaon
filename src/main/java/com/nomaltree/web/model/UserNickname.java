package com.nomaltree.web.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserNickname {

	@NotEmpty(message="닉네임을 입력하세요")
	String nickname;
}
