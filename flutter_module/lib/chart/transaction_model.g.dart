// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'transaction_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

TransactionModel _$TransactionModelFromJson(Map<String, dynamic> json) {
  return TransactionModel(
    json['accountName'] as String,
    TransactionModel._fromJson(json['date'] as String),
    (json['money'] as num).toDouble(),
    json['description'] as String,
  );
}

Map<String, dynamic> _$TransactionModelToJson(TransactionModel instance) =>
    <String, dynamic>{
      'accountName': instance.accountName,
      'date': TransactionModel._toJson(instance.date),
      'money': instance.money,
      'description': instance.description,
    };
