import 'package:flutter_test/flutter_test.dart';
import 'package:intl/intl.dart';

void main() {
  test('Print out dar date time format options', () {
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
}
