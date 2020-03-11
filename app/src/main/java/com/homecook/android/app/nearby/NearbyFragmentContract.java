package com.homecook.android.app.nearby;

import androidx.annotation.NonNull;

import com.homecook.android.app.common.MvpPresenter;
import com.homecook.android.app.common.MvpView;
import com.homecook.android.app.feed.feed_item.FeedItemData;

import java.util.List;

/**
 * @author rohansaigaonkar
 */
public interface NearbyFragmentContract {
    public interface View extends MvpView<Presenter> {
        void showLoading();
        void hideLoading();
        void disableSubmissions();
        void enableSubmissions();
        void upvote(int position);
        void downvote(int position);
        void refresh();

        void setListData(List<FeedItemData> response);
    }

    public interface Presenter extends MvpPresenter {
        void fetchItems();
        void setItems();
        void postItem(@NonNull String message);

        void onRefresh();

        void onUpvotePressed(String postId);
        void onDownvotePressed(String postId);
        void onItemPressed(String postId);
    }
}
