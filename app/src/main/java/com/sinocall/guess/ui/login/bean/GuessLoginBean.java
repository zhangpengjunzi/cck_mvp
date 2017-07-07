package com.sinocall.guess.ui.login.bean;

import com.sinocall.guess.base.BaseBean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/6.
 */

public class GuessLoginBean extends BaseBean implements Serializable {

    /**
     * errorVo : {"code":"1000","msg":"登录成功"}
     * data : {"userId":225,"lId":"595BE06A01A2DC2D94999471EB91D7FAF","umengToken":"","jpushToken":"","nickName":"13718682361","gender":1,"mobile":"13718682361","hasPayPass":0,"showRewardCartoon":1,"perBuyCoin":100,"logo":""}
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * userId : 225
         * lId : 595BE06A01A2DC2D94999471EB91D7FAF
         * umengToken :
         * jpushToken :
         * nickName : 13718682361
         * gender : 1
         * mobile : 13718682361
         * hasPayPass : 0
         * showRewardCartoon : 1
         * perBuyCoin : 100
         * logo :
         */

        private int userId;
        private String lId;
        private String umengToken;
        private String jpushToken;
        private String nickName;
        private int gender;
        private String mobile;
        private int hasPayPass;
        private int showRewardCartoon;
        private int perBuyCoin;
        private String logo;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getLId() {
            return lId;
        }

        public void setLId(String lId) {
            this.lId = lId;
        }

        public String getUmengToken() {
            return umengToken;
        }

        public void setUmengToken(String umengToken) {
            this.umengToken = umengToken;
        }

        public String getJpushToken() {
            return jpushToken;
        }

        public void setJpushToken(String jpushToken) {
            this.jpushToken = jpushToken;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getHasPayPass() {
            return hasPayPass;
        }

        public void setHasPayPass(int hasPayPass) {
            this.hasPayPass = hasPayPass;
        }

        public int getShowRewardCartoon() {
            return showRewardCartoon;
        }

        public void setShowRewardCartoon(int showRewardCartoon) {
            this.showRewardCartoon = showRewardCartoon;
        }

        public int getPerBuyCoin() {
            return perBuyCoin;
        }

        public void setPerBuyCoin(int perBuyCoin) {
            this.perBuyCoin = perBuyCoin;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}
