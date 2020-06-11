package com.andyyeh.examE

import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.andyyeh.examE.novelDataBase.BookTable.BookInfo
import com.andyyeh.examE.novelDataBase.BookTable.BookInfoDao
import com.andyyeh.examE.novelDataBase.NovelDataBase
import io.reactivex.functions.Consumer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class DataBaseTest {

    private var mBookInfoDao: BookInfoDao? = null
    private var mNovelDataBase : NovelDataBase? = null

    private var mReceivedBookInfo: BookInfo? = null

    @Before
    fun setUp(){
        NovelDataBase.TEST_MODE = true
        mNovelDataBase = NovelDataBase.getInstance(ApplicationProvider.getApplicationContext())
        mBookInfoDao = mNovelDataBase?.bookInfoDao()
    }

    @Test
    fun testFunctionIsNormal(){
        val bookInfo = BookInfo()
        bookInfo.currentChapter=5
        bookInfo.currentChapterName="I am handsome"
        bookInfo.currentPageTag=6
        bookInfo.bookId=23412
        bookInfo.lastTimeRead = Calendar.getInstance().timeInMillis

//        mBookInfoDao?.insert(bookInfo)

        mBookInfoDao?.getAll()?.subscribe(consumer)

        bookInfo.bookId = 25674
        mBookInfoDao?.update(bookInfo)
//        mBookInfoDao?.insert(bookInfo)
    }

    private val consumer = object : Consumer<List<BookInfo>>{
        override fun accept(t: List<BookInfo>?) {
            mReceivedBookInfo = t?.get(10)
            t?.forEach {
                Log.i("each = ", "$it")
            }
            Log.i("onNext = ", "$t")
            assert(mReceivedBookInfo?.bookId!!.equals("25674"))
        }

    }
}