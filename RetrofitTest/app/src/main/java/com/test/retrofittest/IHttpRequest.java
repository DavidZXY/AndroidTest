package com.test.retrofittest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author DavidZXY
 * @date 2019/12/18
 */
public interface IHttpRequest {
    @POST("{path}")
    Call<ArrayList<String>> call(@Path("path") String path);

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<String>> contributors(
            @Path("owner") String user,
            @Path("repo") String repo);
}
