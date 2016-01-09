package com.capsule.apps.rxbookman.actions;

/**
 * @author Larry Pham
 * @since 2015.Dec.17
 */
public interface BookAppActions {
    String SEARCH_BOOK_BY_KEYWORD = "get_book_by_keyword";
    String SEARCH_BOOK_BY_KEYWORD_PAGE = "get_book_by_keyword_page";
    String GET_BOOK_DETAIL_BY_ID = "get_book_detail_by_id";

    String GET_BOOK_BY_ID = "get_book_by_id";
    String GET_LIST_BOOKS = "get_list_books";

    String GET_RECENT_BOOKS = "get_recent_books";
    String GET_FAVOURITE_BOOKS = "get_favourite_books";

    String GET_DOWNLOADED_BOOKS = "get_downloaded_books";
    String GET_DOWNLOADING_BOOKS = "get_downloading_books";

    /**
     * Method search the books by using the specified keywords. After input the keyword for getting the list of books
     * from API Service, we will parse the response from server for displaying the list of books into screen
     * @param keyword The Searchable Keyword.
     */
    void searchBooksByKeyword(String keyword);

    /**
     * Method search the books by using the specified keywords. After input the keyword for getting the list of books
     * from API Service, we will parse the response from server for displaying the list of books into screen
     * @param keyword The Searchable Keyword.
     * @param page The paging number of displayed results.
     */
    void setSearchBookByKeywordPage(String keyword, int page);

    /**
     * Method getBookDetailById() which represents to used for sending the book's id to server and then getting the details of
     * the book which was been stored into server with the specified id
     * @param bookId The Book's Id.
     */
    void getBookDetailById(long bookId);

    /**
     * Get the list of books which stored into the app-database or <code>AppDataModel</code>'s instance
     */
    void getBooks();

    /**
     * Get the list of recent books which stored into the app-database or <code>AppDataModel</code>'s instance
     */
    void getRecentBooks();

    /**
     * Get the list of downloaded books which stored into the app-database or <code>AppDataModel</code>'s instance.
     */
    void getDownloadedBooks();

    /**
     * Get the list of downloading books which recently stored into the app-database or <code>AppDataModel</code>'s instance.
     */
    void getDownloadingBooks();
}
