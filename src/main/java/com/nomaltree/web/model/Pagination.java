package com.nomaltree.web.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Pagination {
    int pg = 1;        // 현재 페이지 번호
    int sz = 10;       // 페이지 당 레코드 수
    int recordCount;   // 전체 레코드 수
    String url;        // 목록 페이지 url
    int od = 0; 	   //정렬 기능 변수 초기값은 최신순
    String query;

    public int getFirstRecordIndex() {
        return (pg - 1) * sz;
    }

    public String getQueryString() {
    	try {
			String encQuery = URLEncoder.encode(query, "UTF-8");
			return String.format("pg=%d&od=%d&query=%s", pg, od, encQuery);
    	} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return String.format("pg=%d&od=%d&query=%s", pg, od, query);
    }

    public List<Page> getPages() {
        List<Page> pages = new ArrayList<Page>();
        int pageCount = (int)Math.ceil((float)recordCount / sz);
        if (pg > pageCount) {
			pg = pageCount;
		}
        if (url.indexOf("pg=") < 0) {
			url = url + (url.indexOf("?") < 0 ? "?" : "&") + "pg=1";
		}
        int baseNo = (pg - 1) / 10 * 10;
        if (baseNo > 0) {
			pages.add(new Page("<", false, url.replaceAll("pg=[0-9]+", "pg=" + baseNo)));
		}
        for (int i = 1; i <= 10; ++i) {
            int no = baseNo + i;
            if (no > pageCount) {
				break;
			}
            pages.add(new Page(String.valueOf(no), no == pg,
                    url.replaceAll("pg=[0-9]+", "pg=" + no)));
        }
        int no = baseNo + 11;
        if (no <= pageCount) {
			pages.add(new Page(">", false, url.replaceAll("pg=[0-9]+", "pg=" + no)));
		}
        return pages;
    }
}

@Data
@AllArgsConstructor
class Page {
    String label;
    boolean active;
    String url;
}

