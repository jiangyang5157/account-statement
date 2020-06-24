import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'chart/simple_time_series_chart.dart';

class TestPage extends StatefulWidget {
  final String title = 'Flutter Demo Home Page';

  @override
  _TestPageState createState() => _TestPageState();
}

class _TestPageState extends State<TestPage> {
  static const methodChannel = const MethodChannel(
      'com.gmail.jiangyang5157.account_statement/MethodChannel');

  int _counter = 0;
  String _transactions =
  '''
  [{"accountName":"asdasd","date":"Jun 2, 2020 12:00:00 AM","description":"Payment, Amp General Ins","id":402,"money":-1224.18},{"accountName":"asdasd","date":"Jun 2, 2020 12:00:00 AM","description":"Payment, Amp General Ins","id":418,"money":-1224.18},{"accountName":"asdasd","date":"Jun 2, 2020 12:00:00 AM","description":"Payment, Amp General Ins","id":434,"money":-1224.18}]
  '''
  ;

  Future<void> _initTransactions() async {
    try {
      final String result =
          await methodChannel.invokeMethod('transactionsChannel');
      setState(() {
        _transactions = result;
      });
    } on MissingPluginException catch (e) {
      print("#### _initCounter MissingPluginException $e");
    } on PlatformException catch (e) {
      print("#### _initCounter PlatformException $e");
    }
  }

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  void dispose() {
    super.dispose();
    print('#### _TestPageState - dispose');
  }

  @override
  void initState() {
    super.initState();
    print('#### _TestPageState - initState');
    _initTransactions();
  }

  @override
  Widget build(BuildContext context) {
    print('#### _TestPageState - build');
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
//        child: SimpleTimeSeriesChart.withSampleData(),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline4,
            ),
            Text(
              'Received transactions:\n$_transactions',
            )
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),
    );
  }
}
