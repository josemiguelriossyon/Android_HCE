package de.androidcrypto.android_hce_beginner_app.JWT.Model;

public class ResultOf<T> {

    private final T value;
    private final Exception error;

    private ResultOf(T value, Exception error) {
        this.value = value;
        this.error = error;
    }

    public static <T> ResultOf<T> success(T value) {
        return new ResultOf<>(value, null);
    }

    public static <T> ResultOf<T> failure(Exception error) {
        return new ResultOf<>(null, error);
    }

    public T getValue() {
        return value;
    }

    public Exception getError() {
        return error;
    }
}

