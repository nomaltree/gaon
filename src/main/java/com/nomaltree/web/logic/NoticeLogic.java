package com.nomaltree.web.logic;

public class NoticeLogic {

	//작성한 글의 해당 게시판으로 이동할 수 있게 하는 로직
	public String board(String board) {
		switch(board) {
		case "자유":
			board = "free";
			break;
		case "장터":
			board = "market";
			break;
		case "입문":
			board = "begin";
			break;
		case "기타":
			board = "guitar";
			break;
		case "베이스":
			board = "bass";
			break;
		case "건반":
			board = "piano";
			break;
		case "드럼":
			board = "drum";
			break;
		case "보컬":
			board = "vocal";
			break;
		}
		return board;
	}
}