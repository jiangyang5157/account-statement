import 'package:account_statement/chart/data/transaction_model.dart';
import 'package:account_statement/chart/domain/transaction_repository.dart';
import 'package:account_statement/chart/presentation/cubit/chart_state.dart';
import 'package:bloc/bloc.dart';
import 'package:fl_chart/fl_chart.dart';

/// Manages the state of the [ChartPage].
///
/// This cubit is responsible for fetching the transaction data, processing it
/// for the chart, and emitting the appropriate state to the UI.
class ChartCubit extends Cubit<ChartState> {
  final TransactionRepository _transactionRepository;

  /// Creates a new [ChartCubit].
  ChartCubit(this._transactionRepository) : super(ChartInitial());

  /// Fetches the transactions and updates the state.
  ///
  /// Emits [ChartLoading] while the data is being fetched, [ChartLoaded] on
  /// success, and [ChartError] on failure.
  void getTransactions() async {
    emit(ChartLoading());
    try {
      final transactions = await _transactionRepository.getTransactions();
      final spots = _calculateSpots(transactions);
      emit(ChartLoaded(spots));
    } catch (e) {
      emit(ChartError(e.toString()));
    }
  }

  /// Calculates the cumulative sum of transactions and converts them to [FlSpot]s
  /// for the chart.
  List<FlSpot> _calculateSpots(List<TransactionModel> transactions) {
    if (transactions.isEmpty) {
      return [];
    }

    double cumulativeSum = 0;
    List<FlSpot> spots = [];

    for (var transaction in transactions) {
      cumulativeSum += transaction.money;
      spots.add(FlSpot(
          transaction.date.millisecondsSinceEpoch.toDouble(), cumulativeSum));
    }
    return spots;
  }
}
