package com.capsule.apps.rxbookman.models;

import com.capsule.apps.rxbookman.utils.CollectionsUtil;

import java.util.ArrayList;

/**
 * @Author: larry
 * History: 12/16/15.
 */
public class ShortBooks extends ArrayList<ShortBook> {

    public ArrayList<Long> getBookIds() {
        ArrayList<Long> ids = CollectionsUtil.newArrayList();
        for (ShortBook book : this) {
            ids.add(book.getBookId());
        }
        return ids;
    }

    public ShortBook getShortBookAtIndex(int index) {
        if (index >= 0 && index < this.size()) {
            return this.get(index);
        }
        return null;
    }

    public String getTitleForBookId(long bookId) {
        if (bookId == -1) {
            return null;
        }

        for (ShortBook book : this) {
            if (book.getBookId() >= 0 && book.getBookId() == bookId) {
                return book.getTitle();
            }
        }
        return null;
    }

    public String getImageUrlForBookId(long bookId) {
        if (bookId == -1) {
            return null;
        }

        for (ShortBook book : this) {
            if (book.getBookId() >= 0 && book.getBookId() == bookId && book.getImageUrl() != null) {
                return book.getImageUrl();
            }
        }
        return null;
    }

    public int getBooksCount() {
        return size();
    }

    public int indexOfShortBook(ShortBook book) {
        if (book ==  null) {
            return -1;
        }

        for (int index = 0; index < this.size(); index++) {
            if (this.get(index).getBookId() == book.getBookId()) {
                return index;
            }
        }
        return -1;
    }

    public int indexOfShortBookId(long shortBookId) {
        for (int index = 0; index < this.size(); index++) {
            if (shortBookId == this.get(index).getBookId()) {
                return index;
            }
        }
        return -1;
    }

    public ShortBook findShortBookById(final long bookId) {
        for (int index = 0; index < size(); index++) {
            if (this.get(index).getBookId() == bookId) {
                return this.get(index);
            }
        }
        return null;
    }

    public boolean isSameList(ShortBooks other) {
        if (other == null || other.size() != this.size()) {
            return false;
        }

        for (ShortBook book : other) {
            int index = indexOfShortBook(book);
            if (index == -1 || !book.isSameBook(this.get(index))) {
                return false;
            }
        }
        return true;
    }

    public boolean isSameList(ArrayList<Long> compareIds) {
        return !(compareIds == null || compareIds.size() != this.size()) && this.containsAll(compareIds);
    }
}
