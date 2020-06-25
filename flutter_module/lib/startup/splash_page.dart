import 'package:account_statement/core/error/failures.dart';
import 'package:account_statement/core/injection.dart';
import 'package:account_statement/core/nav.dart';
import 'package:fluro/fluro.dart';
import 'package:flutter/material.dart';

class SplashPage extends StatefulWidget {
  SplashPage({Key key}) : super(key: key);

  @override
  _SplashPageState createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> {
  Future<void> _init() async {
    final failure = await _setup();
    if (failure == null) {
      locator<Nav>().router.navigateTo(context, 'ChartPage',
          clearStack: true, transition: TransitionType.fadeIn);
    } else {
      locator<Nav>().exit();
    }
  }

  Future<Failure> _setup() async {
    // setting up app...
    return null;
  }

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

  @override
  Widget build(BuildContext context) {
    print('#### _SplashPageState - build');
    return Container();
  }
}
