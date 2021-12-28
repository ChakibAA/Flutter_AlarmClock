import 'dart:async';

import 'package:flutter/services.dart';

class FlutterAlarmclock {
  static const MethodChannel _channel = MethodChannel('alarmclock');

  static Future<bool> setAlarm(
      {required DateTime time,
      required String label,
      required int days}) async {
    var can = await _channel.invokeMethod('setAlarm', {
      'hour': time.hour,
      'minute': time.minute,
      'label': label,
      'days': days
    });

    return can;
  }

  static Future<bool> dissimisAlarmByLabel({required String label}) async {
    var can = await _channel.invokeMethod('dissimisAlarmByLabel', {
      'label': label,
    });

    return can;
  }

  static Future<bool> dissimisAlarmByTime({required DateTime time}) async {
    var can = await _channel.invokeMethod('dissimisAlarmByTime', {
      'hour': time.hour,
      'minute': time.minute,
    });

    return can;
  }

  static Future<bool> showAlarms() async {
    var can = await _channel.invokeMethod(
      'showAlarms',
    );

    return can;
  }

  static Future<bool> setTimer({
    required int length,
    required String label,
  }) async {
    var can = await _channel
        .invokeMethod('setTimer', {'length': length, 'label': label});

    return can;
  }

  static Future<bool> dismissAllExpiredTimers() async {
    var can = await _channel.invokeMethod(
      'dismissAllExpiredTimers',
    );

    return can;
  }

  static Future<bool> showTimers() async {
    var can = await _channel.invokeMethod(
      'showTimers',
    );

    return can;
  }
}
