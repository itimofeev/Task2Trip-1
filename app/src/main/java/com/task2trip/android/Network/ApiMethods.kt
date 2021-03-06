package com.task2trip.android.Network

import android.content.Context
import com.google.gson.GsonBuilder
import com.task2trip.android.BuildConfig
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.*
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.Model.Task.TaskList
import com.task2trip.android.Model.Task.TaskSaveModel
import com.task2trip.android.Model.User.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiMethods {
    companion object {
        const val BASE_URL = "http://116.203.97.169:8000/api/"
        private const val VERSION: String = "v1/"

        fun getInstance(context: Context): ApiMethods {
            val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
                .baseUrl(BASE_URL + VERSION)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .client(getOkHttpClient(context))

            return  retrofitBuilder.build().create(ApiMethods::class.java)
        }

        private fun getOkHttpClient(context: Context): OkHttpClient {
            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            httpClient.interceptors().add(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            httpClient.interceptors().add(getInterceptorAuthorizationParam(context))

            return httpClient.build()
        }

        private fun getInterceptorAuthorizationParam(context: Context): Interceptor {
            return Interceptor { chain ->
                val original: Request = chain.request()

                // Настраиваем запросы
                val requestBuilder: Request.Builder = original.newBuilder()
                val localStorage = LocalStoreManager(context)
                if (!localStorage.get(Constants.EXTRA_USER_TOKEN, "").isNullOrEmpty()) {
                    requestBuilder
                        .header("Authorization", localStorage.get(Constants.EXTRA_USER_TOKEN, "").orEmpty())
                }

                requestBuilder
                    .method(original.method(), original.body())

                return@Interceptor chain.proceed(requestBuilder.build())
            }
        }
    }

    @POST("user")
    fun userRegister(@Body user: UserDataReq): Call<UserInfoResp>

    @POST("user/login")
    fun userLogin(@Body user: UserDataReq): Call<UserLoginResp>

    @GET("user")
    fun userInfo(): Call<UserInfoResp>

    @GET("category")
    fun getCategoryList(): Call<List<TaskCategory>>

    @PUT("task")
    fun saveTask(@Body taskSaveModel: TaskSaveModel): Call<Task>

    @GET("task")
    fun getAllTasks(): Call<TaskList>

    @GET("task")
    fun getTasks(@Query("userId") userId: String? = null,
                 @Query("searchString") searchString: String? = null,
                 @Query("categoryId") categoryId: String? = null,
                 @Query("skip") skip: Int? = null,
                 @Query("limit") limit: Int? = null,
                 @Query("status") status: String? = null): Call<TaskList>

    @PUT("task/{taskId}/offer")
    fun sendOfferByTaskId(@Path("taskId") taskId: String,
                          @Body offerForSave: OfferForSave): Call<Offer>

    @GET("task/{taskId}/offer")
    fun getOffersByTaskId(@Path("taskId") taskId: String): Call<List<Offer>>
}