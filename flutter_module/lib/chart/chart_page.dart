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

  List<TransactionModel> _transactions = [];

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
        '''[{"accountName":"asdasd","date":"Jun 2, 2020 12:00:00 AM","description":"Payment, Amp General Ins","id":402,"money":-1224.18},{"accountName":"asdasd","date":"Jun 2, 2020 12:00:00 AM","description":"Payment, Amp General Ins","id":418,"money":-1224.18},{"accountName":"asdasd","date":"Jun 2, 2020 12:00:00 AM","description":"Payment, Amp General Ins","id":434,"money":-1224.18}]''';
    if (data != null) {
      setState(() {
        _transactions.clear();
        List<dynamic> transactionItems = jsonDecode(data);
        transactionItems.forEach((element) {
          _transactions.add(TransactionModel.fromJson(element));
        });
      });
      print('#### _transactions:\n${_transactions[0].toJson()}');
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
        child: SimpleTimeSeriesChart.withSampleData(),
      ),
    );
  }
}
