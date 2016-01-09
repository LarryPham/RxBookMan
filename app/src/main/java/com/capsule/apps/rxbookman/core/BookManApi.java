package com.capsule.apps.rxbookman.core;

import com.capsule.apps.rxbookman.models.results.BookResult;
import com.capsule.apps.rxbookman.models.results.BooksResult;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface BookManApi {

    String SERVICE_ENDPOINT = com.capsule.apps.rxbookman.Properties.BASE_API_URL;

    @GET("/search/{query}")
    Observable<BooksResult> searchBooksByKeyword(@Path("query") String keyword);

    @GET("/search/{query}/page/{number}")
    Observable<BooksResult> searchBooksByKeywordAndPage(@Path("query") String keyword, @Path("number") int page);

    @GET("/book/{id}")
    Observable<BookResult> getBookDetail(@Path("id") long bookId);
}
