import 'dart:convert';

import 'package:account_statement/chart/transaction_model.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:charts_flutter/flutter.dart' as charts;

class ChartPage extends StatefulWidget {
  ChartPage({Key key}) : super(key: key);

  @override
  _ChartPageState createState() => _ChartPageState();
}

class _ChartPageState extends State<ChartPage> {
  static const methodChannel = const MethodChannel(
      'com.gmail.jiangyang5157.account_statement/MethodChannel');

  List<charts.Series<TestSeries, DateTime>> _transactionSeries = [];

  Future<void> _initTransactions() async {
    String data;
    try {
      final String result =
          await methodChannel.invokeMethod('transactionsChannel');
      data = result;
    } on MissingPluginException catch (e) {
      print("#### _initTransactions MissingPluginException $e");
    } on PlatformException catch (e) {
      print("#### _initTransactions PlatformException $e");
    } on Exception catch (e) {
      print("#### _initTransactions Exception $e");
    }

    // TODO remove debug code
    data =
        '''[{"accountName":"asdasd","date":"25/06/2020","description":"Payment, Amp General Ins","id":402,"money":-1224.18},{"accountName":"asdasd","date":"24/06/2020","description":"Payment, Amp General Ins","id":418,"money":-1224.18},{"accountName":"asdasd","date":"26/06/2020","description":"Payment, Amp General Ins","id":434,"money":-1224.18}]''';
    if (data != null) {
      setState(() {
        List<TransactionModel> _transactions = [];

        List<dynamic> transactionItems = jsonDecode(data);
        transactionItems.forEach((element) {
          _transactions.add(TransactionModel.fromJson(element));
        });
        _transactions.sort((a, b) => a.date.compareTo(b.date));

//        _transactions.forEach((transaction) {});
        print('####, _transactions size: ${_transactions.length}');
        print('####, _transactions 0: ${_transactions[0].toJson()}');

        final test = [
          new TestSeries(new DateTime(2017, 9, 19), 5),
          new TestSeries(new DateTime(2017, 9, 26), 25),
          new TestSeries(new DateTime(2017, 10, 3), 100),
          new TestSeries(new DateTime(2017, 10, 10), 75),
        ];

        _transactionSeries.clear();
        _transactionSeries.add(new charts.Series<TestSeries, DateTime>(
          id: 'Transactions',
          colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
          domainFn: (TestSeries transaction, _) => transaction.date,
          measureFn: (TestSeries transaction, _) => transaction.money,
          data: test,
        ));
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
    _initTransactions();
  }

  @override
  Widget build(BuildContext context) {
    print('#### _ChartPageState - build');
    return Scaffold(
      appBar: AppBar(
        title: Text('Chart'),
      ),
      body: Center(
        child: _buildChart(),
      ),
    );
  }

  Widget _buildChart() {
    if (_transactionSeries.isNotEmpty) {
      return charts.TimeSeriesChart(_transactionSeries,
          animate: true, dateTimeFactory: const charts.LocalDateTimeFactory());
    } else {
      return Container();
    }
  }
}

class TestSeries {
  final DateTime date;
  final int money;

  TestSeries(this.date, this.money);
}
