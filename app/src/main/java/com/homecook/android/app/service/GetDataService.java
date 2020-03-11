package com.homecook.android.app.service;

import com.google.gson.internal.LinkedTreeMap;
import com.homecook.android.app.common.User;
import com.homecook.android.app.feed.feed_item.FeedItemData;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GetDataService {

    String AUTHORIZATION = "Authorization";

    @GET("feedItems.json")
    Call<LinkedTreeMap<String, FeedItemData>> getNearbyFeed(@Header(AUTHORIZATION) String authHeader);

    @POST("feedItems.json")
    Call<Map<String,String>> postMessage(@Body FeedItemData data, @Header(AUTHORIZATION) String authHeader);

    @PUT("users/{uid}/posts/{postId}.json")
    Call<Map<String,String>> updateUser(@Path("uid") String uid, @Path("postId") String postId,
                                        @Body boolean marker, @Header(AUTHORIZATION) String authHeader);

    @PUT("users/{uid}/upvoted/{postId}.json")
    Call<LinkedTreeMap<String, String>> addToUpvoted(@Path("uid") String uid, @Path("postId") String postId, @Body boolean marker, @Header(AUTHORIZATION) String authHeader);

    @PUT("users/{uid}/downvoted/{postId}.json")
    Call<LinkedTreeMap<String, String>> addToDownvoted(@Path("uid") String uid, @Path("postId") String postId, @Body boolean marker, @Header(AUTHORIZATION) String authHeader);

    @DELETE("users/{uid}/upvoted/{postId}.json")
    Call<Object> removeFromUpvoted(@Path("uid") String uid, @Path("postId") String postId, @Header(AUTHORIZATION) String authHeader);

    @DELETE("users/{uid}/upvoted/{postId}.json")
    Call<Object> removeFromDownvoted(@Path("uid") String uid, @Path("postId") String postId, @Header(AUTHORIZATION) String authHeader);

}