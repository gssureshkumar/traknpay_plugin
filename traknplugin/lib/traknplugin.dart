
import 'dart:async';

import 'package:flutter/services.dart';

class Traknplugin {
  static const MethodChannel _channel =
      const MethodChannel('traknplugin');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('traknPayPlugin',{"amount":"100"});
    return version;
  }
}
