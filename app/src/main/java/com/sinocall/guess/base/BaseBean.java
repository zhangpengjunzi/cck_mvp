package com.sinocall.guess.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/3.
 */

public class BaseBean implements Serializable {

    /**
     * errorVo : {"code":"1000","msg":"发送成功"}
     * requestId : 101383403196486321975970891
     */

    private ErrorVoBean errorVo;
    private String requestId;

    public ErrorVoBean getErrorVo() {
        return errorVo;
    }

    public void setErrorVo(ErrorVoBean errorVo) {
        this.errorVo = errorVo;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public static class ErrorVoBean implements Serializable {
        /**
         * code : 1000
         * msg : 发送成功
         */

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
