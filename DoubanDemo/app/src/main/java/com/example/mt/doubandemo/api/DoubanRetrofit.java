package com.example.mt.doubandemo.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mt on 2017/5/21.
 */

public class DoubanRetrofit {

    private static DoubanService mDoubanService;

    public static DoubanService createDoubanService(){
        if(mDoubanService==null){
            Retrofit retrofit = createRetrofit();
            mDoubanService = retrofit.create(DoubanService.class);
        }
        return mDoubanService;
    }
    private static Retrofit createRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(DoubanService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
