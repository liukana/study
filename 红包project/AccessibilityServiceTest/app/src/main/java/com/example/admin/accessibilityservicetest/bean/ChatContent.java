package com.example.admin.accessibilityservicetest.bean;

/**
 * Created by liukan on 2018/3/19.
 */

public class ChatContent {


    /**
     * perception : {"inputText":{"text":"啦啦啦"}}
     * userInfo : {"apiKey":"demo"}
     */

    private PerceptionBean perception;
    private UserInfoBean userInfo;

    public PerceptionBean getPerception() {
        return perception;
    }

    public void setPerception(PerceptionBean perception) {
        this.perception = perception;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class PerceptionBean {
        /**
         * inputText : {"text":"啦啦啦"}
         */

        private InputTextBean inputText;

        public InputTextBean getInputText() {
            return inputText;
        }

        public void setInputText(InputTextBean inputText) {
            this.inputText = inputText;
        }

        public static class InputTextBean {
            /**
             * text : 啦啦啦
             */

            private String text;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }

    public static class UserInfoBean {
        /**
         * apiKey : demo
         */

        private String apiKey;
        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }
    }
}
