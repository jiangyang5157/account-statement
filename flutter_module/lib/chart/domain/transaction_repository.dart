import 'dart:convert';

import 'package:account_statement/chart/data/transaction_model.dart';
import 'package:flutter/services.dart';

abstract class TransactionRepository {
  Future<List<TransactionModel>> getTransactions();
}

class TransactionRepositoryImpl implements TransactionRepository {
  static const methodChannel =
      MethodChannel('com.gmail.jiangyang5157.account_statement/MethodChannel');

  @override
  Future<List<TransactionModel>> getTransactions() async {
    String? data;
    try {
      final String? result =
          await methodChannel.invokeMethod('transactionsChannel');
      data = result;
    } on MissingPluginException catch (e) {
      print("#### getTransactions MissingPluginException $e");
      rethrow;
    } on PlatformException catch (e) {
      print("#### getTransactions PlatformException $e");
      rethrow;
    } on Exception catch (e) {
      print("#### getTransactions Exception $e");
      rethrow;
    }

    if (data != null) {
      List<TransactionModel> transactions = [];
      List<dynamic> transactionJsonList = jsonDecode(data);
      for (var element in transactionJsonList) {
        transactions.add(TransactionModel.fromJson(element));
      }
      transactions.sort((a, b) => a.date.compareTo(b.date));
      return transactions;
    } else {
      return [];
    }
  }
}
