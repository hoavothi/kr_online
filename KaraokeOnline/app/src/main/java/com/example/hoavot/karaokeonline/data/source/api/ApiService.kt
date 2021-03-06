package com.example.hoavot.karaokeonline.data.source.api

import com.example.hoavot.karaokeonline.data.model.other.User
import com.example.hoavot.karaokeonline.data.model.remote.*
import com.example.hoavot.karaokeonline.data.source.request.LoginBody
import com.example.hoavot.karaokeonline.data.source.response.*
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

/**
 *  Copyright © 2017 AsianTech inc.
 *  Created by hoavot on 10/12/2017.
 */
interface ApiService {
    companion object {
        const val URL_SEARCH_VIDEO = "/youtube/v3/search"
        const val URL_SEARCH_DETAIL_VIDEO = "/youtube/v3/videos"
        const val URL_SEARCH_CHANNEL = "/youtube/v3/channels"
        const val URL_SEARCH_PLAYLIST = "youtube/v3/playlists"
        const val URL_SEARCH_DETAIL_PLAYLIST = "/youtube/v3/playlistItems"
        const val KEY_BROWSER = "AIzaSyATxFUmJOzuagSH_qs9jVZza7p6-Ycpo4k"
    }

    // Use get list video when search by keyword
    @GET(URL_SEARCH_VIDEO)
    fun getVideoSearch(
            @Query("part") part: String,
            @Query("q") q: String,
            @Query("maxResults") maxResult: Int,
            @Query("key") key: String = KEY_BROWSER
    ): Single<VideoSearchFromApi>

    // Use get detail of each video
    @GET(URL_SEARCH_DETAIL_VIDEO)
    fun getVideoDetail(@Query("part") part: String,
                       @Query("id") id: String,
                       @Query("maxResult") maxResult: Int,
                       @Query("key") key: String = KEY_BROWSER
    ): Single<VideoDetailFromApi>

    @GET(URL_SEARCH_CHANNEL)
    fun getChannelDetail(@Query("part") part: String,
                         @Query("id") id: String,
                         @Query("key") key: String = KEY_BROWSER
    ): Single<ChannelSearchFromApi>

    @GET(URL_SEARCH_PLAYLIST)
    fun getPlaylistSearch(@Query("part") part: String,
                          @Query("id") id: String,
                          @Query("key") key: String = KEY_BROWSER
    ): Single<PlaylistSearchFromApi>

    @GET(URL_SEARCH_DETAIL_PLAYLIST)
    fun getPlaylistDetails(@Query("part") part: String,
                           @Query("playlistId") playlistId: String,
                           @Query("key") key: String = KEY_BROWSER
    ): Single<PlaylistDetailFromApi>

    @GET("/youtube/v3/videos")
    fun getPopularVideo(@Query("part") part: String,
                        @Query("chart") chart: String,
                        @Query("regionCode") regionCode: String,
                        @Query("maxResults") maxResults: Int = 50,
                        @Query("key") key: String = KEY_BROWSER): Single<VideoDetailFromApi>

    @GET(URL_SEARCH_VIDEO)
    fun getMoreVideos(
            @Query("part") part: String,
            @Query("eventType") eventType: String,
            @Query("maxResults") maxResults: String,
            @Query("relatedToVideoId") relatedToVideoId: String,
            @Query("type") type: String,
            @Query("key") key: String = KEY_BROWSER
    )
            : Single<VideoSearchFromApi>

    @GET("/api/user/infor/{id}")
    fun getMeUser(
            @Path("id") id: Int
    ): Single<User>

    @FormUrlEncoded
    @PUT("/api/user/update")
    fun updateInforUser(
            @Field("username") username: String,
            @Field("password") password: String,
            @Field("email") email: String
    ): Single<LoginResponse>

    @Multipart
    @PUT("/api/user/update/avatar")
    fun updateAvatarUser(
            @Part avatar: MultipartBody.Part
    ): Single<UserResponse>

    @GET("/api/feeds")
    fun getFeeds(): Single<FeedsResponse>

    @GET("/api/feed/infor/{id}")
    fun getFeedMe(@Path("id") id: Int): Single<FeedsResponse>

    /**
     *  Function for me comment to feed
     */
    @FormUrlEncoded
    @POST("/api/feed/{id}/comment")
    fun postComment(@Path("id") feedId: Int, @Field("comment") comment: String): Single<CommentResponse>

    @POST("/api/feed/{id}/like")
    fun postLike(@Path("id") feedId: Int): Single<LikeResponse>

    @DELETE("/api/feed/{id}/like")
    fun postUnLike(@Path("id") feedId: Int): Single<LikeResponse>

    @GET("/api/feed/{id}/comments")
    fun getComments(@Path("id") feedId: Int): Single<CommentResponse>

    @Multipart
    @POST("/api/feed/create")
    fun postFeed(@Part audio: MultipartBody.Part?, @Part("file_name") file_name: RequestBody, @Part("caption") caption: RequestBody, @Part imageFile: MultipartBody.Part?)
            : Single<FeedResponse>

    @POST("/api/login")
    fun login(@Body loginBody: LoginBody): Single<LoginResponse>

    @POST("/api/register")
    fun register(@Body loginBody: LoginBody): Single<LoginResponse>

    @DELETE("/api/feed/me/{id}")
    fun deleteFeed(@Path("id") id: Int): Single<DeleteFeedResponse>

    @Multipart
    @PUT("/api/feed/me")
    fun updateFeed(@Part audio: MultipartBody.Part?, @Part("file_name") file_name: RequestBody, @Part("caption") caption: RequestBody, @Part("id") id: RequestBody, @Part imageFile: MultipartBody.Part?)
            : Single<FeedResponse>

//    @GET("/api/user")
//    fun getInforUser(@Query("id") id: Int): Single<UserInforResponse>

    @GET("/api/feed/{id}/likes")
    fun getUsersLike(@Path("id") id: Int): Single<UserLikeResponse>
}
