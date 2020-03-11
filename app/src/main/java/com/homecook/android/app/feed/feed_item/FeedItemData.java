package com.homecook.android.app.feed.feed_item;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

/**
 * Created by Rohan Saigaonkar on 2020-01-17.
 */
public class FeedItemData {
    @NonNull private String postingDateMillis;
    @NonNull private String title;
    @NonNull private String imgUrl;
    @Nullable private String postId;
    @NonNull private String authorId;

    private int points;

    private FeedItemData(final Builder builder) {
        postingDateMillis = builder.postingDate;
        title = builder.title;
        imgUrl = builder.imgUrl;
        points = builder.points;
        authorId = builder.authorId;
    }

    public String getPostingDateMillis() {
        return postingDateMillis;
    }

    @NonNull
    public String getImgUrl() {
        return imgUrl;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public int getPoints() {
        return points;
    }

    public void setId(String id) {
        this.postId = id;
    }

    @Nullable
    public String getPostId() {
        return postId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }

        if(obj.getClass() != getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        FeedItemData data = (FeedItemData) obj;
        return Objects.equals(postingDateMillis, data.postingDateMillis)
                && Objects.equals(title, data.title)
                && Objects.equals(imgUrl, data.imgUrl);
    }

    public static class Builder {

        @NonNull private String authorId;
        @NonNull private String postingDate;
        @NonNull private String title;
        @NonNull private String imgUrl;
        private int points;

        public Builder withDate(final String postingDate) {
            this.postingDate = postingDate;
            return this;
        }

        public Builder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder withUrl(final String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public Builder withPoints(final int points) {
            this.points = points;
            return this;
        }

        public Builder withAuthorId(final String authorId) {
            this.authorId = authorId;
            return this;
        }

        public FeedItemData build() {
            return new FeedItemData(this);
        }
    }
}
