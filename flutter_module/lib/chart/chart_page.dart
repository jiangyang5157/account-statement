import 'dart:convert';

import 'package:account_statement/chart/transaction_model.dart';
import 'package:account_statement/core/string_localization.dart';
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

  List<charts.Series<MoneySeries, DateTime>> _moneySeries;

  Future<void> _init() async {
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

//    data = '''
//      [{"accountName":"asdasd","date":"15/05/2020","description":"CARD 8117 ZHA ZHA TEA  AUCKLAND","money":-9.0},{"accountName":"asdasd","date":"15/05/2020","description":"EX 12-3059- 0004114-00","money":1000.0},{"accountName":"asdasd","date":"15/05/2020","description":"Yang","money":1000.0},{"accountName":"asdasd","date":"16/05/2020","description":"CARD 4298 GONG CHA WES TFIELD ALBAN AUCKLAND","money":-6.29},{"accountName":"asdasd","date":"16/05/2020","description":"CARD 4298 BRISCOES ALB ANY ALBANY","money":-36.0},{"accountName":"asdasd","date":"17/05/2020","description":"CARD 4298 GONG CHA WES TFIELD ALBAN AUCKLAND","money":-12.07},{"accountName":"asdasd","date":"17/05/2020","description":"CARD 4298 JAPAN HOME M ART AUCKLAND","money":-92.5},{"accountName":"asdasd","date":"18/05/2020","description":"CARD 8117 PAK N SAVE S ILVERDALE SILVERDALE N","money":-50.56},{"accountName":"asdasd","date":"20/05/2020","description":"NZD 9.00 ZHA ZHA TEA  AUCKLAND","money":-9.0},{"accountName":"asdasd","date":"20/05/2020","description":"EFTPOS","money":-16.48},{"accountName":"asdasd","date":"20/05/2020","description":"CARD 4298 TAI PING ALB ANY AUCKLAND","money":-78.27},{"accountName":"asdasd","date":"21/05/2020","description":"CARD 4298 GONG CHA WES TFIELD ALBAN AUCKLAND","money":-6.29},{"accountName":"asdasd","date":"22/05/2020","description":"CARD 4298 NEW WORLD AL BANY ALBANY NZ","money":-64.5},{"accountName":"asdasd","date":"23/05/2020","description":"CARD 4298 GONG CHA WES TFIELD ALBAN AUCKLAND","money":-6.29},{"accountName":"asdasd","date":"24/05/2020","description":"CARD 4298 GONGCHA QUEE N ST AUCKLAND","money":-6.29},{"accountName":"asdasd","date":"24/05/2020","description":"EFTPOS","money":-26.0},{"accountName":"asdasd","date":"24/05/2020","description":"CARD 4298 H\u0026M  AUCKLAND","money":-99.98},{"accountName":"asdasd","date":"24/05/2020","description":"CARD 4298 TWL 191 SILV ERDALE SILVERDALE","money":-19.29},{"accountName":"asdasd","date":"26/05/2020","description":"CARD 4298 SIERRA CAFE SILVERDALE SILVERDALE","money":-16.5},{"accountName":"asdasd","date":"26/05/2020","description":"CARD 8117 ZHA ZHA TEA  AUCKLAND","money":-9.8},{"accountName":"asdasd","date":"26/05/2020","description":"CARD 4298 COUNTDOWN SI LVERDALE SILVERDALE","money":-31.06},{"accountName":"asdasd","date":"07/06/2020","description":"Countdown Silverdale   Silverdale    Nzl","money":-71.43},{"accountName":"asdasd","date":"06/06/2020","description":"Hollywood Vape Limit   Auckland      Nz","money":-40.0},{"accountName":"asdasd","date":"05/06/2020","description":"Vodafone Prepay Visa M Auckland      Nz","money":-20.0},{"accountName":"asdasd","date":"03/06/2020","description":"Powershop              Wellington    Nz","money":-13.82},{"accountName":"asdasd","date":"02/06/2020","description":"Hollywood Bakery Silve Silverdale    Nzl","money":-10.5},{"accountName":"asdasd","date":"02/06/2020","description":"St Pierre\u0027S Silverdale Silverdale    Nzl","money":-31.9},{"accountName":"asdasd","date":"02/06/2020","description":"Vf Card Convenience    Auckland      Nz","money":-1.85},{"accountName":"asdasd","date":"02/06/2020","description":"Vodafone Fxdlne/Bband  Newton        Nz","money":-92.99},{"accountName":"asdasd","date":"01/06/2020","description":"Powershop              Wellington    Nz","money":-28.85},{"accountName":"asdasd","date":"31/05/2020","description":"Countdown Silverdale   Silverdale    Nzl","money":-3.0},{"accountName":"asdasd","date":"31/05/2020","description":"Countdown Silverdale   Silverdale    Nzl","money":-17.5},{"accountName":"asdasd","date":"30/05/2020","description":"Mi Oceania Limited     Auckland      Nz","money":-862.55},{"accountName":"asdasd","date":"30/05/2020","description":"Eshopworld             35318809114   Ie","money":-161.99},{"accountName":"asdasd","date":"28/05/2020","description":"Ting Tea               Auckland      Nzl","money":-9.0}]
//      ''';
//    data = '''
//    [{"accountName":"a","date":"10/01/2020","description":"Payment, Huang Xuefan","money":86.0},{"accountName":"a","date":"09/12/2019","description":"Payment, Huang Xuefan","money":-500.0},{"accountName":"a","date":"06/08/2019","description":"Payment, Huang Xuefan","money":-500.0}]
//    ''';
    if (data != null) {
      setState(() {
        List<TransactionModel> _transactions = [];
        List<dynamic> transactionJsonList = jsonDecode(data);
        transactionJsonList.forEach((element) {
          _transactions.add(TransactionModel.fromJson(element));
        });
        _transactions.sort((a, b) => a.date.compareTo(b.date));

        List<MoneySeries> moneySeriesData = [];
        for (int i = 0; i < _transactions.length; i++) {
          TransactionModel t = _transactions[i];
          if (i == 0) {
            moneySeriesData.add(MoneySeries(t.date, t.money));
          } else {
            moneySeriesData
                .add(MoneySeries(t.date, moneySeriesData[i - 1].sum + t.money));
          }
        }

        _moneySeries = [];
        _moneySeries.add(new charts.Series<MoneySeries, DateTime>(
            id: 'MoneySeries',
            domainFn: (MoneySeries it, _) => it.date,
            measureFn: (MoneySeries it, _) => it.sum,
            data: moneySeriesData));
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
    if (_moneySeries != null && _moneySeries.isNotEmpty) {
      return charts.TimeSeriesChart(_moneySeries,
          animate: true, dateTimeFactory: const charts.LocalDateTimeFactory());
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
