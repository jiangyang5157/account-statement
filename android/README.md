# FCF: Example Flutter Module
An example of using flutter page for visualization data.

Source:
- https://github.com/jiangyang5157/account-statement/tree/master/android
- https://github.com/jiangyang5157/account-statement/tree/master/flutter_module

## Things in this example:

Flutter:
- [fluro](https://pub.dev/packages/fluro), Flutter routing library that adds flexible routing options like wildcards, named parameters and clear route definitions.
- [get_it](https://pub.dev/packages/get_it), Simple Service Locator for Dart and Flutter projects with some additional goodies highly inspired by Splat.
- [google charts_flutter](https://pub.dev/packages/charts_flutter), Material Design data visualization library written natively in Dart.
- Flutter strings localization implementation

Android:
- [Room](https://developer.android.com/training/data-storage/room) from Android Jetpack
- [Retrofit](https://square.github.io/retrofit/)
- [Dagger Hilt](https://dagger.dev/hilt/)
- [Router](https://github.com/jiangyang5157/kotlin-android/tree/master/router)
- MVVM & Data flow

###### [PNG](https://auk-tfs.nzlabs.net/tfs/DefaultCollection/Auckland_PD/_git/FCF_Yang?path=/android/example-flutter-module/assets/data_flow.png)

<img src="https://auk-tfs.nzlabs.net/tfs/DefaultCollection/13ad62b8-a977-4115-bdd2-4bc9a106fb80/_apis/git/repositories/e1493d3b-cfa2-4f6b-976c-b9018765c9b3/Items?path=%2Fandroid%2Fexample-flutter-module%2Fassets%2Fdata_flow.png&versionDescriptor%5BversionOptions%5D=0&versionDescriptor%5BversionType%5D=0&versionDescriptor%5Bversion%5D=master&download=false&resolveLfs=true&%24format=octetStream&api-version=5.0-preview.1"/>

  - ViewModel: managing data in the UI lifecycle (eg: provide livedata/flowdata to UI)
  - UseCase: manipulate data sources from repository for different individual business logic 
  - Repository: managing data sources (eg: add/delete/search/edit), contains remote/local caching/persistence mechanism if required.
    - In order to unify remote data model and local common value objects (CVOs), data transfer objects (DTOs) are required
    - In order to fit in remote call response to livedata/flowdata, custom call adapter may required

[Add Flutter to existing app](https://flutter.dev/docs/development/add-to-app)

## To be improved (but no intent to do):
- Solution to close RoomDb after application closed.
- Implement a more interactive chart instead of simple chart, see [charts_flutter gallery](https://google.github.io/charts/flutter/gallery.html)
- Avoid to reach the 536808 bytes data channel limits between Mobile and Flutter
- Custom data source (eg: more .csv format supports), current supports:
  - ANZ Saving csv downloaded from ANZ desktop online banking: "Type,Details,Particulars,Code,Reference,Amount,Date,ForeignCurrencyAmount,ConversionCharge"
  - ANZ Credit csv downloaded from ANZ desktop online banking: "Card,Type,Amount,Details,TransactionDate,ProcessedDate,ForeignCurrencyAmount,ConversionCharge"
  - ASB Saving csv downloaded from ASB desktop online banking: "Date,Unique Id,Tran Type,Cheque Number,Payee,Memo,Amount"

## Demo:

###### [GIF](https://auk-tfs.nzlabs.net/tfs/DefaultCollection/Auckland_PD/_git/FCF_Yang?path=/android/example-flutter-module/assets/demo.gif)

<img src="https://auk-tfs.nzlabs.net/tfs/DefaultCollection/13ad62b8-a977-4115-bdd2-4bc9a106fb80/_apis/git/repositories/e1493d3b-cfa2-4f6b-976c-b9018765c9b3/Items?path=%2Fandroid%2Fexample-flutter-module%2Fassets%2Fdemo.gif&versionDescriptor%5BversionOptions%5D=0&versionDescriptor%5BversionType%5D=0&versionDescriptor%5Bversion%5D=master&download=false&resolveLfs=true&%24format=octetStream&api-version=5.0-preview.1" width=320/>
