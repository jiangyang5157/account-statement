import 'package:intl/intl.dart';
import 'package.json_annotation/json_annotation.dart';

part 'transaction_model.g.dart';

/// Represents a single financial transaction.
///
/// This model is used to parse the transaction data received from the
/// platform-specific method channel.
@JsonSerializable(explicitToJson: true)
class TransactionModel {
  /// The name of the account associated with the transaction.
  @JsonKey(name: 'accountName')
  final String accountName;

  /// The date of the transaction.
  @JsonKey(name: 'date', fromJson: _dateFromJson, toJson: _dateToJson)
  final DateTime date;

  /// The monetary value of the transaction.
  @JsonKey(name: 'money')
  final double money;

  /// A description of the transaction.
  @JsonKey(name: 'description')
  final String description;

  /// Creates a new [TransactionModel].
  TransactionModel(this.accountName, this.date, this.money, this.description);

  /// The date format used for parsing the date string from the JSON data.
  static final DateFormat _dateFormat = DateFormat("dd/MM/yyyy");

  /// A helper function to parse the date from a JSON string.
  static DateTime _dateFromJson(String date) => _dateFormat.parse(date);

  /// A helper function to format the date to a JSON string.
  static String _dateToJson(DateTime date) => _dateFormat.format(date);

  /// Creates a new [TransactionModel] from a JSON map.
  factory TransactionModel.fromJson(Map<String, dynamic> json) =>
      _$TransactionModelFromJson(json);

  /// Converts this [TransactionModel] to a JSON map.
  Map<String, dynamic> toJson() => _$TransactionModelToJson(this);
}
