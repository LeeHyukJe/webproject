package com.leehyukje.webproject.search.service;


import org.springframework.stereotype.Service;

import com.leehyukje.webproject.search.common.WNCollection;
import com.leehyukje.webproject.search.common.WNDefine;
import com.leehyukje.webproject.search.common.WNSearch;
import com.leehyukje.webproject.search.common.WNSearchInfo;
import com.leehyukje.webproject.search.domain.SrchParamVO;



@Service
public class SearchServiceImpl implements SearchService {

	public WNSearch wnsearch;
	public WNSearchInfo wnSearchInfo;

	public static int TOTALVIEWCOUNT = 5; // 통합검색시 기본적으로 보여주는 갯
	public static int COLLECTIONVIEWCOUNT = 3; // 컬렉션 검색시 기본적으로 보여주는 갯수

	String START_DATE = "1970.01.01"; //

	int totalCount = 0;
	int startCount = 0;

	String strOperation = "";

	// 실시간 검색어 화면 출력 여부 체크
	boolean isRealTimeKeyword = false;

	// 오타 후 추천 검색어 화면 출력 여부 체크
	boolean useSuggestedQuery = true;
	String suggestQuery = "";

	// 디버깅 보기 설정
	boolean isDebug = true; 

	@Override
	public WNSearchInfo setting(SrchParamVO value) throws Exception {
		// TODO Auto-generated method stub
		String[] searchFields = null;
		if (value.getSearchField() != null)
			searchFields = value.getSearchField().split(",");



		String[] collections = null;

		if (value.getCollection().equals("ALL")) {
			collections = WNCollection.COLLECTIONS;
		} else {
			collections = new String[] { value.getCollection() };
		}

		if (value.getReQuery().equals("1")) {
			value.setRealQuery(value.getQuery() + " " + value.getRealQuery());
		} else if (value.getReQuery().equals("2")) {
			value.setRealQuery(value.getQuery());
		}

		//wnsearch = new WNSearch(isDebug, false, collections, searchFields);
		wnSearchInfo = new WNSearchInfo.WNSearchBuilder().isDebug(isDebug).isUidSrch(false).collections(collections).searchFields(searchFields).Build();

		int viewResultCount = value.getListCount();

		if (value.getCollection().equals("ALL") || value.getCollection().equals(""))
			viewResultCount = TOTALVIEWCOUNT;

		for (int i = 0; i < collections.length; i++) {

			wnSearchInfo.setCollectionInfoValue(collections[i], WNDefine.PAGE_INFO, value.getStartCount() + "," + viewResultCount);

			if (!value.getQuery().equals("")) {
				wnSearchInfo.setCollectionInfoValue(collections[i], WNDefine.SORT_FIELD, value.getSort() + "/DESC");
				//wnSearchInfo.setCollectionInfoValue(collections[i], WNDefine.SORT_FIELD, "RANK/DESC");
			} else {
				wnSearchInfo.setCollectionInfoValue(collections[i], WNDefine.DATE_RANGE,
						START_DATE.replaceAll("[.]", "/") + ",2030/01/01,-");
				wnSearchInfo.setCollectionInfoValue(collections[i], WNDefine.SORT_FIELD, "DATE/DESC");
			}

			// searchField 값이 있으면 설정, 없으면 기본검색필드
			if (!value.getSearchField().equals("") && value.getSearchField().indexOf("ALL") == -1) {
				wnSearchInfo.setCollectionInfoValue(collections[i], WNDefine.SEARCH_FIELD, value.getSearchField());
			}

			// operation 설정
			if (!strOperation.equals("")) {
				wnSearchInfo.setCollectionInfoValue(collections[i], WNDefine.FILTER_OPERATION, strOperation);
			}

			// exquery 설정
			if (!value.getExquery().equals("")) {
				wnSearchInfo.setCollectionInfoValue(collections[i], WNDefine.EXQUERY_FIELD, value.getExquery());
			}	
			// 기간 설정 , 날짜가 모두 있을때
			if (!value.getStartDate().equals("") && !value.getEndDate().equals("")) {
				wnSearchInfo.setCollectionInfoValue(collections[i], WNDefine.DATE_RANGE,
						value.getStartDate().replaceAll("[.]", "/") + "," + value.getEndDate().replaceAll("[.]", "/")
								+ ",-");
			}


		}
		System.out.println("현재VO " + value.toString());
		wnSearchInfo.search(value.getRealQuery(), isRealTimeKeyword, WNDefine.CONNECTION_CLOSE, useSuggestedQuery);

		String debugMsg = wnSearchInfo.printDebug() != null ? wnSearchInfo.printDebug().trim() : "";
		System.out.println(debugMsg);

//		if (!value.getCollection().equals("ALL")) {
//			String paging = wnSearchInfo.getPageLinks(wnSearchInfo, 10, 10, 10);
//			value.setPaging(paging);
//		}

		return wnSearchInfo;
	}



}
