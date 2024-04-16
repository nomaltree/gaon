package com.nomaltree.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLoginSignup {

	@NotEmpty @NotBlank
	String id;

	@NotEmpty @NotBlank
	String nickname;

	@NotEmpty @NotBlank
	String password;

}
