import 'package:account_statement/chart/presentation/chart_page.dart';
import 'package:account_statement/startup/splash_page.dart';
import 'package:go_router/go_router.dart';

/// The router configuration for the application.
///
/// This uses the `go_router` package to define the routes and their
/// corresponding pages.
final GoRouter router = GoRouter(
  routes: [
    GoRoute(
      path: '/',
      builder: (context, state) => const SplashPage(),
    ),
    GoRoute(
      path: '/chart',
      builder: (context, state) => const ChartPage(),
    ),
  ],
);
