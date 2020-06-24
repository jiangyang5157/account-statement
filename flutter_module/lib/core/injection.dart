import 'package:account_statement/startup/startup_view_model.dart';
import 'package:account_statement/theme/data/repositories/theme_repository_impl.dart';
import 'package:account_statement/theme/data/sources/theme_local_data_source.dart';
import 'package:account_statement/theme/domain/repositories/theme_repository.dart';
import 'package:account_statement/theme/presentation/theme_view_model.dart';
import 'package:account_statement/theme/domain/usecases/usecases.dart' as Theme;
import 'package:get_it/get_it.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'nav.dart';

GetIt locator = GetIt.instance;

Future<void> init() async {
  /// Storage
  final prefs = await SharedPreferences.getInstance();
  locator.registerLazySingleton(() => prefs);

  /// Navigation
  locator.registerLazySingleton<Nav>(() => Nav());

  /// Data sources
  locator.registerLazySingleton<ThemeLocalDataSource>(
    () => ThemeLocalDataSourceImpl(prefs: locator()),
  );

  /// Repositories
  locator.registerLazySingleton<ThemeRepository>(
    () => ThemeRepositoryImpl(localDataSource: locator()),
  );

  /// View models
  locator.registerFactory(() => ThemeViewModel(
        glt: locator(),
        st: locator(),
      ));
  locator.registerFactory(() => StartupViewModel());

  /// Use cases
  locator.registerFactory(() => Theme.GetLastTheme(locator()));
  locator.registerFactory(() => Theme.SetTheme(locator()));
}
