package com.nomaltree.web.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class User {

	@Id
	String id;
	String nickname;
	String password;

}
