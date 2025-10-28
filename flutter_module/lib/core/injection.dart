import 'package:account_statement/chart/domain/transaction_repository.dart';
import 'package:account_statement/chart/presentation/cubit/chart_cubit.dart';
import 'package:get_it/get_it.dart';

import 'nav.dart';

GetIt locator = GetIt.instance;

Future<void> init() async {
  // Navigation
  locator.registerLazySingleton<Nav>(() => Nav());

  // Chart
  locator.registerLazySingleton<TransactionRepository>(
      () => TransactionRepositoryImpl());
  locator.registerFactory<ChartCubit>(() => ChartCubit(locator()));
}
