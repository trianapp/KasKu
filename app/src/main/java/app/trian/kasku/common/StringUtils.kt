package app.trian.kasku.common

import java.util.regex.Pattern

/**
 *
 * author Trian Damai
 * created_at 13/03/22 - 23.48
 * site https://trian.app
 */
fun String.isEmailValid() =
    Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    ).matcher(this).matches()