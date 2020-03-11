package com.homecook.android.app.nearby;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.homecook.android.app.common.ServerCallback;
import com.homecook.android.app.feed.feed_item.FeedItemData;
import com.homecook.android.app.service.ServerRequestService;

import java.util.List;
import java.util.Map;

/**
 * @author rohansaigaonkar
 */
public class NearbyFragmentPresenter implements NearbyFragmentContract.Presenter {

    private NearbyFragmentContract.View view;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference databaseReference;

    public NearbyFragmentPresenter(NearbyFragmentContract.View view) {
        this.view = view;
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        view.setPresenter(this);

        fetchItems();
    }
    @Override
    public void start() {

    }


    @Override
    public void fetchItems() {
        view.showLoading();
        ServerRequestService.getNearbyFeed(mUser, new ServerCallback<List<FeedItemData>>() {
            @Override
            public void onSuccess(List<FeedItemData> response) {
                view.setListData(response);
                view.hideLoading();
                view.enableSubmissions();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void setItems() {
    }



    @Override
    public void postItem(@NonNull String message) {
        view.disableSubmissions();
        FeedItemData feedData = new FeedItemData.Builder()
                .withDate(String.valueOf(System.currentTimeMillis()))
                .withPoints(0)
                .withTitle(message)
                .withAuthorId(mUser.getUid())
                .build();

        ServerRequestService.postMessage(mUser, feedData, new ServerCallback<String>() {
            @Override
            public void onSuccess(String response) {
                updateUser(response);
                fetchItems();
            }

            @Override
            public void onFailure(Throwable t) {
                view.enableSubmissions();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onRefresh() {
        fetchItems();
    }

    @Override
    public void onUpvotePressed(String postId) {
        view.upvote(1);

        ServerRequestService.addToUpvoted(mUser, postId, new ServerCallback<String>() {
            @Override
            public void onSuccess(String response) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void onDownvotePressed(String postId) {


         view.downvote(4);

         ServerRequestService.addToDownvoted(mUser, postId, new ServerCallback<String>() {
             @Override
             public void onSuccess(String response) {

             }

             @Override
             public void onFailure(Throwable t) {

             }
         });
    }

    @Override
    public void onItemPressed(String postId) {

    }

    private void updateUser(String snapshotKey) {
        ServerRequestService.updateUser(mUser, snapshotKey, new ServerCallback<Map<String, String>>() {
            @Override
            public void onSuccess(Map<String, String> response) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
