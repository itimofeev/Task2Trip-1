package com.task2trip.android.Network

import android.content.Context
import com.google.gson.GsonBuilder
import com.task2trip.android.BuildConfig
import com.task2trip.android.Common.Constants
import com.task2trip.android.Common.ServerConstants
import com.task2trip.android.Model.*
import com.task2trip.android.Model.Chat.*
import com.task2trip.android.Model.Location.GeoCountryCity
import com.task2trip.android.Model.Task.*
import com.task2trip.android.Model.User.*
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiMethods {
    companion object {
        const val HTTP_ADDRESS = ServerConstants.PROTOCOL_HTTP
            .plus(ServerConstants.API_ADDRESS_DOMAIN)
            .plus("/api/")
            .plus(ServerConstants.API_VERSION)
            .plus("/")

        fun getInstance(context: Context): ApiMethods {
            val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
                .baseUrl(HTTP_ADDRESS)
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

    /**
     * Вход пользователя
     */
    @POST("user/login")
    fun userLogin(@Body user: UserLoginReq): Call<UserLoginResp>

    /**
     * Регистрация пользователя
     */
    @POST("user/signup")
    fun userRegister(@Body user: UserSignUpReq): Call<Void>

    @GET("user")
    fun getUserInfo(): Call<UserImpl>

    @PATCH("user/profile")
    fun updateUserProfile(@Body profile: ProfileImpl): Call<ProfileImpl>

    @POST("user")
    fun updateUserRoleLocal(@Body role: UserLevelUp): Call<UserImpl>

    @GET("user/{userId}")
    fun getUserProfileById(@Path("userId") userId: String): Call<UserImpl>

    /**
     * Сохранение аватарки пользователя
     */
    @Multipart
    @PUT("user/profile/image")
    fun saveUserImageAvatar(@Part profileImage: MultipartBody.Part): Call<String>

    /**
     * Получение списка категорий
     */
    @GET("category")
    fun getCategoryList(): Call<List<TaskCategory>>

    @PATCH("task/{taskId}")
    fun updateTask(@Path("taskId") taskId: String,
                   @Body taskSaveModel: TaskSaveModel): Call<Task>

    @PUT("task")
    fun saveTask(@Body taskSaveModel: TaskSaveModel): Call<Task>

    @GET("task")
    fun getAllTasks(): Call<TaskList>

    /**
     * Поиск задач по критериям
     */
    @GET("task")
    fun getTasks(@Query("userId") userId: String? = null,
                 @Query("searchString") searchString: String? = null,
                 @Query("categoryId") categoryIds: String? = null,
                 @Query("skip") skip: Int? = null,
                 @Query("limit") limit: Int? = null,
                 @Query("status") status: String? = null,
                 @Query("lat") lat: Double? = null,
                 @Query("long") long: Double? = null,
                 @Query("radius") radius: Int? = null): Call<TaskList>

    @PUT("task/{taskId}/offer")
    fun sendOfferByTaskId(@Path("taskId") taskId: String,
                          @Body offerForSave: OfferForSave): Call<Offer>

    @GET("task/{taskId}/offer")
    fun getOffersByTaskId(@Path("taskId") taskId: String): Call<List<Offer>>

    @POST("task/{taskId}/offer/{offerId}")
    fun setPerformerOfferForTask(@Path("taskId") taskId: String,
                                 @Path("offerId") offerId: String): Call<Offer>

    @PATCH("task/{taskId}/offer/{offerId}")
    fun setTaskCompletedOrCanceled(@Path("taskId") taskId: String,
                                   @Path("offerId") offerId: String,
                                   @Body statusAndRating: TaskStatusAndRating): Call<Offer>

    @GET("offer")
    fun getMyOffers(): Call<List<Offer>>

    @GET("geocode")
    fun getCountryAndCity(@Query("query") query: String?): Call<List<GeoCountryCity>>

    /**
     * Список чатов для текущего пользователя
     */
    @GET("chat")
    fun getChats(@Query("limit") limit: Int?,
                 @Query("skip") skip: Int?): Call<ChatList>

    /**
     * Создание чата с пользователем
     */
    @POST("chat")
    fun createChat(@Body userId: ChatCreateForUser): Call<Chat>

    /**
     * Отправка сообщения в чат
     */
    @POST("chat/{chatId}/message")
    fun sendMessageToChat(@Path("chatId") chatId: String,
                          @Body message: ChatMessageForSend): Call<ChatMessage>

    /**
     * Получение сообщений для чата
     */
    @GET("chat/{chatId}/message")
    fun getMessagesFromChat(@Path("chatId") chatId: String,
                            @Query("beforeTime") beforeTime: String?,
                            @Query("limit") limit: Int?): Call<List<ChatMessage>>

    /**
     * Помечает чат, как прочитанный
     */
    @POST("chat/{chatId}/read")
    fun markChatAsRead(@Path("chatId") chatId: String): Call<Void>
}