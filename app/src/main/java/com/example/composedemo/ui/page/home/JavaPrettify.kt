package com.example.composedemo.ui.page.home

import android.text.Html
import android.widget.TextView
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.PrettifyHighlighter


@Composable
fun JavaPrettify(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        val code = " package com.ineighbor.commom.aop;\n" +
                "\n" +
                "            import org.aspectj.lang.ProceedingJoinPoint;\n" +
                "            import org.aspectj.lang.annotation.Around;\n" +
                "            import org.aspectj.lang.annotation.Aspect;\n" +
                "            import org.slf4j.Logger;\n" +
                "            import org.slf4j.LoggerFactory;\n" +
                "            import org.springframework.stereotype.Component;\n" +
                "\n" +
                "\n" +
                "            /**\n" +
                "             * @Author houguanglin\n" +
                "             * @Date 2023/02/22 17:14:05\n" +
                "             */\n" +
                "            @Aspect\n" +
                "            @Component\n" +
                "            public class LogAspact {\n" +
                "                private final static Logger logger = LoggerFactory.getLogger(LogAspact.class);\n" +
                "\n" +
                "                @Around(\"@within(com.ineighbor.commom.aop.Log) || @annotation(com.ineighbor.commom.aop.Log)\")\n" +
                "                public Object log(ProceedingJoinPoint joinPoint) throws Throwable {\n" +
                "                    String methodName = joinPoint.getSignature().getDeclaringTypeName() + \".\" + joinPoint.getSignature().getName();\n" +
                "\n" +
                "                    Object[] args = joinPoint.getArgs();\n" +
                "                    StringBuilder argsString = new StringBuilder();\n" +
                "                    if (args.length > 0) {\n" +
                "                        for (int i = 0; i < args.length; i++) {\n" +
                "                            if (i == args.length - 1) {\n" +
                "                                argsString.append(args[i]);\n" +
                "                            } else {\n" +
                "                                argsString.append(args[i]).append(\",\");\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n" +
                "\n" +
                "            //        获取注解内的值：\n" +
                "            //        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();\n" +
                "            //        Method method = methodSignature.getMethod();\n" +
                "            //        Log logAnnotation = method.getAnnotation(Log.class);\n" +
                "            //        logger.info(\"《{}》: Entering method: {} with args: [{}]\", logAnnotation.value(), methodName, argsString);\n" +
                "                    logger.info(\"Entering method: {} with args: [{}]\", methodName, argsString);\n" +
                "                    long startTime = System.currentTimeMillis();\n" +
                "\n" +
                "                    Object result = joinPoint.proceed();\n" +
                "\n" +
                "                    long endTime = System.currentTimeMillis();\n" +
                "                    logger.info(\"Exiting method: {} with result: {}. Time taken: {}ms\", methodName, result, endTime - startTime);\n" +
                "\n" +
                "                    return result;\n" +
                "                }\n" +
                "            }"

        val list = mutableListOf<String>()

        val lines = code.reader().readLines()
        val highlighter = PrettifyHighlighter()
        lines.forEach {
            val highlighted = highlighter.highlight("java", it)
            list.add(highlighted)
        }

        LazyColumn(content = {
            list.forEachIndexed { index, s ->
                item {

                    AndroidView(factory = { context ->
                        TextView(context).apply {
                            setText(Html.fromHtml(s))
                        }
                    })
                }
            }
        })
    }
}