package com.lowe.core.network;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import dagger.Module;
import dagger.Provides;
import java.util.ServiceLoader;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public interface NetworkModule {

  @Provides
  @NonNull
  @Singleton
  static Gson provideGson() {
    final GsonBuilder builder = new GsonBuilder();
    for (TypeAdapterFactory factory : ServiceLoader.load(TypeAdapterFactory.class)) {
      builder.registerTypeAdapterFactory(factory);
    }
    return builder
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .setLenient()
        .create();
  }

  @Provides
  @NonNull
  @Singleton
  static Converter.Factory provideFactory(@NonNull final Gson gson) {
    return GsonConverterFactory.create(gson);
  }

  @Provides
  @NonNull
  @Singleton
  static OkHttpClient provideOkHttpClient() {
    final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
    // must be the last interceptor to catch and log modified requests
    final HttpLoggingInterceptor loggingInterceptor =
        new HttpLoggingInterceptor();
    loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
    okHttpBuilder.addNetworkInterceptor(loggingInterceptor);
    return okHttpBuilder
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build();
  }
}
