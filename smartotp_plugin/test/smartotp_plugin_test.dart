import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:smartotp_plugin/smartotp_plugin.dart';

void main() {
  const MethodChannel channel = MethodChannel('smartotp_plugin');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await SmartotpPlugin.platformVersion, '42');
  });
}
