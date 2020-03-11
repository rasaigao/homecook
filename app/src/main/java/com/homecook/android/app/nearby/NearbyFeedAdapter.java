package com.homecook.android.app.nearby;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.homecook.android.app.R;
import com.homecook.android.app.databinding.ItemFeedBinding;
import com.homecook.android.app.feed.FeedActivityContract;
import com.homecook.android.app.feed.feed_item.FeedItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rohansaigaonkar
 */
public class NearbyFeedAdapter extends RecyclerView.Adapter<NearbyFeedAdapter.NearbyFeedTextViewHolder> {
    private List<FeedItemData> feedItems = new ArrayList<>();
    private NearbyFragmentContract.Presenter presenter;

    public NearbyFeedAdapter(NearbyFragmentContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public NearbyFeedTextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemFeedBinding itemFeedBinding = ItemFeedBinding.inflate(inflater, parent, false);
        return new NearbyFeedTextViewHolder(itemFeedBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NearbyFeedTextViewHolder holder, final int position) {
        FeedItemData data = feedItems.get(position);
        holder.itemView.findViewById(R.id.upvote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onUpvotePressed(feedItems.get(position).getPostId());
            }
        });
        holder.itemView.findViewById(R.id.downvote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDownvotePressed(feedItems.get(position).getPostId());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onItemPressed(feedItems.get(position).getPostId());
            }
        });

        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public void setData(List<FeedItemData> items) {
        this.feedItems = items;
        notifyDataSetChanged();
    }

    @Nullable
    public FeedItemData getDataAt(int position) {
        if(feedItems.size() > position) {
            return feedItems.get(position);
        }

        return null;
    }

    class NearbyFeedTextViewHolder extends RecyclerView.ViewHolder {
        ItemFeedBinding binding;
        public NearbyFeedTextViewHolder(ItemFeedBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(FeedItemData data) {
            binding.setItemData(data);
        }
    }



}
