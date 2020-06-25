import 'package:intl/intl.dart';
import 'package:json_annotation/json_annotation.dart';

part 'transaction_model.g.dart';

@JsonSerializable(nullable: false, explicitToJson: true)
class TransactionModel {
  @JsonKey(name: 'accountName')
  String accountName;

  @JsonKey(name: 'date', fromJson: _dateFromJson, toJson: _dateToJson)
  DateTime date;

  @JsonKey(name: 'money')
  double money;

  @JsonKey(name: 'description')
  String description;

  TransactionModel(this.accountName, this.date, this.money, this.description);

  static DateFormat _dateFormat = new DateFormat("dd/MM/yyyy");

  static DateTime _dateFromJson(String date) => _dateFormat.parse(date);

  static String _dateToJson(DateTime date) => _dateFormat.format(date);

  /// A necessary factory constructor for creating a new User instance
  /// from a map. Pass the map to the generated `_$UserFromJson()` constructor.
  /// The constructor is named after the source class, in this case, User.
  factory TransactionModel.fromJson(Map<String, dynamic> json) =>
      _$TransactionModelFromJson(json);

  Map<String, dynamic> toJson() => _$TransactionModelToJson(this);
}
