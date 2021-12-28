# flutter_alarmclock

-----

## Getting Started

After importing this plugin to your project as usual, add the following permission to your
`AndroidManifest.xml`:

```xml
<uses-permission android:name="com.android.alarm.permission.SET_ALARM"/>
```

Then in Dart code add:

```dart
import 'package:flutter_alarmclock/flutter_alarmclock.dart';


void setAlarm() {
      Alarmclock.setAlarm(
        time: DateTime.now().add(const Duration(minutes: 1)),
        label: 'AlarmClock Test',
        days: 1);
}

```


