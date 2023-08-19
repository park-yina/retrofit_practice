package com.example.retrofit_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RestrictTo
import com.example.retrofit_practice.databinding.ActivityMainBinding
import okhttp3.ResponseBody
import okhttp3.internal.platform.Platform.get
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class MainActivity : AppCompatActivity() {
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NetworkService::class.java)
        val createRepoRequest = create_repo("my-new-repo", "1")

        val call = service.createRepo(createRepoRequest)
        call.enqueue(object : Callback<create_repo> { // 응답을 create_repo로 받습니다.
            override fun onFailure(call: Call<create_repo>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<create_repo>, response: Response<create_repo>) {
                if (response.isSuccessful) {
                    val createdRepo = response.body() // 응답을 create_repo 객체로 받습니다.
                    if (createdRepo != null) {
                        Log.d("kkang", "새로운 리포지토리 생성 성공")
                        // 생성된 리포지토리 정보를 사용할 수 있습니다.
                    } else {
                        Log.d("kkang", "리포지토리 생성 응답에서 데이터가 null입니다.")
                    }
                } else {
                    Log.d("kkang", "리포지토리 생성 실패: ${response.code()}")
                }
            }
        })



    }
}
