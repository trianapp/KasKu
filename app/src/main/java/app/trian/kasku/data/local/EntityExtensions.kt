package app.trian.kasku.data.local

import app.trian.kasku.data.local.entity.BankAccount
import app.trian.kasku.data.local.entity.User

/**
 *
 * author Trian Damai
 * created_at 14/03/22 - 01.20
 * site https://trian.app
 */

fun BankAccount.toHashmap()=hashMapOf(
        "uid" to uid,
        "bankName" to bankName,
        "created_at" to created_at,
        "updated_at" to updated_at
)