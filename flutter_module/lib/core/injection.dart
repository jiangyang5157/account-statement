import 'package:account_statement/chart/domain/transaction_repository.dart';
import 'package:account_statement/chart/presentation/cubit/chart_cubit.dart';
import 'package:get_it/get_it.dart';

/// The service locator for the application.
///
/// This uses the `get_it` package to register and retrieve dependencies.
GetIt locator = GetIt.instance;

/// Initializes the service locator with the required dependencies.
Future<void> init() async {
  // Chart
  locator.registerLazySingleton<TransactionRepository>(
      () => TransactionRepositoryImpl());
  locator.registerFactory<ChartCubit>(() => ChartCubit(locator()));
}
