package hackathonnatura.edeploy.com.br.hackathonnatura.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hackathonnatura.edeploy.com.br.hackathonnatura.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        HomeActivity_.intent(this).start();
        finish()
    }
}
