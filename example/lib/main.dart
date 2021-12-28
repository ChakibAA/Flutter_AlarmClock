import 'package:flutter/material.dart';
import 'package:flutter_alarmclock/flutter_alarmclock.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('AlarmClock example app'),
        ),
        body: Center(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              TextButton(
                  onPressed: () async {
                    FlutterAlarmclock.setAlarm(
                        time: DateTime.now().add(const Duration(minutes: 1)),
                        label: 'FlutterAlarmclock Test',
                        days: 1);
                  },
                  child: const Text('Set Alarm')),
              TextButton(
                  onPressed: () async {
                    FlutterAlarmclock.dissimisAlarmByLabel(
                        label: 'FlutterAlarmclock Test');
                  },
                  child: const Text('Dissmiss Alarm by label')),
              TextButton(
                  onPressed: () async {
                    FlutterAlarmclock.showAlarms();
                  },
                  child: const Text('Show Alarms')),
              TextButton(
                  onPressed: () async {
                    FlutterAlarmclock.setTimer(length: 30, label: 'Timer Test');
                  },
                  child: const Text('Set Timer')),
              TextButton(
                  onPressed: () async {
                    FlutterAlarmclock.dismissAllExpiredTimers();
                  },
                  child: const Text('Dissmiss All timers')),
              TextButton(
                  onPressed: () async {
                    FlutterAlarmclock.showTimers();
                  },
                  child: const Text('Show Timers')),
            ],
          ),
        ),
      ),
    );
  }
}
