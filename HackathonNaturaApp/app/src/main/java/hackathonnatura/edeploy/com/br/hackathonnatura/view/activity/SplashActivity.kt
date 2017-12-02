package hackathonnatura.edeploy.com.br.hackathonnatura.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hackathonnatura.edeploy.com.br.hackathonnatura.R
import hackathonnatura.edeploy.com.br.hackathonnatura.view.activity.mock.MockLoginActivity_

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        MockLoginActivity_.intent(this).start()
        finish()
    }
}
