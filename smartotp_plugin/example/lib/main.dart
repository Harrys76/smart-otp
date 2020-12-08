import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:smartotp_plugin/smartotp_plugin.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}


class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  String _secretKey = 'D6DWFQ236VLOQSQE';
  int type = 0; //0 with TOTP , 1 with HOTP
  String _smartOtp = '123456';


  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    String smartOtp;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await SmartotpPlugin.platformVersion;
      smartOtp = await SmartotpPlugin.getSmartOtp(_secretKey , type);
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
      _smartOtp = smartOtp;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          children: [
            Center(
              child: Text('Running on: $_platformVersion\n'),
            ),
            SizedBox(height: 30,),
            Center(
              child: Text('Smart Otp: $_smartOtp\n'),
            )
          ],
        ),
      ),
    );
  }
}
