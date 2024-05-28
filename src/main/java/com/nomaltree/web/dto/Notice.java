package com.nomaltree.web.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Notice {

	int id;
	String title;
	int hit;
	Date regdate;
	String strRegDate;
	String content;
	int comment;
	MultipartFile files;
	String file;
	String board;
	String writerId;

	String nickname;
}
