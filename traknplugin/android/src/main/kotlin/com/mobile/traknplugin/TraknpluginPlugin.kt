package com.mobile.traknplugin

import android.app.Activity
import android.content.Context
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import org.json.JSONException
import org.json.JSONObject
import com.mobile.pgv1.PGConstants
import com.mobile.pgv1.PaymentGatewayInitializer
import com.mobile.pgv1.PaymentParams
import com.mobile.pgv1.ResponseCallbackListener


/** TraknpluginPlugin */
class TraknpluginPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private lateinit var context: Context
    private lateinit var activity: Activity

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "traknplugin")
        channel.setMethodCallHandler(this)
        context = flutterPluginBinding.applicationContext
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "traknPayPlugin") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onDetachedFromActivity() {
        TODO("Not yet implemented")
    }


    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        TODO("Not yet implemented")
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity as FlutterActivity
        channel.setMethodCallHandler { call, result ->
            if (call.method.equals("traknPayPlugin")) {
                val pgPaymentParams = PaymentParams(call.arguments())

                val pgPaymentInitialzer = PaymentGatewayInitializer(SampleAppConstants.PG_PAYMENT_URL, pgPaymentParams, activity)
                pgPaymentInitialzer.initiatePaymentProcess { paymentResponse ->
                    try {
                        println("paymentResponse: $paymentResponse")
                        if (paymentResponse == "null") {
                            result.success("Transaction Status: Transaction Error!")
                        } else {
                            val response = JSONObject(paymentResponse)
                            result.success(response.getString("transaction_id"))
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        result.success("Transaction Status: Transaction Error!")

                    }
                }
            } else {
                result.notImplemented()
            }
        }
    }

    override fun onDetachedFromActivityForConfigChanges() {
        TODO("Not yet implemented")
    }
}
