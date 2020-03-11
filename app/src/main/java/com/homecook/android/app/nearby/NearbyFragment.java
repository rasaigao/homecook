package com.homecook.android.app.nearby;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.homecook.android.app.R;
import com.homecook.android.app.auth.signup.SignUpFragment;
import com.homecook.android.app.feed.feed_item.FeedItemData;

import java.util.List;

/**
 * @author rohansaigaonkar
 */
public class NearbyFragment extends Fragment implements NearbyFragmentContract.View {

    private RecyclerView feedItemList;
    private NearbyFeedAdapter adapter;
    private AppCompatButton postButton;
    private EditText inputEditText;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NearbyFragmentContract.Presenter presenter;

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void upvote(int position) {

    }

    @Override
    public void downvote(int position) {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void setListData(List<FeedItemData> response) {
        adapter.setData(response);
    }

    @Override
    public void setPresenter(@NonNull NearbyFragmentContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void enableSubmissions() {
        postButton.setEnabled(true);
    }

    @Override
    public void disableSubmissions() {
        postButton.setEnabled(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);

        feedItemList = view.findViewById(R.id.content_feed_list);
        inputEditText = view.findViewById(R.id.et_input);
        postButton = view.findViewById(R.id.btn_post);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        presenter = new NearbyFragmentPresenter(this);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.postItem(inputEditText.getText().toString());
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
            }
        });

        adapter = new NearbyFeedAdapter(presenter);
        feedItemList.setLayoutManager(new LinearLayoutManager(getContext()));
        feedItemList.setAdapter(adapter);


        return view;
    }
}
