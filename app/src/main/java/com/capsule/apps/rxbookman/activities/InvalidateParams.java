package com.capsule.apps.rxbookman.activities;

public class InvalidateParams {
    private int mMessage;
    private Object mData;

    public int getMessage() {
        return mMessage;
    }

    public void setMessage(int message) {
        mMessage = message;
    }

    public Object getData() {
        return mData;
    }

    public void setData(Object data) {
        mData = data;
    }

    public InvalidateParams(int msg, Object data) {
        this.mMessage = msg;
        this.mData = data;
    }

    public static class Builder {
        private int msg;
        private Object data;

        public Builder with(int msg) {
            if (msg == 0) {
                throw new IllegalArgumentException("Message must be not zero");
            }
            this.msg = msg;
            return this;
        }

        public Builder with(Object data) {
            if (data == null) {
                throw new IllegalArgumentException("Data must be not null");
            }
            this.data = data;
            return this;
        }

        public InvalidateParams build() {
            if (msg == 0 || data == null) {
                throw new IllegalArgumentException("At least one key is required");
            }
            return new InvalidateParams(msg, data);
        }
    }
}
