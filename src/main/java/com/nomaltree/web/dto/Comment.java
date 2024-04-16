package com.nomaltree.web.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {
	String content;
	Date regdate;
	int noticeId;
	String nickname;
	int id;
}
