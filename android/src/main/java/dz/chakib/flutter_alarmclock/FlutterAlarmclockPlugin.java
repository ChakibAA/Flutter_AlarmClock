package dz.chakib.flutter_alarmclock;

import androidx.annotation.NonNull;

import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** FlutterAlarmclockPlugin */
public class FlutterAlarmclockPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native
  /// Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine
  /// and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private Context context;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    this.context = flutterPluginBinding.getApplicationContext();
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_alarmclock");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    try {
      switch (call.method) {
        case "setAlarm": {
          setAlarm(call);
          result.success(
              true);
          break;
        }
        case "dissimisAlarmByLabel": {
          dissimisAlarmByLabel(call);
          result.success(
              true);
          break;
        }
        case "dissimisAlarmByTime": {
          dissimisAlarmByTime(call);
          result.success(
              true);
          break;
        }
        case "showAlarms": {
          showAlarms();
          result.success(
              true);
          break;
        }
        case "setTimer": {
          setTimer(call);
          result.success(
              true);
          break;
        }

        case "dismissAllExpiredTimers": {
          dismissAllExpiredTimers();
          result.success(
              true);
          break;
        }
        case "showTimers": {
          showTimers();
          result.success(
              true);
          break;
        }
        default: {
          result.notImplemented();
          break;
        }
      }
    } catch (Exception e) {
      result.error("error", "AlarmClock error: " + e.getMessage(), null);
    }
  }

  // ALARM Clock

  private void setAlarm(MethodCall call) {

    java.lang.Integer hour = call.argument("hour");
    java.lang.Integer minute = call.argument("minute");
    java.lang.String label = call.argument("label");
    java.lang.Integer days = call.argument("days");

    Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
    i.putExtra(AlarmClock.EXTRA_HOUR, hour);
    i.putExtra(AlarmClock.EXTRA_MINUTES, minute);
    i.putExtra(AlarmClock.EXTRA_MESSAGE, label);
    i.putExtra(AlarmClock.EXTRA_DAYS, days);

    context.startActivity(i);

  }

  private void dissimisAlarmByTime(MethodCall call) {

    java.lang.Integer hour = call.argument("hour");
    java.lang.Integer minute = call.argument("minute");

    Intent i = new Intent(AlarmClock.ACTION_DISMISS_ALARM);
    i.putExtra(AlarmClock.EXTRA_ALARM_SEARCH_MODE, AlarmClock.ALARM_SEARCH_MODE_TIME);
    i.putExtra(AlarmClock.EXTRA_HOUR, hour);
    i.putExtra(AlarmClock.EXTRA_MINUTES, minute);
    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);

    context.startActivity(i);

  }

  private void dissimisAlarmByLabel(MethodCall call) {

    java.lang.String label = call.argument("label");

    Intent i = new Intent(AlarmClock.ACTION_DISMISS_ALARM);
    i.putExtra(AlarmClock.EXTRA_ALARM_SEARCH_MODE, AlarmClock.ALARM_SEARCH_MODE_LABEL);
    i.putExtra(AlarmClock.EXTRA_MESSAGE, label);
    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);

    context.startActivity(i);
  }

  private void showAlarms() {

    Intent i = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    context.startActivity(i);
  }

  // TIMER

  private void setTimer(MethodCall call) {

    java.lang.Integer length = call.argument("length");
    java.lang.String label = call.argument("label");

    Intent i = new Intent(AlarmClock.ACTION_SET_TIMER);
    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
    i.putExtra(AlarmClock.EXTRA_LENGTH, length);
    i.putExtra(AlarmClock.EXTRA_MESSAGE, label);

    context.startActivity(i);
  }

  private void dismissAllExpiredTimers() {

    Intent i = new Intent(AlarmClock.ACTION_DISMISS_TIMER);
    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);

    context.startActivity(i);
  }

  private void showTimers() {

    Intent i = new Intent(AlarmClock.ACTION_SHOW_TIMERS);
    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    context.startActivity(i);
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
