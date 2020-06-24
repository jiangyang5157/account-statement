import 'package:account_statement/core/failures.dart';
import 'package:account_statement/theme/domain/entities/theme_entity.dart';
import 'package:dartz/dartz.dart';

abstract class ThemeRepository {
  Future<Either<Failure, ThemeEntity>> getLastTheme({bool fromMemory = true});

  Future<Either<Failure, ThemeEntity>> setTheme(ThemeType type);
}
