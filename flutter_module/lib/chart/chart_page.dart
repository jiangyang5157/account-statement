import 'dart:convert';

import 'package:account_statement/chart/transaction_model.dart';
import 'package:account_statement/core/string_localization.dart';
import 'package:fl_chart/fl_chart.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class ChartPage extends StatefulWidget {
  const ChartPage({Key? key}) : super(key: key);

  @override
  _ChartPageState createState() => _ChartPageState();
}

class _ChartPageState extends State<ChartPage> {
  static const methodChannel =
      MethodChannel('com.gmail.jiangyang5157.account_statement/MethodChannel');

  List<FlSpot> _spots = [];

  Future<void> _init() async {
    String? data;
    try {
      final String? result =
          await methodChannel.invokeMethod('transactionsChannel');
      data = result;
    } on MissingPluginException catch (e) {
      print("#### _initTransactions MissingPluginException $e");
    } on PlatformException catch (e) {
      print("#### _initTransactions PlatformException $e");
    } on Exception catch (e) {
      print("#### _initTransactions Exception $e");
    }

    if (data != null) {
      setState(() {
        List<TransactionModel> transactions = [];
        List<dynamic> transactionJsonList = jsonDecode(data!);
        for (var element in transactionJsonList) {
          transactions.add(TransactionModel.fromJson(element));
        }
        transactions.sort((a, b) => a.date.compareTo(b.date));

        List<MoneySeries> moneySeriesData = [];
        for (int i = 0; i < transactions.length; i++) {
          TransactionModel t = transactions[i];
          if (i == 0) {
            moneySeriesData.add(MoneySeries(t.date, t.money));
          } else {
            moneySeriesData.add(
                MoneySeries(t.date, moneySeriesData[i - 1].sum + t.money));
          }
        }

        _spots = moneySeriesData
            .map((series) => FlSpot(
                series.date.millisecondsSinceEpoch.toDouble(), series.sum))
            .toList();
      });
    }
  }

  @override
  void dispose() {
    super.dispose();
    print('#### _ChartPageState - dispose');
  }

  @override
  void initState() {
    super.initState();
    print('#### _ChartPageState - initState');
    _init();
  }

  @override
  Widget build(BuildContext context) {
    print('#### _ChartPageState - build');
    return Scaffold(
      appBar: AppBar(
        title: Text(string(context, 'title_chart')),
      ),
      body: Center(
        child: _buildChart(),
      ),
    );
  }

  Widget _buildChart() {
    if (_spots.isNotEmpty) {
      return LineChart(
        LineChartData(
          lineBarsData: [
            LineChartBarData(
              spots: _spots,
              isCurved: true,
              barWidth: 2.5,
              belowBarData: BarAreaData(show: false),
            ),
          ],
        ),
      );
    } else {
      return Container();
    }
  }
}

class MoneySeries {
  final DateTime date;
  final double sum;

  MoneySeries(this.date, this.sum);
}
