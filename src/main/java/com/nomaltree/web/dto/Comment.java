package com.nomaltree.web.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {

	int id;
	String content;
	Date regdate;
	String strRegDate;
	int noticeId;
	String nickname;

}
