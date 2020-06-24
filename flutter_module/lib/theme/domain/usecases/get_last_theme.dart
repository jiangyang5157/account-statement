import 'package:account_statement/core/failures.dart';
import 'package:account_statement/core/usecase.dart';
import 'package:account_statement/theme/domain/entities/theme_entity.dart';
import 'package:account_statement/theme/domain/repositories/theme_repository.dart';
import 'package:dartz/dartz.dart';
import 'package:equatable/equatable.dart';

class GetLastTheme implements UseCase<ThemeEntity, GetLastThemeParams> {
  final ThemeRepository repository;

  GetLastTheme(this.repository);

  @override
  Future<Either<Failure, ThemeEntity>> call(GetLastThemeParams params) async {
    return await repository.getLastTheme(fromMemory: params.fromMemory);
  }
}

class GetLastThemeParams extends Equatable {
  final bool fromMemory;

  GetLastThemeParams({this.fromMemory = true});

  @override
  List<Object> get props => [fromMemory];
}
