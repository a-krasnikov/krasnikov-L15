package krasnikov.project.textapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import krasnikov.project.textapp.databinding.RecyclerItemPersonBinding

class PersonAdapter(private val items: MutableList<Person>) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding =
            RecyclerItemPersonBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addPerson(person: Person) {
        items.add(person)
        notifyItemInserted(items.size - 1)
    }

    class PersonViewHolder(private val binding: RecyclerItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {
            binding.tvName.text = "${person.firstName} ${person.lastName}"
        }
    }
}