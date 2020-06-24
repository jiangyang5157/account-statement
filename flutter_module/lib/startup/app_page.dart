import 'package:account_statement/core/injection.dart';
import 'package:account_statement/core/nav.dart';
import 'package:account_statement/core/string_localization.dart';
import 'package:account_statement/startup/splash_page.dart';
import 'package:account_statement/theme/presentation/theme_view_model.dart';
import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:provider/provider.dart';

class AppPage extends StatefulWidget {
  AppPage({Key key}) : super(key: key);

  @override
  _AppPageState createState() => _AppPageState();
}

class _AppPageState extends State<AppPage> {
  ThemeViewModel themeViewModel = locator<ThemeViewModel>();

  @override
  void dispose() {
    themeViewModel.dispose();
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

    return MultiProvider(
      providers: [
        ChangeNotifierProvider<ThemeViewModel>(create: (_) => themeViewModel),
      ],
      child: Consumer<ThemeViewModel>(
        builder: (context, themeViewModel, _) {
          return MaterialApp(
            localizationsDelegates: [
              const StringDelegate(),
              GlobalMaterialLocalizations.delegate,
              GlobalWidgetsLocalizations.delegate,
            ],
            supportedLocales: StringDelegate.supportedLanguageCodes
                .map<Locale>((languageCode) => Locale(languageCode)),
            home: SplashPage(),
            onGenerateRoute: locator<Nav>().router.generator,
            theme: themeViewModel.getLastTheme().toThemeData(context),
          );
        },
      ),
    );
  }
}
