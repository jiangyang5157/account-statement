import 'package:account_statement/core/error/failures.dart';
import 'package:flutter/material.dart';

class StartupViewModel extends ChangeNotifier {
  StartupViewModel() {
    print('#### StartupViewModel - constructor');
  }

  @override
  void dispose() {
    super.dispose();
    print('#### StartupViewModel - dispose');
  }

  Future<Failure> setup() async {
    return null;
  }
}
