package com.example.health_food

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class KakaoAplication: Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "66dd2107e67e67301fe91d09b6257ce5")
    }
}