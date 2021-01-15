package krasnikov.project.textapp

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import krasnikov.project.textapp.databinding.FragmentPersonListBinding

class PersonListFragment : Fragment() {

    private lateinit var binding: FragmentPersonListBinding
    private lateinit var adapter: PersonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupListeners()
        setupValidationOnLoosFocus()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun setupRecycler() {
        adapter = PersonAdapter(Person.genPersonList())

        val recyclerView = binding.rvFamily
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
    }

    private fun setupListeners() {
        binding.btnAdd.setOnClickListener {
            addPersonToRecyclerView()
        }
    }

    private fun addPersonToRecyclerView() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()

        if (!isValidName(firstName)) {
            binding.etFirstName.requestFocus()
            binding.etFirstName.error = getString(R.string.error_input_first_name)
            return
        }

        if (!isValidName(firstName)) {
            binding.etFirstName.requestFocus()
            binding.etFirstName.error = getString(R.string.error_input_first_name)
            return
        }

        adapter.addPerson(Person(firstName, lastName))
        Toast.makeText(
            context,
            getString(R.string.msg_add_person, firstName, lastName),
            Toast.LENGTH_SHORT
        ).show()
    }

    // Setup validation on loss of focus TextInputEditText
    private fun setupValidationOnLoosFocus() {
        val etFocusListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                when (view.id) {
                    R.id.etFirstName -> {
                        view as TextInputEditText
                        if (!isValidName(view.text.toString())) {
                            binding.etFirstName.error = getString(R.string.error_input_first_name)
                        }
                    }
                    R.id.etLastName -> {
                        view as TextInputEditText
                        if (!isValidName(view.text.toString())) {
                            binding.etLastName.error = getString(R.string.error_input_last_name)
                        }
                    }
                }
            }
        }

        binding.etFirstName.onFocusChangeListener = etFocusListener
        binding.etLastName.onFocusChangeListener = etFocusListener
    }

    private fun isValidName(str: String): Boolean {
        val minLength = 4
        val regex = Regex("[A-Z][A-Za-z]{${minLength - 1},}")

        if (!str.matches(regex)) {
            return false
        }

        return true
    }

    companion object {
        @JvmStatic
        fun newInstance() = PersonListFragment()
    }
}