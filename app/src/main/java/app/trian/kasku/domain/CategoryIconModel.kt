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
    var icon:Int
){
    object Aeroplane:CategoryIconModel(
        iconName = "Aeroplane",
        icon = R.drawable.ic_category_aeroplane
    )
    object Armchair:CategoryIconModel(
        iconName = "Armchair",
        icon = R.drawable.ic_category_armchair
    )
    object Atm:CategoryIconModel(
        iconName = "Atm",
        icon = R.drawable.ic_category_atm
    )
    object AutonomousCar:CategoryIconModel(
        iconName = "Autonomous",
        icon = R.drawable.ic_category_autonomous_car
    )
    object Bank:CategoryIconModel(
        iconName = "Bank",
        icon = R.drawable.ic_category_bank
    )
    object Cat:CategoryIconModel(
        iconName = "Cat",
        icon = R.drawable.ic_category_cat
    )
    object Coins:CategoryIconModel(
        iconName = "Coins",
        icon = R.drawable.ic_category_coins
    )
    object CreditCard:CategoryIconModel(
        iconName = "CreditCard",
        icon = R.drawable.ic_category_credit_card
    )
    object Cutlery:CategoryIconModel(
        iconName = "Cutlery",
        icon = R.drawable.ic_category_cutlery
    )
    object Gift:CategoryIconModel(
        iconName = "Gift",
        icon = R.drawable.ic_category_gift
    )
    object Group:CategoryIconModel(
        iconName = "Group",
        icon = R.drawable.ic_category_group
    )
    object Headphones:CategoryIconModel(
        iconName = "Headphones",
        icon = R.drawable.ic_category_headphones
    )
    object Hotel:CategoryIconModel(
        iconName = "Hotel",
        icon = R.drawable.ic_category_hotel
    )
    object Invoice:CategoryIconModel(
        iconName = "Invoice",
        icon = R.drawable.ic_category_invoice
    )
    object Language:CategoryIconModel(
        iconName = "Language",
        icon = R.drawable.ic_category_language
    )
    object Medicine:CategoryIconModel(
        iconName = "Medicine",
        icon = R.drawable.ic_category_medicine
    )
    object Money:CategoryIconModel(
        iconName = "Money",
        icon = R.drawable.ic_category_money
    )
    object Mortgage:CategoryIconModel(
        iconName = "Mortgage",
        icon = R.drawable.ic_category_mortgage
    )
    object Notebook:CategoryIconModel(
        iconName = "Notebook",
        icon = R.drawable.ic_category_notebook
    )
    object OpenBook:CategoryIconModel(
        iconName = "OpenBook",
        icon = R.drawable.ic_category_open_book
    )
    object Philosophy:CategoryIconModel(
        iconName = "Philosophy",
        icon = R.drawable.ic_category_philosophy
    )
    object PiggyBank:CategoryIconModel(
        iconName = "PiggyBank",
        icon = R.drawable.ic_category_piggy_bank
    )
    object Psychology:CategoryIconModel(
        iconName = "Psychology",
        icon = R.drawable.ic_category_psychology
    )
    object School:CategoryIconModel(
        iconName = "School",
        icon = R.drawable.ic_category_school
    )
    object ShoppingBasket:CategoryIconModel(
        iconName = "ShoppingBasket",
        icon = R.drawable.ic_category_shopping_basket
    )
    object ShoppingBag:CategoryIconModel(
        iconName = "ShoppingBag",
        icon = R.drawable.ic_category_shopping_bag
    )
    object Target:CategoryIconModel(
        iconName = "Target",
        icon = R.drawable.ic_category_target
    )
    object Toolbox:CategoryIconModel(
        iconName = "Toolbox",
        icon = R.drawable.ic_category_toolbox
    )
    object Van:CategoryIconModel(
        iconName = "Van",
        icon = R.drawable.ic_category_van
    )
}
