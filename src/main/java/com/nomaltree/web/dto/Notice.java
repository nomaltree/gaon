package com.nomaltree.web.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
