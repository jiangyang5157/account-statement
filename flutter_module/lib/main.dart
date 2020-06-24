import 'package:account_statement/startup/app_page.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:provider/provider.dart';
import 'core/injection.dart' as di;

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  debugPaintSizeEnabled = true;
  Provider.debugCheckInvalidValueType = null;

  await di.init();
  runApp(AppPage());
}
