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

  List<charts.Series<TransactionModel, DateTime>> _transactionSeries = [];

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
    data ='''
        [{"accountName":"asdasd","date":"25/06/2020","description":"Payment, Amp General Ins","id":402,"money":-1224.18},{"accountName":"asdasd","date":"24/06/2020","description":"Payment, Amp General Ins","id":418,"money":-1224.18},{"accountName":"asdasd","date":"26/06/2020","description":"Payment, Amp General Ins","id":434,"money":-1224.18}]
        ''';
//    data = '''
//      [{"accountName":"asdasd","date":"01/05/2020","description":"\"EFTPOS\"","money":-16.71},{"accountName":"asdasd","date":"01/05/2020","description":"\"EFTPOS\"","money":-75.52},{"accountName":"asdasd","date":"01/05/2020","description":"\"CARD 8117 RBL HEAD OFF ICE LOAN TER AUCKLAND\"","money":-34.99},{"accountName":"asdasd","date":"02/05/2020","description":"\"\"","money":-3.5},{"accountName":"asdasd","date":"02/05/2020","description":"\"CARD 4298 PAK N SAVE S ILVERDALE SILVERDALE N\"","money":-41.75},{"accountName":"asdasd","date":"06/05/2020","description":"\"EFTPOS\"","money":-4.6},{"accountName":"asdasd","date":"06/05/2020","description":"\"EFTPOS\"","money":-12.68},{"accountName":"asdasd","date":"07/05/2020","description":"\"CARD 8117 TAI PING ALB ANY AUCKLAND\"","money":-53.0},{"accountName":"asdasd","date":"07/05/2020","description":"\"CARD 8117 TAI PING ALB ANY AUCKLAND\"","money":-2.4},{"accountName":"asdasd","date":"07/05/2020","description":"\"TO 12-3136- 0497064-00\"","money":-13.0},{"accountName":"asdasd","date":"08/05/2020","description":"\"EFTPOS\"","money":-9.0},{"accountName":"asdasd","date":"01/05/2020","description":"\"EFTPOS\"","money":-16.71},{"accountName":"asdasd","date":"01/05/2020","description":"\"EFTPOS\"","money":-75.52},{"accountName":"asdasd","date":"01/05/2020","description":"\"CARD 8117 RBL HEAD OFF ICE LOAN TER AUCKLAND\"","money":-34.99},{"accountName":"asdasd","date":"02/05/2020","description":"\"\"","money":-3.5},{"accountName":"asdasd","date":"02/05/2020","description":"\"CARD 4298 PAK N SAVE S ILVERDALE SILVERDALE N\"","money":-41.75},{"accountName":"asdasd","date":"06/05/2020","description":"\"EFTPOS\"","money":-4.6},{"accountName":"asdasd","date":"06/05/2020","description":"\"EFTPOS\"","money":-12.68},{"accountName":"asdasd","date":"07/05/2020","description":"\"CARD 8117 TAI PING ALB ANY AUCKLAND\"","money":-53.0},{"accountName":"asdasd","date":"07/05/2020","description":"\"CARD 8117 TAI PING ALB ANY AUCKLAND\"","money":-2.4},{"accountName":"asdasd","date":"07/05/2020","description":"\"TO 12-3136- 0497064-00\"","money":-13.0},{"accountName":"asdasd","date":"08/05/2020","description":"\"EFTPOS\"","money":-9.0},{"accountName":"asdasd","date":"07/06/2020","description":"Countdown Silverdale   Silverdale    Nzl","money":-71.43},{"accountName":"asdasd","date":"06/06/2020","description":"Hollywood Vape Limit   Auckland      Nz","money":-40.0},{"accountName":"asdasd","date":"05/06/2020","description":"Vodafone Prepay Visa M Auckland      Nz","money":-20.0},{"accountName":"asdasd","date":"03/06/2020","description":"Powershop              Wellington    Nz","money":-13.82},{"accountName":"asdasd","date":"02/06/2020","description":"Hollywood Bakery Silve Silverdale    Nzl","money":-10.5},{"accountName":"asdasd","date":"02/06/2020","description":"St Pierre\u0027S Silverdale Silverdale    Nzl","money":-31.9},{"accountName":"asdasd","date":"02/06/2020","description":"Vf Card Convenience    Auckland      Nz","money":-1.85},{"accountName":"asdasd","date":"02/06/2020","description":"Vodafone Fxdlne/Bband  Newton        Nz","money":-92.99},{"accountName":"asdasd","date":"01/06/2020","description":"Powershop              Wellington    Nz","money":-28.85},{"accountName":"asdasd","date":"31/05/2020","description":"Countdown Silverdale   Silverdale    Nzl","money":-3.0},{"accountName":"asdasd","date":"31/05/2020","description":"Countdown Silverdale   Silverdale    Nzl","money":-17.5},{"accountName":"asdasd","date":"30/05/2020","description":"Mi Oceania Limited     Auckland      Nz","money":-862.55},{"accountName":"asdasd","date":"30/05/2020","description":"Eshopworld             35318809114   Ie","money":-161.99},{"accountName":"asdasd","date":"28/05/2020","description":"Ting Tea               Auckland      Nzl","money":-9.0},{"accountName":"asdasd","date":"27/05/2020","description":"Gong Cha Westfield Alb Auckland      Nz","money":-6.29}]
//      ''';
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

        _transactionSeries.clear();
        _transactionSeries.add(new charts.Series<TransactionModel, DateTime>(
          id: 'Transactions',
          colorFn: (_, __) => charts.MaterialPalette.blue.shadeDefault,
          domainFn: (TransactionModel transaction, _) => transaction.date,
          measureFn: (TransactionModel transaction, _) => transaction.money,
          data: _transactions,
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
