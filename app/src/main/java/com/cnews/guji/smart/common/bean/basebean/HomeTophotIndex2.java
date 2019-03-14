package com.cnews.guji.smart.common.bean.basebean;

import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.github.library.entity.MultiItemEntity;

import java.util.List;

/**
 * @author：dingcl. home
 * |- 头条
 */
public class HomeTophotIndex2 {
    public String adcode;
    public String ads;
    public int adsType;
    public String adsdebug;
    public int channelId;
    public String channelName;
    public String city;
    public String displayInfo;
    public int errorCode;
    public String exts;
    public String ip;
    public String loadid;
    public long loadtime;
    public String message;
    public int reqtype;
    public int supplement;
    public int totalNum;
    public List<Articles> articlesList;

    public static class Articles implements MultiItemEntity {
        public List<Pics> pics;
        public int playCount;
        public int recommendEvent;
        public int recpool;
        public int recreason;
        public double score;
        public int state;
        public int template;


        @Override
        public int getItemType() {
            if (0 == template) {
                return NewsConstant.TYPE_TOP_BANNER;
            } else if (1 == template) {
                return NewsConstant.TYPE_SINGLE_IMAGE;
            } else if (3 == template) {
                return NewsConstant.TYPE_THREE_IMAGE;
            } else if (4 == template) {
                return NewsConstant.TYPE_EMPTY_IMAGE;
            }
            return NewsConstant.TYPE_TITLE;
        }

//        public int getSpanSize() {
//            if("recommended_ware".equals(itemType)){
//                return 2;
//            }
//            return 4;
//        }

        public class Pics {
            /**
             * "height": 312,
             * "url": "http://p.cdn.sohu.com/covers/273f1e40/c451589233695f3364e0d464e14f0849.jpeg",
             * "width": 440
             */
            public int height;
            public String url;
            public int width;
        }
    }
}
