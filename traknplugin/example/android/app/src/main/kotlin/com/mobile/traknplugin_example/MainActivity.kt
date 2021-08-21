package com.mobile.traknplugin_example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.test.pg.secure.pgsdkv4.PGConstants
import com.test.pg.secure.pgsdkv4.PaymentGatewayPaymentInitializer
import com.test.pg.secure.pgsdkv4.PaymentParams
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class MainActivity : FlutterActivity() {

    lateinit var passResult: MethodChannel.Result
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "traknplugin").setMethodCallHandler { call, result ->
            if (call.method.equals("traknPayPlugin")) {
                this.passResult = result
                val amount = call.argument<String>("amount")
                val rnd = Random()
                val n: Int = 100000 + rnd.nextInt(900000)
                SampleAppConstants.PG_ORDER_ID = "Test" + Integer.toString(n)

                val pgPaymentParams = PaymentParams()
                pgPaymentParams.setAPiKey(SampleAppConstants.PG_API_KEY)
                pgPaymentParams.setAmount(amount)
                pgPaymentParams.setEmail(SampleAppConstants.PG_EMAIL)
                pgPaymentParams.setName(SampleAppConstants.PG_NAME)
                pgPaymentParams.setPhone(SampleAppConstants.PG_PHONE)
                pgPaymentParams.setOrderId(SampleAppConstants.PG_ORDER_ID)
                pgPaymentParams.setCurrency(SampleAppConstants.PG_CURRENCY)
                pgPaymentParams.setDescription(SampleAppConstants.PG_DESCRIPTION)
                pgPaymentParams.setCity(SampleAppConstants.PG_CITY)
                pgPaymentParams.setState(SampleAppConstants.PG_STATE)
                pgPaymentParams.setAddressLine1(SampleAppConstants.PG_ADD_1)
                pgPaymentParams.setAddressLine2(SampleAppConstants.PG_ADD_2)
                pgPaymentParams.setZipCode(SampleAppConstants.PG_ZIPCODE)
                pgPaymentParams.setCountry(SampleAppConstants.PG_COUNTRY)
                pgPaymentParams.setReturnUrl(SampleAppConstants.PG_RETURN_URL)
                pgPaymentParams.setMode(SampleAppConstants.PG_MODE)
                pgPaymentParams.setUdf1(SampleAppConstants.PG_UDF1)
                pgPaymentParams.setUdf2(SampleAppConstants.PG_UDF2)
                pgPaymentParams.setUdf3(SampleAppConstants.PG_UDF3)
                pgPaymentParams.setUdf4(SampleAppConstants.PG_UDF4)
                pgPaymentParams.setUdf5(SampleAppConstants.PG_UDF5)
                pgPaymentParams.setEnableAutoRefund("n")
                pgPaymentParams.setOfferCode("testcoupon")
                //pgPaymentParams.setSplitInfo("{\"vendors\":[{\"vendor_code\":\"24VEN985\",\"split_amount_percentage\":\"20\"}]}");
                val pgPaymentInitialzer = PaymentGatewayPaymentInitializer(pgPaymentParams, this@MainActivity)
                pgPaymentInitialzer.initiatePaymentProcess()
//                passResult.success("success$amount")
            } else {
                result.notImplemented()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PGConstants.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && data !=null) {
                try {
                    val paymentResponse = data.getStringExtra(PGConstants.PAYMENT_RESPONSE)
                    println("paymentResponse: $paymentResponse")
                    if (paymentResponse == "null") {
                        passResult.success("Transaction Status: Transaction Error!")
                    } else {
                        val response = JSONObject(paymentResponse)
                        passResult.success(response.getString("transaction_id"))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    passResult.success("Transaction Status: Transaction Error!")

                }
            }else if (resultCode == Activity.RESULT_CANCELED) {
                passResult.success("Transaction Status: Transaction Error!")
            }
        }
    }
}