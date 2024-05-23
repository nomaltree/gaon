package com.nomaltree.web.dto;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BookMark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int noticeId;
	String userId;
	Date regdate;
	String strRegDate;



	int id;	//게시글 번호로 noticeId와 중복
	String title;
	String nickname;
	int hit;
	int comment;

}
