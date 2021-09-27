import 'dart:async';

import 'package:flutter/services.dart';

class Traknplugin {
  static const MethodChannel _channel = const MethodChannel('traknplugin');

  static Future<String> initPay(String request) async {
    final String version =
        await _channel.invokeMethod('traknPayPlugin',  request);
    return version;
  }
}
