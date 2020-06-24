import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart' show rootBundle;

import 'constant.dart';

String string(BuildContext context, String id) =>
    StringLocalization.of(context).string(id) ?? null;

class StringLocalization {
  StringLocalization(this._locale, this._localizedStringMap);

  final Locale _locale;

  final Map<String, dynamic> _localizedStringMap;

  static StringLocalization of(BuildContext context) {
    return Localizations.of<StringLocalization>(context, StringLocalization);
  }

  String string(String id) => _localizedStringMap[_locale.languageCode][id];
}

class StringDelegate extends LocalizationsDelegate<StringLocalization> {
  const StringDelegate();

  static const String _localizedStringFilePath = localizedStringFilePath;

  static const List<String> _supportedLanguageCodes = [englishLanguageCode];

  static List<String> get supportedLanguageCodes => _supportedLanguageCodes;

  @override
  bool isSupported(Locale locale) =>
      supportedLanguageCodes.contains(locale.languageCode);

  @override
  Future<StringLocalization> load(Locale locale) async {
    String _localizedStringJson =
        await rootBundle.loadString(_localizedStringFilePath);
    Map<String, dynamic> localizedValues = json.decode(_localizedStringJson);

    return StringLocalization(locale, localizedValues);
  }

  @override
  bool shouldReload(StringDelegate old) => false;
}
