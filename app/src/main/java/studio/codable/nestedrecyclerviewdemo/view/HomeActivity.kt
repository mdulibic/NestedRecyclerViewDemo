package studio.codable.nestedrecyclerviewdemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import studio.codable.nestedrecyclerviewdemo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityHomeBinding.inflate(layoutInflater).root)
    }
}