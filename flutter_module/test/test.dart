// This is a basic Flutter widget test.
//
// To perform an interaction with a widget in your test, use the WidgetTester
// utility that Flutter provides. For example, you can send tap and scroll
// gestures. You can also use WidgetTester to find child widgets in the widget
// tree, read text, and verify that the values of widget properties are correct.

import 'package:flutter_test/flutter_test.dart';

import 'package:intl/intl.dart';

void main() {
  test('Expected date time format', () {
    print(
        "####, format by dd-MM-yyyy: ${new DateFormat("dd-MM-yyyy").format(DateTime.now())}");
    print(
        "####, format by dd/MM/yyyy: ${new DateFormat("dd/MM/yyyy").format(DateTime.now())}");
    print(
        "####, format by default: ${new DateFormat().format(DateTime.now())}");
    print(
        "####, parse by dd-MM-yyyy: ${new DateFormat("dd-MM-yyyy").parse("25-06-2020")}");
    print(
        "####, parse by dd/MM/yyyy: ${new DateFormat("dd/MM/yyyy").parse("25/06/2020")}");
    print(
        "####, parse by default: ${new DateFormat().parse("June 25, 2020 1:59:27 PM")}");
  });

  test('Expected remove quotes from string', () {
    print(
        "####, my string: ${"my string".replaceAll(new RegExp(r'[^\w\s]+'), '')}");
    print(
        "####, \"my string\": ${"\"my string\"".replaceAll(new RegExp(r'[^\w\s]+'), '')}");
    print(
        "####, \'my string\': ${"\'my string\'".replaceAll(new RegExp(r'[^\w\s]+'), '')}");
    print(
        "####, \"\"my string\"\": ${"\"\"my string\"\"".replaceAll(new RegExp(r'[^\w\s]+'), '')}");
    print(
        "####, \"-\"my string\"-\": ${"\"-\"my string\"-\"".replaceAll(new RegExp(r'[^\w\s]+'), '')}");
  });
}
