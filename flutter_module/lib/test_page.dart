import 'package:account_statement/core/injection.dart';
import 'package:account_statement/core/nav.dart';
import 'package:account_statement/startup/startup_view_model.dart';
import 'package:fluro/fluro.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class TestPage extends StatefulWidget {
  final String title = 'Flutter Demo Home Page';

  @override
  _TestPageState createState() => _TestPageState();
}

class _TestPageState extends State<TestPage> {
  static const methodChannel =
      const MethodChannel('com.gmail.jiangyang5157.account_statement/data');

  int _counter = 0;

  Future<void> _initCounter() async {
    int data;
    try {
      final int result = await methodChannel.invokeMethod('intChannel');
      data = result;
    } on MissingPluginException catch (e) {
      data = 0;
      print("#### _initCounter MissingPluginException $e");
    } on PlatformException catch (e) {
      data = 0;
      print("#### _initCounter PlatformException $e");
    }
    setState(() {
      _counter = data;
    });
  }

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  void initState() {
    super.initState();
    _initCounter();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
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
