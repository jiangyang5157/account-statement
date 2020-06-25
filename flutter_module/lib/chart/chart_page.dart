import 'dart:convert';

import 'package:account_statement/chart/simple_time_series_chart.dart';
import 'package:account_statement/chart/transaction_model.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class ChartPage extends StatefulWidget {
  ChartPage({Key key}) : super(key: key);

  @override
  _ChartPageState createState() => _ChartPageState();
}

class _ChartPageState extends State<ChartPage> {
  static const methodChannel = const MethodChannel(
      'com.gmail.jiangyang5157.account_statement/MethodChannel');

  List<TransactionModel> _transactions;

  Future<void> _initTransactions() async {
    try {
      final String result =
          await methodChannel.invokeMethod('transactionsChannel');
      setState(() {
        _transactions = jsonDecode(result);
      });
    } on MissingPluginException catch (e) {
      print("#### _initCounter MissingPluginException $e");
    } on PlatformException catch (e) {
      print("#### _initCounter PlatformException $e");
    }

    TransactionModel transaction = jsonDecode('''"a"={"accountName":"asdasd","date":"Jun 2, 2020 12:00:00 AM","description":"Payment, Amp General Ins","id":402,"money":-1224.18}''');
    print('#####, Decoded transaction: $transaction');
    print('#####, Encode transaction: ${jsonEncode(transaction)}');

//    _transactions = jsonDecode(
//        '''[{"accountName":"asdasd","date":"Jun 2, 2020 12:00:00 AM","description":"Payment, Amp General Ins","id":402,"money":-1224.18},{"accountName":"asdasd","date":"Jun 2, 2020 12:00:00 AM","description":"Payment, Amp General Ins","id":418,"money":-1224.18},{"accountName":"asdasd","date":"Jun 2, 2020 12:00:00 AM","description":"Payment, Amp General Ins","id":434,"money":-1224.18}]''');
//    print('#####, jsonDecode [0]: ${_transactions[0]}');
//    print('#####, jsonEncode [0]: ${jsonEncode(_transactions[0])}');
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
        child: SimpleTimeSeriesChart.withSampleData(),
      ),
    );
  }
}
