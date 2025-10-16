package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    //execute one and serve with the same value from the getFunction
    private val dieViewModel : DieViewModel by lazy{
        ViewModelProvider(this)[DieViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // dieViewModel = ViewModelProvider(this)[DieViewModel::class.java]

        if(supportFragmentManager.findFragmentById(R.id.fragmentContainerView) == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, DieFragment.newInstance(20))
                .commit()



        findViewById<Button>(R.id.rollDiceButton).setOnClickListener {
            dieViewModel.throwDie()

        }


    }
}