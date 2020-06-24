import 'package:account_statement/core/exceptions.dart';
import 'package:account_statement/core/failures.dart';
import 'package:account_statement/theme/data/sources/theme_local_data_source.dart';
import 'package:account_statement/theme/domain/entities/theme_entity.dart';
import 'package:account_statement/theme/domain/repositories/theme_repository.dart';
import 'package:dartz/dartz.dart';
import 'package:flutter/material.dart';

class ThemeRepositoryImpl implements ThemeRepository {
  final ThemeLocalDataSource localDataSource;

  ThemeRepositoryImpl({
    @required this.localDataSource,
  });

  @override
  Future<Either<Failure, ThemeEntity>> getLastTheme(
      {bool fromMemory = true}) async {
    try {
      return Right(await localDataSource.getLastTheme(fromMemory: fromMemory));
    } on CacheException {
      return Left(CacheFailure());
    }
  }

  @override
  Future<Either<Failure, ThemeEntity>> setTheme(ThemeType type) async {
    try {
      return Right(await localDataSource.setTheme(ThemeEntity(type: type)));
    } on CacheException {
      return Left(CacheFailure());
    }
  }
}
