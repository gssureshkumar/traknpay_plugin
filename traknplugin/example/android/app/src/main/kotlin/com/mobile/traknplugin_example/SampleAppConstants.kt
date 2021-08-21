package com.mobile.traknplugin_example


object SampleAppConstants {
    //API_KEY is given by the Payment Gateway. Please Copy Paste Here.
    const val PG_API_KEY = "08490a7f-3ffc-44d5-b14e-c7509d6cc7b2"

    //URL to Accept Payment Response Afdashboardter Payment. This needs to be done at the client's web server.
    const val PG_RETURN_URL = " https://biz.traknpay.in/v2/xdikfgldqfqryjlnjrmy/Z"

    //Enter the Mode of Payment Here . Allowed Values are "LIVE" or "TEST".
    const val PG_MODE = "TEST"

    //PG_CURRENCY is given by the Payment Gateway. Only "INR" Allowed.
    const val PG_CURRENCY = "INR"

    //PG_COUNTRY is given by the Payment Gateway. Only "IND" Allowed.
    const val PG_COUNTRY = "IND"
    const val PG_AMOUNT = "2"
    const val PG_EMAIL = "flutter@gmail.com"
    const val PG_NAME = "flutter"
    const val PG_PHONE = "9876543210"
    var PG_ORDER_ID = ""
    const val PG_DESCRIPTION = "text flutter"
    const val PG_CITY = "flutter"
    const val PG_STATE = "Coimbatore"
    const val PG_ADD_1 = "Flutter Coimbatore"
    const val PG_ADD_2 = "Coimbatore"
    const val PG_ZIPCODE = "641001"
    const val PG_UDF1 = ""
    const val PG_UDF2 = ""
    const val PG_UDF3 = ""
    const val PG_UDF4 = ""
    const val PG_UDF5 = ""
}