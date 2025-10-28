import 'package:account_statement/chart/data/transaction_model.dart';
import 'package:account_statement/chart/domain/transaction_repository.dart';
import 'package:account_statement/chart/presentation/cubit/chart_state.dart';
import 'package:bloc/bloc.dart';
import 'package:fl_chart/fl_chart.dart';

class ChartCubit extends Cubit<ChartState> {
  final TransactionRepository _transactionRepository;

  ChartCubit(this._transactionRepository) : super(ChartInitial());

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
