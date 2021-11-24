package com.example.health_food.retrofit

import com.example.health_food.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @FormUrlEncoded
    @POST("/")
    fun postLogin(
        @Field("userId") id: String,
        @Field("password") password: String
    ) : Call<String>

    @FormUrlEncoded
    @POST("/signup")
    fun postSignUp(
        @Field("userId") id:String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("nickname") nickname: String
    ):Call<String>

    @FormUrlEncoded
    @POST("/idOverlap")
    fun postIdOverlap(
        @Field("userId") id: String
    ): Call<String>

    @FormUrlEncoded
    @POST("/emailOverlap")
    fun postEmailOverlap(
        @Field("email") email: String
    ) : Call<String>

    @FormUrlEncoded
    @POST("/getNickname")
    fun postGetNickName(
        @Field("userId") id:String
    ) : Call<ArrayList<User>>

    @FormUrlEncoded
    @POST("/emailAuth")
    fun postEmailAuth(
        @Field("userId") id: String
    ): Call<String>

    @FormUrlEncoded
    @POST("/pwChange")
    fun postPwChange(
        @Field("userId") id: String,
        @Field("password") pw: String
    ): Call<String>

    @FormUrlEncoded
    @POST("/isTasteDiv")
    fun postIsTasteDiv(
        @Field("userId") id:String,
        @Field("local") local: String,
        @Field("health") health: String
    ) : Call<String>

    @FormUrlEncoded
    @POST("/nameChange")
    fun postNameChange(
        @Field("userId") id: String,
        @Field("nickname") username: String
    ) : Call<String>

    @Multipart
    @POST("/upload")
    fun postUpload(
        @Part("userId") id: RequestBody,
        @Part file: MultipartBody.Part,
        @Part("tag") tag: RequestBody,
        @Part("content") content: RequestBody,
        @Part ("url") url: RequestBody
    ) : Call<String>

    @GET("/list/1")
    fun getList(): Call<ArrayList<CommunityDTO>>

    @Multipart
    @POST("/pageUpdate")
    fun postPageUpdate(
        @Part("id") id: RequestBody,
        @Part("userId") userId: RequestBody,
        @Part file: MultipartBody.Part,
        @Part ("tag") tag: RequestBody,
        @Part ("content") content: RequestBody,
        @Part ("url") url: RequestBody
    ): Call<String>

    @FormUrlEncoded
    @POST("/pageDelete")
    fun postPageDelete(
        @Field("id") id: String
    ): Call<String>

    @FormUrlEncoded
    @POST("/pageOneList")
    fun postPageOneList(
        @Field("id") id: String
    ): Call<ArrayList<CommunityDTO>>

    @FormUrlEncoded
    @POST("/vegetableMain")
    fun getVegetable(
        @Field("tasteDiv") taste: Int
    ): Call<ArrayList<RecommendDTO>>

    @FormUrlEncoded
    @POST("/meatMain")
    fun getMeat(
        @Field("tasteDiv") taste: Int
    ): Call<ArrayList<RecommendDTO>>

    @FormUrlEncoded
    @POST("/dessertMain")
    fun getDessert(
        @Field("tasteDiv") taste: Int
    ): Call<ArrayList<RecommendDTO>>

    @FormUrlEncoded
    @POST("/etcMain")
    fun getEtc(
        @Field("tasteDiv") taste: Int
    ): Call<ArrayList<RecommendDTO>>

    @FormUrlEncoded
    @POST("/ingredientSearch")
    fun postIngredient(
        @Field("id") id: String
    ): Call<ArrayList<String>>

    @FormUrlEncoded
    @POST("/recipe")
    fun postRecipe(
        @Field("id") id: String
    ) : Call<ArrayList<Recipe>>

    @GET("/ingredientList")
    fun postIngredientList(): Call<ArrayList<Food>>

    @FormUrlEncoded
    @POST("/frige")
    fun postFrige(
        @Field("userId") id: String,
        @Field("food") food: ArrayList<Int>
    ): Call<String>

    @FormUrlEncoded
    @POST("/userFridge")
    fun postUserFridge(
        @Field("userId") id: String
    ): Call<ArrayList<Refrigerator>>

    @FormUrlEncoded
    @POST("/ingredientDelete")
    fun postIngredientDelete(
        @Field("userId") id: String,
        @Field("id") foodid: ArrayList<Int>,
        @Field("ingredient") ingredient: ArrayList<Int>
    ): Call<String>

    @FormUrlEncoded
    @POST("/frigeRecipeList")
    fun postfrigeRecipe(
        @Field("userId") id: String,
        @Field("foodChoose") food: ArrayList<Int>
    ) : Call<ArrayList<FrigeRecipe>>
}