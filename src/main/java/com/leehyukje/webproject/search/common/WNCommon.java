package com.leehyukje.webproject.search.common;


import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import QueryAPI530.Search;

/**
*  file: WNCommon.jsp

*  subject: Search Formula-1 API Wrapper Ŭ����
*  ------------------------------------------------------------------------
*  @original author: WISEnut
*  @edit author: WISEnut
*  @update date 2012.04.24
*  ------------------------------------------------------------------------
*/

public class WNCommon {
   private Search search = null;
   private JspWriter out = null;
   boolean isDebug = false;
   

   private WNUtils wnutils;


   /**
    * SF-1 Search Ŭ���� ��ü�� �����ϴ� WNCommon�� ������ �Լ��̴�.
    */
   public WNCommon() {
       this.search = new Search();
       this.wnutils = new WNUtils();
   }

   /**
    * WNCommon �����ε�(overloading) �Լ��̴�.
    * WNCommon�� �ܵ����� ����� ��� debug ��������� ������ �� �ִ�.
    * @param out
    * @param isDubug
    */
   public WNCommon(JspWriter out, boolean isDubug) {
       this.search = new Search();
       this.out = out;
       this.isDebug = isDubug;
   }

   /**
    * �˻� ���� Ű����� ���ǳ� ��¿� ���� ���������� ���Ѵ�.
    * v4.x ȣȯ method�̹Ƿ� COMMON_OR_WHEN_NORESULT�� �������� �ʴ´�.
    * @param query
    * @param charSet
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    * @deprecated  As of v4.x, replaced by
    * {@link #setCommonQuery(String,String,int)}
    */
   public int setCommonQuery(String query, String charSet) {
       int ret = 0;
       ret = search.w3SetCodePage(charSet);
       ret = search.w3SetQueryLog(1);
       ret = search.w3SetTraceLog(0);
       ret = search.w3SetCommonQuery(query, 0);
       return ret;
   }

    /**
    * �˻� ���� UID�� ���ǳ� ��¿� ���� ���������� ���Ѵ�.
    * @param charSet
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setUidQuery(String charSet) {
       int ret = 0;
       ret = search.w3SetCodePage(charSet);
       ret = search.w3SetQueryLog(0);
       return ret;
   }

   /**
    * �˻� ���� Ű����� ���ǳ� ��¿� ���� ���������� ���Ѵ�.
    * @param query
    * @param charSet
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setCommonQuery(String query, String charSet, int useOrResult, boolean useSuggestedQuery) {
       int ret = 0;
       ret = search.w3SetCodePage(charSet);
       ret = search.w3SetQueryLog(1);
       ret = search.w3SetTraceLog(0);
       ret = search.w3SetCommonQuery(query, useOrResult);
       if(useSuggestedQuery) {
           ret = search.w3SetSpellCorrectionQuery(query, 0);
       }
       return ret;
   }

   /**
    * setCommonQuery�� �����ε�(overloading) �Լ���
    * session������ �ΰ�������� ����� �� �ִ�.
    * v4.x ȣȯ method�̹Ƿ� COMMON_OR_WHEN_NORESULT�� �������� �ʴ´�.
    * @param query
    * @param charSet
    * @param logInfo
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    * @deprecated  As of v4.x, replaced by
    * {@link #setCommonQuery(String,String,String[],int)}
    */
   public int setCommonQuery(String query, String charSet, String[] logInfo) {
       int ret = 0;
       if (logInfo != null && logInfo.length > 2) {
           ret = search.w3SetSessionInfo(logInfo[0], logInfo[1], logInfo[2]);
       }
       setCommonQuery(query, charSet);
       return ret;
   }


   /**
    * setCommonQuery�� �����ε�(overloading) �Լ���
    * session������ �ΰ�������� ����� �� �ִ�.
    * @param query
    * @param charSet
    * @param logInfo
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setCommonQuery(String query, String charSet, int useOrResult, String[] logInfo) {
       int ret = 0;
       if (logInfo != null && logInfo.length > 2) {
           ret = search.w3SetSessionInfo(logInfo[0], logInfo[1], logInfo[2]);
       }
       ret = search.w3SetCodePage(charSet);
       ret = search.w3SetQueryLog(1);
       ret = search.w3SetCommonQuery(query, useOrResult);
       return ret;
   }

   /**
    * setCommonQuery�� �����ε�(overloading) �Լ���
    * session������ �ΰ�������� ����� �� �ִ�.
    * @param query
    * @param charSet
    * @param logInfo
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setCommonQuery(String query, String charSet, int useOrResult, boolean useSuggestedQuery, String[] logInfo) {
       int ret = 0;
       if (logInfo != null && logInfo.length > 2) {
           ret = search.w3SetSessionInfo(logInfo[0], logInfo[1], logInfo[2]);
       }
       ret = search.w3SetCodePage(charSet);
       ret = search.w3SetQueryLog(1);
       ret = search.w3SetCommonQuery(query, useOrResult);
       if(useSuggestedQuery) {
           ret = search.w3SetSpellCorrectionQuery(query, 0);
       }
       return ret;
   }

   /**
    * �÷��� ���� �˻��� ����
    * @param collectionName
    * @param query
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setCollectionQuery(String collectionName, String query) {
       return search.w3SetCollectionQuery(collectionName, query);
   }


   /**
    * �÷��� ���� boost query ����
    * @param collectionName
    * @param boost query
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setBoostQuery(String collectionName, String query) {
       int idx = query.lastIndexOf("/");
       int option = Integer.parseInt(query.substring(idx, query.length()));
       return search.w3SetBoostQuery(collectionName, query, option);
   }

   /**
    * �÷��� ���� boost query ����
    * @param collectionName
    * @param boost query
    * @param option 0,1,2
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setBoostQuery(String collectionName, String query, int option) {
       return search.w3SetBoostQuery(collectionName, query, option);
   }

   /**
    * �˻��ϰ��� �ϴ� �÷����� UID�� �����Ѵ�.
    * @param collectionName
    * @param values
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setUid(String collectionName, String[] values,
           String searcherId) {
       int ret = 0;
       for (int i = 0; i < values.length; i++) {
           long uid = 0L;
           try {
               uid = Long.parseLong(values[i]);
           } catch (NumberFormatException e) {
               return -1;
           }
           ret = search.w3AddUid(collectionName, uid, searcherId);
       }
       return ret;
   }

   /**
    * �˻���� �÷���, ���м��� �������, ��ҹ��� ���������� �����Ѵ�.
    * v4.x ȣȯ method�̹Ƿ� USEORIGINAL, USESYNONYM�� �������� �ʴ´�.
    * @param collectionName
    * @param useKma
    * @param isCase
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    * @deprecated  As of v4.5, replaced by
    * {@link #addCollection(String,int,int,int,int,int)}
    */
   public int addCollection(String collectionName, int useKma, int isCase) {
       int ret = 0;
       ret = search.w3AddCollection(collectionName);
        //USEMA, ISCASE, USEORIGINAL, USESYNONYM
       ret = search.w3SetQueryAnalyzer(collectionName, useKma, isCase, 1,1);
       ret = search.w3SetDuplicateDetection(collectionName);
       //systemOut("[w3AddCollection] "+collectionName);
       return ret;
   }

   /**
    * �˻���� �÷���, ���м��� �������, ��ҹ��� ���������� �����Ѵ�.
    * @param collectionName
    * @param useKma
    * @param isCase
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int addCollection(String collectionName, int useKma, int isCase,
           int useOriginal, int useSynonym, int duplicateDectection) {
       int ret = 0;
       ret = search.w3AddCollection(collectionName);
       //USEMA, ISCASE, USEORIGINAL, USESYNONYM
       ret = search.w3SetQueryAnalyzer(collectionName, useKma, isCase, useOriginal, useSynonym);
       //systemOut("[w3AddCollection] "+collectionName);
       if(duplicateDectection == 1) {
           ret = search.w3SetDuplicateDetection(collectionName);
       }
       return ret;
   }

   /**
    * �߻� �÷��� �߰�
    * v4.x ȣȯ method�̹Ƿ� USEORIGINAL, USESYNONYM�� �������� �ʴ´�.
    * @param aliasName ������ ��Ī �÷��Ǹ�
    * @param collectionName ���� �÷���
    * @param useKma
    * @param isCase
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    * @deprecated  As of v4.5, replaced by
    * {@link #addAliasCollection(String,String,int,int,int,int,int)}
    */
   public int addAliasCollection(String aliasName, String collectionName,
           int useKma, int isCase) {
       int ret = 0;
       ret = search.w3AddAliasCollection(aliasName, collectionName);
       ret = search.w3SetQueryAnalyzer(aliasName, useKma, isCase, 1, 1);
       ret = search.w3SetDuplicateDetection(aliasName);
       return ret;
   }

   /**
    * �߻� �÷��� �߰�
    * @param aliasName ������ ��Ī �÷��Ǹ�
    * @param collectionName ���� �÷���
    * @param useKma
    * @param isCase
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int addAliasCollection(String aliasName, String collectionName,
           int useKma, int isCase, int useOriginal, int useSynonym, int duplicateDectection) {
       int ret = 0;
       ret = search.w3AddAliasCollection(aliasName, collectionName);
       ret = search.w3SetQueryAnalyzer(aliasName, useKma, isCase,
               useOriginal, useSynonym);
       if(duplicateDectection == 1) {
           ret = search.w3SetDuplicateDetection(aliasName);
       }
       return ret;
   }

   /**
    * �˻��� ����
    * @param ip
    * @param port
    * @param timeOut
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int getConnection(String ip, int port, int timeOut) {
       int ret = search.w3ConnectServer(ip, port, timeOut);
       return ret;
   }

   /**
    * �ش� �÷����� �˻� ��� �ʵ带 ���� ���� �����Ѵ�.
    * v4.x ȣȯ method�̹Ƿ� fieldscore�� �������� �ʴ´�.
    * @param collectionName
    * @param fields
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    * @deprecated  As of v4.5, replaced by
    * {@link #setSearchField(String,String)}
    **/
   public int addSearchField(String collectionName, String[] fields) {
       int ret = 0;
       for (int i = 0; i < fields.length; i++) {
           ret = search.w3AddSearchField(collectionName, fields[i]);
           systemOut("[w3AddSearchField] " + collectionName + " / "
                   + fields[i]);
       }
       return ret;
   }

    /**
    * �˻� �ʵ庰 ��ŷ ���ھ�(score)�� �����Ѵ�.
    * @param collectionName
    * @param fields TITLE,CONTENT
    * @param rankScores 100,30
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int addSearchFieldScore(String collectionName, String[] fields, int[] rankScores) {
       int ret = 0;
       for (int i = 0; i < fields.length; i++) {
           ret = search.w3AddSearchFieldScore(collectionName, fields[i], rankScores[i]);
           systemOut("[w3AddSearchFieldScore] " + collectionName + " / "+ fields[i] + "/" + rankScores[i]);
       }
       return ret;
   }

   /**
    * �ش� �÷����� �˻� ��� �ʵ带 ���� ���� �����Ѵ�.
    * @param collectionName
    * @param fields TITLE/100,CONTENT/30
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setSearchField(String collectionName, String fields) {
       int ret = 0;
       String[] searchFieldTemp = wnutils.split(fields, ","); //TITLE/100,CONTENT/30
       for (int i = 0; i < searchFieldTemp.length; i++) {
           String[] searchField = wnutils.split(searchFieldTemp[i], "/");
           if (searchField != null && searchField.length > 0) {
               ret = search.w3AddSearchField(collectionName,
                       searchField[0]);
               systemOut("[w3AddSearchField] " + collectionName + " / "+ searchField[0]);
           }
       }
       ret = search.w3SetSearchFieldScore(collectionName, fields);
       systemOut("[w3AddSearchField] " + collectionName + " / " + fields);
       return ret;
   }



   /**
    * �˻������ �����ʵ带 �����Ѵ�.
    * v4.x ȣȯ method
    * @param collectionName
    * @param sortField
    * @param sortOrder
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    * @deprecated  As of v4.5, replaced by
    * {@link #setSortField(String,String)}
    */
   public int addSortField(String collectionName, String sortField, int sortOrder) {
       int ret = 0;
       ret = search.w3AddSortField(collectionName, sortField, sortOrder);
       systemOut("[w3AddSortField] " + collectionName + " / " + sortField);
       return ret;
   }

   /**
    * �˻������ �����ʵ带 �����Ѵ�.
    * @param collectionName
    * @param sortValue
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setSortField(String collectionName, String sortValue) {
       int ret = 0;
       ret = search.w3SetSortField(collectionName, sortValue);
       systemOut("[w3SetSortField] " + collectionName + " / " + sortValue);
       return ret;
   }

   /**
    * �˻���� RANKING�� �����Ѵ�. SF-1 v5.0 �ű� �߰� method.
    * @param collectionName
    * @param method ��ŷ �޼ҵ�( basic, custom, BM25 )
    * @param option
       - p( proximity )
       - r( field weight )
       - k( multi keyword factor )
       - f( frequency )
       - m( morpheme )
       - o( offset )
       - l( length )
    * @param maxcount Ranking ǥ���� MAX ��. (��) %�� ǥ���� �� 100
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
   */
   public int setRanking(String collectionName, String method, String option, int maxcount) {
       return search.w3SetRanking(collectionName, method, option, maxcount);
   }

   /**
   * �˻��� ��� ��ũ���� ���� ������ ����
   * @param collectionName Collection �̸�
   * @param minRank �ּ� ��ŷ ��
   * @param maxRank �ִ� ��ŷ ��
   * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
   */
   public int setRankRange(String collectionName, int minRank, int maxRank) {
       return search.w3SetRankRange(collectionName, minRank, maxRank);

   }

   /**
    *
    * @param collectionName
    * @param fieldNameValues
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setPrefixQuery(String collectionName,
           String fieldNameValues) {
       //value���� /�� ���� �� �����Ƿ� >/�� ã�Ƽ� query�� �����ڸ� �и��Ѵ�.
       fieldNameValues = fieldNameValues.trim();
       int index = fieldNameValues.lastIndexOf("/");
       String prefixQuery = "";
       int operator = 1;
       if(index != -1) {
           prefixQuery = fieldNameValues.substring(0, index);
           String temp = fieldNameValues.substring(index+1, fieldNameValues.length());
           temp = temp.trim();
           operator = Integer.parseInt(temp);
       }else{
           prefixQuery = fieldNameValues.trim();
       }
       int ret = search.w3SetPrefixQuery(collectionName, prefixQuery, operator);
       systemOut("[w3SetPrefixQuery]" + fieldNameValues);
       return ret;
   }

   /**
    *
    * @param collectionName
    * @param fieldNameValues
    * @return  �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setFilterOperation(String collectionName, String fieldNameValues) {
       int ret = search.w3SetFilterQuery(collectionName, fieldNameValues);
       systemOut("[w3SetFilterQuery]" + fieldNameValues);
       return ret;
   }

   /**
    *
    * @param collectionName
    * @param fieldNameValues
    * @return  �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setFilterQuery(String collectionName, String fieldNameValues) {
       int ret = search.w3SetFilterQuery(collectionName, fieldNameValues);
       systemOut("[w3SetFilterQuery]" + fieldNameValues);
       return ret;
   }

   /**
   * setResultField���� ������ ��� �ʵ���� ���� ��� �Լ��̴�.
   * v4.x ȣȯ method�̴�. displaylength�� �������� �����Ƿ� �����ؾ��Ѵ�.
   * @param collectionName
   * @param fields
   * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
   * @deprecated  As of v4.5, replaced by
   * {@link #setResultField(String,String)}
   */
   public int setResultField(String collectionName, String[] fields) {
       int ret = 0;
       for(int i=0; i< fields.length; i++) {
           ret = search.w3AddDocumentField(collectionName, fields[i], 0);
           systemOut("[w3AddDocumentField] " + collectionName + " / " + fields[i]);
       }
       return ret;
   }

   /**
    * setResultField���� ������ ��� �ʵ���� ���� ��� �Լ��̴�.
    * @param collectionName
    * @param fields
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setResultField(String collectionName, String fields) {
       int ret = 0;
       ret = search.w3SetDocumentField(collectionName, fields);
       systemOut("[w3SetDocumentField] " + collectionName + " / " + fields);
       return ret;
   }

   /**
    * �ش� �÷����� �� ��° �˻� ������� �� ����
    * ������ �������� �����ϰ� ���̶���Ʈ ��ɰ� ������� �����ϴ� �Լ��̴�.
    * �˻� API v3.5������ w3SetHighlight�� �Ķ���Ͱ� 2�������� v3.7������ 3���̴�.
    * @param collName
    * @param highlight
    * @param startPos
    * @param resultCnt
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setPageInfo(String collName, int highlight, int startPos,
           int resultCnt) {
       int ret = 0;
       if (highlight == 1) { //OFF, ON
           ret = search.w3SetHighlight(collName, 0, 1);
       } else if (highlight == 2) { //ON , OFF
           ret = search.w3SetHighlight(collName, 1, 0);
       } else if (highlight == 3) { //ON, ON
           ret = search.w3SetHighlight(collName, 1, 1);
       } else {//OFF, OFF
           ret = search.w3SetHighlight(collName, 0, 0);
       }
       // ������, �⺻���� ����
       ret = search.w3SetPageInfo(collName, startPos, resultCnt);

       return ret;
   }

   /**
    * �˻��� ��� ��¥/�ð� ������ �����ϰ�
    * ���۳�¥�� ���ᳯ¥�� ������ YYYY/MM/DD�� �ƴ϶��
    * ������ ���ڸ� ���ڷ� �����Ѵ�.
    * @param collectionName
    * @param startDate
    * @param endDate
    * @param replaceChr
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setDateRange(String collectionName, String startDate,
           String endDate, String replaceChr) {
       // ��¥ ���� ����
       int ret = 0;
       if (!startDate.equals("") && !endDate.equals("")) {
           startDate = wnutils.replace(startDate, replaceChr, "/");
           endDate = wnutils.replace(endDate, replaceChr, "/");
           ret = search.w3SetDateRange(collectionName, startDate, endDate);
       }
       return ret;
   }

   /**
    *
    * @param collectionName
    * @param field
    * @param matchType
    * @param boostID
    * @param boostKeyword
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setBoostCategory(String collectionName,
           String field, String matchType, String boostKeyword) {
       return search.w3SetBoostCategory(collectionName, field, matchType, boostKeyword);
   }
	
   /**
    * �˻�������� �ߺ����� ���� ������ �����Ѵ�.
    * @param collectionName Collection ��
     * @param categoryGroup Category �׷�����
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int addDuplicateDetectionCriterionField(String collection, String field, int nOrder) {        	
   	int ret = -1;
   	ret = search.w3AddDuplicateDetectionCriterionField(collection, field, nOrder);
       return ret;
   }
   
   
   /**
    * �˻�������� �ߺ����� ���� ������ �����Ѵ�.
    * @param collectionName Collection ��
     * @param categoryGroup Category �׷�����
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setDuplicateDetectionCriterionField(String collection, String fieldList ) {        	
   	int ret = -1;
   	ret = search.w3SetDuplicateDetectionCriterionField(collection, fieldList);
       return ret;
   }
   
   /**
    * �˻���� ��Ŷ�� ī�װ� ���� �÷����� ī�װ� �ʵ忡�� Depth�� Category�� ����Ʈ�� ���Խ�Ų��.
    * @param collectionName Collection ��
     * @param categoryGroup Category �׷�����
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int addCategoryGroupBy(String collectionName, String[] categoryGroup) {        	
   	int ret = -1;
       int length = categoryGroup.length;
       for(int i=0; i < length; i++) {
      		String[] dataFields = wnutils.split(categoryGroup[i], ":");
			if(dataFields.length == 2){
				//depthList depth�� list�� �޸�(:) �� �����Ͽ� �Է�
				ret = search.w3AddCategoryGroupBy(collectionName, dataFields[0], dataFields[1]);
			}
       }
       return ret;
   }

    /**
    * ������ �÷��� ���� ���� ī�װ� �ʵ��� ������ �˻������ ���͸� �Ѵ�.
    * Ȥ�� �ٸ� �������� �������� �����ϸ� �ش� ī�װ��� ���� ��ġ�Ǵ� ������ ����Ѵ�.
    * @param collectionName Collection ��
    * @param categoryQuery
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int addCategoryQuery(String collectionName, String[] categoryQuery) {
  		int ret = -1;
       int length = categoryQuery.length;
       for(int i=0; i < length; i++) {
      	String[] dataFields = wnutils.split(categoryQuery[i], "|");
			if(dataFields.length == 2){
				ret = search.w3AddCategoryQuery(collectionName, dataFields[0], dataFields[1]);
			}
       }
       return ret;
   }

   /**
    * w3SetCategoryGroupBy�� ���� ��Ŷ�� ���Ե� �ش� collection-field-depth�� key�� �ϴ�
     * ī�װ��� �� ������ ��ȯ
    * @param collectionName �˻� ��� collection��
     * @param fieldName Category Field ��
     * @param depth ���� �׷����Ͽ� ������ �ϴ� depth
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int getCategoryCount(String collectionName, String field, int depth) {
       return search.w3GetCategoryCount(collectionName, field, depth);
   }

   /**
    * w3SetCategoryGroupBy�� ���� ��Ŷ�� ���Ե� �ش� collection-field-depth�� key�� �ϴ�
     * ī�װ� �� categoryIdx��°�� ������ ī�װ� �̸��� ��ȯ
    * @param collectionName �˻� ��� collection��
     * @param fieldName Category Field ��
     * @param depth ���� �׷����Ͽ� ������ �ϴ� depth
     * @param idx �ش� �÷���-ī�װ�-Depth�� �ش��ϴ� ī�װ� ����� �ε���
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public String getCategoryName(String collectionName, String field, int depth, int idx){
       return search.w3GetCategoryName(collectionName, field, depth, idx);
   }

    /**
    * w3SetCategoryGroupBy�� ���� ��Ŷ�� ���Ե� �ش� collection-field-depth�� key�� �ϴ� ī�װ� ��
     * categoryIdx��°�� ī�װ��ȿ� �� ���� ������ ���ԵǾ��ִ���(�˻� ���������)�� ��ȯ
    * @param collectionName �˻� ��� collection��
     * @param fieldName Category Field ��
     * @param depth ���� �׷����Ͽ� ������ �ϴ� depth
     * @param idx �ش� �÷���-ī�װ�-Depth�� �ش��ϴ� ī�װ� ����� �ε���
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int getDocumentCountInCategory(String collectionName, String field, int depth, int idx){
       return search.w3GetDocumentCountInCategory(collectionName, field, depth, idx);
   }

   /**
   * Ư�� �ʵ��� ���� �������� ���� ���� �׷��� ����ų�, �� �ʵ� ���� Ư�� ������ ���ϴ� ���ڵ常 ����
   * @ collectionName
   * @ field
   * @ min
   * @ max
   * @ groupCount
   */
   public int setPropertyGroup(String collectionName, String field, int min, int max, int groupCount) {
       int ret = 0;
       ret = search.w3SetPropertyGroup(collectionName, field, min, max, groupCount);
       return ret;
   }

   /**
    * v3.7������ 2���� �������� v4.0������ 3���� ��������
    * @param collectionName
    * @param field
    * @param docCount
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setGroupBy(String collectionName, String field, int docCount) {
       int ret = 0;
       ret = search.w3SetGroupBy(collectionName, field, docCount);
       return ret;
   }

   /**
     * �ܼ��� ���� ������� Ư�� �ʵ�(��)���� �׷��ε� ����� ������ ���� �� ���
     * @param collectionName �÷��Ǹ�
     * @param fieldName �׷����� �ʵ��
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int addMultiGroupBy(String collectionName, String fields) {
       int ret = 0;
       String[] field = wnutils.split(fields, ",");
       int length = field.length;
       for(int i = 0; i < length; i++ ) {
           ret = search.w3AddMultiGroupBy(collectionName, field[i]);
       }
       return ret ;
   }

   public int setMultiGroupBy (String collectionName, String fields) {
       return search.w3SetMultiGroupBy(collectionName, fields);
   }

   /**
     * ��Ƽ �׷���̵� ����� ��ȯ
     * @param collectionName �÷��Ǹ�
     * @param fieldName �׷����� �ʵ��
     * @return ���� �� �ش� �÷��� ������� ���ڷ� �־��� �ʵ��� �׷��� ��� ���ڿ��� ��ȯ�ϸ�, ���� �߻��� �� ���ڿ��� ��ȯ
    */
   public String getMultiGroupByResult(String collectionName, String field) {
       String ret = "";
       ret = search.w3GetMultiGroupByResult(collectionName, field);
       return ret ;
   }

   /**
    * �׷� �˻������ �����ʵ带 �����Ѵ�.
    * @param collectionName
    * @param sortField
    * @param sortOrder
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int addSortFieldInGroup(String collectionName, String sortField,
           int sortOrder) {
       int ret = 0;
       ret = search.w3AddSortFieldInGroup(collectionName, sortField,
               sortOrder);
       systemOut("[w3AddSortFieldInGroup] " + collectionName + " / "
               + sortField);
       return ret;
   }

   /**
    * �׷쳻�� �������� ���� �������� ������ �ִ� �Լ�
    * @param collectionName
    * @param sortValue url/DESC,RANK/DESC,DATE/ASC
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setSortFieldInGroup(String collectionName, String sortValue) {
       int ret = 0;
       ret = search.w3SetSortFieldInGroup(collectionName, sortValue);
       systemOut("[w3SetSortFieldInGroup] " + collectionName + " / " + sortValue);
       return ret;
   }


   /**
    * ���� ���� (Authority Query)
    * @param collection
    * @param authTargetField
    * @param authCollection
    * @param authReferField
    * @param authorityQuery
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int setAuthorityQuery(String collection, String authTargetField,
           String authCollection, String authReferField, String authorityQuery ) {
       int ret = search.w3SetAuthorityQuery(collection, authTargetField,
               authCollection, authReferField, authorityQuery);
       systemOut("[w3SetAuthorityQuery]" + authorityQuery);
       return ret;
   }

   /**
    *
    * @param collectionName
    * @return �˻���κ��� �޾ƿ� �Ӽ� �׷��� ������ ��ȯ
    */
   public int getCountPropertyGroup(String collectionName) {
       return search.w3GetCountPropertyGroup(collectionName);
   }

   /**
    *
    * @param collectionName
    * @return ��ü �׷� ����
    */
   public int getResultGroupCount(String collectionName) {
       return search.w3GetResultGroupCount(collectionName);
   }


   /**
    *
    * @param collectionName
    * @return ��ü �׷� ����
    */
   public int getResultTotalGroupCount(String collectionName) {
       return search.w3GetResultTotalGroupCount(collectionName);
   }

   /**
    *
    * @param collectionName
    * @parma groupIndex
    * @return �׷쿡 ���ϴ� ���� �� ������ ���� ����
    */
   public int getCountInGroup(String collectionName, int groupIndex) {
       return search.w3GetCountInGroup(collectionName, groupIndex);
   }

   /**
    *
    * @param collectionName
    * @parma groupIndex
    * @return �׷쿡 ���ϴ� ��ü���� ����
    */
   public int getTotalCountInGroup(String collectionName, int groupIndex) {
       return search.w3GetTotalCountInGroup(collectionName, groupIndex);
   }


   /**
    *
    * @param collectionName
    * @return ������ �ش� �Ӽ� �׷��� �ּҰ��� ��ȯ�ϸ�, ���� �߻��� ������ ��ȯ
    */
   public int getMinValuePropertyGroup(String collectionName){
       return search.w3GetMinValuePropertyGroup(collectionName);
   }

   /**
    *
    * @param collectionName
    * @return ������ �ش� �Ӽ� �׷��� �ִ밪�� ��ȯ�ϸ�, ���� �߻��� ������ ��ȯ
    */
   public int getMaxValuePropertyGroup(String collectionName){
       return search.w3GetMaxValuePropertyGroup(collectionName);
   }

   /**
    *
    * @param collectionName
    * @parma groupIndex
    * @return ������ �Ӽ� �׷� ����� �ּҰ��� ��ȯ�ϸ�, ���� �߻��� ������ ��ȯ
    */
   public int getMinValueInPropertyGroup(String collectionName, int groupIndex){
       return search.w3GetMinValueInPropertyGroup(collectionName, groupIndex);
   }

   /**
    *
    * @param collectionName
    * @parma groupIndex
    * @return ������ �Ӽ� �׷� ����� �ִ밪�� ��ȯ�ϸ�, ���� �߻��� ������ ��ȯ
    */
   public int getMaxValueInPropertyGroup(String collectionName, int groupIndex){
       return search.w3GetMaxValueInPropertyGroup(collectionName, groupIndex);
   }


   /**
    *
    * @param collectionName
    * @parma groupIndex
    * @return ������ �Ӽ� �׷� �� ���������� ��ȯ
    */
   public int getDocumentCountInPropertyGroup(String collectionName, int groupIndex){
       return search.w3GetDocumentCountInPropertyGroup(collectionName, groupIndex);
   }

   /**
    * �׷�ȭ�� ���� �� ������ ������ �׷� ������ �־��� collection ��� ���� index ��° ������ Collection ID�� ��ȯ
    * @param collectionName
    * @param groupIndex
    * @param docIndex
    * @return �׷쿡 ���ϴ� ������ ���ϴ� ������ �ʵ尪
    */
   public String getCollectionIdInGroup(String collectionName,	int groupIndex, int docIndex) {
       return search.w3GetCollectionIdInGroup(collectionName, groupIndex, docIndex);
   }

   /**
    *
    * @param collectionName
    * @param groupIndex
    * @param docIndex
    * @return �׷쿡 ���ϴ� ������ ���ϴ� ������ �ʵ尪
    */
   public long getRankInGroup(String collectionName,	int groupIndex, int docIndex) {
       return search.w3GetRankInGroup(collectionName, groupIndex, docIndex);
   }

   /**
    *
    * @param collectionName
    * @param groupIndex
    * @param docIndex
    * @return �׷쿡 ���ϴ� ������ ���ϴ� ������ �ʵ尪
    */
   public String getFieldInGroup(String collectionName, String fieldName,
           int groupIndex, int docIndex) {
       return search.w3GetFieldInGroup(collectionName, fieldName,
               groupIndex, docIndex);
   }

   /**
    *
    * @param collectionName
    * @param groupIndex
    * @param docIndex
    * @return �׷쿡 ���ϴ� ������ ���ϴ� ������ uid��
    */
   public long getUidInGroup(String collectionName, int groupIndex,
           int docIndex) {
       return search.w3GetUidInGroup(collectionName, groupIndex, docIndex);
   }

   /**
    *
    * @param collectionName
    * @param groupIndex
    * @param docIndex
    * @return �׷쿡 ���ϴ� ������ ���ϴ� ������ ����ġ ��
    */
   public long getWeightInGroup(String collectionName, int groupIndex,
           int docIndex) {
       return search.w3GetWeightInGroup(collectionName, groupIndex,
               docIndex);
   }

   /**
    *
    * @param collectionName
    * @param groupIndex
    * @param docIndex
    * @return �׷쿡 ���ϴ� ������ ���ϴ� ������ ��¥ ����
    */
   public String getDateInGroup(String collectionName, int groupIndex,
           int docIndex) {
       return search
               .w3GetDateInGroup(collectionName, groupIndex, docIndex);
   }

   /**
    *
    * @param collectionName
    * @param groupIndex
    * @param docIndex
    * @return �׷쿡 ���ϴ� ������ ���ϴ� ������ sc ����
    */
   public String getSearcherIdInGroup(String collectionName, int groupIndex,
           int docIndex) {
       return search.w3GetSearcherIdInGroup(collectionName, groupIndex, docIndex);
   }


   /**
    * v4.x ȣȯ method
    * v5.0������ �����Ǿ���.
    * ���̶����õ� ���ڿ��� �����ִ� �Լ��̴�.
    * @return ���̶����õ� ���ڿ�
   public String getHighlightKeyword() {
       String keyWord = this.search.w3GetHighlightKeyword().trim();
       return keyWord;
   }
    */

   /**
    * ���¼� �м��� ��� ���ڿ��� �����ִ� �Լ��̴�.
    * @param colName
    * @param field
    * @return ���̶����õ� ���ڿ�
    */
   public String getHighlightKeywordByField(String colName,
           String searchField) {
       String keyWord = this.search.w3GetHighlightByField(colName,
               searchField);
       return keyWord;
   }

   /**
    *
    * @param collectionName
    * @return �˻���� ����
    */
   public int getResultCount(String collectionName) {
       return search.w3GetResultCount(collectionName);
   }

   /**
    *
    * @param collectionName
    * @return �˻���� �� ����
    */
   public int getResultTotalCount(String collectionName) {
       return search.w3GetResultTotalCount(collectionName);
   }


   /**
    *
    * @param collectionName
    * @param idx
    * @return �˻��� �ش� ������ �ߺ����� ������ ��ȯ
    */
   public int getDuplicateDocumentCount(String collectionName, int idx) {
       return search.w3GetDuplicateDocumentCount(collectionName, idx);
   }

   /**
    *
    * @param collectionName
    * @param groupIndex
    * @param docIndex
    * @return �׷�ȭ�� ���� �� ������ ������ �׷� ������ �� ������� �ߺ��� ������ ������ ��ȯ
    */
   public int getDuplicateDocumentCountInGroup(String collectionName, int groupIndex,
           int docIndex) {
       return search.w3GetDuplicateDocumentCountInGroup(collectionName, groupIndex, docIndex);
   }

   /**
    * �ֱٿ� �˻��� Ű���帮��Ʈ�� ��ȯ�Ѵ�.
    * @param count
    * @return �ֱٿ� �˻��� Ű���帮��Ʈ
    */
   public String[] receiveRecentQueryListResult(int mode, int count) {
       int ret = search.w3ReceiveRecentQueryListResult(mode, count);
       if( ret < 0 ){
           systemOut("[W3ReceiveRecentQueryListResult] " + search.w3GetErrorInfo() + ", ret=" + ret);
           return null;
       }
       int size = search.w3GetRecentQueryListSize();
       String[] keyList = new String[size];
       for(int i=0; i<size; i++ ){
           keyList[i] = search.w3GetRecentQuery(i);
       }
       return keyList;
   }

   /**
    * �ֱٿ� �˻��� Ű���帮��Ʈ�� ��ȯ�Ѵ�.
    * @param count
    * @return �ֱٿ� �˻��� Ű���帮��Ʈ
    */
   public int receiveRecentQueryListResultAsXml(int mode, int count) {
       int ret = search.w3ReceiveRecentQueryListResultAsXml(mode, count);
       if( ret < 0 ){
           systemOut("[w3ReceiveRecentQueryListResultAsXml] " + search.w3GetErrorInfo() + ", ret=" + ret);
           return -1;
       }
       return ret;
   }

   /**
    * �ֱٿ� �˻��� Ű���帮��Ʈ�� ��ȯ�Ѵ�.
    * @param count
    * @return �ֱٿ� �˻��� Ű���帮��Ʈ
    */
   public int receiveRecentQueryListResultAsJson(int mode, int count) {
       int ret = search.w3ReceiveRecentQueryListResultAsJson(mode, count);
       if( ret < 0 ){
           systemOut("[w3ReceiveRecentQueryListResultAsJson] " + search.w3GetErrorInfo() + ", ret=" + ret);
           return -1;
       }
       return ret;
   }


   /**
   */
   public String getSuggestedQuery() {
       return search.w3GetSuggestedQuery();
   }

   /**
    * v4.x ȣȯ method
    * @param mode
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    * @deprecated  As of v4.x, replaced by
    * {@link #receiveSearchQueryResult(int)}
    */
   public int recvResult(int mode) {
       int ret = search.w3ReceiveSearchQueryResult(mode);
       return ret;
   }

   /**
   *
   */
   public int recvDuplicateDocumentsResult(int mode) {
       int ret = search.w3ReceiveDuplicateDocumentsResult(mode);
       return ret;
   }

   /**
   *
   */
   public int recvDuplicateDocumentsResultAsJson(int mode) {
       int ret = search.w3ReceiveDuplicateDocumentsResultAsJson(mode);
       return ret;
   }

   /**
   *
   */
   public int recvDuplicateDocumentsResultAsXml(int mode) {
       int ret = search.w3ReceiveDuplicateDocumentsResultAsXml(mode);
       return ret;
   }


   /**
    *
    * @param mode
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int receiveSearchQueryResult(int mode) {
       int ret = search.w3ReceiveSearchQueryResult(mode);
       return ret;
   }

   /**
    *
    * @param mode
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int receiveSearchQueryResultAsXml(int mode) {
       int ret = search.w3ReceiveSearchQueryResultAsXml(mode);
       return ret;
   }


   /**
    *
    * @param mode
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int receiveSearchQueryResultAsJson(int mode) {
       int ret = search.w3ReceiveSearchQueryResultAsJson(mode);
       return ret;
   }

   /**
    *
    * @return �����̸� �˻������ XML�� ��ȯ�Ѵ�.
    */
   public String getResultXml() {
       String ret = search.w3GetResultXml();
       return ret;
   }

   /**
    *
    * @return �����̸� �˻������ JSON data�� ��ȯ�Ѵ�.
    */
   public String getResultJson() {
       String ret = search.w3GetResultJson();
       return ret;
   }

   /**
    * UID �˻��� �˻��⿡ �����ϰ�, ����� �޴� �Լ��̴�.
    * v4.x ȣȯ method
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    * @deprecated  As of v4.x, replaced by
    * {@link #receiveUidResult(int)}
    */
   public int recvDocument(int mode) {
       int ret = search.w3ReceiveUidResult(mode);
       return ret;
   }

   /**
    * UID �˻��� �˻��⿡ �����ϰ�, ����� �޴� �Լ��̴�.
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int receiveUidResult(int mode) {
       int ret = search.w3ReceiveUidResult(mode);
       return ret;
   }

   /**
    * UID �˻��� �˻��⿡ �����ϰ�, ����� �޴� �Լ��̴�.
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int receiveUidResultAsXml(int mode) {
       int ret = search.w3ReceiveUidResultAsXml(mode);
       return ret;
   }

   /**
    * UID �˻��� �˻��⿡ �����ϰ�, ����� �޴� �Լ��̴�.
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int receiveUidResultAsJson(int mode) {
       int ret = search.w3ReceiveUidResultAsJson(mode);
       return ret;
   }

   /**
    * v4.x ȣȯ method
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    * @deprecated  As of v4.x, replaced by
    * {@link #receiveMorphemeAnalysisResult(int)}
    */
   public int recvAnalyzedQuery(int mode) {
       int ret = search.w3ReceiveMorphemeAnalysisResult(mode);
       return ret;
   }

   /**
    *
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int receiveMorphemeAnalysisResult(int mode) {
       int ret = search.w3ReceiveMorphemeAnalysisResult(mode);
       return ret;
   }

   /**
    *
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int receiveMorphemeAnalysisResultAsXml(int mode) {
       int ret = search.w3ReceiveMorphemeAnalysisResultAsXml(mode);
       return ret;
   }

   /**
    *
    * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
    */
   public int receiveMorphemeAnalysisResultAsJson(int mode) {
       int ret = search.w3ReceiveMorphemeAnalysisResultAsJson(mode);
       return ret;
   }


   /**
    * �־��� collection ��� ���� index ��° ������ Collection ID�� ��ȯ�Ѵ�.
    * @param coll
    * @param idx
    * @return FIELD VALUE
    */
   public String getCollectionId(String coll, int idx) {
       return search.w3GetCollectionId(coll, idx);
   }

   /**
    *
    * @param coll
    * @param field
    * @param idx
    * @return FIELD VALUE
    */
   public String getField(String coll, String field, int idx) {
       return search.w3GetField(coll, field, idx);
   }

   /**
    *
    * @param coll
    * @param idx
    * @return DATE
    */
   public String getDate(String coll, int idx) {
       return search.w3GetDate(coll, idx);
   }

   /**
    *
    * @param coll
    * @param idx
    * @return DATE
    */
   public long getWeight(String coll, int idx) {
       return search.w3GetWeight(coll, idx);
   }

   /**
    *
    * @param coll
    * @param idx
    * @return RANK
    */
   public long getRank(String coll, int idx) {
       return search.w3GetRank(coll, idx);
   }

   /**
    *
    * @param coll
    * @param idx
    * @return UID
    */
   public long getUid(String coll, int idx) {
       return search.w3GetUid(coll, idx);
   }

   /**
    *
    * @param coll
    * @param idx
    * @return SearcherId
    */
   public String getSearcherId(String coll, int idx) {
       return search.w3GetSearcherId(coll, idx);
   }

   /*
     * ���� �÷����� �˻������ �ϳ��� ���� �÷���(Merge Collection) ����� �����Ͽ� �������� ���� �� ����ϴ� �Լ�
     * @param mergeCollection ���� ���� �÷��Ǹ�
     * @param collections[] (���մ��) �÷��Ǹ�
     * @param start (��� ��) ���� ���� �ε���
     * @param count (��� ��) ������ ���� ����
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
   public int addMergeCollectionInfo(String mergeCollection, String[] collections, int start, int count ){
       int collectionLen = collections.length;
       int ret = 0;

       for(int i=0; i < collectionLen; i++) {

           ret = search.w3AddMergeCollection(mergeCollection, collections[i]);
       }

       ret = search.w3SetMergePageInfo(mergeCollection, start, count);

       return ret;
   }

   /*
     * ���� �÷���(Merge Collection)�� ��� �ʵ�(Document Field)�� ���յ� ���� �÷����� ��� �ʵ带 1:N ( 0 < N < 64 ) ����� �����Ų��
     * @param mergeCollection ���� ���� �÷��Ǹ�
     * @param mergeFields[] ���� ���� �÷����� ��� �ʵ��
     * @param collections[] (���մ��) �÷��Ǹ�
     * @param fields[,] (���մ��) �÷����� ��� �ʵ��
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
   public int addMergeDocumentField(String mergeCollection, String[] mergeFields, String[] collections, String[][] fields) {
       int ret = 0;
       int collectionLen = collections.length;
       int fieldsLen = mergeFields.length;

       for(int i=0; i < collectionLen; i++) {

           for(int j=0; j<fieldsLen; j++) {
               ret = search.w3AddMergeDocumentField(mergeCollection, mergeFields[j], collections[i], fields[i][j]);
           }
       }
       return ret;
   }

     /*
      * w3AddMergeDocumentField �Լ��� ���� �÷����� ��� �ʵ带 ���� �Ͽ��ٸ�, �� �Լ��� ���� �÷�����
      * MultiGroupBy �ʵ���� ���� �� ���� �÷����� MultiGroupBy �ʵ��� �����Ű�� ����
      * @param mergeCollection ���� ���� �÷��Ǹ�
      * @param mergeFields[] ���� ���� �÷����� ��� �ʵ��
      * @param collections[] (���մ��) �÷��Ǹ�
      * @param fields[,] (���մ��) �÷����� ��� �ʵ��
      * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
      */
   public int addMergeMultiGroupByField(String mergeCollection, String[] mergeFields, String[] collections, String[][] fields) {
       int ret = 0;
       int collectionLen = collections.length;
       int fieldsLen = mergeFields.length;
       for(int i=0; i < collectionLen; i++) {
           for(int j=0; j<fieldsLen; j++) {
               ret = search.w3AddMergeMultiGroupByField(mergeCollection, mergeFields[j], collections[i], fields[i][j]);
           }
       }
       return ret;
   }

   /*
     * ���� �÷����� CategoryGroupBy �ʵ���� ���� �� ���� �÷�����
     * CategoryGroupBy �ʵ��� �����Ű�� ����
     * @param mergeCollection ���� ���� �÷��Ǹ�
     * @param mergeFields[] ���� ���� �÷����� ��� �ʵ��
     * @param collections[] (���մ��) �÷��Ǹ�
     * @param fields[,] (���մ��) �÷����� ��� �ʵ��
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
   public int addMergeCategoryGroupByField(String mergeCollection, String[] mergeFields, String[] collections, String[][] fields) {
       int ret = 0;
       int collectionLen = collections.length;
       int fieldsLen = mergeFields.length;
       for(int i=0; i < collectionLen; i++) {
           for(int j=0; j<fieldsLen; j++) {
               ret = search.w3AddMergeCategoryGroupByField(mergeCollection, mergeFields[j], collections[i], fields[i][j]);
           }
       }
       return ret;
   }

   /*
     * �˻��⿡�� �޾ƿ� ���� �÷���(Merge Collection) ����� ���� �ε����� ���� ������ ����
     * CategoryGroupBy �ʵ��� �����Ű�� ����
     * @param mergeCollection ���� ���� �÷��Ǹ�
     * @param collections (���մ��) �÷��Ǹ�
     *
     * @return ���� ���� ��ȯ�Ѵ�.
     */
   public int getResultTotalCountInMerge(String mergeCollection, String collection){
       return search.w3GetResultTotalCountInMerge(mergeCollection, collection);
   }

   /**
    * ��Ʈ��ũ ����
    */
   public void closeServer() {
       if (search != null) {
           search.w3CloseServer();
           search = null;
       }
   }

   /**
    * ���ڷ� ���޹��� collection�� �˻������ ������ �߻��ϸ� ���� �ڵ�� �޽����� ��ȯ�Ѵ�.
    * @param collectionName
    * @return
    */
   public String getCollectionErrorInfo(String collectionName) {
       String errorMsg = "";
       int errorCode = search.w3GetCollectionError(collectionName);
       if (errorCode != 0) {
           errorMsg = search.w3GetCollectionErrorInfo(collectionName);
           errorMsg = errorMsg + "(<b>code:<font color='red'>" + errorCode
                   + "</font></b>)";
       }
       return errorMsg;
   }



   /**
    * �˻������ ������ �߻��ϸ� ���� �ڵ�� �޽����� ��ȯ�Ѵ�.
    * @return
    */
   public String getErrorInfo() {
       String errorMsg = "";
       int errorCode = search.w3GetError();
       if (errorCode != 0) {
           errorMsg = search.w3GetErrorInfo();
           errorMsg = errorMsg + "(<b>code:<font color='red'>" + errorCode
                   + "</font></b>)";
       }
       return errorMsg;
   }

   /**
    * �˻� api�� ���������� ��ȯ�Ѵ�.
    * @return
    */
   public String getVersionInfo() {
       return search.w3GetVersionInfo();
   }



   /**
    * ���� �ڵ忡 ���� ���������� web application�� standard out log�� ����Ѵ�.
    * @param msg
    */
   public void systemOut(String msg) {
       if (out != null && isDebug) {
           try {
               out.println(msg + "<br>");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   
   public static void main(String[] args) {
	   WNCommon comm=new WNCommon();
   }
}
