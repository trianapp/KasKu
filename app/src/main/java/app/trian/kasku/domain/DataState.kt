package app.trian.kasku.domain

/**
 *
 * author Trian Damai
 * created_at 13/03/22 - 19.08
 * site https://trian.app
 */
sealed class DataState<out T> {
    object OnLoading : DataState<Nothing>()
    data class OnData<out Result>(val data:Result):DataState<Result>()
    data class OnFailure(val message:String):DataState<Nothing>()
}
