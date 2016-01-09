package com.capsule.apps.rxbookman.adapters;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.capsule.apps.rxbookman.BookApp;
import com.capsule.apps.rxbookman.R;
import com.capsule.apps.rxbookman.models.AppDataModel;
import com.capsule.apps.rxbookman.models.ShortBook;
import com.capsule.apps.rxbookman.models.ShortBooks;
import com.capsule.apps.rxbookman.utils.LogUtils;
import com.capsule.apps.rxbookman.widgets.AspectRatioView;
import com.capsule.apps.rxbookman.widgets.EllipsizedTextView;
import com.squareup.picasso.Picasso;

import org.lucasr.twowayview.widget.TwoWayView;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {
    private static final String TAG = LogUtils.makeLogTag(BooksAdapter.class);

    protected Context mContext;
    protected final int mLayoutId;

    protected ShortBooks mShortBooks = new ShortBooks();
    protected AppDataModel mAppDataModel;
    protected BookApp mBookApp;

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        public AspectRatioView mBookImageLayout;
        public ImageView mBookImage;
        public ImageView mBookMark;
        public ImageView mBookOptionMenu;

        public EllipsizedTextView mBookTitle;
        public EllipsizedTextView mBookSubTitle;
        public EllipsizedTextView mBookDescription;
        public EllipsizedTextView mBookIsbn;

        public BookViewHolder(View itemView) {
            super(itemView);
            mBookImageLayout = (AspectRatioView) itemView.findViewById(R.id.short_book_image_layout);
            mBookImage = (ImageView) itemView.findViewById(R.id.short_book_image);

            mBookMark = (ImageView) itemView.findViewById(R.id.book_bookmark);
            mBookOptionMenu = (ImageView) itemView.findViewById(R.id.short_book_menu);

            mBookTitle = (EllipsizedTextView) itemView.findViewById(R.id.short_book_title);
            mBookSubTitle = (EllipsizedTextView) itemView.findViewById(R.id.short_book_subtitle);
            mBookDescription = (EllipsizedTextView) itemView.findViewById(R.id.short_book_description);
            mBookIsbn = (EllipsizedTextView) itemView.findViewById(R.id.short_book_isbn);
        }
    }

    public BooksAdapter(Context context, TwoWayView recyclerView, int layoutId) {
        mContext = context;
        mLayoutId = layoutId;
        mBookApp = (BookApp) mContext.getApplicationContext();
        mAppDataModel = mBookApp.getAppDataModel();
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        final ShortBook shortBook = mShortBooks.getShortBookAtIndex(position);
        if (mLayoutId == R.layout.view_short_book_item) {
            holder.mBookTitle.setText(shortBook.getTitle());
            holder.mBookIsbn.setText(String.valueOf(shortBook.getISBN()));

            Picasso.with(mContext).load(shortBook.getImageUrl()).into(holder.mBookImage);
        } else if (mLayoutId == R.layout.view_short_book_list_item) {
            holder.mBookTitle.setText(shortBook.getTitle());
            holder.mBookSubTitle.setText(shortBook.getSubTitle());
            holder.mBookDescription.setText(shortBook.getDescription());

            Picasso.with(mContext).load(shortBook.getImageUrl()).into(holder.mBookImage);
        }
    }

    @Override
    public int getItemCount() {
        return mShortBooks.size();
    }

    public void clear() {
        if (!mShortBooks.isEmpty()) {
            mShortBooks.clear();
            notifyDataSetChanged();
        }
    }

    public void refresh() {
        loadShortBooks();
    }

    public void reloadShortBook(ShortBook shortBook) {
        int index = indexOfShortBook(shortBook);
        if (index == -1) {
            return;
        }
        final ShortBook book = mAppDataModel.getShortBooks().get(index);
        if (book != null) {
            mShortBooks.set(index, book);
            notifyDataSetChanged();
        }
    }

    public int indexOfShortBook(ShortBook shortBook) {
        return mShortBooks.indexOfShortBook(shortBook);
    }

    public void loadShortBooks() {
        if (mIsTaskRunning) {
            LogUtils.LOGD(TAG, "ShortBooks Task Already Running");
        }
        new LoadBooksTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public ShortBook getItem(int position) {
        if (isValidPosition(position)) {
            return mShortBooks.get(position);
        } else {
            return null;
        }
    }

    boolean isValidPosition(int position) {
        return (position >= 0 && position < getItemCount());
    }

    private boolean mIsTaskRunning = false;

    private class LoadBooksTask extends AsyncTask<Void, Void, Boolean> {
        ShortBooks allHomeBooks;

        @Override
        protected void onPreExecute() {
            mIsTaskRunning = true;
        }

        @Override
        protected void onCancelled() {
            mIsTaskRunning = false;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            allHomeBooks = mAppDataModel.getHomeShortBooks();
            if (mShortBooks.isSameList(allHomeBooks)) {
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                if (mShortBooks.size() == 0) {
                    mShortBooks.addAll(allHomeBooks);
                    notifyDataSetChanged();
                } else {
                    // full refresh if any books were removed (can happen after user change searchable-keywords
                    boolean anyRemoved = false;
                    for (ShortBook shortBook: mShortBooks) {
                        if (allHomeBooks.indexOfShortBook(shortBook) == -1) {
                            anyRemoved = true;
                            mShortBooks.clear();
                            mShortBooks.addAll(allHomeBooks);
                            notifyDataSetChanged();
                            break;
                        }
                    }

                    if (!anyRemoved) {
                        int addIndex = 0;
                        int index;
                        for (ShortBook shortBook: mShortBooks) {
                            index = mShortBooks.indexOfShortBook(shortBook);
                            if (index == -1) {
                                mShortBooks.add(addIndex, shortBook);
                                notifyItemInserted(addIndex);
                                addIndex++;
                            } else {
                                addIndex = index + 1;
                                if (!shortBook.isSameBook(mShortBooks.get(index))) {
                                    mShortBooks.set(index, shortBook);
                                    notifyItemChanged(index);
                                }
                            }
                        }
                    }
                }
            }
            mIsTaskRunning = false;
        }
    }
}
