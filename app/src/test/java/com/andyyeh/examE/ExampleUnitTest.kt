package com.andyyeh.examE

import com.andyyeh.examE.mainActivity.MainActivityViewModel
import com.andyyeh.examE.mainActivity.MainContract
import com.andyyeh.examE.mainActivity.MainContract.Repository
import com.andyyeh.examE.mainActivity.model.UserBean
import com.andyyeh.examE.mainActivity.model.UserModel
import io.reactivex.Single
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import org.junit.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest{

    private lateinit var rp : MainContract.Repository
    private lateinit var vm: MainActivityViewModel
    private lateinit var userModel: UserModel

    companion object{
        @BeforeClass
        fun beforeClass(){
            println("do something before class")
        }

        @AfterClass
        fun afterClass(){
            println("do something after class")
        }
    }

    @Before
    fun beforeEachTest(){
        println("do something before each Test")
        rp = object : Repository {
            override fun getDataFromInternet(since: Int, consumer: Consumer<ArrayList<UserBean>>) {
                val data = ArrayList<UserBean>()
                data.add(UserBean(0, "http://...", "andy", false))
                Single.just(data).subscribe(consumer)
            }
        }

        userModel = UserModel()

        vm = MainActivityViewModel(rp, userModel)
    }

    @Test
    fun test1(){
        println("test 1 start")

        for (i in 1..110){
            vm.requestUserData(
                Consumer {
                    println("the start = ${it[0]} , size = ${it[1]}")
                },
                Action
                {
                    println("size is over 100, size = ${userModel.datas.size}")
                })
        }
        Assert.assertEquals(100, userModel.datas.size)
    }

    @After
    fun  afterEachTest(){
        println("do something after each Test")
    }
}
