import 'dart:async';

import 'package:flutter/services.dart';

class SmartotpPlugin {
  static const MethodChannel _channel =
      const MethodChannel('smartotp_plugin');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> getSmartOtp(String secretKey , int type) async {
    String smartOtp;
    try{
      smartOtp = await _channel.invokeMethod('getSmartOtp' , <String , dynamic> {
        'secretKey' : secretKey,
        'type': type
      });
    }catch(e){
      print(e);
      smartOtp = '123456';
    }
    return smartOtp;
  }
}
