import 'package:account_statement/core/error/failures.dart';
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

class SplashPage extends StatefulWidget {
  const SplashPage({Key? key}) : super(key: key);

  @override
  _SplashPageState createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> {
  Future<void> _init() async {
    final failure = await _setup();
    if (failure == null) {
      context.go('/chart');
    } else {
      // Handle exit gracefully
    }
  }

  Future<Failure?> _setup() async {
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
    return const Scaffold(
      body: Center(
        child: CircularProgressIndicator(),
      ),
    );
  }
}
