package com.capsule.apps.rxbookman.core;

import com.capsule.apps.rxbookman.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * @Author: larry
 * History: 12/16/15.
 */
public class BookManService {

    public static final String TAG = LogUtils.makeLogTag(BookManService.class);

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static BookManApi mAppApi;

    private static void build() {
        try {
            final Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT_PATTERN)
                    .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                    .create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BookManApi.SERVICE_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            mAppApi = retrofit.create(BookManApi.class);
        } catch (Exception e) {
            com.capsule.apps.rxbookman.utils.LogUtils.LOGD(TAG, "Unexpected exception: %s" + e.toString());
            e.printStackTrace();
        }
    }

    public static synchronized BookManApi getAppApi() {
        if (mAppApi == null) {
            build();
        }
        return mAppApi;
    }
}
