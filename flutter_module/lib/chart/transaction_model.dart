import 'package:intl/intl.dart';
import 'package:json_annotation/json_annotation.dart';

part 'transaction_model.g.dart';

@JsonSerializable(explicitToJson: true)
class TransactionModel {
  @JsonKey(name: 'accountName')
  final String accountName;

  @JsonKey(name: 'date', fromJson: _dateFromJson, toJson: _dateToJson)
  final DateTime date;

  @JsonKey(name: 'money')
  final double money;

  @JsonKey(name: 'description')
  final String description;

  TransactionModel(this.accountName, this.date, this.money, this.description);

  static final DateFormat _dateFormat = DateFormat("dd/MM/yyyy");

  static DateTime _dateFromJson(String date) => _dateFormat.parse(date);

  static String _dateToJson(DateTime date) => _dateFormat.format(date);

  factory TransactionModel.fromJson(Map<String, dynamic> json) =>
      _$TransactionModelFromJson(json);

  Map<String, dynamic> toJson() => _$TransactionModelToJson(this);
}
