import 'package:equatable/equatable.dart';
import 'package:fl_chart/fl_chart.dart';

abstract class ChartState extends Equatable {
  const ChartState();

  @override
  List<Object> get props => [];
}

class ChartInitial extends ChartState {}

class ChartLoading extends ChartState {}

class ChartLoaded extends ChartState {
  final List<FlSpot> spots;

  const ChartLoaded(this.spots);

  @override
  List<Object> get props => [spots];
}

class ChartError extends ChartState {
  final String message;

  const ChartError(this.message);

  @override
  List<Object> get props => [message];
}
