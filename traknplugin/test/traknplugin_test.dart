import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:traknplugin/traknplugin.dart';

void main() {
  const MethodChannel channel = MethodChannel('traknplugin');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('traknPayPlugin', () async {
    expect(await Traknplugin.platformVersion, '42');
  });
}
