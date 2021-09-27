package com.mobile.traknplugin_example

import android.os.Bundle
import android.util.Log
import com.mobile.pgv1.PaymentGatewayInitializer
import com.mobile.pgv1.PaymentParams
import com.mobile.pgv1.ResponseCallbackListener
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
                val pgPaymentParams = PaymentParams(call.arguments())

                val pgPaymentInitialzer = PaymentGatewayInitializer(SampleAppConstants.PG_PAYMENT_URL, pgPaymentParams, this@MainActivity)
                pgPaymentInitialzer.initiatePaymentProcess { paymentResponse ->
                    try {
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
                }
            } else {
                result.notImplemented()
            }
        }
    }


}