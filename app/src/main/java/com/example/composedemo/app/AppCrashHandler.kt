package com.example.composedemo.app

import android.content.Context
import android.content.Intent
import android.os.Looper
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.composedemo.ui.activity.MainActivity
import com.example.composedemo.utils.XLogger
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Suppress("UNREACHABLE_CODE")
class AppCrashHandler : Thread.UncaughtExceptionHandler {

    private var context: Context? = null

    private var  defaultSystemExpHandler : Thread.UncaughtExceptionHandler?=null

    // 创建一个具有固定线程数的线程池
    private var executors: ExecutorService = Executors.newFixedThreadPool(4)


    fun init(context: Context) {
        this.context = context
        Thread.setDefaultUncaughtExceptionHandler(this)
        defaultSystemExpHandler = Thread.getDefaultUncaughtExceptionHandler()
    }


    override fun uncaughtException(t: Thread, e: Throwable) {
       XLogger.e("thread name ${t.name} throw error ${e?.message}")
        if (e == null) {
            defaultSystemExpHandler?.uncaughtException(t, e)
        } else {
            executors.execute {
                Looper.prepare()
                //处理异常
                Toast.makeText(context, "系统崩溃了~", Toast.LENGTH_SHORT).show()
                Looper.loop()
            }
            //这里就是将进程干掉
//            android.os.Process.killProcess(android.os.Process.myPid())
            //这里等价 System.exit(1) 进程被干掉后，然后重启
//            exitProcess(1)


            val intent = Intent(context!!, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(context!!,intent,null)
            System.exit(0)

            /**
             * 这种方式 功能是可以达成
             * 但是有问题就是如果说你的app挂了 这时候会显示系统桌面
             * 然后你的app有启动起来了
             * 给人的感觉不太好
             */
//            val intent = Intent()
//            intent.setClass(context!!, MainActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context!!.startActivity(intent)
////            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)
//            val pendingIntent = PendingIntent.getActivity(context, 0, intent,
//                PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
//            val alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            alarmManager[AlarmManager.RTC, System.currentTimeMillis() + 100] = pendingIntent
//            android.os.Process.killProcess(android.os.Process.myPid())
//            exitProcess(1)
        }
    }

    companion object {
        val instance: AppCrashHandler by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            AppCrashHandler()
        }
    }
} 
