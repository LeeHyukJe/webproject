package com.leehyukje.webproject.search.service;

import org.springframework.stereotype.Service;

import com.leehyukje.webproject.search.common.WNSearchInfo;
import com.leehyukje.webproject.search.domain.SrchParamVO;

@Service
public interface ResultService {
	
	public String result(WNSearchInfo wnSearchInfo, String colName);
}
