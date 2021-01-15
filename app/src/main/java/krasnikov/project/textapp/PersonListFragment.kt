package krasnikov.project.textapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import krasnikov.project.textapp.databinding.FragmentPersonListBinding

class PersonListFragment : Fragment() {
    private lateinit var binding: FragmentPersonListBinding
    private lateinit var adapter: PersonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun setupRecycler() {
        adapter = PersonAdapter(genPersonList())

        val recyclerView = binding.rvFamily
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
    }

    private fun setupListeners() {
        binding.btnAdd.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()

            adapter.addPerson(Person(firstName, lastName))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PersonListFragment()
    }
}