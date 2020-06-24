import 'package:account_statement/startup/app_page.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';
import 'package:provider/provider.dart';
import 'core/injection.dart' as di;

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
//  debugPaintSizeEnabled = true;
  Provider.debugCheckInvalidValueType = null;

  await di.init();
  runApp(AppPage());
}

class MyHomePage extends StatefulWidget {
  final String title = 'Flutter Demo Home Page';

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
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
      print("_initCounter MissingPluginException $e");
    } on PlatformException catch (e) {
      data = 0;
      print("_initCounter PlatformException $e");
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
