import 'package:get_it/get_it.dart';

import 'nav.dart';

GetIt locator = GetIt.instance;

Future<void> init() async {
  /// Navigation
  locator.registerLazySingleton<Nav>(() => Nav());
}
