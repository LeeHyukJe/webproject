package com.leehyukje.webproject.search.common;

public class WNSearchInfo {
    private WNCommon common;
    private WNCollection wncol;
    private WNUtils wnutils;

    private String[] collections;
    private String[] mergeCollections;
    private String[] searchFields;
    private String managerURL = "";
    private int hiSum = 0;
    private int connectionOpt = 0;
    private StringBuffer sb = null;
    private StringBuffer warningMsg = null;

    private String searcherId = "";
    private boolean isDebug = false;
    private boolean isUidSrch = false;
    private int resultType = 0;

    public String realTimeKeywords = "";
    public String suggestedQuery = "";

    public static class WNSearchBuilder {

        private WNCommon common = new WNCommon();
        private WNCollection wncol = new WNCollection();
        private WNUtils wnutils;

        private String[] collections;
        private String[] mergeCollections;
        private String[] searchFields;
        private String managerURL = "";
        private int hiSum = 0;
        private int connectionOpt = 0;
        private StringBuffer sb = new StringBuffer();
        private StringBuffer warningMsg = new StringBuffer();

        private String searcherId = "";
        private boolean isDebug = false;
        private boolean isUidSrch = false;
        private int resultType = 0;

        public String realTimeKeywords = "";
        public String suggestedQuery = "";

        /*
        필수로 등록되야 하는 필드
        public WNSearchBuilder(args....){

        }
         */

        public WNSearchBuilder wnutils(WNUtils wnUtils) {
            this.wnutils = wnUtils;
            return this;
        }

        public WNSearchBuilder collections(String[] collections) {
            this.collections = collections;
            return this;
        }

        public WNSearchBuilder mergeCollections(String[] mergeCollections) {
            this.mergeCollections = mergeCollections;
            return this;
        }

        public WNSearchBuilder searchFields(String[] searchFields) {
            this.searchFields = searchFields;
            return this;
        }

        public WNSearchBuilder managerURL(String managerURL) {
            this.managerURL = managerURL;
            return this;
        }

        public WNSearchBuilder hisum(int hiSum) {
            this.hiSum = hiSum;
            return this;
        }

        public WNSearchBuilder connectionOpt(int connectionOpt) {
            this.connectionOpt = connectionOpt;
            return this;
        }

        public WNSearchBuilder searcherId(String searcherId) {
            this.searcherId = searcherId;
            return this;
        }

        public WNSearchBuilder isDebug(boolean isDebug) {
            this.isDebug = isDebug;
            return this;
        }

        public WNSearchBuilder isUidSrch(boolean isUidSrch) {
            this.isUidSrch = isUidSrch;
            return this;
        }

        public WNSearchBuilder resultType(int resultType) {
            this.resultType = resultType;
            return this;
        }

        public WNSearchBuilder realTimeKeywords(String realTimeKeywords) {
            this.realTimeKeywords = realTimeKeywords;
            return this;
        }

        public WNSearchBuilder suggestedQuery(String suggestedQuery) {
            this.suggestedQuery = suggestedQuery;
            return this;
        }


        public WNSearchInfo Build() {
            return new WNSearchInfo(this);
        }

    }

    public WNSearchInfo(WNSearchBuilder wnSearchBuilder) {
        this.common = new WNCommon();
        this.wncol = new WNCollection();
        this.sb = new StringBuffer();
        this.warningMsg = new StringBuffer();

        this.wnutils = wnSearchBuilder.wnutils;
        this.collections = wnSearchBuilder.collections;
        this.mergeCollections = wnSearchBuilder.mergeCollections;
        this.searchFields = wnSearchBuilder.searchFields;
        this.managerURL = wnSearchBuilder.managerURL;
        this.hiSum = wnSearchBuilder.hiSum;
        this.connectionOpt = wnSearchBuilder.connectionOpt;
        this.searcherId = wnSearchBuilder.searcherId;
        this.isDebug = wnSearchBuilder.isDebug;
        this.isUidSrch = wnSearchBuilder.isUidSrch;
        this.resultType = wnSearchBuilder.resultType;
        this.realTimeKeywords = wnSearchBuilder.realTimeKeywords;
        this.suggestedQuery = wnSearchBuilder.suggestedQuery;

    }


    /**
     * �˻� Ű���带 �����Ѵ�.
     *
     * @param query       �˻� Ű����
     * @param isRealQuery �ǽð� �˻�� ����� ������ ���θ� ����
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int search(String query, boolean isRealQuery) {
        return search(query, isRealQuery, null, WNDefine.HI_SUM_ONON, WNDefine.COMMON_OR_WHEN_NORESULT_OFF, WNDefine.CONNECTION_CLOSE, false);
    }


    /**
     * �˻� Ű���带 �����Ѵ�.
     *
     * @param query       �˻� Ű����
     * @param isRealQuery �ǽð� �˻�� ����� ������ ���θ� ����
     * @param useOrResult �˻���� ���� �� OR�� �˻����� ���θ� ����
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int search(String query, boolean isRealQuery, int useOrResult) {
        return search(query, isRealQuery, null, WNDefine.HI_SUM_ONON, useOrResult, WNDefine.CONNECTION_CLOSE, false);
    }

    /**
     * �˻� Ű���带 �����Ѵ�.
     *
     * @param query             �˻� Ű����
     * @param isRealQuery       �ǽð� �˻�� ����� ������ ���θ� ����
     * @param connectionOpt     �˻���� ������ ������ ������ ���� ������ ���θ� ����
     * @param useSuggestedQuery speller ����� ����� ������ ���θ� ����
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int search(String query, boolean isRealQuery, int connectionOpt, boolean useSuggestedQuery) {
        this.connectionOpt = connectionOpt;
        return search(query, isRealQuery, null, WNDefine.HI_SUM_ONON, WNDefine.COMMON_OR_WHEN_NORESULT_OFF, connectionOpt, useSuggestedQuery);
    }


    /**
     * �˻� Ű���带 �����Ѵ�.
     *
     * @param query             �˻� Ű����
     * @param isRealQuery       �ǽð� �˻�� ����� ������ ���θ� ����
     * @param useOrResult       �˻���� ���� �� OR�� �˻����� ���θ� ����
     * @param connectionOpt     �˻���� ������ ������ ������ ���� ������ ���θ� ����
     * @param useSuggestedQuery speller ����� ����� ������ ���θ� ����
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int search(String query, boolean isRealQuery, int useOrResult, int connectionOpt, boolean useSuggestedQuery) {
        this.connectionOpt = connectionOpt;
        return search(query, isRealQuery, null, WNDefine.HI_SUM_ONON, useOrResult, connectionOpt, useSuggestedQuery);
    }

    /**
     * �˻� Ű���带 �����Ѵ�.
     *
     * @param query             �˻� Ű����
     * @param isRealQuery       �ǽð� �˻�� ����� ������ ���θ� ����
     * @param useOrResult       �˻���� ���� �� OR�� �˻����� ���θ� ����
     * @param connectionOpt     �˻���� ������ ������ ������ ���� ������ ���θ� ����
     * @param hisum             ���̶����ð� ����� ����� �ɼ��� ����
     * @param useSuggestedQuery speller ����� ����� ������ ���θ� ����
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int search(String query, boolean isRealQuery, int useOrResult, int connectionOpt, int hiSum, boolean useSuggestedQuery) {
        this.connectionOpt = connectionOpt;
        return search(query, isRealQuery, null, hiSum, useOrResult, connectionOpt, useSuggestedQuery);
    }

    /**
     * �˻� Ű���带 �����Ѵ�.
     *
     * @param query             �˻� Ű����
     * @param isRealQuery       �ǽð� �˻�� ����� ������ ���θ� ����
     * @param userInfo          ����� ������ QueryLog������ ������ �� ���ȴ�
     * @param hisum             ���̶����ð� ����� ����� �ɼ��� ����
     * @param useOrResult       �˻���� ���� �� OR�� �˻����� ���θ� ����
     * @param connectionOpt     �˻���� ������ ������ ������ ���� ������ ���θ� ����
     * @param useSuggestedQuery speller ����� ����� ������ ���θ� ����
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    private int search(String query, boolean isRealQuery, String[] userInfo, int hiSum, int useOrResult, int connectionOpt, boolean useSuggestedQuery) {
        this.connectionOpt = connectionOpt;
        this.hiSum = hiSum;
        int ret = 0;
        if (!isUidSrch) {
            ret = common.setCommonQuery(query, WNDefine.CHARSET, useOrResult, useSuggestedQuery, userInfo);
        } else {
            ret = common.setUidQuery(WNDefine.CHARSET);
        }

        if (collections != null) {
            for (int i = 0; i < collections.length; i++) {
                //�÷��� index ����
                int idx = getCollIdx(collections[i]);
                //�÷��� index ��� ��
                if (idx < 0) {
                    appendWarnings("[WARNING] [w3AddCollection] [search] [" + collections[i] + "] Collection name is not exist");
                    break;
                }

                //�˻� �÷��� ����
                ret = setCollectionBasicInfo(idx);

                //�˻� �ʵ� ����
                if (!isUidSrch) {
                    ret = setSearchFieldInfo(idx);
                    //��¥ ���� �˻�
                    ret = setDateRange(idx);
                    //Rank ���� �˻�
                    ret = setRankRange(idx);
                    //PrefixQuery ���� �˻�
                    ret = setPrefixQuery(idx);
                    //CollectionQuery �˻�
                    ret = setCollectionQuery(idx);
                    //ī�װ� �ν��� ����
                    ret = setBoostCategory(idx);
                    //ī�װ� �׷����
                    ret = addCategoryGroupBy(idx);
                    //ī�װ� �˻�
                    ret = addCategoryQuery(idx);
                    //FilterQuery ���� �˻�
                    ret = setFilterQuery(idx);
                    //���� �˻� ����
                    ret = setAuthorityQuery(idx);
                    //�Ӽ� �׷� �˻� ����
                    ret = setPropertyGroup(idx);
                    //�׷���� ���ǰ˻�
                    ret = setGroupBy(idx);
                    //��Ƽ�׷���� ���ǰ˻�
                    ret = setMultiGroupBy(idx);
                    // DEDUP ���ǰ˻�
                    ret = setDuplicateDetectionCriterionField(idx);
                } else {
                    //UID�˻�
                    ret = setUid(idx, query, searcherId);
                }
                //��� �ʵ� ����
                ret = setResultFieldInfo(idx);
            }


        } else {
            appendWarnings("[WARNING] [w3AddCollection] [search] [ ] CollectionName is null.");
        }

        if (mergeCollections != null) {
            for (int n = 0; n < mergeCollections.length; n++) {
                int idx = getMergeCollIdx(mergeCollections[n]);
                //�÷��� index ��� ��
                if (idx < 0) {
                    appendWarnings("[WARNING] [w3AddMergeCollection] [search] [" + mergeCollections[n] + "] Merge Collection name is not exist");
                    break;
                }
                //���� collection ����
                ret = setMergeCollectionBasicnfo(idx);
                //���� collection document field
                ret = addMergeDocumentField(idx);
                //���� collection multi-group by
                ret = addMergeMultiGroupBy(idx);
                //���� collection category group by
                ret = addMergeCategoryGroupByField(idx);
            }
        }

        //Connection
        ret = common.getConnection(WNCollection.SEARCH_IP, WNCollection.SEARCH_PORT, WNDefine.CONNECTION_TIMEOUT);

        //�ǽð� �˻�Ű���带 ��û
        if (isRealQuery && !query.equals("")) {
            this.realTimeKeywords = recvRealTimeSearchKeywordList(WNDefine.REALTIME_COUNT);
        }

        //�˻������ ��´�.
        if (!isUidSrch) {
            if (resultType == WNDefine.USE_RESULT_XML) {
                ret = common.receiveSearchQueryResultAsXml(this.connectionOpt);
            } else if (resultType == WNDefine.USE_RESULT_JSON) {
                ret = common.receiveSearchQueryResultAsJson(this.connectionOpt);
            } else {
                ret = common.receiveSearchQueryResult(this.connectionOpt);
            }
        } else {
            if (resultType == WNDefine.USE_RESULT_XML) {
                ret = common.receiveUidResultAsXml(this.connectionOpt);
            } else if (resultType == WNDefine.USE_RESULT_JSON) {
                ret = common.receiveUidResultAsJson(this.connectionOpt);
            } else if (resultType == WNDefine.USE_RESULT_DUPLICATE_STRING) {
                ret = common.recvDuplicateDocumentsResult(this.connectionOpt);
            } else if (resultType == WNDefine.USE_RESULT_DUPLICATE_XML) {
                ret = common.recvDuplicateDocumentsResultAsXml(this.connectionOpt);
            } else if (resultType == WNDefine.USE_RESULT_DUPLICATE_JSON) {
                ret = common.recvDuplicateDocumentsResultAsJson(this.connectionOpt);
            } else {
                ret = common.receiveUidResult(this.connectionOpt);
            }
        }

        //����� ����� ���
        if (isDebug) {
            //����� ����
            debugMsg(isRealQuery, query);
            //���� ����
            String errMsg = common.getErrorInfo();
            appendErrors(errMsg);
        }

        //��Ÿ�� ��Ÿ ��õ Ű���� ��û
        if (useSuggestedQuery) {
            this.suggestedQuery = common.getSuggestedQuery();
        }

        return ret;
    }


    /**
     * �⺻ �˻� ������ �����Ѵ�. ���м�, ������ ����, ������ �ش�ȴ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setCollectionBasicInfo(int idx) {
        int ret = 0;
        String[] analyzer = WNUtils.split(wncol.COLLECTION_INFO[idx][WNDefine.ANALYZER], ",");
        String[] pageInfo = WNUtils.split(wncol.COLLECTION_INFO[idx][WNDefine.PAGE_INFO], ",");
        String[] rankingOption = WNUtils.split(wncol.COLLECTION_INFO[idx][WNDefine.RANKING_OPTION], ",");
        String sortFieldInfo = wncol.COLLECTION_INFO[idx][WNDefine.SORT_FIELD];
        if (analyzer.length < 5 || pageInfo.length < 2
                || rankingOption.length < 3) {
            appendWarnings("[WARNING] [setCollectionBasicInfo] [" + WNCollection.COLLECTIONS[idx]
                    + "] Analyzer : '" + wncol.COLLECTION_INFO[idx][WNDefine.ANALYZER]
                    + "', PageInfo : '" + wncol.COLLECTION_INFO[idx][WNDefine.PAGE_INFO]
                    + "', RankingOption : '" + wncol.COLLECTION_INFO[idx][WNDefine.RANKING_OPTION]
                    + "' error");
            return -1;
        }

        int useKma = Integer.parseInt(analyzer[0]);
        int isCase = Integer.parseInt(analyzer[1]);
        int useOriginal = Integer.parseInt(analyzer[2]);
        int useSynonym = Integer.parseInt(analyzer[3]);
        int useDuplicateDetection = Integer.parseInt(analyzer[4]);

        if (checkKeywordAnalyzer(WNCollection.COLLECTIONS[idx], useKma, isCase, useOriginal, useSynonym, useDuplicateDetection) == -1) {
            return -1;
        }

        ret = addCollection(idx, useKma, isCase, useOriginal, useSynonym, useDuplicateDetection);

        if (checkSetPageInfo(WNCollection.COLLECTIONS[idx], this.hiSum, Integer.parseInt(pageInfo[0]), Integer.parseInt(pageInfo[1])) == -1) {
            return -1;
        }
        ret = common.setPageInfo(WNCollection.COLLECTIONS[idx], this.hiSum,
                Integer.parseInt(pageInfo[0]), Integer.parseInt(pageInfo[1]));

        ret = common.setRanking(WNCollection.COLLECTIONS[idx], rankingOption[0], rankingOption[1], WNUtils.parseInt(rankingOption[2], 100));

        for (String sortField : sortFieldInfo.split(",")) {
            String[] tokens = sortField.split("/");
            if (tokens.length != 2) {
                appendWarnings("[ERROR] Sort Field format is not correct. ( FIELD/ORDER )");
                return -1;
            }
            if (checkSortFieldName(WNCollection.COLLECTIONS[idx], tokens[0], tokens[1]) != 0)
                return -1;
        }
        ret = common.setSortField(WNCollection.COLLECTIONS[idx], sortFieldInfo);
        return ret;
    }

    /**
     * @param idx
     * @param useKma
     * @param isCase
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int addCollection(int idx, int useKma, int isCase, int useOriginal, int useSynonym, int useDuplicateDetection) {
        int ret = 0;
        String indexName = WNCollection.COLLECTIONS[idx];
        String colName = wncol.COLLECTION_INFO[idx][WNDefine.COLLECTION_NAME];
        if (indexName.equals(colName)) {
            if (useOriginal == -1 && useSynonym == -1) {
                ret = common.addCollection(indexName, useKma, isCase);
            } else {
                ret = common.addCollection(indexName, useKma, isCase, useOriginal, useSynonym, useDuplicateDetection);
            }
        } else {
            if (useOriginal == -1 && useSynonym == -1) {
                ret = common.addAliasCollection(indexName, colName, useKma, isCase);
            } else {
                ret = common.addAliasCollection(indexName, colName, useKma, isCase, useOriginal, useSynonym, useDuplicateDetection);
            }

        }

        return ret;
    }

    /**
     * WNCollection�� wncol.COLLECTION_INFO�� ������ �ʿ��� ���� Assign�Ѵ�.
     *
     * @param collName �˻��ϰ��� �ϴ� �÷����� �̸�
     * @param target   Assign ���
     * @param value    Assign ��
     * @return �����̸� true, �����ϸ� false�� ��ȯ�Ѵ�.
     */
    public boolean setCollectionInfoValue(String collName, int target, String value) {
        int idx = getCollIdx(collName);
        if (idx == -1) {
            appendWarnings("[WARNING] [w3AddCollection] [setCollectionInfoValue] [" + collName + "] Collection name is not exist");
            return false;
        }
        wncol.COLLECTION_INFO[idx][target] = value;
        return true;
    }

    /**
     * WNCollection�� wncol.COLLECTION_INFO�� ������ �ʿ��� ���� Assign�Ѵ�.
     *
     * @param collName �˻��ϰ��� �ϴ� �÷����� �̸�
     * @param target   Assign ���
     * @param value    Assign ��
     * @return �����̸� true, �����ϸ� false�� ��ȯ�Ѵ�.
     */
    public boolean setMergeCollectionInfoValue(String collName, int target, String value) {
        int idx = getMergeCollIdx(collName);
        if (idx == -1) {
            appendWarnings("[WARNING] [w3AddMergeCollection] [setMergeCollectionInfoValue] [" + collName + "] Collection name is not exist");
            return false;
        }
        wncol.MERGE_COLLECTION_INFO[idx][target] = value;
        return true;
    }

    /**
     * �˻��ϰ��� �ϴ� �÷����� ��� �˻� �ʵ带 �����Ѵ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setSearchFieldInfo(int idx) {
        int ret = 0;//������ �˻��ʵ� ��ü���ǿ��� �˻�
        //WNCollection�� ���ǵ� �˻��ʵ� ����Ʈ�� ��´�.
        String searchFields = wncol.COLLECTION_INFO[idx][WNDefine.SEARCH_FIELD];
        String[] searchFieldList = WNUtils.split(searchFields, ",");
        if (searchFieldList.length < 1) {
            appendWarnings("[WARNING] [w3AddSearchField] [setAllSearchFieldInfo] [" + WNCollection.COLLECTIONS[idx] + "] SEARCH_FIELD should be defined.");
            return -1;
        }
        int i = checkFieldName("[w3AddSearchField]", WNCollection.COLLECTIONS[idx], searchFieldList, WNDefine.SEARCH_FIELD);
        if (i != 0) {
            appendWarnings("[WARNING] [w3AddSearchField] [setAllSearchFieldInfo] [" + WNCollection.COLLECTIONS[idx] + "] '" + searchFieldList[i - 1] + "' is not exist.");
            return -1;
        }
        ret = common.setSearchField(WNCollection.COLLECTIONS[idx], searchFields);
        return ret;
    }


    /**
     * �÷��� ���� �˻��� �����Ѵ�.
     *
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setCollectionQuery(int idx) {
        int ret = 0;
        //�÷��� �˻� ����
        if (!wncol.COLLECTION_INFO[idx][WNDefine.COLLECTION_QUERY].equals("")) {
            if (isDebug) {
                checkCollectionQueryFieldName("[w3SetCollectionQuery] [setCollectionQuery]", WNCollection.COLLECTIONS[idx], wncol.COLLECTION_INFO[idx][WNDefine.COLLECTION_QUERY]);
            }
            ret = common.setCollectionQuery(WNCollection.COLLECTIONS[idx],
                    wncol.COLLECTION_INFO[idx][WNDefine.COLLECTION_QUERY]);
        }
        return ret;
    }

    /**
     * Ư�� �ʵ忡 ���� �� ����ġ�� �ο��Ͽ� �˻� ����� �����ϵ��� ���ش�.
     *
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setBoostQuery(int idx) {
        int ret = 0;
        //Boost �˻� ����
        if (!wncol.COLLECTION_INFO[idx][WNDefine.BOOST_QUERY].equals("")) {
			/* if(isDebug) {
				checkCollectionQueryFieldName("[w3SetBoostQuery] [setBoostQuery]", WNCollection.COLLECTIONS[idx],wncol.COLLECTION_INFO[idx][COLLECTION_QUERY] );
			} */
            ret = common.setBoostQuery(WNCollection.COLLECTIONS[idx], wncol.COLLECTION_INFO[idx][WNDefine.BOOST_QUERY]);
        }
        return ret;
    }

    /**
     * �˻��ϰ��� �ϴ� �÷����� UID �˻��� �����Ѵ�.
     *
     * @param idx   �˻��ϰ��� �ϴ� �÷����� index
     * @param value �˻��ϰ��� �ϴ� UID ��
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setUid(int idx, String value, String searcherId) {
        int ret = 0;

        if (!value.equals("")) {
            String[] values = WNUtils.split(value, ",");
            ret = common.setUid(WNCollection.COLLECTIONS[idx], values, searcherId);
        }
        return ret;
    }

    /**
     * �˻��ϰ��� �ϴ� �÷����� �˻� ��¥ ������ �����Ѵ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setDateRange(int idx) {
        int ret = 0;
        //��¥�˻� ���� ����
        if (!wncol.COLLECTION_INFO[idx][WNDefine.DATE_RANGE].equals("")) {
            String[] dateFields = WNUtils.split(wncol.COLLECTION_INFO[idx][WNDefine.DATE_RANGE], ",");
            if (dateFields.length < 2) {
                appendWarnings("[WARNING] [w3SetDateRange] [setDateRange] [" + WNCollection.COLLECTIONS[idx] + "] DATE_RANGE : '" + wncol.COLLECTION_INFO[idx][WNDefine.DATE_RANGE] + "' error.");
                return -1;
            }
            ret = common.setDateRange(WNCollection.COLLECTIONS[idx], dateFields[0], dateFields[1], dateFields[2]);
        }
        return ret;
    }

    /**
     * �˻��ϰ��� �ϴ� �÷����� �˻� Ranking ������ �����Ѵ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setRankRange(int idx) {
        int ret = 0;
        //��¥�˻� ���� ����
        if (!wncol.COLLECTION_INFO[idx][WNDefine.RANK_RANGE].equals("")) {
            String[] rankFields = WNUtils.split(wncol.COLLECTION_INFO[idx][WNDefine.RANK_RANGE], ",");
            if (rankFields.length < 2) {
                appendWarnings("[WARNING] [w3SetRankRange] [setRankRange] [" + WNCollection.COLLECTIONS[idx] + "] RANK_RANGE : '" + wncol.COLLECTION_INFO[idx][WNDefine.RANK_RANGE] + "' error.");
                return -1;
            }
            int min = WNUtils.parseInt(rankFields[0], 0);
            int max = WNUtils.parseInt(rankFields[1], 100);
            ret = common.setRankRange(WNCollection.COLLECTIONS[idx], min, max);
        }
        return ret;
    }

    /**
     * �˻��ϰ��� �ϴ� �÷����� �⺻ �˻� �ʵ� �ܿ� Ư�� �ʵ忡 Ű���带 ������ �����Ѵ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setPrefixQuery(int idx) {
        int ret = 0;
        //Ȯ�� �˻� ����
        if (!wncol.COLLECTION_INFO[idx][WNDefine.EXQUERY_FIELD].equals("")) {
            if (isDebug) {
                checkExFieldName("[w3AddExQuery] [setExQuery]", WNCollection.COLLECTIONS[idx]
                        , wncol.COLLECTION_INFO[idx][WNDefine.EXQUERY_FIELD]);
            }
            ret = common.setPrefixQuery(WNCollection.COLLECTIONS[idx],
                    wncol.COLLECTION_INFO[idx][WNDefine.EXQUERY_FIELD]);
        }
        return ret;
    }

    /**
     * ī�װ� �ν����� �����Ѵ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setBoostCategory(int idx) {
        int ret = 0;
        //Ȯ�� �˻� ����
        if (!wncol.COLLECTION_INFO[idx][WNDefine.CATEGORY_BOOST].equals("")) {
            String[] dataFields = WNUtils.split(wncol.COLLECTION_INFO[idx][WNDefine.CATEGORY_BOOST], ",");

            if (dataFields.length == 3) {
                ret = common.setBoostCategory(WNCollection.COLLECTIONS[idx],
                        dataFields[0], dataFields[1], dataFields[2]);
            }
        }
        return ret;
    }


    /**
     * �˻���� ��Ŷ�� ī�װ� ���� �÷����� ī�װ� �ʵ忡�� Depth�� Category�� ����Ʈ�� ���Խ�Ų��.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int addCategoryGroupBy(int idx) {
        int ret = 0;

        if (!wncol.COLLECTION_INFO[idx][WNDefine.CATEGORY_GROUPBY].equals("")) {
            String[] categoryGroup = WNUtils.split(wncol.COLLECTION_INFO[idx][WNDefine.CATEGORY_GROUPBY], "|");

            common.addCategoryGroupBy(WNCollection.COLLECTIONS[idx], categoryGroup);
        }

        return ret;
    }

    /**
     * ������ �÷��� ���� ���� ī�װ� �ʵ��� ������ �˻������ ���͸� �Ѵ�.
     * Ȥ�� �ٸ� �������� �������� �����ϸ� �ش� ī�װ��� ���� ��ġ�Ǵ� ������ ����Ѵ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int addCategoryQuery(int idx) {
        int ret = 0;

        if (!wncol.COLLECTION_INFO[idx][WNDefine.CATEGORY_QUERY].equals("")) {
            String[] categoryQuery = WNUtils.split(wncol.COLLECTION_INFO[idx][WNDefine.CATEGORY_QUERY], ",");

            ret = common.addCategoryQuery(WNCollection.COLLECTIONS[idx], categoryQuery);
        }

        return ret;
    }

    public int getCategoryCount(String collectionName, String fieldName, int depth) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetCategoryCount] [getCategoryCount]", collectionName);
        if (chk == -1) {
            return -1;
        }
		/*
		chk = checkValidDocumentField(collectionName, fieldName, RESULT_FIELD);
		if(chk == -1){
			appendWarnings("[WARNING] [w3GetCategoryCount] [getCategoryCount] [" + collectionName + "] RESULT_FIELD should be defined.");
			return -1;
		}
		*/

        return common.getCategoryCount(collectionName, fieldName, depth);
    }


    public String getCategoryName(String collectionName, String fieldName, int depth, int idx) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetCategoryName] [getCategoryName]", collectionName);
        if (chk == -1) {
            return null;
        }
		/*
		chk = checkValidDocumentField(collectionName, fieldName, RESULT_FIELD);
		if(chk == -1){
			appendWarnings("[WARNING] [w3GetCategoryName] [getCategoryName] [" + collectionName + "] RESULT_FIELD should be defined.");
			return null;
		}
		*/
        return common.getCategoryName(collectionName, fieldName, depth, idx);
    }

    public int getDocumentCountInCategory(String collectionName, String fieldName, int depth, int idx) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetDocumentCountInCategory] [getDocumentCountInCategory]", collectionName);
        if (chk == -1) {
            return -1;
        }
		/*
		chk = checkValidDocumentField(collectionName, fieldName, RESULT_FIELD);
		if(chk == -1){
			appendWarnings("[WARNING] [w3GetDocumentCountInCategory] [getDocumentCountInCategory] [" + collectionName + "] RESULT_FIELD should be defined.");
			return -1;
		}
		*/
        return common.getDocumentCountInCategory(collectionName, fieldName, depth, idx);
    }

    /**
     * ���Ѱ˻��ϰ��� �ϴ� �÷����� Ư�� �ʵ�� ������ �����Ѵ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setAuthorityQuery(int idx) {
        int ret = 0;
        //�÷��� �˻� ����
        if (!wncol.COLLECTION_INFO[idx][WNDefine.AUTH_QUERY].equals("")) {
            String[] dataFields = WNUtils.split(wncol.COLLECTION_INFO[idx][WNDefine.AUTH_QUERY], ",");

            if (dataFields.length == 4) {
                ret = common.setAuthorityQuery(WNCollection.COLLECTIONS[idx],
                        dataFields[0], dataFields[1], dataFields[2], dataFields[3]);
            }

        }
        return ret;
    }


    /**
     * Ư�� �ʵ��� ���� �������� ���� ���� �׷��� ����ų�, �� �ʵ� ���� Ư�� ������ ���ϴ� ���ڵ常 ����
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setPropertyGroup(int idx) {
        int ret = 0;
        //�÷��� �˻� ����
        if (!wncol.COLLECTION_INFO[idx][WNDefine.PROPERTY_GROUP].equals("")) {
            String[] dataFields = WNUtils.split(wncol.COLLECTION_INFO[idx][WNDefine.PROPERTY_GROUP], ",");

            if (dataFields.length == 4) {
                ret = common.setPropertyGroup(WNCollection.COLLECTIONS[idx],
                        dataFields[0], WNUtils.parseInt(dataFields[1], 0),
                        WNUtils.parseInt(dataFields[2], 1000000), WNUtils.parseInt(dataFields[3], 5));
            }
        }
        return ret;
    }

    /**
     * �˻���κ��� �޾ƿ� �Ӽ� �׷��� ������ ��ȯ
     *
     * @param collectionName
     * @return �����̸� �˻���� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ��ȯ�Ѵ�.
     */
    public int getCountPropertyGroup(String collectionName) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetCountPropertyGroup] [getCountPropertyGroup]", collectionName);
        if (chk == -1) {
            return cnt;
        }
        return common.getCountPropertyGroup(collectionName);
    }

    /**
     * ��ü �Ӽ� �׷� ������� �ּҰ��� ��ȯ
     *
     * @param collectionName
     * @return �����̸� �˻���� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ��ȯ�Ѵ�.
     */
    public int getMinValuePropertyGroup(String collectionName) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetMinValuePropertyGroup] [getMinValuePropertyGroup]", collectionName);
        if (chk == -1) {
            return cnt;
        }
        return common.getMinValuePropertyGroup(collectionName);
    }

    /**
     * ��ü �Ӽ� �׷� ������� �ִ밪�� ��ȯ
     *
     * @param collectionName
     * @return �����̸� �˻���� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ��ȯ�Ѵ�.
     */
    public int getMaxValuePropertyGroup(String collectionName) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetMaxValuePropertyGroup] [getMaxValuePropertyGroup]", collectionName);
        if (chk == -1) {
            return cnt;
        }
        return common.getMaxValuePropertyGroup(collectionName);
    }

    /**
     * ������ �Ӽ� �׷� �� �ּҰ��� ��ȯ
     *
     * @param collectionName
     * @param groupIndex
     * @return �����̸� �˻���� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ��ȯ�Ѵ�.
     */
    public int getMinValueInPropertyGroup(String collectionName, int groupIndex) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetMinValueInPropertyGroup] [getMinValueInPropertyGroup]", collectionName);
        if (chk == -1) {
            return cnt;
        }
        return common.getMinValueInPropertyGroup(collectionName, groupIndex);
    }

    /**
     * ������ �Ӽ� �׷� �� �ִ밪�� ��ȯ
     *
     * @param collectionName
     * @param groupIndex
     * @return �����̸� �˻���� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ��ȯ�Ѵ�.
     */
    public int getMaxValueInPropertyGroup(String collectionName, int groupIndex) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetMaxValueInPropertyGroup] [getMaxValueInPropertyGroup]", collectionName);
        if (chk == -1) {
            return cnt;
        }
        return common.getMaxValueInPropertyGroup(collectionName, groupIndex);
    }


    /**
     * �˻��ϰ��� �ϴ� �÷����� Ư�� �ʵ��� ������ ���͸� �Ѵ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setFilterQuery(int idx) {
        int ret = 0;
        if (!wncol.COLLECTION_INFO[idx][WNDefine.FILTER_OPERATION].equals("")) {
            if (isDebug) {
                checkFilterFieldName("[w3SetFilterQuery] [setFilterQuery]", WNCollection.COLLECTIONS[idx]
                        , wncol.COLLECTION_INFO[idx][WNDefine.FILTER_OPERATION]);
            }
            ret = common.setFilterQuery(WNCollection.COLLECTIONS[idx], wncol.COLLECTION_INFO[idx][WNDefine.FILTER_OPERATION]);
        }
        return ret;
    }

    public int setMultiGroupBy(int idx) {
        int ret = 0;
        if (!wncol.COLLECTION_INFO[idx][WNDefine.MULTI_GROUP_BY].equals("")) {
            String field = wncol.COLLECTION_INFO[idx][WNDefine.MULTI_GROUP_BY];
            //�׷��������� Ȯ��
            ret = common.setMultiGroupBy(WNCollection.COLLECTIONS[idx], field);
        }
        return ret;
    }

    /**
     * �˻��ϰ��� �ϴ� �÷����� Ư�� �ʵ��� ������ �׷�ȭ �Ѱ� ���� ������ ���� ��� �����Ѵ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int addMultiGroupBy(int idx) {
        int ret = 0;
        if (!wncol.COLLECTION_INFO[idx][WNDefine.MULTI_GROUP_BY].equals("")) {
            String field = wncol.COLLECTION_INFO[idx][WNDefine.MULTI_GROUP_BY];
            //�׷��������� Ȯ��
            ret = common.addMultiGroupBy(WNCollection.COLLECTIONS[idx], field);
        }
        return ret;
    }

    /**
     *
     */
    public String getMultiGroupByResult(String collectionName, String fieldName) {
        int cnt = 0;
        int chk = checkValidCollection("[w3AddMultiGroupBy] [getMultiGroupByResult]", collectionName);
        if (chk == -1) {
            return null;
        }
        chk = checkValidDocumentField(collectionName, fieldName, WNDefine.RESULT_FIELD);
        if (chk == -1) {
            appendWarnings("[WARNING] [w3AddMultiGroupBy] [getMultiGroupByResult] [" + collectionName + "] RESULT_FIELD should be defined.");
            return null;
        }
        return common.getMultiGroupByResult(collectionName, fieldName);
    }

    /**
     * �˻��ϰ��� �ϴ� �÷����� Ư�� �ʵ��� ������ �׷�ȭ �Ѱ� ���� ������ ���� ��� �����Ѵ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setGroupBy(int idx) {
        int ret = 0;
        if (!wncol.COLLECTION_INFO[idx][WNDefine.GROUP_BY].equals("")) {
            String[] groupby = WNUtils.split(wncol.COLLECTION_INFO[idx][WNDefine.GROUP_BY], ",");
            if (isDebug) {
                // �׷�ȭ�� �ʵ尡 fastacces�� �����ִ��� üũ
                if (checkValidField(WNCollection.COLLECTIONS[idx], groupby[0], WNDefine.FAST_ACCESS) == -1) {
                    appendWarnings("[WARNING] [w3SetGroupBy] [setGroupBy] [" + WNCollection.COLLECTIONS[idx] + "] " + groupby[0] + " is not fastaccess field.");
                }
            }
            //�׷��������� Ȯ��
            if (groupby.length == 2) {
                //�׷�ȭ ����
                ret = common.setGroupBy(WNCollection.COLLECTIONS[idx], groupby[0], WNUtils.parseInt(groupby[1], 0));
                //�׷� ���� ���� ���� Ȯ��
                ret = addSortFieldInGroup(idx);
            } else {
                appendWarnings("[WARNING] [w3SetGroupBy] [setGroupBy] [" + WNCollection.COLLECTIONS[idx] + "] " + wncol.COLLECTION_INFO[idx][WNDefine.GROUP_BY] + "  Check variable format and value.");
            }
        }
        return ret;
    }

    /**
     * �׷�ȭ �� �÷����� ������ ���Ѵ�.
     *
     * @param idx
     * @return �����̸� 0 ���и� 0 ���� ���� �� ��ȯ
     */
    public int addSortFieldInGroup(int idx) {
        int ret = -1;
        //�׷� ���� �������� Ȯ��
        if (wncol.COLLECTION_INFO[idx][WNDefine.GROUP_SORT_FIELD] != "") {
            String sortValue = wncol.COLLECTION_INFO[idx][WNDefine.GROUP_SORT_FIELD];
            ret = common.setSortFieldInGroup(WNCollection.COLLECTIONS[idx], sortValue);
        }
        return ret;
    }

    /**
     * �˻�������� �ߺ����� ���� ������ �����Ѵ�.
     *
     * @param collectionName
     * @param idx
     * @return �˻��� �ش� ������ �ߺ����� ������ ��ȯ
     */
    public int setDuplicateDetectionCriterionField(int idx) {
        int ret = 0;
        if (!wncol.COLLECTION_INFO[idx][WNDefine.DEDUP_SORT_FIELD].equals("")) {
            String sortValue = wncol.COLLECTION_INFO[idx][WNDefine.DEDUP_SORT_FIELD];
            ret = common.setDuplicateDetectionCriterionField(WNCollection.COLLECTIONS[idx], sortValue);
        }
        return ret;
    }

    /**
     * �˻� ��� �� ���� ������ ���ؼ� �ߺ����� �Ǻ��� ������ ������ ��ȯ�Ѵ�.
     *
     * @param collectionName
     * @param idx
     * @return �˻��� �ش� ������ �ߺ����� ������ ��ȯ
     */
    public int getDuplicateDocumentCount(String collectionName, int idx) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetDuplicateDocumentCount] [getDuplicateDocumentCount]", collectionName);
        if (chk == -1) {
            return cnt;
        }
        return common.getDuplicateDocumentCount(collectionName, idx);
    }

    /**
     * �׷�ȭ�� ���� �� ������ ������ �׷� ������ �� ������� �ߺ��� ������ ������ ��ȯ
     *
     * @param collectionName
     * @param groupIndex
     * @param idx
     * @return �ߺ����� ������ ��ȯ
     */
    public int getDuplicateDocumentCountInGroup(String collectionName, int groupIndex, int idx) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetDuplicateDocumentCount] [getDuplicateDocumentCount]", collectionName);
        if (chk == -1) {
            return cnt;
        }
        return common.getDuplicateDocumentCountInGroup(collectionName, groupIndex, idx);
    }

    /**
     * �׷�ȭ ������ ��ü �׷� ���� �����´�.
     *
     * @param collectionName
     * @return �����̸� �˻���� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ��ȯ�Ѵ�.
     */
    public int getResultTotalGroupCount(String collectionName) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetResultTotalGroupCount] [getResultTotalGroupCount]", collectionName);
        if (chk == -1) {
            return cnt;
        }
        return common.getResultTotalGroupCount(collectionName);
    }

    /**
     * ȭ�鿡 ������ �׷�ȭ ������ �׷� ���� �����´�.
     *
     * @param collectionName
     * @return �����̸� �˻���� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ��ȯ�Ѵ�.
     */
    public int getResultGroupCount(String collectionName) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetGroupByCount] [getGroupByCount]", collectionName);
        if (chk == -1) {
            return cnt;
        }
        return common.getResultGroupCount(collectionName);
    }


    /**
     * �׷쿡 ���ϴ� ������ ������ ���� ������ ���Ѵ�.
     *
     * @param collectionName
     * @param groupIndex
     * @return �׷쿡 ���ϴ� �������� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ������ ��ȯ�Ѵ�.
     */
    public int getTotalCountInGroup(String collectionName, int groupIndex) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetTotalCountInGroup] [getTotalCountInGroup]", collectionName);
        if (chk == -1) {
            return cnt;
        }
        return common.getTotalCountInGroup(collectionName, groupIndex);
    }

    /**
     * @param collectionName
     * @param groupIndex
     * @return �׷쿡 ���ϴ� ������ ������ ���� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ������ ��ȯ�Ѵ�.
     */
    public int getCountInGroup(String collectionName, int groupIndex) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetCountInGroup] [getCountInGroup]", collectionName);
        if (chk == -1) {
            return cnt;
        }
        return common.getCountInGroup(collectionName, groupIndex);
    }

    /**
     * �׷�ȭ�� ���� �� ������ ������ �׷� ������ �־��� collection ��� ���� index ��° ������ Collection ID�� ��ȯ
     *
     * @param collectionName
     * @param groupIndex
     * @param docIndex
     * @return �׷쿡 ���ϴ� ������ Ư�� ������ �ʵ尪�� ��ȯ�Ѵ�. ������ ��� null���� ��ȯ�Ѵ�.
     */
    public String getCollectionIdInGroup(String collectionName, int groupIndex, int docIndex) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetCollectionIdInGroup] [getCollectionIdInGroup]", collectionName);
        if (chk == -1) {
            return null;
        }
        return common.getCollectionIdInGroup(collectionName, groupIndex, docIndex);
    }

    /**
     * �׷쿡 ���ϴ� ������ Ư�� ������ Ranking���� ���Ѵ�.
     *
     * @param collectionName
     * @param groupIndex
     * @param docIndex
     * @return �׷쿡 ���ϴ� ������ Ư�� ������ �ʵ尪�� ��ȯ�Ѵ�. ������ ��� -1���� ��ȯ�Ѵ�.
     */
    public long getRankInGroup(String collectionName, int groupIndex, int docIndex) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetRankInGroup] [getRankInGroup]", collectionName);
        if (chk == -1) {
            return -1;
        }
        return common.getRankInGroup(collectionName, groupIndex, docIndex);
    }

    /**
     * �׷쿡 ���ϴ� ������ Ư�� ������ �ʵ尪�� ���Ѵ�.
     *
     * @param collectionName
     * @param fieldName
     * @param groupIndex
     * @param docIndex
     * @return �׷쿡 ���ϴ� ������ Ư�� ������ �ʵ尪�� ��ȯ�Ѵ�. ������ ��� null���� ��ȯ�Ѵ�.
     */
    public String getFieldInGroup(String collectionName, String fieldName, int groupIndex, int docIndex) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetFieldInGroup] [getFieldInGroup]", collectionName);
        if (chk == -1) {
            return null;
        }
        chk = checkValidDocumentField(collectionName, fieldName, WNDefine.RESULT_FIELD);
        if (chk == -1) {
            appendWarnings("[WARNING] [w3AddDocumentField] [setResultFieldInfo] [" + collectionName + "] RESULT_FIELD should be defined.");
            return null;
        }
        return common.getFieldInGroup(collectionName, fieldName, groupIndex, docIndex);
    }

    /**
     * �׷쿡 ���ϴ� ������ Ư�� ������ UID���� ���Ѵ�.
     *
     * @param collectionName
     * @param groupIndex
     * @param docIndex
     * @return �׷쿡 ���ϴ� ������ Ư�� ������ UID���� ��ȯ�Ѵ�. ������ ��� 0���� ���� ���� ��ȯ�Ѵ�.
     */
    public long getUidInGroup(String collectionName, int groupIndex, int docIndex) {
        int cnt = 0;
        int chk = checkValidCollection("[w3getUidInGroup] [getCountInGroup]", collectionName);
        if (chk == -1) {
            return -1;
        }
        return common.getUidInGroup(collectionName, groupIndex, docIndex);
    }

    /**
     * �׷쿡 ���ϴ� ������ Ư�� ������ ����ġ���� ���Ѵ�.
     *
     * @param collectionName
     * @param groupIndex
     * @param docIndex
     * @return �׷쿡 ���ϴ� ������ Ư�� ������ ����ġ���� ��ȯ�Ѵ�. ������ ��� 0���� ���� ���� ��ȯ�Ѵ�.
     */
    public long getWeightInGroup(String collectionName, int groupIndex, int docIndex) {
        int cnt = 0;
        int chk = checkValidCollection("[w3getUidInGroup] [getCountInGroup]", collectionName);
        if (chk == -1) {
            return -1;
        }
        return common.getWeightInGroup(collectionName, groupIndex, docIndex);
    }

    /**
     * �׷쿡 ���ϴ� ������ Ư�� ������ ��¥�������� ���Ѵ�.
     *
     * @param collectionName
     * @param groupIndex
     * @param docIndex
     * @return �׷쿡 ���ϴ� ������ Ư�� ������ ��¥�������� ��ȯ�Ѵ�. ������ ��� ���ڿ��� ��ȯ�Ѵ�.
     */
    public String getDateInGroup(String collectionName, int groupIndex, int docIndex) {
        int cnt = 0;
        int chk = checkValidCollection("[w3getUidInGroup] [getCountInGroup]", collectionName);
        if (chk == -1) {
            return "";
        }
        return common.getDateInGroup(collectionName, groupIndex, docIndex);
    }


    /**
     * �׷쿡 ���ϴ� ������ Ư�� ������ �˻��� ���̵��� ���Ѵ�.
     *
     * @param collectionName
     * @param groupIndex
     * @param docIndex
     * @return �׷쿡 ���ϴ� ������ Ư�� ������ �˻��� ���̵��� ��ȯ�Ѵ�. ������ ��� ���ڿ��� ��ȯ�Ѵ�.
     */
    public String getSearcherIdInGroup(String collectionName, int groupIndex, int docIndex) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetSearcherIdInGroup] [getSearcherIdInGroup]", collectionName);
        if (chk == -1) {
            return "";
        }
        return common.getSearcherIdInGroup(collectionName, groupIndex, docIndex);
    }

    /**
     * �˻��ϰ��� �ϴ� �÷����� ��� �ʵ带 �����Ѵ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int setResultFieldInfo(int idx) {
        int ret = 0;
        String resultFields = wncol.COLLECTION_INFO[idx][WNDefine.RESULT_FIELD];
        String[] resultFieldList = WNUtils.split(resultFields, ",");
        if (resultFieldList.length < 1) {
            appendWarnings("[WARNING] [w3AddDocumentField] [setResultFieldInfo] [" + WNCollection.COLLECTIONS[idx] + "] RESULT_FIELD should be defined.");
            return -1;
        }
        int i = checkFieldName("[w3AddDocumentField]", WNCollection.COLLECTIONS[idx], resultFieldList, WNDefine.RESULT_FIELD);
        if (i != 0) {
            appendWarnings("[WARNING] [w3AddDocumentField] [setResultFieldInfo] [" + WNCollection.COLLECTIONS[idx] + "] '" + resultFieldList[i - 1] + "' is not exist.");
            return -1;
        }
        ret = common.setResultField(WNCollection.COLLECTIONS[idx], resultFields);
        return ret;
    }

    /**
     * �˻� ������ �����ϴ� ���ڵ�� ������ ����,
     * �˻� ������ start ��ġ�������� count���� ���ų� ���� ������ �����´�.
     *
     * @param collectionName
     * @return �����̸� �˻���� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ��ȯ�Ѵ�.
     */
    public int getResultCount(String collectionName) {
        int cnt = 0;
        int chk = checkValidCollection("[w3GetResultCount] [getResultCount]", collectionName);
        if (chk == -1) {
            return cnt;
        }
        return common.getResultCount(collectionName);
    }

    /**
     * �˻� ������ �����ϴ� �� �˻� ��� ����
     *
     * @param collectionName
     * @return �����̸� �˻���� �� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ��ȯ�Ѵ�.
     */
    public int getResultTotalCount(String collectionName) {
        int chk = checkValidCollection("[w3GetResultTotalCount] [getResultTotalCount]", collectionName);
        if (chk == -1) {
            return chk;
        }
        return common.getResultTotalCount(collectionName);
    }

    /**
     * ���� �˻� �ǰ� �ִ� �ǽð� �˻��� ����Ʈ�� ��´�.
     *
     * @param count �ǽð� �˻��� ����
     * @return �ǽð� �˻��� ����Ʈ�� ��ȯ�Ѵ�.
     */
    public String recvRealTimeSearchKeywordList(int count) {
        StringBuffer sbKey = new StringBuffer();
        String[] keywordList = common.receiveRecentQueryListResult(WNDefine.CONNECTION_KEEP, count);
        if (keywordList != null) {
            int keyCount = keywordList.length;
            for (int i = 0; i < keyCount; i++) {
                sbKey.append(keywordList[i].replaceAll("'", "").replaceAll("\\\\", ""));
                if (i < keywordList.length - 1) {
                    sbKey.append(",");
                }
            }
        } else {
            return "";
        }
        return sbKey.toString();
    }

    /**
     * �˻� ��� �ʵ��� ���� ��´�. field�� ���� UID, RANK, DATE ���� ���� �� �ִ�.
     *
     * @param collectionName ����� ����� �ϴ� �÷��� �̸�
     * @param field          ��� �ʵ�
     * @param idx            �˻���� �÷����� index
     * @param isHL           ���̶������� �� ������ ���θ� ����
     * @return FIELD VALUE
     */
    public String getField(String collectionName, String field, int idx, boolean isHL) {
        String ret = "";
        if (collectionName.equals("")) return ret;
        if (field.toUpperCase().equals("UID")) {
            ret = String.valueOf(common.getUid(collectionName, idx));
        } else if (field.toUpperCase().equals("RANK")) {
            ret = String.valueOf(common.getRank(collectionName, idx));
        } else if (field.toUpperCase().equals("DATE")) {
            ret = WNUtils.parseDate(common.getDate(collectionName, idx), "yyyy/MM/dd HH:mm:ss", "yyyy.MM.dd");
        } else if (field.toUpperCase().equals("WEIGHT")) {
            ret = String.valueOf(common.getWeight(collectionName, idx));
        } else if (field.toUpperCase().equals("SEARCHERID")) {
            ret = common.getSearcherId(collectionName, idx);
        } else if (field.toUpperCase().equals("COLLECTIONID")) {
            ret = common.getCollectionId(collectionName, idx);
        } else {

            if (checkValidDocumentField(collectionName, field, WNDefine.RESULT_FIELD) == -1) {
                return ret;
            }
            if (isHL) {
                ret = getKeywordHl(common.getField(collectionName, field, idx), "red");
            } else {
                ret = common.getField(collectionName, field, idx);
            }
        }
        return ret;
    }

    /**
     * ���� �÷����� �˻������ �ϳ��� ���� ���� �÷���(Merge Collection) ����� �����Ͽ� �������� ���� �� ����ϴ� �Լ�
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� �˻���� �� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ��ȯ�Ѵ�.
     */
    public int setMergeCollectionBasicnfo(int idx) {
        int ret = 0;

        String mergeCollectionName = wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_COLLECTION_NAME];
        String[] collectionNames = WNUtils.split(wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MAPPING_COLLECTION_NAME], "/");
        String[] pageInfo = WNUtils.split(wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_PAGE_INFO], ",");
        if (pageInfo.length < 2) {
            appendWarnings("[WARNING] [setMergeCollectionBasicnfo] [PageInfo : '" + wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_PAGE_INFO]
                    + "' error");
            return -1;
        }
        int length = collectionNames.length;
        for (int i = 0; i < length; i++) {
            int chk = checkValidCollection("[setMergeCollectionBasicnfo]", collectionNames[i]);
            if (chk == -1) {
                return -1;
            }
        }

        int start = WNUtils.parseInt(pageInfo[0], 0);
        int count = WNUtils.parseInt(pageInfo[1], 10);


        ret = common.addMergeCollectionInfo(mergeCollectionName, collectionNames, start, count);

        return ret;
    }

    /**
     * ���� ���� �÷���(Merge Collection)�� ���� ��� �ʵ�(Document Field)�� ���յ� ���� Collection�� ���� ��� �ʵ���� �����Ų��.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� �˻���� �� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ��ȯ�Ѵ�.
     */
    public int addMergeDocumentField(int idx) {
        int ret = 0;
        String mergeCollectionName = wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_COLLECTION_NAME];
        String[] collectionNames = WNUtils.split(wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MAPPING_COLLECTION_NAME], "/");

        int collectionLength = collectionNames.length;
        for (int i = 0; i < collectionLength; i++) {
            int chk = checkValidCollection("[setMergeCollectionBasicnfo]", collectionNames[i]);
            if (chk == -1) {
                return -1;
            }
        }

        String[] mergeFields = WNUtils.split(wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_RESULT_FIELD], ",");
        int mergeFieldLength = mergeFields.length;

        String[][] documentFields = new String[collectionLength][mergeFieldLength];
        String[] temp = WNUtils.split(wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MAPPING_RESULT_FIELD], "/");

        for (int k = 0; k < collectionLength; k++) {
            for (int n = 0; n < temp.length; n++) {
                if (k == n) documentFields[k] = WNUtils.split(temp[n], ",");
                if (mergeFieldLength != documentFields[k].length) {
                    appendWarnings("[Error] [addMergeDocumentField] [merge fields, collection document and different.]");
                    return -1;
                }
            }
        }

        ret = common.addMergeDocumentField(mergeCollectionName, mergeFields, collectionNames, documentFields);

        return ret;
    }


    /**
     * ���� ���� �÷���(Merge Collection)�� Multi Group-By �ʵ���� ���� �� ���� Collection�� Multi Group-By �ʵ��� �����Ű�� ������ �Ѵ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� �˻���� �� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ��ȯ�Ѵ�.
     */
    public int addMergeMultiGroupBy(int idx) {
        int ret = 0;
        if (!wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MULTI_GROUP_BY_FIELD].equals("")) {
            String mergeCollectionName = wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_COLLECTION_NAME];
            String[] collectionNames = WNUtils.split(wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MAPPING_COLLECTION_NAME], "/");

            int collectionLength = collectionNames.length;
            for (int i = 0; i < collectionLength; i++) {
                int chk = checkValidCollection("[addMergeMultiGroupBy]", collectionNames[i]);
                if (chk == -1) {
                    return -1;
                }
            }

            String[] mergeMultiGroupByFields = WNUtils.split(wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MULTI_GROUP_BY_FIELD], ",");
            int mergeLength = mergeMultiGroupByFields.length;

            String[][] documentFields = new String[collectionLength][mergeLength];
            String[] temp = WNUtils.split(wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MAPPING_MULTI_GROUP_BY_FIELD], "/");

            for (int k = 0; k < collectionLength; k++) {
                for (int n = 0; n < temp.length; n++) {
                    if (k == n) documentFields[k] = WNUtils.split(temp[n], ",");
                    if (mergeLength != documentFields[k].length) {
                        appendWarnings("[Error] [addMergeMultiGroupBy] [merge multi-group-by, collection multi-group-by and different.]");
                        return -1;
                    }
                }
            }

            ret = common.addMergeMultiGroupByField(mergeCollectionName, mergeMultiGroupByFields, collectionNames, documentFields);
        }
        return ret;
    }

    /**
     * ���� ���� �÷���(Merge Collection)�� ī�װ� �׷� �ʵ���� ���� �� ���� Collection�� ī�װ� �׷� �ʵ��� �����Ű�� ������ �Ѵ�.
     *
     * @param idx �˻��ϰ��� �ϴ� �÷����� index
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int addMergeCategoryGroupByField(int idx) {
        int ret = 0;
        if (!wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_CATEGORY_GROUPBY_FIELD].equals("")) {

            String mergeCollectionName = wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_COLLECTION_NAME];
            String[] collectionNames = WNUtils.split(wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MAPPING_COLLECTION_NAME], "/");

            int collectionLength = collectionNames.length;
            for (int i = 0; i < collectionLength; i++) {
                int chk = checkValidCollection("[addMergeCategoryGroupByField]", collectionNames[i]);
                if (chk == -1) {
                    return -1;
                }
            }

            String[] mergeCategoryGroupByFields = WNUtils.split(wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_CATEGORY_GROUPBY_FIELD], ",");
            int mergeCategoryLength = mergeCategoryGroupByFields.length;

            String[][] documentFields = new String[collectionLength][mergeCategoryLength];
            String[] temp = WNUtils.split(wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MAPPING_CATEGORY_GROUPBY_FIELD], "/");

            for (int k = 0; k < collectionLength; k++) {
                for (int n = 0; n < temp.length; n++) {
                    if (k == n) documentFields[k] = WNUtils.split(temp[n], ",");
                    if (mergeCategoryLength != documentFields[k].length) {
                        appendWarnings("[Error] [addMergeCategoryGroupByField] [merge category-group-by, collection category-group-by and different.]");
                        return -1;
                    }
                }
            }


            ret = common.addMergeCategoryGroupByField(mergeCollectionName, mergeCategoryGroupByFields, collectionNames, documentFields);
        }
        return ret;
    }


    /**
     * �ϳ��� ���� ���� �÷���(Merge Collection)���� ���յ� ���� Collection���� �˻��� ������ ��ü ������ ��ȯ�Ѵ�.
     *
     * @param mergeCollection �����÷��� �̸�
     * @param collectionName  �÷��� �̸�
     * @return �����̸� �˻���� �� ������ ��ȯ�Ѵ�. �����ϰų� ����� ���� ��� 0 ���� ��ȯ�Ѵ�.
     */
    public int getResultTotalCountInMerge(String mergeCollection, String collection) {
        int cnt = 0;

        int chk = checkValidCollection("[w3GetResultTotalCountInMerge] [getResultTotalCountInMerge]", collection);
        if (chk == -1) {
            return cnt;
        }
        return common.getResultTotalCountInMerge(mergeCollection, collection);
    }

    /**
     * �˻������ XML �����ͷ� ��ȯ
     */
    public String getResultXml() {
        return common.getResultXml();
    }

    /**
     * �˻������ JSON �����ͷ� ��ȯ
     */
    public String getResultJson() {
        return common.getResultJson();
    }


    /**
     * ��Ʈ��ũ ����
     */
    public void closeServer() {
        common.closeServer();
    }


    /**
     * �˻��� Ű������ �м� ����� ����Ʈ�� �� ���ڷ� ���� ���ڿ��� ���̶����� �Ѵ�.
     *
     * @param content ���̶����� �ϰ��� �ϴ� ���ڿ�
     * @param color
     * @return ���̶����õ� ���ڿ��� ��ȯ�Ѵ�.
     */
    public String getKeywordHl(String content, String color) {
        if (content != null) {
            content = content.replaceAll("<!HS>", "<font color='" + color + "'>");
            content = content.replaceAll("<!HE>", "</font>");
        }
        return content;
    }


    /**
     * �˻��� Ű������ �м� ����� ����Ʈ�� �� ���ڷ� ���� ���ڿ��� ���̶����� �Ѵ�.
     *
     * @param content  ���̶����� �ϰ��� �ϴ� ���ڿ�
     * @param startTag ���̶����� �ϰ��� �ϴ� Tag�� ���� ����
     * @param endTag   ���̶����� �ϰ��� �ϴ� Tag�� �� ����
     * @return ���̶����õ� ���ڿ��� ��ȯ�Ѵ�.
     */
    public String getKeywordHl(String content, String startTag, String endTag) {
        if (content != null) {
            content = WNUtils.replace(content, "<!HS>", startTag);
            content = WNUtils.replace(content, "<!HE>", endTag);
        }
        return content;
    }


    /**
     * WNCollection���� ���ǵ� WNCollection.COLLECTIONS�� index�� ��´�.
     *
     * @param collName �˻��ϰ��� �ϴ� �÷����� �̸�
     * @return �÷��� index�� ��ȯ�Ѵ�.
     */
    public int getCollIdx(String collName) {
        for (int i = 0; i < WNCollection.COLLECTIONS.length; i++) {
            if (WNCollection.COLLECTIONS[i].trim().equals(collName.trim())) return i;
        }
        return -1;
    }

    /**
     * WNCollection���� ���ǵ� MERGE WNCollection.COLLECTIONS�� index�� ��´�.
     *
     * @param collName �˻��ϰ��� �ϴ� �÷����� �̸�
     * @return �÷��� index�� ��ȯ�Ѵ�.
     */
    public int getMergeCollIdx(String collName) {
        for (int i = 0; i < (WNCollection.MERGE_COLLECTIONS).length; i++) {
            if (WNCollection.MERGE_COLLECTIONS[i].trim().equals(collName.trim())) return i;
        }
        return -1;
    }

    /**
     * �˻� �� �ش� Collection�� Erro������ ��ȯ�Ѵ�.
     *
     * @param collectionName �˻��� ������ �÷��� �̸�
     * @return collection�� ��������
     */
    public String getCollectionErrorInfo(String collectionName) {
        return common.getCollectionErrorInfo(collectionName);
    }

    /**
     * �˻� API�� ���������� ��ȯ�Ѵ�.
     *
     * @return API ��������
     */
    public String getVersionInfo() {
        return common.getVersionInfo();
    }

    /**
     * ���¼� �м� �� ��ҹ��� ���� üũ�Ѵ�.
     *
     * @param useKma ���¼� �м� ��
     * @param isCase ��ҹ��� ���� ����
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int checkKeywordAnalyzer(String collectionName, int useKma, int isCase, int useOriginal, int useSynonym, int useDuplicateDetection) {
        int chk = 0;
        if (useKma != 0 && useKma != 1 && useKma != 2) {
            appendWarnings("[WARNING] [w3SetQueryAnalyzer] [checkKeywordAnalyzer] [" + collectionName + "] '" + useKma + "' is not kma option");
            return -1;
        }
        if (isCase != 0 && isCase != 1) {
            appendWarnings("[WARNING] [w3SetQueryAnalyzer] [checkKeywordAnalyzer] [" + collectionName + "] '" + isCase + "' is not case option");
            return -1;
        }
        if (useOriginal != 0 && useOriginal != 1) {
            appendWarnings("[WARNING] [w3SetQueryAnalyzer] [checkKeywordAnalyzer] [" + collectionName + "] '" + useOriginal + "' is not use original option");
            return -1;
        }
        if (useSynonym != 0 && useSynonym != 1) {
            appendWarnings("[WARNING] [w3SetQueryAnalyzer] [checkKeywordAnalyzer] [" + collectionName + "] '" + useSynonym + "' is not use synonym option");
            return -1;
        }
        if (useDuplicateDetection != 0 && useDuplicateDetection != 1) {
            appendWarnings("[WARNING] [w3SetQueryAnalyzer] [checkKeywordAnalyzer] [" + collectionName + "] '" + useDuplicateDetection + "' is not use duplicate detection option");
            return -1;
        }

        return chk;
    }


    /**
     * �˻��ϰ��� �ϴ� �÷����� üũ�Ѵ�.
     *
     * @param collectionName �˻��ϰ��� �ϴ� �÷���
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int checkValidCollection(String msg, String collectionName) {

        int chk = getCollIdx(collectionName);
        if (chk == -1 && mergeCollections != null) { //merge collection���� check�� �ٽ��Ѵ�.
            chk = getMergeCollIdx(collectionName);
        }

        if (chk == -1) {
            appendWarnings("[WARNING] " + msg + " [" + collectionName + "] Collection name is not exist.");
        }
        return chk;
    }


    /**
     * �ʵ带 üũ�ϴ� �� �Լ����� ���ȴ�.
     *
     * @param collectionName �˻��ϰ��� �ϴ� �÷���
     * @param field          �ʵ� �̸�
     * @param item           WNCollection�� ������ ���
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int checkValidField(String collectionName, String field, int item) {

        int idx = getCollIdx(collectionName);
        String[] fields = WNUtils.split(wncol.COLLECTION_INFO[idx][item], ",");
//		int chk = wnutils.findArrayValue(field, fields);
        int chk = WNUtils.getFindArrayValue(field, fields);
        return chk;
    }


    /**
     * �ʵ带 üũ�ϴ� �� �Լ����� ���ȴ�.
     *
     * @param collectionName �˻��ϰ��� �ϴ� �÷���
     * @param field          �ʵ� �̸�
     * @param item           WNCollection�� ������ ���
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int checkValidDocumentField(String collectionName, String field, int item) {
        int chk = -1;
        int idx = getCollIdx(collectionName);
        if (idx != -1) {
            String[] fields = WNUtils.split(wncol.COLLECTION_INFO[idx][item], ",");
            int length = fields.length;

            for (int i = 0; i < length; i++) {
                String[] documentFields = WNUtils.split(fields[i], "/");
                if (documentFields != null && documentFields.length > 0 && documentFields[0].equals(field)) {
                    chk = i;
                }
            }
        } else {    //merge collection�� üũ�Ѵ�.
            idx = getMergeCollIdx(collectionName);
            if (idx != -1) {
                String[] fields = WNUtils.split(wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_RESULT_FIELD], ",");
                int length = fields.length;
                for (int i = 0; i < length; i++) {
                    if (fields[i].equals(field)) {
                        chk = i;
                    }
                }
                if (chk == -1) {
                    String[] fields1 = WNUtils.split(wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_CATEGORY_GROUPBY_FIELD], ",");
                    length = fields1.length;
                    for (int ii = 0; ii < length; ii++) {
                        if (fields1[ii].equals(field)) {
                            chk = ii;
                        }
                    }
                }
            }
        }

        return chk;
    }

    /**
     * �˻��ϰ��� �ϴ� �÷����� �� �ʵ� ���� üũ�Ѵ�.
     *
     * @param msg            ȭ�鿡 ����� �޽���
     * @param collectionName �˻��ϰ��� �ϴ� �÷���
     * @param fields         üũ�ϰ��� �ϴ� �ʵ�
     * @param item           WNCollection�� ������ ���
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int checkFieldName(String msg, String collectionName, String[] fields, int item) {
        int chk = 0;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] == null || (checkValidField(collectionName, fields[i], item) == -1)) {
                return i + 1;
            }
        }
        return chk;
    }


    /**
     * �˻��ϰ��� �ϴ� �÷����� Ȯ�� �ʵ� ���� üũ�Ѵ�.
     *
     * @param msg            ȭ�鿡 ����� �޽���
     * @param collectionName �˻��ϰ��� �ϴ� �÷���
     * @param value          üũ�ϰ��� �ϴ� �ʵ�
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int checkExFieldName(String msg, String collectionName, String value) {
        int chk = 0;
        String[] replaceChar = new String[]{"!", "|", "(", ")", "[", "]", "{", "}", "\"", "^", "<", ">"};
        for (int i = 0; i < replaceChar.length; i++) {
            value = WNUtils.replace(value, replaceChar[i], " ");
        }

        String[] fieldName = WNUtils.split(value, " ");
        for (int i = 0; i < fieldName.length; i++) {
            int index = 0;
            if (fieldName[i].indexOf(":", 0) != -1) {
                index = fieldName[i].indexOf(":", 0);
                fieldName[i] = fieldName[i].trim().substring(0, index);
            }
            // Ȯ��˻� �ʵ� �˻�
            if (checkValidField(collectionName, fieldName[i], WNDefine.PREFIX_FIELD) == -1) {
                appendWarnings("[WARNING] " + msg + "[" + collectionName + "] " + fieldName[i] + " is not prefix field.");
                chk = -1;
            }
        }
        return chk;
    }

    /**
     * �˻��ϰ��� �ϴ� �÷����� ���� �ʵ� ���� üũ�Ѵ�.
     *
     * @param msg            ȭ�鿡 ����� �޽���
     * @param collectionName �˻��ϰ��� �ϴ� �÷���
     * @param value          üũ�ϰ��� �ϴ� �ʵ�
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int checkCollectionQueryFieldName(String msg, String collectionName, String value) {
        int chk = 0;
        String[] replaceChar = new String[]{"!", "|", "(", ")", "[", "]", "{", "}", "\"", "^", "<", ">"};
        for (int i = 0; i < replaceChar.length; i++) {
            value = WNUtils.replace(value, replaceChar[i], " ");
        }

        String[] fieldName = WNUtils.split(value, " ");
        for (int i = 0; i < fieldName.length; i++) {
            int index = 0;
            if (fieldName[i].indexOf(":", 0) != -1) {
                index = fieldName[i].indexOf(":", 0);
                fieldName[i] = fieldName[i].trim().substring(0, index);
            }
            String[] fields = fieldName[i].split(",");
            for (int j = 0; j < fields.length; j++) {
                // �÷��� ���� �ʵ� �˻�
                if (checkValidField(collectionName, fields[j], WNDefine.PREFIX_FIELD) != -1) {
                    appendWarnings("[WARNING] " + msg + "[" + collectionName + "] " + fields[j] + " is prefix field.");
                    chk = -1;
                }
            }
        }
        return chk;
    }

    /**
     * �˻��ϰ��� �ϴ� �÷����� ���͸� �ʵ� ���� üũ�Ѵ�.
     *
     * @param msg            ȭ�鿡 ����� �޽���
     * @param collectionName �˻��ϰ��� �ϴ� �÷���
     * @param value          üũ�ϰ��� �ϴ� �ʵ�
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int checkFilterFieldName(String msg, String collectionName, String value) {
        int chk = 0;
        String[] replaceChar = new String[]{"|", "(", ")", "[", "]", "{", "}", "\"", "^"};
        for (int i = 0; i < replaceChar.length; i++) {
            value = WNUtils.replace(value, replaceChar[i], " ");
        }

        String[] fieldName = WNUtils.split(value, " ");
        for (int i = 0; i < fieldName.length; i++) {
            int index = 0;
            if (fieldName[i].indexOf("<", 0) != -1) {
                index = fieldName[i].indexOf("<", 0);
                fieldName[i] = fieldName[i].trim().substring(0, index);
                // ���͸� �˻� �ʵ� �˻�
                if (checkValidField(collectionName, fieldName[i], WNDefine.FAST_ACCESS) == -1) {
                    appendWarnings("[WARNING] " + msg + " [" + collectionName + "] " + fieldName[i] + " is not fastaccess field.");
                    chk = -1;
                }
            }
        }
        return chk;
    }


    /**
     * �˻������ ���� ������ üũ�Ѵ�.
     *
     * @param collectionName �˻��ϰ��� �ϴ� �÷���
     * @param highlight      ���̶����� �ɼ� ��
     * @param startPos       �˻���� ���� offset
     * @param resultCnt      �˻� ��� count
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int checkSetPageInfo(String collectionName, int highlight, int startPos, int resultCnt) {
        int chk = 0;
        if (highlight < 0 || highlight > 3 || startPos < 0 || resultCnt < 0) {
            appendWarnings("[WARNING] [] [checkSetPageInfo] [" + collectionName + "] Highlight: '"
                    + highlight + "', StartPosition: '" + startPos + "', ResultCount: '" + resultCnt + "' is invalid value");
            return -1;
        }
        return chk;
    }


    /**
     * �˻� ���� �ʵ带 üũ�Ѵ�.
     *
     * @param collectionName �˻��ϰ��� �ϴ� �÷���
     * @param sortField      �����ʵ�
     * @param sortOrder      ��������/��������
     * @return �����̸� 0�� ��ȯ�Ѵ�. ���и� 0�� �ƴ� ���� ��ȯ�Ѵ�.
     */
    public int checkSortFieldName(String collectionName, String sortField, String sortOrder) {
        if (sortField.toUpperCase().equals("DATE")) {
            if (sortOrder.equals("DESC") == false && sortOrder.equals("ASC") == false) {
                //DATE�� ���������� �����������θ� ���� ����
                appendWarnings("[ERROR] [w3AddSortField] [checkSortFieldName] [" + collectionName + "] Date sortOrder is '" + sortOrder + "'. Only DESC or ASC is allowed.");
                return -1;
            }
        } else if (sortField.toUpperCase().equals("RANK")) {
            if (sortOrder.equals("DESC") == false) {
                //RANK�� ��������(1)���θ� ���� ����
                appendWarnings("[ERROR] [w3AddSortField] [checkSortFieldName] [" + collectionName + "] RANK sortOrder is '" + sortOrder + "' Only DESC is allowed.");
                return -1;
            }
        } else if (sortField.toUpperCase().equals("WEIGHT")) {
            if (sortOrder.equals("DESC") == false && sortOrder.equals("ASC") == false) {
                //WEIGHT�� ��������(0)�� ��������(1)���θ� ���� ����
                appendWarnings("[ERROR] [w3AddSortField] [checkSortFieldName] [" + collectionName + "] WEIGHT sortOrder is '" + sortOrder + "' Only DESC or ASC is allowed");
                return -1;
            }
        } else if (sortField.toUpperCase().equals("UID")) {
            if (sortOrder.equals("DESC") == false && sortOrder.equals("ASC") == false) {
                appendWarnings("[ERROR] [w3AddSortField] [checkSortFieldName] [" + collectionName + "] UID sortOrder is '" + sortOrder + "' Only DESC or ASC is allowed");
                return -1;
            }
        } else {
            // ���� �ʵ� �˻�
            if (checkValidDocumentField(collectionName, sortField, WNDefine.RESULT_FIELD) == -1) {
                appendWarnings("[ERROR] [w3AddSortField] [checkSortFieldName] [" + collectionName + "] '" + sortField + "' is not exist.");
                return -1;
            }
            if (checkValidField(collectionName, sortField, WNDefine.FAST_ACCESS) == -1) {
                // fastaccess�� �������� ���� �ʵ忡�� �ӵ� ������ ����.
                appendWarnings("[WARNING] [w3AddSortField] [checkSortFieldName] [" + collectionName + "] " + sortField + " is not fastaccess field.");
            }
        }
        return 0;
    }

    /**
     * ����� �޽����� �����Ѵ�.
     *
     * @param isRealQuery
     * @param query
     */
    public void debugMsg(boolean isRealQuery, String query) {
        sb.append("[CHARSET] " + WNDefine.CHARSET + " [QUERY] " + query);
        sb.append("<br>[w3ConnectServer] [IP] " + WNCollection.SEARCH_IP + " [PORT] " + WNCollection.SEARCH_PORT + " [TIMEOUT]" + WNDefine.CONNECTION_TIMEOUT);
        if (!isUidSrch) {
            sb.append("<br>[w3RecvResult] Mode : " + this.connectionOpt);
            if (isRealQuery) {
                sb.append("<br>[W3ReceiveRecentQueryListResult] Set Count : " + WNDefine.REALTIME_COUNT);
            }
        }

        sb.append("<br>");
        for (int i = 0; i < WNCollection.COLLECTIONS.length; i++) {
            int idx = getCollIdx(WNCollection.COLLECTIONS[i]);

            if (idx < 0) {
                return;
            }
            int count = 0;

            // �÷��� ����
            String content = wncol.COLLECTION_INFO[idx][WNDefine.INDEX_NAME];
            if (content != null && !content.equals("")) {
                sb.append("<br>[w3AddCollection] <b>" + content + "</b>");
            }
            if (!isUidSrch) {
                //������ ����
                content = wncol.COLLECTION_INFO[idx][WNDefine.PAGE_INFO];
                if (content != null && !content.equals("")) {
                    sb.append("<br>[w3SetPageInfo] " + content);
                }

                //�м� ����
                content = wncol.COLLECTION_INFO[idx][WNDefine.ANALYZER];
                if (content != null && !content.equals("")) {
                    sb.append("<br>[w3SetQueryAnalyzer] " + content);
                }

                //���̶����� ����
                count = this.hiSum;
                if (count >= 0) {
                    switch (count) {
                        case 0:
                            content = " : Highlight off, Snippet off.";
                            break;
                        case 1:
                            content = " : Highlight off, Snippet on.";
                            break;
                        case 2:
                            content = " : Highlight on, Snippet off.";
                            break;
                        case 3:
                            content = " : Highlight on, Snippet on.";
                            break;
                        default:
                            content = " : wrong infomation";
                    }
                    sb.append("<br>[w3SetQueryAnalyzer] " + count + content);
                }

                //���� ����
                content = wncol.COLLECTION_INFO[idx][WNDefine.SORT_FIELD];
                if (content != null && !content.equals("")) {
                    sb.append("<br>[w3AddSortField] " + content);
                }

                //�˻� �ʵ� ����
                String search_field = wncol.COLLECTION_INFO[idx][WNDefine.SEARCH_FIELD];
                String[] searchField = search_field.split(",");

                content = wncol.COLLECTION_INFO[idx][WNDefine.SEARCH_FIELD];
                if (content != null && !content.equals("")) {
                    sb.append("<br>[w3AddSearchField] ");
                    for (int k = 0; k < searchField.length; k++) {
                        if (k != 0) {
                            sb.append(", ");
                        }
                        sb.append(searchField[k]);
                    }
                }

                //�Ⱓ ����
                content = wncol.COLLECTION_INFO[idx][WNDefine.DATE_RANGE];
                if (content != null && !content.equals("")) {
                    sb.append("<br>[w3SetDateRange] " + content);
                }

                //Ȯ�� �˻�  ����
                content = wncol.COLLECTION_INFO[idx][WNDefine.EXQUERY_FIELD];
                if (content != null && !content.equals("")) {
                    sb.append("<br>[w3SetPrefixQuery] " + content.replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
                }

                //ī�װ� �ν�Ʈ ����
                content = wncol.COLLECTION_INFO[idx][WNDefine.CATEGORY_BOOST];
                if (content != null && !content.equals("")) {
                    sb.append("<br>[w3SetBoostCategory] " + content);
                }

                //�÷��� ���� ����
                content = wncol.COLLECTION_INFO[idx][WNDefine.COLLECTION_QUERY];
                if (content != null && !content.equals("")) {
                    sb.append("<br>[w3SetCollectionQuery] " + content.replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
                }

                //���� ����
                content = wncol.COLLECTION_INFO[idx][WNDefine.FILTER_OPERATION];
                if (content != null && !content.equals("")) {
                    sb.append("<br>[w3SetFilterQuery] " + content.replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
                }

                //�׷�ȭ  ����
                content = wncol.COLLECTION_INFO[idx][WNDefine.GROUP_BY];
                if (content != null && !content.equals("")) {
                    sb.append("<br>[w3SetGroupBy] " + content);
                }

                //�׷� ����  ����
                content = wncol.COLLECTION_INFO[idx][WNDefine.GROUP_SORT_FIELD];
                if (content != null && !content.equals("")) {
                    sb.append("<br>[w3AddSortFieldInGroup] " + content);
                }


                if (!wncol.COLLECTION_INFO[idx][WNDefine.GROUP_BY].equals("")) {
                    //��ü �׷� ���� ����
                    count = getResultGroupCount(WNCollection.COLLECTIONS[i]);
                    if (count > 0) {
                        sb.append("<br>[w3GetGroupBycount] " + count);
                    }
                }

                // dedup field
                content = wncol.COLLECTION_INFO[idx][WNDefine.DEDUP_SORT_FIELD];
                if (content != null && !content.equals("")) {
                    sb.append("<br>[w3SetDuplicateDetectionCriterionField] " + content);
                }
            }

            //��� �ʵ� ����
            content = wncol.COLLECTION_INFO[idx][WNDefine.RESULT_FIELD];
            if (content != null && !content.equals("")) {
                sb.append("<br>[w3AddDocumentField] " + content);
            }
            sb.append("<br>");
        }

        //merge collection debug info
        if (mergeCollections != null) {
            for (int i = 0; i < mergeCollections.length; i++) {
                int idx = getMergeCollIdx(mergeCollections[i]);

                if (idx < 0) {
                    return;
                }

                //merge collection name ����
                String content = wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_COLLECTION_NAME];
                String mappingColl = wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MAPPING_COLLECTION_NAME];
                if (content != null && !content.equals("") && mappingColl != null && !mappingColl.equals("")) {
                    sb.append("<br>[w3AddMergeCollection] <b>" + content + "</b>, [Collection Name]<b>" + mappingColl + "</b>");
                }

                content = wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_PAGE_INFO];
                if (content != null && !content.equals("")) {
                    sb.append("<br>[w3SetMergePageInfo] " + content);
                }

                content = wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_RESULT_FIELD];
                String mappingDocs = wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MAPPING_RESULT_FIELD];
                if (content != null && !content.equals("") && mappingDocs != null && !mappingDocs.equals("")) {
                    sb.append("<br>[w3AddMergeDocumentField] " + content + ", [Collection Document Fields]" + mappingDocs);
                }

                content = wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MULTI_GROUP_BY_FIELD];
                String mappingMulti = wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MAPPING_MULTI_GROUP_BY_FIELD];
                if (content != null && !content.equals("") && mappingMulti != null && !mappingMulti.equals("")) {
                    sb.append("<br>[w3AddMergeMultiGroupByField] " + content + ", [Collection Multi-Groupby Fields]" + mappingMulti);
                }

                content = wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_CATEGORY_GROUPBY_FIELD];
                String mappingCategory = wncol.MERGE_COLLECTION_INFO[idx][WNDefine.MERGE_MAPPING_CATEGORY_GROUPBY_FIELD];
                if (content != null && !content.equals("") && mappingCategory != null && !mappingCategory.equals("")) {
                    sb.append("<br>[w3AddMergeCategoryGroupByField] " + content + ", [Collection Category Groupby Fields]" + mappingCategory);
                }
            }

            sb.append("<br>");
        }
    }

    /**
     * ��� �޽����� ���ۿ� �����Ѵ�.
     *
     * @param msg ��� �޽���
     */
    public void appendWarnings(String msg) {
        if (isDebug && !msg.equals("")) {
            warningMsg.append(msg + "<br>");
        }
    }


    /**
     * ���� �޽����� ���ۿ� �����Ѵ�.
     *
     * @param msg ���� �޽���
     */
    public void appendErrors(String msg) {
        if (!msg.equals("")) {
            sb.append("<br>" + msg + "<br>");
        }
    }

    /**
     * ����� ������ ȭ�鿡 ����� ��� �޽����� ��ȯ�Ѵ�.
     *
     * @return ����� ���� ��ȯ
     */
    public String printDebug() {
        if (!warningMsg.toString().equals("")) {
            return sb.toString() + "<br>" + warningMsg.toString() + "<br>";
        }
        return sb.toString();
    }

    /**
     * ���������� ��θ� �����Ѵ�.
     *
     * @param managerURL �������� ���
     */
    public void setManagerMode(String managerURL) {
        if (managerURL != null && !managerURL.trim().equals("")) {
            this.managerURL = managerURL;
        }
    }

    /*
     * �Ŵ��� ���� �غ� �ױ׸� �����Ѵ�.
     * @param colName �÷��� ��
     * @param idx ��� �ε���
     * @param docid Ư�� ��� DOCID
     * @return �Ŵ��� ���� �غ� �ױ�
     */
    public String getManagerOperationTag(String colName, int idx, String docid) {
        String uniid = colName + "_" + idx;
        if (!this.managerURL.equals("")) {
            return "<input type='checkbox' name='manager_idx' id='" + uniid + "'  value='checkbox' onclick=\"javascript:manager_addDocIdForDelete('" + colName + "', '" + docid + "', '" + uniid + "')\" />" + docid + "&nbsp";
        } else {
            return "";
        }
    }

    /*
     * �Ŵ��� ���� �ױ׸� �����Ѵ�.
     * @param transferURL xml �Ľ� ���� ���
     * @return �Ŵ��� ���� �ױ�
     */
    public String getManagerActionTag(String transferURL) {
        if (!this.managerURL.equals(""))
            return "<input type='button'  value='Delete' onclick=\"javascript:manager_runDelete('" + transferURL + "', '" + this.managerURL + "', '" + WNCollection.SEARCH_IP + "', " + WNCollection.SEARCH_PORT + ")\" />";
        else
            return "";
    }

    /**
     * �˻� ��� ����¡ ��ȣ�� ����Ѵ�.
     *
     * @param startCount    �˻� ��� ���� offset
     * @param totalCount    �˻� ����� �� ����
     * @param viewListCount �˻������ ��û�� offset�� ����
     * @param bundleCount   ������ �̵��� ����
     * @return ����¡ ���ڿ� ��ȯ
     */
    public String getPageLinks(int startCount, int totalCount, int viewListCount, int bundleCount) {
        StringBuffer sbRet = new StringBuffer();
        WNAnchor wnanchor = getPageAnchor(startCount, totalCount, viewListCount, bundleCount);

        if (wnanchor.getFirstPage() != -1) {
            sbRet.append("<a class=\"pre\" href=\"#none\" onClick=\"javascript:doPaging('" + wnanchor.getBundleBefore() + "');\" title=\"Previous Page\">&lt;&lt;</a>");
        } else {
            sbRet.append("<a class=\"pre\" href=\"#none\" title=\"Previous Page\">&lt;&lt;</a>");
        }

        int pageCount = wnanchor.getPageCount();
        String pages[][] = wnanchor.getPages();

        for (int i = 0; i < pageCount && i < pages.length; i++) {
            if (pages[i][1].equals("-1")) {
                sbRet.append("<strong>" + pages[i][0] + "</strong>");
            } else {
                sbRet.append("<a href=\"#none\" onClick=\"javascript:doPaging('" + pages[i][1] + "');\" title=\"Paging\"> " + pages[i][0] + " </a>");
            }
        }

        if (wnanchor.getBundleNext() != -1) {
            sbRet.append("<a class=\"next\" href=\"#none\" onClick=\"javascript:doPaging('" + wnanchor.getBundleNext() + "')\" title=\"Next Page\">&gt;&gt;</a>");
        } else {
            sbRet.append("<a class=\"next\" href=\"#none\" title=\"Next Page\">&gt;&gt;</a>");
        }

        return sbRet.toString();
    }

    /**
     * ������ Anchor�� �����Ѵ�.
     *
     * @param startNum    �˻� ��� ���� offset
     * @param totalcount  �˻� ����� �� ����
     * @param resultCnt   �˻������ ��û�� offset�� ����
     * @param anchorSacle ������ �̵��� ����
     * @return WNAnchor Object�� ��ȯ
     */
    public WNAnchor getPageAnchor(int startNum, int totalcount, int resultCnt, int anchorSacle) {
        WNAnchor anchor = new WNAnchor();

        if (totalcount == 0) {   //��ϵ� ������ ������ ������ Anchor�� ���� ���� �ʴ´�.
            return anchor;
        }

        int curBunbleNum = startNum / (resultCnt * anchorSacle);
        int totalPageCnt = totalcount / resultCnt;
        if (totalcount % resultCnt > 0) {
            totalPageCnt++;
        }

        anchor.setTotalPgCount(totalPageCnt);      // ��ü ������ ����

        if (startNum > 0) {        // ���� ������
            int beforePg = startNum - resultCnt;
            anchor.setBefore(beforePg);
        }

        if (startNum + resultCnt < totalcount) {        // ����������
            int nextPg = startNum + resultCnt;
            anchor.setNext(nextPg);
        }

        //���� �ڷ� �̵�
        int bunbleBeforePg = (curBunbleNum - 1) * resultCnt * anchorSacle;    //���� ����� �̵��ϴ� ��ȣ
        if (curBunbleNum > 0) {
            anchor.setBundleBefore(bunbleBeforePg);
        }

        //���� ������ �̵�
        int bundleNextPg = (1 + curBunbleNum) * anchorSacle * resultCnt;
        if (totalPageCnt >= anchorSacle && (curBunbleNum + 1) * anchorSacle < totalPageCnt) {
            anchor.setBundleNext(bundleNextPg);
        }

        //��ó��..
        if (startNum != 0 && curBunbleNum != 0) {
            anchor.setFirstPage(0);
        }
        //�ǳ�...
        int lastPage = (resultCnt * totalPageCnt) - resultCnt;
        if (totalPageCnt >= anchorSacle && (curBunbleNum + 1) * anchorSacle < totalPageCnt) {
            anchor.setLastPage(lastPage);
        }

        int pageCount = 1;
        String[][] pages = new String[anchorSacle][2];
        for (int i = 0; i < anchorSacle; i++) {
            int startCnt = ((curBunbleNum * anchorSacle) + i) * resultCnt;
            int pageNum = (curBunbleNum * anchorSacle) + i + 1;
            if (startCnt < totalcount) {
                if (startCnt != startNum) {
                    pages[i][0] = Integer.toString(pageNum);
                    pages[i][1] = Integer.toString(startCnt);
                } else {
                    pages[i][0] = Integer.toString(pageNum);
                    pages[i][1] = "-1";
                    anchor.setCurPageNumber(pageNum);
                }
                anchor.setPageCount(pageCount);
                pageCount++;
            }
        }
        anchor.setPages(pages);
        return anchor;
    }


    /**
     * �˻� ��� ����¡ ��ȣ�� ����Ѵ�.
     *
     * @param startCount    �˻� ��� ���� offset
     * @param totalCount    �˻� ����� �� ����
     * @param viewListCount �˻������ ��û�� offset�� ����
     * @param bundleCount   ������ �̵��� ����
     * @return ����¡ ���ڿ� ��ȯ
     */
    public String getPageLinksWebAccess(String base, String url, int startCount, int totalCount, int viewListCount, int bundleCount) {

        String RequestURI = base;
        String RequestURL = url;

        StringBuffer sbRet = new StringBuffer();
        WNAnchor wnanchor = getPageAnchor(startCount, totalCount, viewListCount, bundleCount);
        String ppreImg = "";
        String preImg = "";
        String nextImg = "";
        String nnextImg = "";
        ppreImg = "<IMG SRC='images/navi/icon_first.gif' BORDER='0' align='absmiddle'>";
        preImg = "<IMG SRC='images/navi/icon_preview.gif' BORDER='0' align='absmiddle'>";
        nextImg = "<IMG SRC='images/navi/icon_next.gif' BORDER='0' align='absmiddle'>";
        nnextImg = "<IMG SRC='images/navi/icon_end.gif' BORDER='0' align='absmiddle'>";

        if (wnanchor.getFirstPage() != -1) {
            sbRet.append("<A HREF='" + wnutils.replaceURL(RequestURI, RequestURL, "startCount", String.valueOf(wnanchor.getFirstPage())) + "'  >" + ppreImg + "</A>&nbsp;&nbsp;");
        } else {
            sbRet.append(ppreImg + "&nbsp;");
        }
        if (wnanchor.getBundleBefore() != -1) {
            sbRet.append("<A HREF='" + wnutils.replaceURL(RequestURI, RequestURL, "startCount", String.valueOf(wnanchor.getFirstPage())) + "'  >" + preImg + "</A>&nbsp;&nbsp;");
        } else {
            sbRet.append(preImg + "&nbsp;");
        }

        int pageCount = wnanchor.getPageCount();
        String pages[][] = wnanchor.getPages();

        for (int i = 0; i < pageCount && i < pages.length; i++) {
            if (pages[i][1].equals("-1")) {
                sbRet.append("<b>" + pages[i][0] + "</b>");
            } else {
                sbRet.append("<A HREF='" + wnutils.replaceURL(RequestURI, RequestURL, "startCount", String.valueOf(pages[i][1])) + "' class='nav'> " + pages[i][0] + " </A>");
            }
            if (pageCount > i + 1) {
                sbRet.append("&nbsp;|&nbsp;");// ������ ��� 1 | 2 | 3
            }
        }
        if (wnanchor.getBundleNext() != -1) {
            sbRet.append("&nbsp;&nbsp;<A HREF='" + wnutils.replaceURL(RequestURI, RequestURL, "startCount", String.valueOf(wnanchor.getBundleNext())) + "'>" + nextImg + "</A>");
        } else {
            sbRet.append("&nbsp;&nbsp;" + nextImg + "");
        }
        if (wnanchor.getLastPage() != -1) {
            sbRet.append("&nbsp;&nbsp;<A HREF='" + wnutils.replaceURL(RequestURI, RequestURL, "startCount", String.valueOf(wnanchor.getLastPage())) + "'>" + nnextImg + "</A>");
        } else {
            sbRet.append("&nbsp;&nbsp;" + nnextImg + "");
        }
        return sbRet.toString();
    }


    public String getCollectionKorName(String col) {
        String value = "";
        for (int idx = 0; idx < WNCollection.COLLECTIONS.length; idx++) {
            String coll = wncol.COLLECTION_INFO[idx][WNDefine.COLLECTION_NAME].trim();
            if (coll.equals(col))
                return wncol.COLLECTION_INFO[idx][WNDefine.COLLECTION_KOR];

        }

        return value;
    }

    public String getCollectionName(String col) {
        String value = "";
        for (int idx = 0; idx < WNCollection.COLLECTIONS.length; idx++) {
            if (WNCollection.COLLECTIONS[idx].equals(col)) {
                return WNCollection.COLLECTIONS_NAME[idx];
            }
        }

        return value;
    }
}


