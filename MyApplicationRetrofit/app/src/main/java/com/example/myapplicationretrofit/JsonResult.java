package com.example.myapplicationretrofit;

import java.util.List;

public class JsonResult {


    /**
     * success : true
     * code : 10000
     * message : 获取成功
     * data : [{"id":"1234359073492299776","title":"Android加载大图片，解决OOM问题","viewCount":144,"commentCount":22,"publishTime":"2020-03-02T06:05:11.060+0000","userName":"程序员拉大锯","cover":"/imgs/4.png"},{"id":"1234359073492299777","title":"Volley/Xutils对大图片处理算法源码分析","viewCount":43,"commentCount":20,"publishTime":"2020-03-02T06:05:11.060+0000","userName":"程序员拉大锯","cover":"/imgs/3.png"},{"id":"1234359073492299778","title":"Android开发网络安全配置","viewCount":223,"commentCount":70,"publishTime":"2020-03-02T06:05:11.060+0000","userName":"程序员拉大锯","cover":"/imgs/1.png"},{"id":"1234359073492299779","title":"Android开发网络编程，请求图片","viewCount":224,"commentCount":98,"publishTime":"2020-03-02T06:05:11.060+0000","userName":"程序员拉大锯","cover":"/imgs/15.png"},{"id":"1234359073492299780","title":"Intent页面跳转工具类分享","viewCount":206,"commentCount":45,"publishTime":"2020-03-02T06:05:11.060+0000","userName":"程序员拉大锯","cover":"/imgs/1.png"},{"id":"1234359073492299781","title":"阳光沙滩商城的API文档","viewCount":189,"commentCount":51,"publishTime":"2020-03-02T06:05:11.060+0000","userName":"程序员拉大锯","cover":"/imgs/11.png"},{"id":"1234359073492299782","title":"Android课程视频打包下载","viewCount":308,"commentCount":11,"publishTime":"2020-03-02T06:05:11.060+0000","userName":"程序员拉大锯","cover":"/imgs/15.png"},{"id":"1234359073492299783","title":"非常轻量级的gif录制软件","viewCount":309,"commentCount":33,"publishTime":"2020-03-02T06:05:11.060+0000","userName":"程序员拉大锯","cover":"/imgs/7.png"},{"id":"1234359073492299784","title":"Fiddler抓包工具，墙裂推荐，功能很强大很全的一个工具","viewCount":187,"commentCount":37,"publishTime":"2020-03-02T06:05:11.060+0000","userName":"程序员拉大锯","cover":"/imgs/8.png"},{"id":"1234359073492299785","title":"AndroidStudio奇淫技巧-代码管理","viewCount":35,"commentCount":69,"publishTime":"2020-03-02T06:05:11.060+0000","userName":"程序员拉大锯","cover":"/imgs/2.png"},{"id":"1234359073492299786","title":"OC和Swift混编","viewCount":293,"commentCount":45,"publishTime":"2020-03-02T06:05:11.060+0000","userName":"程序员拉大锯","cover":"/imgs/11.png"},{"id":"1234359073492299787","title":"最新的Android studio是不是没有Android Device Monitor","viewCount":273,"commentCount":49,"publishTime":"2020-03-02T06:05:11.060+0000","userName":"程序员拉大锯","cover":"/imgs/8.png"}]
     */

    private boolean success;
    private int code;
    private String message;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1234359073492299776
         * title : Android加载大图片，解决OOM问题
         * viewCount : 144
         * commentCount : 22
         * publishTime : 2020-03-02T06:05:11.060+0000
         * userName : 程序员拉大锯
         * cover : /imgs/4.png
         */

        private String id;
        private String title;
        private int viewCount;
        private int commentCount;
        private String publishTime;
        private String userName;
        private String cover;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
