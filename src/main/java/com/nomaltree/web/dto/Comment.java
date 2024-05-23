package com.nomaltree.web.dto;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String content;
	Date regdate;
	String strRegDate;
	int noticeId;
	String nickname;

}
