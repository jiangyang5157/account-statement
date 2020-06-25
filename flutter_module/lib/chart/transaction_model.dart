import 'package:json_annotation/json_annotation.dart';

part 'transaction_model.g.dart';

@JsonSerializable()
class TransactionModel {
  @JsonKey(name: 'accountName')
  String accountName;

  @JsonKey(name: 'date', required: true)
  String date;

  @JsonKey(name: 'money', required: true)
  double money;

  @JsonKey(name: 'description', defaultValue: "")
  String description;

  TransactionModel(this.accountName, this.date, this.money, this.description);

  /// A necessary factory constructor for creating a new User instance
  /// from a map. Pass the map to the generated `_$UserFromJson()` constructor.
  /// The constructor is named after the source class, in this case, User.
  factory TransactionModel.fromJson(Map<String, dynamic> json) =>
      _$TransactionModelFromJson(json);

  Map<String, dynamic> toJson() => _$TransactionModelToJson(this);
}
