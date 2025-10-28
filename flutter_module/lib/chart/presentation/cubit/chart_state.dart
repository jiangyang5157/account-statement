import 'package:equatable/equatable.dart';
import 'package:fl_chart/fl_chart.dart';

/// The base class for all states of the chart feature.
abstract class ChartState extends Equatable {
  const ChartState();

  @override
  List<Object> get props => [];
}

/// The initial state of the chart feature.
class ChartInitial extends ChartState {}

/// The state indicating that the chart data is being loaded.
class ChartLoading extends ChartState {}

/// The state indicating that the chart data has been successfully loaded.
class ChartLoaded extends ChartState {
  /// The data points for the chart.
  final List<FlSpot> spots;

  /// Creates a new [ChartLoaded] state.
  const ChartLoaded(this.spots);

  @override
  List<Object> get props => [spots];
}

/// The state indicating that an error occurred while loading the chart data.
class ChartError extends ChartState {
  /// The error message.
  final String message;

  /// Creates a new [ChartError] state.
  const ChartError(this.message);

  @override
  List<Object> get props => [message];
}
