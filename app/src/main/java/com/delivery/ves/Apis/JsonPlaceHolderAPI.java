package com.delivery.ves.Apis;

import com.delivery.ves.Models.NewOrder.NewOrderResponse;
import com.delivery.ves.Models.RateOrder.RateOrderResponse;
import com.delivery.ves.Models.ShowOrders.AllOrders.AllOrderResponse;
import com.delivery.ves.Models.ShowOrders.ShowLastOrderAndSpecificOrderResponse;
import com.delivery.ves.Models.SignUp.ApiSignUpResponse;
import com.delivery.ves.Models.SignUp.SignUpSuccessfully;
import com.delivery.ves.Models.SignUp.User;
import com.delivery.ves.Models.UserProfile.UpdateUser;
import com.delivery.ves.Models.UserProfile.UpdateUserProfileResponse;
import com.delivery.ves.Models.product.GetProduct;
import com.delivery.ves.Models.Login.LoginResponse;
import com.delivery.ves.Models.Login.LoginSuccessResponse;
import com.delivery.ves.Models.promoCode.PromoCodeError;
import com.delivery.ves.Models.promoCode.PromoCodeResponse;

import java.io.File;
import java.util.ArrayList;


import local.org.apache.http.entity.mime.MultipartEntity;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface JsonPlaceHolderAPI {

    @FormUrlEncoded
    @POST("signup.php")
    Call<ApiSignUpResponse> setSignUpData(@Field("auth") String auth,
                                          @Field("fullname") String fullname,
                                          @Field("phone") String phone,
                                          @Field("email") String email,
                                          @Field("gender") String gender,
                                          @Field("address") String address,
                                          @Field("birthdate") String birthdate,
                                          @Field("password") String password);

    @FormUrlEncoded
    @POST("signup.php")
    Call<SignUpSuccessfully> setSignUpDataSuccess(@Field("auth") String auth,
                                                  @Field("fullname") String fullname,
                                                  @Field("phone") String phone,
                                                  @Field("email") String email,
                                                  @Field("gender") String gender,
                                                  @Field("address") String address,
                                                  @Field("birthdate") String birthdate,
                                                  @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> setSignInData(@Field("auth") String auth,
                                      @Field("phoneoremail") String phoneOrEmail,
                                      @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginSuccessResponse> setSignInSuccess(@Field("auth") String auth,
                                                @Field("phoneoremail") String phoneOrEmail,
                                                @Field("password") String password);

    @FormUrlEncoded
    @POST("products.php")
    Call<GetProduct> getproduct(@Field("auth") String auth,
                                 @Field("cat") String cat);


    @FormUrlEncoded
    @POST("promocode.php")
    Call<PromoCodeResponse> getPromoCodeSuccess(@Field("auth") String auth,
                                         @Field("promo") String promo,
                                         @Field("total") String total);

    @FormUrlEncoded
    @POST("promocode.php")
    Call<PromoCodeError> getPromoCode(@Field("auth") String auth,
                                      @Field("promo") String promo,
                                      @Field("total") String total);

    @FormUrlEncoded
    @POST("neworder.php")
    Call<NewOrderResponse> setNewOrder(@Field("auth") String auth,
                                       @Field("userid") String userId,
                                       @Field("products[]") ArrayList<String> productsId,
                                       @Field("quantity[]") ArrayList<String> quantity,
                                       @Field("promocode") String promocode,
                                       @Field("quantity2[]") ArrayList<String> quantity2);




    @FormUrlEncoded
    @POST("showorder.php")
    Call<ShowLastOrderAndSpecificOrderResponse> LastOrder(@Field("auth") String auth,
                                       @Field("userid") String userId);

    @FormUrlEncoded
    @POST("showorder.php")
    Call<ShowLastOrderAndSpecificOrderResponse> SpecificOrder(@Field("auth") String auth,
                                                          @Field("orderid") String orderId);

    @FormUrlEncoded
    @POST("showorder.php")
    Call<AllOrderResponse> AllOrder(@Field("auth") String auth,
                                    @Field("userid") String userId);

    @FormUrlEncoded
    @POST("rate.php")
    Call<RateOrderResponse> rateOrder(@Field("auth") String auth,
                                      @Field("orderid") String orderId,
                                      @Field("rate") String rate);



    @POST("updateuser.php")
    Call<UpdateUserProfileResponse> UpdateUser(@Body RequestBody file);
}
