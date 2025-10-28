import 'dart:convert';

import 'package:account_statement/chart/data/transaction_model.dart';
import 'package:flutter/services.dart';

/// An abstract repository for fetching transaction data.
///
/// This layer decouples the data source from the business logic, allowing for
/// different data sources to be swapped in without changing the business logic.
abstract class TransactionRepository {
  /// Fetches the list of transactions.
  ///
  /// Throws an exception if the data cannot be fetched.
  Future<List<TransactionModel>> getTransactions();
}

/// The implementation of [TransactionRepository] that fetches data from a
/// platform-specific method channel.
class TransactionRepositoryImpl implements TransactionRepository {
  /// The method channel used to communicate with the native platform.
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
