package com.example.mobiledevelopmentcourselabapp.presentation.view.second
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentCardBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.NeuroUIClass
class CardFragment  : Fragment() {
    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!
    private val neuro by lazy { arguments?.getSerializable(CARD_NEURO_KEY) as? NeuroUIClass }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        var arrayTypeWords = arrayOf(" слов в минуту", " мелодий за 20 секунд", " картинки в минуту", " картинок в минуту",
            " мелодия за 20 секунд");
        neuro?.let { neuro ->
            binding.name.text = neuro.position.Name
            binding.number.text = neuro.number.toString()
            binding.positionValue.text = neuro.easy_content_velocity_gen.toString()
            binding.teamValue.text = neuro.company
            var content = binding.positionValue
            if (neuro.position.Name == "GPT-4" || neuro.position.Name == "Gemini") {
                content.append(arrayTypeWords[0])
            }
            else if (neuro.position.Name == "MusicFX") {
                content.append(arrayTypeWords[1])
            }
            else if (neuro.position.Name == "MidJourney") {
                content.append(arrayTypeWords[2])
            }
            else {
                content.append(arrayTypeWords[3])
            }
            Glide
                .with(this)
                .load(neuro.photoUrl)
                .circleCrop()
                .into(binding.icon)
        }
    }

    // https://developer.android.com/training/sharing/send
    private fun sharePlayerText() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, neuro.toString())
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val CARD_NEURO_KEY = "NEURO"
    }
}