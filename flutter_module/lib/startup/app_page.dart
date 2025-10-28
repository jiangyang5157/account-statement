import 'package:account_statement/core/injection.dart';
import 'package:account_statement/core/nav.dart';
import 'package:account_statement/startup/splash_page.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

class AppPage extends StatefulWidget {
  const AppPage({Key? key}) : super(key: key);

  @override
  _AppPageState createState() => _AppPageState();
}

class _AppPageState extends State<AppPage> {
  @override
  void dispose() {
    super.dispose();
    print('#### _AppPageState - dispose');
  }

  @override
  void initState() {
    super.initState();
    print('#### _AppPageState - initState');
  }

  @override
  Widget build(BuildContext context) {
    print('#### _AppPageState - build');

    return MaterialApp(
      localizationsDelegates: AppLocalizations.localizationsDelegates,
      supportedLocales: AppLocalizations.supportedLocales,
      home: SplashPage(),
      onGenerateRoute: locator<Nav>().router.generator,
    );
  }
}
