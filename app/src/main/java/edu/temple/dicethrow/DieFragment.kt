package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    val DIESIDE = "sidenumber"
    val CURRENT_ROLL_KEY = "currentroll"
    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    var currentRoll: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            it.getInt(CURRENT_ROLL_KEY).run {
                currentRoll = this
            }
        }
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (currentRoll != null) {
            currentRoll?.run {
                updateView(this)
            }
        } else {
            throwDie()
        }
    }

    fun throwDie() {
        currentRoll = (Random.nextInt(dieSides) + 1)
        currentRoll?.run {
            updateView(this)
        }
        // dieTextView.text = Random.nextInt(dieSides).toString()
    }

    private fun updateView(value: Int) {
        dieTextView.text = value.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentRoll?.run {
            outState.putInt(CURRENT_ROLL_KEY, this)
        }
    }

    companion object {
        fun newInstance(sides: Int): DieFragment {
            return DieFragment().apply {
                arguments = Bundle().apply {
                    putInt(DIESIDE, sides)

                }
            }
        }
    }
}