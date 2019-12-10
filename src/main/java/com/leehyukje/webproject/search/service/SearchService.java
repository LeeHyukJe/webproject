package com.leehyukje.webproject.search.service;

import com.leehyukje.webproject.search.common.WNSearchInfo;
import com.leehyukje.webproject.search.domain.SrchParamVO;

public interface SearchService {
	public WNSearchInfo setting(SrchParamVO value) throws Exception;
}
