import 'package:account_statement/core/injection.dart';
import 'package:account_statement/core/string_localization.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:fl_chart/fl_chart.dart';

import 'cubit/chart_cubit.dart';
import 'cubit/chart_state.dart';

class ChartPage extends StatelessWidget {
  const ChartPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (_) => locator<ChartCubit>()..getTransactions(),
      child: Scaffold(
        appBar: AppBar(
          title: Text(string(context, 'title_chart')),
        ),
        body: Center(
          child: BlocBuilder<ChartCubit, ChartState>(
            builder: (context, state) {
              if (state is ChartLoading) {
                return const CircularProgressIndicator();
              } else if (state is ChartLoaded) {
                return LineChart(
                  LineChartData(
                    lineBarsData: [
                      LineChartBarData(
                        spots: state.spots,
                        isCurved: true,
                        barWidth: 2.5,
                        belowBarData: BarAreaData(show: false),
                      ),
                    ],
                  ),
                );
              } else if (state is ChartError) {
                return Text(state.message);
              } else {
                return Container();
              }
            },
          ),
        ),
      ),
    );
  }
}
