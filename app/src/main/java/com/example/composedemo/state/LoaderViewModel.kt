package com.example.composedemo.state

import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import com.example.composedemo.app.App
import com.example.composedemo.utils.XLogger

/**
 * Created by finn on 2022/5/25
 */
class LoaderViewModel : ViewModel() {

    val image_projection = arrayOf(
        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media.DATE_MODIFIED,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.DATE_ADDED,
        MediaStore.Images.Media.MIME_TYPE,
        MediaStore.Images.Media.SIZE,
        MediaStore.Images.Media.IS_DRM,
        MediaStore.Images.Media.RELATIVE_PATH,
        MediaStore.Images.Media._ID
    )

    val File_projection = arrayOf(
        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media.DATE_MODIFIED,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.DATE_ADDED,
        MediaStore.Images.Media.MIME_TYPE,
        MediaStore.Images.Media.SIZE,
        MediaStore.Images.Media.IS_DRM,
        MediaStore.Images.Media.RELATIVE_PATH,
        MediaStore.Images.Media._ID
    )


    fun loadFilesData() {
        val external = MediaStore.Files.getContentUri("external")
        val startTime = System.currentTimeMillis()
        val cursor = App.CONTEXT.contentResolver.query(external, null, null, null, null)
        XLogger.d("seconds------------>${System.currentTimeMillis() - startTime}")
        cursor?.let {
            try {
                while (cursor.moveToNext()) {
                    val path =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA))
                    val name =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME))
                    val dateTime =
                        cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED))
                    val mediaType =
                        cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE))
                    val size =
                        cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE))
                    val id =
                        cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID))
                    if (size < 1) continue
                    if (path == null || path == "") continue
//                    XLogger.d(
//                        """
//                                                    path:${path}
//                                                    name:${name}
//                                                    dateTime:${dateTime}
//                                                    mediaType:${mediaType}
//                                                    size:${size}
//                                                    id:${id}
//                                                """.trimIndent()
//                    )

                    XLogger.d("path:${path}")
                }
                cursor.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        cursor?.close()
    }

    fun loadData() {
        val external = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val startTime = System.currentTimeMillis()
        val cursor = App.CONTEXT.contentResolver.query(external, image_projection, null, null, null)
        XLogger.d("seconds------------>${System.currentTimeMillis() - startTime}")
        cursor?.let {
            try {
                while (cursor.moveToNext()) {
                    val path =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                    val name =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                    val dateTime =
                        cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED))
                    val mediaType =
                        cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE))
                    val size =
                        cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
                    val id =
                        cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                    if (size < 1) continue
                    if (path == null || path == "") continue
                    XLogger.d(
                        """
                                                    path:${path}
                                                    name:${name}
                                                    dateTime:${dateTime}
                                                    mediaType:${mediaType}
                                                    size:${size}
                                                    id:${id}
                                                """.trimIndent()
                    )
                    //                            }
                }
                cursor.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        cursor?.close()
    }
}