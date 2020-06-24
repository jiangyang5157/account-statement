import 'package:account_statement/core/injection.dart';
import 'package:account_statement/core/nav.dart';
import 'package:account_statement/startup/startup_view_model.dart';
import 'package:fluro/fluro.dart';
import 'package:flutter/material.dart';

class SplashPage extends StatefulWidget {
  SplashPage({Key key}) : super(key: key);

  @override
  _SplashPageState createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> {
  @override
  void dispose() {
    super.dispose();
    print('#### _SplashPageState - dispose');
  }

  @override
  void initState() {
    super.initState();
    print('#### _SplashPageState - initState');
    _init();
  }

  Future<void> _init() async {
    final failure = await locator<StartupViewModel>().setup();
    if (failure == null) {
      locator<Nav>().router.navigateTo(context, 'TestPage',
          clearStack: true, transition: TransitionType.fadeIn);
    } else {
      locator<Nav>().exit();
    }
  }

  @override
  Widget build(BuildContext context) {
    print('#### _SplashPageState - build');
    return Container();
  }
}
