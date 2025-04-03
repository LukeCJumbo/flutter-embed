package com.jumbo.flutterembedder

import android.widget.Toast
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class BallFlutterFragment : FlutterFragment() {

    override fun getDartEntrypointFunctionName(): String {
        return "ball"
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        val methodChannel = MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "com.jumbo.flutterembedder/channel"
        )
        methodChannel.setMethodCallHandler { call, result ->
            if (call.method == "sendMessage") {
                val message = call.arguments as String
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                GlobalVariables.ballBounces++
                result.success(message)
            } else {
                result.notImplemented()
            }
        }
        methodChannel.invokeMethod("setSpeed", GlobalVariables.ballSpeed)
        super.configureFlutterEngine(flutterEngine)
    }
}

class StarfieldFlutterFragment : FlutterFragment() {
    override fun getDartEntrypointFunctionName(): String {
        return "starfield"
    }
}
