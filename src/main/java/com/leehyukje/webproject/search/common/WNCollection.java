package com.leehyukje.webproject.search.common;

import org.springframework.stereotype.Service;


public class WNCollection {

    public static String SEARCH_IP = "127.0.0.1";
    public static int SEARCH_PORT = 7000;

    public static String MANAGER_IP = "127.0.0.1";
    public static int MANAGER_PORT = 8080;

    public static String[] COLLECTIONS = new String[]{"clien"};
    
    public static String[] COLLECTIONS_NAME = new String[]{"소식"};

    public static String[] MERGE_COLLECTIONS = new String[]{""};

    public String[][] COLLECTION_INFO = null;

    public String[][] MERGE_COLLECTION_INFO = null;

    public WNCollection() {

        // (virtual) merge collection
        /*
         * MERGE_COLLECTION_INFO = new String[][] { { "merge_sample_bbs", // set merge
         * collection name "sample_bbs/sample_edu", // set collection name, delimiter: /
         * "0,3", // set merge collection pageinfo (start,count)
         * "DOCID,TITLE,WRITER,CONTENT",// set merge document field
         * "DOCID,TITLE,WRITER,CONTENT/DOCID,TITLE,WRITER,CONTENT", // set document
         * field, delimiter: / "", // set merge collection multi-group-by field "", //
         * set merge collection multi-group-by field, delimiter: / "", // set merge
         * collection category-group-by field "" // set collection multi-group-by field,
         * delimiter: / } };
         */

        COLLECTION_INFO = new String[][]{
                {
                        "clien", // set collection name
                        "clien", // set index name
                        "0,3",  // set pageinfo (start,count)
                        "1,1,1,1,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
                        "RANK/DESC,DATE/DESC",  // set sort field (field, ASC or DESC) multi sort ','
                        "basic,ropfm,100", //set ranking option (basic(custom,BM25), ranking order, ranking max count)
                        "TITLE,CONTENT",// set search field
                        "DOCID,TITLE,CONTENT",// set document field
                        "", // set date range
                        "", // set rank range
                        "", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
                        "", // set collection query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
                        "", // set boost query (<fieldname:contains:value^weight | value^weight>/option...)  and ' ', or '|'
                        "", // set filter operation (<fieldname:operator:value>)
                        "", // set groupby field(field, count)
                        "", // set sort field group(field/order,field/order,...)
                        "", // set categoryBoost(fieldname,matchType,boostKeyword)
                        "", // set categoryGroupBy (fieldname:value)
                        "", // set categoryQuery (fieldname:value)
                        "", // set property group (fieldname,min,max, groupcount)
                        "", // use check prefix query filed
                        "", // set use check fast access field
                        "", // set multigroupby field
                        "", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
                        "", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
                        "clien" // collection display name
                }
        };
    }
}
