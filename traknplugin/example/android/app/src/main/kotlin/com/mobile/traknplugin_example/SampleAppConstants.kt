package com.mobile.traknplugin_example


object SampleAppConstants {
    //URL to Accept Payment Response Afdashboardter Payment. This needs to be done at the client's web server.
    const val PG_PAYMENT_URL = "https://pgbizbatchsync.omniware.in/"
    //API_KEY is given by the Payment Gateway. Please Copy Paste Here.
    const val PG_API_KEY = "08490a7f-3ffc-44d5-b14e-c7509d6cc7b2"
    //URL to Accept Payment Response Afdashboardter Payment. This needs to be done at the client's web server.
    const val PG_RETURN_URL = "http://localhost:8888/paymentresponse"
    //Enter the Mode of Payment Here . Allowed Values are "LIVE" or "TEST".
    const val PG_MODE = "LIVE"
    //PG_CURRENCY is given by the Payment Gateway. Only "INR" Allowed.
    const val PG_CURRENCY = "INR"
    //PG_COUNTRY is given by the Payment Gateway. Only "IND" Allowed.
    const val PG_COUNTRY = "IND"
    const val PG_AMOUNT = "2"
    const val PG_EMAIL = "emailsenthil@test.com"
    const val PG_NAME = "Senthil"
    const val PG_PHONE = "9597403366"
    var PG_ORDER_ID = "TEST900"
    const val PG_DESCRIPTION = "Test"
    const val PG_CITY = "Chennai"
    const val PG_STATE = "Tamilnadu"
    const val PG_ADD_1 = "ad1"
    const val PG_ADD_2 = "ad2"
    const val PG_ZIPCODE = "630501"
    const val PG_HASH = "AC8783EC41D97380D510258055FD393F78B7963E133DCCC02B7415EFF38482442EAD923944C5FF8BAD7DDA90C90D6879E51298E6760FFD269CF6739625605F97"
    const val PG_UDF1 = ""
    const val PG_UDF2 = ""
    const val PG_UDF3 = ""
    const val PG_UDF4 = ""
    const val PG_UDF5 = ""
}