package com.lowe.core.network;

import androidx.annotation.NonNull;

public interface ResultCallback<T> {

  ResultCallback<?> IGNORE = new ResultCallback<Object>() {
    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onFailure(@NonNull Throwable throwable) {

    }
  };

  @NonNull
  static <T> ResultCallback<T> ignore() {
    //noinspection unchecked
    return (ResultCallback<T>) IGNORE;
  }

  void onSuccess(T data);

  /**
   * {@link ResultCallback#onSuccess(Object)} with additional info about time, when response was
   * received
   */
  default void onSuccess(T data, long receivedResponseAtMillis) {
    onSuccess(data);
  }

  void onFailure(@NonNull Throwable throwable);
}
