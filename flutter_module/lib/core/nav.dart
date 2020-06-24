import 'package:account_statement/startup/splash_page.dart';
import 'package:fluro/fluro.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../test_page.dart';

class Nav {
  Router _router;

  Router get router => _router;

  Nav() {
    _router = Router()
      ..define('SplashPage', handler: Handler(
          handlerFunc: (BuildContext context, Map<String, dynamic> params) {
        return SplashPage();
      }))
      ..define('TestPage', handler: Handler(
          handlerFunc: (BuildContext context, Map<String, dynamic> params) {
        return TestPage();
      }));
  }

  void exit() {
    SystemChannels.platform.invokeMethod('SystemNavigator.pop');
  }
}
