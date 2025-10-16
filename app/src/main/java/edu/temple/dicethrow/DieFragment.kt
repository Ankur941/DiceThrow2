package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class DieFragment : Fragment() {

    val DIESIDE = "sidenumber"
    val CURRENT_ROLL_KEY = "currentroll"
    lateinit var dieTextView: TextView

    var dieSides: Int = 20
    var currentRoll: Int? = null

    private val dieViewModel: DieViewModel by lazy{
        //ViewModelProvider(requireActivity())[DieViewModel::class.java]
        ViewModelProvider(requireActivity())[DieViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        dieViewModel.getRolledNumber().observe(viewLifecycleOwner){
            updateView(it)
        }

    }



    private fun updateView(value: Int) {
        dieTextView.text = value.toString()
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