package com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.AdItemBinding
import com.example.mobiledevelopmentcourselabapp.databinding.AddStrBinding
import com.example.mobiledevelopmentcourselabapp.databinding.ItemNeuroBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter.NeurosAdapter.AdHolder.Companion.ADD_ID
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter.NeurosAdapter.AdHolder.Companion.AD_ID
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter.NeurosAdapter.AdHolder.Companion.NEURO_ID
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.AdUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.AddStrClass
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.ItemUIModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.NeuroUIClass


class NeurosAdapter(
    private val onNeuroClicked: (NeuroUIClass) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: MutableList<ItemUIModel> = arrayListOf()
    private var currentNeuroCount = 0
    fun updateItems(newItems: List<ItemUIModel>){
        items = newItems.toMutableList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == NEURO_ID)
        {
            val binding = ItemNeuroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AvatarHolder(binding)
        }
        else if (viewType == ADD_ID)
        {
            val binding =AddStrBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return  AddHolder(binding)
        }
        val binding = AdItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when(item)
        {
            is NeuroUIClass -> {
                currentNeuroCount++
                NEURO_ID
            }
            is AdUiModel ->{
                if (currentNeuroCount % 5 == 0) {  // Show AddStrClass every 5th NeuroUIClass
                    AD_ID
                } else {
                    2
                }
            }
            is AddStrClass -> {
                if (currentNeuroCount % 5 == 0) {  // Show AddStrClass every 5th NeuroUIClass
                    ADD_ID
                } else {
                    1
                }
            }
            else -> AD_ID
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is AvatarHolder && item is NeuroUIClass)
        {
            holder.bind(item)
            holder.setOnClickListener {
                onNeuroClicked.invoke(item)
                item.isExpanded = !item.isExpanded
                notifyItemChanged(position)
            }
        }
        else if (holder is AddHolder && item is AddStrClass) {
            holder.bind(item)
        }
    }
    class AvatarHolder(private val binding: ItemNeuroBinding) : RecyclerView.ViewHolder(binding.root){
        var arrayTypeWords = arrayOf(" слов в минуту", " мелодий за 20 секунд", " картинки в минуту", " картинок в минуту",
            " мелодия за 20 секунд");
        fun bind(neuro: NeuroUIClass) {
            binding.name.text = neuro.position.Name
            binding.number.text = neuro.number.toString()
            binding.company.text = "Нейросеть создана компанией: ${neuro.company}"
            binding.additionalFields.isVisible = neuro.isExpanded
            binding.taskNeuro.text = neuro.neuroTask
            binding.easyContent.text = "Скорость генерации простого контента: ${neuro.easy_content_velocity_gen}"
            binding.hardContent.text = "Скорость генерации сложного контента: ${neuro.hard_content_velocity_gen}"
            var easyContent = binding.easyContent
            if (neuro.position.Name == "GPT-4" || neuro.position.Name == "Gemini") {
                easyContent.append(arrayTypeWords[0])
            }
            else if (neuro.position.Name == "MusicFX") {
                easyContent.append(arrayTypeWords[1])
            }
            else if (neuro.position.Name == "MidJourney") {
                easyContent.append(arrayTypeWords[2])
            }
            else
            {
                easyContent.append(arrayTypeWords[3])
            }
            var hardContent = binding.hardContent
            if (neuro.position.Name == "GPT-4" || neuro.position.Name == "Gemini") {
                hardContent.append(arrayTypeWords[0])
            }
            else if (neuro.position.Name == "MusicFX") {
                hardContent.append(arrayTypeWords[4])
            }
            else if (neuro.position.Name == "MidJourney") {
                hardContent.append(arrayTypeWords[2])
            }
            else
            {
                hardContent.append(arrayTypeWords[2])
            }
            Glide
                .with(itemView)
                .load(neuro.photoUrl)
                .into(binding.icon)
        }
        fun setOnClickListener(action: () -> Unit) {
            binding.root.setOnClickListener {
                action.invoke()
            }
        }
    }
    class AdHolder(private val binding: AdItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        companion object{
            const val NEURO_ID = 0
            const val AD_ID = 1
            const val ADD_ID = 2
        }
        fun bind(adItem: AdUiModel) {
            binding.idAdd.text = adItem.text
        }

    }
    class AddHolder(private val binding: AddStrBinding) :RecyclerView.ViewHolder(binding.root)
    {
        fun bind(addItem: AddStrClass) {
            binding.description.text = addItem.description
            Glide
                .with(itemView)
                .load(addItem.photo)
                .into(binding.addImage)
        }
    }

}