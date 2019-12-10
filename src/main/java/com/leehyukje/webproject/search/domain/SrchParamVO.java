package com.leehyukje.webproject.search.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SrchParamVO {
	private int startCount;
	private String mode;
	private String sort;
	private String collection;
	private String range;
	private String startDate;
	private String endDate;
	private String searchField;
	private String reQuery;
	private String realQuery;
	private String printView;
	private String exquery;
	private String query;
	private String paging;
	private int listCount;

	public SrchParamVO() {
		this.startCount = 0;
		this.mode = "";
		this.sort = "DATE";
		this.collection = "ALL";
		this.range = "";
		this.startDate = "";
		this.endDate = "";
		this.searchField = "";
		this.reQuery = "2";
		this.realQuery = "";
		this.printView = "";
		this.exquery = "";
		this.query = "";
		this.paging = "";
		this.listCount=3;
	}

}
