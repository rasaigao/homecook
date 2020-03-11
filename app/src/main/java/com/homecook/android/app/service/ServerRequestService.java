package com.homecook.android.app.service;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.gson.internal.LinkedTreeMap;
import com.homecook.android.app.common.ServerCallback;
import com.homecook.android.app.feed.feed_item.FeedItemData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServerRequestService {

    public static void postMessage(final FirebaseUser user, final FeedItemData postBody, final ServerCallback<String> callback) {
        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                if(task.isSuccessful()) {
                    String idToken = task.getResult().getToken();
                    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<Map<String,String>> call = service.postMessage(postBody, idToken);

                    call.enqueue(new Callback<Map<String,String>>() {
                        @Override
                        public void onResponse(Call<Map<String,String>> call, Response<Map<String,String>> response) {
                            try {
                                LinkedTreeMap<String,String> responseMap = (LinkedTreeMap<String,String>) response.body();
                                callback.onSuccess(responseMap.get("name"));

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onFailure(Call<Map<String,String>> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });


                }
            }
        });
    }

    public static void updateUser(final FirebaseUser user, final String postId, final ServerCallback<Map<String,String>> callback) {
        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                if(task.isSuccessful()) {
                    String idToken = task.getResult().getToken();
                    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<Map<String,String>> call = service.updateUser(user.getUid(), postId, true, idToken);

                    call.enqueue(new Callback<Map<String,String>>() {
                        @Override
                        public void onResponse(Call<Map<String,String>> call, Response<Map<String,String>> response) {
                            try {
                                LinkedTreeMap<String,String> responseMap = (LinkedTreeMap<String,String>) response.body();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onFailure(Call<Map<String,String>> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });


                }
            }
        });
    }

    public static void getNearbyFeed(final FirebaseUser user, final ServerCallback<List<FeedItemData>> callback) {
        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                if(task.isSuccessful()) {
                    String idToken = task.getResult().getToken();
                    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<LinkedTreeMap<String,FeedItemData>> call = service.getNearbyFeed(idToken);

                    call.enqueue(new Callback<LinkedTreeMap<String,FeedItemData>>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onResponse(Call<LinkedTreeMap<String,FeedItemData>> call, Response<LinkedTreeMap<String,FeedItemData>> response) {
                            try {
                                LinkedTreeMap responseMap = response.body();
                                List<FeedItemData> result = new ArrayList<>();
                                if(responseMap == null) throw new Exception("Feed is null");
                                for(LinkedTreeMap.Entry<String, FeedItemData> entry : response.body().entrySet()) {
                                    FeedItemData data = entry.getValue();
                                    data.setId(entry.getKey());
                                    result.add(data);
                                }

                                result.sort(new Comparator<FeedItemData>() {
                                    @Override
                                    public int compare(FeedItemData feedItemData, FeedItemData t1) {
                                        return Long.compare(Long.valueOf(t1.getPostingDateMillis()), Long.valueOf(feedItemData.getPostingDateMillis()));
                                    }
                                });

                                callback.onSuccess(result);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onFailure(Call<LinkedTreeMap<String,FeedItemData>> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });


                }
            }
        });
    }

    public static void addToUpvoted(final FirebaseUser user, final String feedItemId, final ServerCallback<String> callback) {
        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                if(task.isSuccessful()) {
                    String idToken = task.getResult().getToken();
                    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<LinkedTreeMap<String,String>> call = service.addToUpvoted(user.getUid(), feedItemId, true, idToken);

                    call.enqueue(new Callback<LinkedTreeMap<String,String>>() {
                        @Override
                        public void onResponse(Call<LinkedTreeMap<String,String>> call, Response<LinkedTreeMap<String,String>> response) {

                        }
                        @Override
                        public void onFailure(Call<LinkedTreeMap<String,String>> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });


                }
            }
        });
    }

    public static void addToDownvoted(final FirebaseUser user, final String feedItemId, final ServerCallback<String> callback) {
        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                if(task.isSuccessful()) {
                    String idToken = task.getResult().getToken();
                    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<LinkedTreeMap<String,String>> call = service.addToDownvoted(user.getUid(), feedItemId, true, idToken);

                    call.enqueue(new Callback<LinkedTreeMap<String,String>>() {
                        @Override
                        public void onResponse(Call<LinkedTreeMap<String,String>> call, Response<LinkedTreeMap<String,String>> response) {

                        }
                        @Override
                        public void onFailure(Call<LinkedTreeMap<String,String>> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });


                }
            }
        });
    }

    public static void removeFromDownvoted(final FirebaseUser user, final String feedItemId, final ServerCallback<String> callback) {
        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                if(task.isSuccessful()) {
                    String idToken = task.getResult().getToken();
                    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<Object> call = service.removeFromDownvoted(user.getUid(), feedItemId,  idToken);

                    call.enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {

                        }
                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });


                }
            }
        });
    }

    public static void removeFromUpvoted(final FirebaseUser user, final String feedItemId, final ServerCallback<String> callback) {
        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                if(task.isSuccessful()) {
                    String idToken = task.getResult().getToken();
                    GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<Object> call = service.removeFromDownvoted(user.getUid(), feedItemId,  idToken);

                    call.enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            callback.onSuccess("Downvoted ");
                        }
                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });


                }
            }
        });
    }

}
