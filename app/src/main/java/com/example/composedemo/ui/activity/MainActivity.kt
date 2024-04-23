package com.example.composedemo.ui.activity

import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.composedemo.ui.page.AppScaffold
import com.example.composedemo.ui.theme.AppTheme
import com.example.composedemo.utils.XLogger
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class MainActivity : androidx.activity.ComponentActivity() {

    @OptIn(
        InternalCoroutinesApi::class,
        ExperimentalPermissionsApi::class,
        ExperimentalComposeUiApi::class,
        ExperimentalFoundationApi::class, ExperimentalPagerApi::class,
    )
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XLogger.d("MainActivity onCreate")
        setContent {
            AppTheme() {
                AppScaffold()
            }
        }

        //getImageData()

    }

    fun getImageData() {
        val IMAGE_PROJECTION = arrayOf(
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.IS_DRM,
            MediaStore.Images.Media._ID
        )

        LoaderManager.getInstance(this)
            .initLoader(0, null, object : LoaderManager.LoaderCallbacks<Cursor> {
                override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
                    XLogger.d("LoaderManager------->onCreateLoader")
                    val cursorLoader = CursorLoader(
                        this@MainActivity,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        IMAGE_PROJECTION,
                        null,
                        null, // Selection args (none).
                        MediaStore.Images.Media.DATE_ADDED + " DESC" // Sort order.
                    );
                    return cursorLoader;
                }

                override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
                    XLogger.d("LoaderManager------->onCreateLoader")
                    data?.let {
                        try {
                            while (data.moveToNext()) {
                                val path =
                                    data.getString(data.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                                val name =
                                    data.getString(data.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                                val dateTime =
                                    data.getLong(data.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED))
                                val mediaType =
                                    data.getInt(data.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE))
                                val size =
                                    data.getLong(data.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
                                val id =
                                    data.getInt(data.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
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

//                            val dirName: String = getParent(path)
//                            val media = Media(path, name, dateTime, mediaType, size, id, dirName)
//                            allFolder.addMedias(media)
//                            val index: Int = hasDir(folders, dirName)
//                            if (index != -1) {
//                                folders[index].addMedias(media)
//                            } else {
//                                val folder = Folder(dirName)
//                                folder.addMedias(media)
//                                folders.add(folder)
//                            }
                            }
                            data.close()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }


                }

                override fun onLoaderReset(loader: Loader<Cursor>) {
                    XLogger.d("------->onLoaderReset")
                }


            })
    }
}
