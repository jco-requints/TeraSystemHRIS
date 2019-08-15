package com.example.terasystemhris

class MyCredentials (var empID: String,
                     var userID: String,
                     var password: String,
                     var firstname: String,
                     var middlename: String,
                     var lastname: String,
                     var email: String,
                     var mobile: String,
                     var landline: String)

val jericho: MyCredentials = MyCredentials("2019015",
    "jericho",
    "hello",
    "Jericho Isaac",
    "Requintina",
    "Magallanes",
    "jericho.magallanes@terasystem.com",
    "+63 917 655 4356",
    "+63 2 741 1356")

val jameena: MyCredentials = MyCredentials("2019027",
    "jameena",
    "123",
    "Jameena Ilaah",
    "Requintina",
    "Magallanes",
    "jameena.magallanes@terasystem.com",
    "+63 917 876 3241",
    "+63 34 748 1723")

val list: ArrayList<MyCredentials> = ArrayList<MyCredentials>().apply {
    add(jericho)
    add(jameena)
}
