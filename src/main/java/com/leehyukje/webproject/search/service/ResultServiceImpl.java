package com.leehyukje.webproject.search.service;

import java.io.IOException;
import java.io.StringWriter;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.leehyukje.webproject.search.common.WNSearchInfo;
import com.leehyukje.webproject.search.domain.SrchParamVO;

import lombok.extern.java.Log;

@Service
@Log
public class ResultServiceImpl implements ResultService {

	@Override
	public String result(WNSearchInfo wnSearchInfo, String colName) {
		// TODO Auto-generated method stub
		JsonFactory jsonFactory = new JsonFactory();
        StringWriter stringWriter = new StringWriter();
        JsonGenerator generator=null;
		try {
	        int count = wnSearchInfo.getResultCount(colName);
	        int thisTotalCount = wnSearchInfo.getResultTotalCount(colName);
	        
			generator = jsonFactory.createGenerator(stringWriter);
			generator.useDefaultPrettyPrinter();
			generator.writeStartObject();
			generator.writeFieldName("collection");
			generator.writeString(colName);
			generator.writeFieldName("result");
			generator.writeStartArray();
			if(thisTotalCount>0) {
				for(int i=0;i<count;i++) {
					generator.writeStartObject();
					String DOCID = wnSearchInfo.getField(colName, "DOCID", i, false);
					String TITLE = wnSearchInfo.getField(colName, "TITLE", i, false);
					String CONTENT = wnSearchInfo.getField(colName, "CONTENT", i, false);
					generator.writeFieldName("DOCID");
					generator.writeString(DOCID);
					generator.writeFieldName("TITLE");
					generator.writeString(TITLE);
					generator.writeFieldName("CONTENT");
					generator.writeString(CONTENT);
					generator.writeEndObject();
				}
			}
			generator.writeEndArray();
			generator.writeEndObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				generator.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stringWriter.toString();
	}


	public static void main(String[] args) throws Exception {
		ResultServiceImpl test = new ResultServiceImpl();
		SearchServiceImpl search = new SearchServiceImpl();
		SrchParamVO vo = new SrchParamVO();
		vo.setCollection("clien");
		log.info(test.result(search.setting(vo), "clien"));
	}

}
