package com.andyyeh.examE.mainActivity.model

/**
 * @param id the "since" id
 * @param imgUrl the user avatar
 * **/
data class UserBean(var id: Int,
                    var imgUrl: String,
                    var userName: String,
                    var isStuff: Boolean)