import 'package:account_statement/core/failures.dart';
import 'package:account_statement/core/usecase.dart';
import 'package:account_statement/theme/domain/entities/theme_entity.dart';
import 'package:account_statement/theme/domain/repositories/theme_repository.dart';
import 'package:dartz/dartz.dart';
import 'package:equatable/equatable.dart';

class SetTheme implements UseCase<ThemeEntity, SetThemeParams> {
  final ThemeRepository repository;

  SetTheme(this.repository);

  @override
  Future<Either<Failure, ThemeEntity>> call(SetThemeParams params) async {
    return await repository.setTheme(params.type);
  }
}

class SetThemeParams extends Equatable {
  final ThemeType type;

  SetThemeParams(this.type);

  @override
  List<Object> get props => [type];
}
