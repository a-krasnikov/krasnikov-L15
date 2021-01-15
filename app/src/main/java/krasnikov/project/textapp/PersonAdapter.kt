package krasnikov.project.textapp

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import krasnikov.project.textapp.databinding.RecyclerItemPersonBinding
import kotlin.random.Random

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
            val spannable = SpannableStringBuilder("${person.firstName} ${person.lastName}")

            spannable.setSpan(
                ForegroundColorSpan(
                    Color.argb(
                        255,
                        Random.nextInt(256),
                        Random.nextInt(256),
                        Random.nextInt(256)
                    )
                ),
                spannable.length - person.lastName.length,
                spannable.length,
                0
            )
            spannable.setSpan(
                UnderlineSpan(),
                spannable.length - person.lastName.length,
                spannable.length,
                0
            )

            binding.tvName.text = spannable
        }
    }
}