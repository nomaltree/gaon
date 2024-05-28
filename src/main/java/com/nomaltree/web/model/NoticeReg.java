package com.nomaltree.web.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class NoticeReg {

	int id;

	@NotEmpty(message="제목을 입력해주세요.")
	String title;
	int hit;
	Date regdate;
	String strRegDate;

	@NotEmpty(message="내용을 입력해주세요.")
	String content;
	int comment;
	MultipartFile files;
	String file;
	String board;
	String writerId;

	String nickname;
}
