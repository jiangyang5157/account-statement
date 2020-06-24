import 'package:account_statement/startup/splash_page.dart';
import 'package:fluro/fluro.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../main.dart';

class Nav {
  Router _router;

  Router get router => _router;

  Nav() {
    _router = Router()
      ..define('SplashPage', handler: Handler(
          handlerFunc: (BuildContext context, Map<String, dynamic> params) {
        return SplashPage();
      }))
      ..define('MyApp', handler: Handler(
          handlerFunc: (BuildContext context, Map<String, dynamic> params) {
        return MyHomePage();
      }));
  }

  void exit() {
    SystemChannels.platform.invokeMethod('SystemNavigator.pop');
  }
}
