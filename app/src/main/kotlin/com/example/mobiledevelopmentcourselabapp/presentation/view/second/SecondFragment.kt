package com.example.mobiledevelopmentcourselabapp.presentation.view.second
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentListBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.adapter.NeurosAdapter
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.generator.Generator
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.NeuroUIClass
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.CardFragment
class SecondFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val neuroAdapter by lazy { NeurosAdapter(::onNeuroClicked) }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Обращайся к элементам View здесь
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.neuroList.adapter = neuroAdapter
        neuroAdapter.updateItems(Generator.generate())
    }
    private fun onNeuroClicked(neuro: NeuroUIClass) {
        val bundle = bundleOf(CardFragment.CARD_NEURO_KEY to neuro)
        view?.findNavController()?.navigate(R.id.action_to_navigation_card, bundle)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}