import 'package:equatable/equatable.dart';

abstract class Failure extends Equatable {}

class ServerFailure extends Failure {
  final String message;

  ServerFailure([this.message]);

  @override
  List<Object> get props => [message];

  String toString() {
    if (message == null) return "ServerFailure";
    return "ServerFailure: $message";
  }
}

class CacheFailure extends Failure {
  @override
  List<Object> get props => [];
}
