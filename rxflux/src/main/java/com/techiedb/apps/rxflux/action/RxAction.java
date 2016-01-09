package com.techiedb.apps.rxflux.action;

import android.util.ArrayMap;

public class RxAction {
    private final String mType;
    private final ArrayMap<String, Object> mData;

    public RxAction(String type, ArrayMap<String, Object> data) {
        this.mType = type;
        this.mData = data;
    }

    public static Builder type(String type) {
        return new Builder().with(type);
    }

    public String getType() {
        return mType;
    }

    public ArrayMap<String, Object> getData() {
        return mData;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RxAction)) {
            return false;
        }

        RxAction rxAction = (RxAction) obj;
        if (!(mType.equals(rxAction.mType))) {
            return false;
        }
        return !(mData != null ? !mData.equals(rxAction.mData) : rxAction.mData != null);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result  + (mType != null ? mType.hashCode() : 0);
        result = prime * result  +(mData != null ? mData.hashCode() : 0);
        return result;
    }

    public static class Builder {
        private String mType;
        private ArrayMap<String, Object> mData;

        public Builder with(String type) {
            if (type == null) {
                throw new IllegalArgumentException("Type maybe not null.");
            }
            this.mType = type;
            this.mData = new ArrayMap<>();
            return this;
        }

        public Builder bundle(String key, Object value) {
            if (key == null) {
                throw new IllegalArgumentException("Key may be not null.");
            }
            if (value == null) {
                throw new IllegalArgumentException("Value may be not null.");
            }
            mData.put(key, value);
            return this;
        }

        public RxAction build() {
            if (mType == null || mType.isEmpty()) {
                throw new IllegalArgumentException("At least one key is required.");
            }
            return new RxAction(mType, mData);
        }
    }
}
