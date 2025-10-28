import 'package:account_statement/chart/domain/transaction_repository.dart';
import 'package:account_statement/chart/presentation/cubit/chart_cubit.dart';
import 'package:get_it/get_it.dart';

GetIt locator = GetIt.instance;

Future<void> init() async {
  // Chart
  locator.registerLazySingleton<TransactionRepository>(
      () => TransactionRepositoryImpl());
  locator.registerFactory<ChartCubit>(() => ChartCubit(locator()));
}
