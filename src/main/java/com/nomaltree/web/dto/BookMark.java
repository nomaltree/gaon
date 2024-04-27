package com.nomaltree.web.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BookMark {

	int noticeId;
	String userId;
	Date regdate;



	int id;
	String title;
	String nickname;
	int hit;
	int comment;

}
