package com.vasist.userlist122.JsonData

import java.io.Serializable

data class JsonData(
    val results: ArrayList<UserList>
) : Serializable
data class UserList(
    val email: String,
    val gender: String,
    val id: Id,
    val login: Login,
    val location: Location,
    val name: Name,
    val phone: String,
    val picture: Picture,
): Serializable
data class Id(
    val name: String,
    val value: String
): Serializable
data class Location(
    val city: String,
    val country: String,
    val postcode: String,
    val state: String,
    val street: Street,
): Serializable
data class Login(
    val md5: String,
    val password: String,
    val salt: String,
    val sha1: String,
    val sha256: String,
    val username: String,
    val uuid: String
): Serializable
data class Name(
    val first: String,
    val last: String,
    val title: String
): Serializable
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
): Serializable

data class Street(
    val name: String,
    val number: String
): Serializable