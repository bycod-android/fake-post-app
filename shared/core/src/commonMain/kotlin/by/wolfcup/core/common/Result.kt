package by.wolfcup.core.common


sealed class Result<T>(val data: T? = null, val msg: String? = null) {
    class Successful<T>(data: T) : Result<T>(data)
    class Loading<T>() : Result<T>()
    class Failure<T>(msg: String): Result<T>(data = null, msg)
}