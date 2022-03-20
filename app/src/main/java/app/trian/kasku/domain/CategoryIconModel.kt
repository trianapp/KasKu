package app.trian.kasku.domain

import app.trian.kasku.R

val listIconCategory = listOf(
    CategoryIconModel.Aeroplane,
    CategoryIconModel.Armchair,
    CategoryIconModel.Atm,
    CategoryIconModel.AutonomousCar,
    CategoryIconModel.Bank,
    CategoryIconModel.Cat,
    CategoryIconModel.Coins,
    CategoryIconModel.CreditCard,
    CategoryIconModel.Cutlery,
    CategoryIconModel.Gift,
    CategoryIconModel.Group,
    CategoryIconModel.Headphones,
    CategoryIconModel.Hotel,
    CategoryIconModel.Invoice,
    CategoryIconModel.Language,
    CategoryIconModel.Medicine,
    CategoryIconModel.Money,
    CategoryIconModel.Mortgage,
    CategoryIconModel.Notebook,
    CategoryIconModel.OpenBook,
    CategoryIconModel.Philosophy,
    CategoryIconModel.PiggyBank,
    CategoryIconModel.Psychology,
    CategoryIconModel.School,
    CategoryIconModel.ShoppingBag,
    CategoryIconModel.ShoppingBasket,
    CategoryIconModel.Target,
    CategoryIconModel.Toolbox,
    CategoryIconModel.Van,
)

sealed class CategoryIconModel(
    var iconName:String,
    var icon:Int,
    var code:Int
){
    object Aeroplane:CategoryIconModel(
        iconName = "Aeroplane",
        icon = R.drawable.ic_category_aeroplane,
        code = 1
    )
    object Armchair:CategoryIconModel(
        iconName = "Armchair",
        icon = R.drawable.ic_category_armchair,
        code = 2
    )
    object Atm:CategoryIconModel(
        iconName = "Atm",
        icon = R.drawable.ic_category_atm,
        code = 3
    )
    object AutonomousCar:CategoryIconModel(
        iconName = "Autonomous",
        icon = R.drawable.ic_category_autonomous_car,
        code = 4
    )
    object Bank:CategoryIconModel(
        iconName = "Bank",
        icon = R.drawable.ic_category_bank,
        code = 5
    )
    object Cat:CategoryIconModel(
        iconName = "Cat",
        icon = R.drawable.ic_category_cat,
        code = 6
    )
    object Coins:CategoryIconModel(
        iconName = "Coins",
        icon = R.drawable.ic_category_coins,
        code = 7
    )
    object CreditCard:CategoryIconModel(
        iconName = "CreditCard",
        icon = R.drawable.ic_category_credit_card,
        code = 8
    )
    object Cutlery:CategoryIconModel(
        iconName = "Cutlery",
        icon = R.drawable.ic_category_cutlery,
        code = 9
    )
    object Gift:CategoryIconModel(
        iconName = "Gift",
        icon = R.drawable.ic_category_gift,
        code = 10
    )
    object Group:CategoryIconModel(
        iconName = "Group",
        icon = R.drawable.ic_category_group,
        code = 11
    )
    object Headphones:CategoryIconModel(
        iconName = "Headphones",
        icon = R.drawable.ic_category_headphones,
        code = 12
    )
    object Hotel:CategoryIconModel(
        iconName = "Hotel",
        icon = R.drawable.ic_category_hotel,
        code = 13
    )
    object Invoice:CategoryIconModel(
        iconName = "Invoice",
        icon = R.drawable.ic_category_invoice,
        code = 14
    )
    object Language:CategoryIconModel(
        iconName = "Language",
        icon = R.drawable.ic_category_language,
        code = 15
    )
    object Medicine:CategoryIconModel(
        iconName = "Medicine",
        icon = R.drawable.ic_category_medicine,
        code = 16
    )
    object Money:CategoryIconModel(
        iconName = "Money",
        icon = R.drawable.ic_category_money,
        code = 17
    )
    object Mortgage:CategoryIconModel(
        iconName = "Mortgage",
        icon = R.drawable.ic_category_mortgage,
        code = 18
    )
    object Notebook:CategoryIconModel(
        iconName = "Notebook",
        icon = R.drawable.ic_category_notebook,
        code = 19
    )
    object OpenBook:CategoryIconModel(
        iconName = "OpenBook",
        icon = R.drawable.ic_category_open_book,
        code = 20
    )
    object Philosophy:CategoryIconModel(
        iconName = "Philosophy",
        icon = R.drawable.ic_category_philosophy,
        code = 21
    )
    object PiggyBank:CategoryIconModel(
        iconName = "PiggyBank",
        icon = R.drawable.ic_category_piggy_bank,
        code = 22
    )
    object Psychology:CategoryIconModel(
        iconName = "Psychology",
        icon = R.drawable.ic_category_psychology,
        code = 23
    )
    object School:CategoryIconModel(
        iconName = "School",
        icon = R.drawable.ic_category_school,
        code = 24
    )
    object ShoppingBasket:CategoryIconModel(
        iconName = "ShoppingBasket",
        icon = R.drawable.ic_category_shopping_basket,
        code = 25
    )
    object ShoppingBag:CategoryIconModel(
        iconName = "ShoppingBag",
        icon = R.drawable.ic_category_shopping_bag,
        code = 26
    )
    object Target:CategoryIconModel(
        iconName = "Target",
        icon = R.drawable.ic_category_target,
        code = 27
    )
    object Toolbox:CategoryIconModel(
        iconName = "Toolbox",
        icon = R.drawable.ic_category_toolbox,
        code = 28
    )
    object Van:CategoryIconModel(
        iconName = "Van",
        icon = R.drawable.ic_category_van,
        code = 29
    )
}
