package com.example.mobiledevelopmentcourselabapp.presentation.view.article

import android.os.Bundle
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {
    private var score = 0
        set(value) {
            field = value
            _binding?.countLikes?.text = score.toString()
        }

    private var _binding: FragmentArticleBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Обращайся к элементам View здесь

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        score = savedInstanceState?.getInt(SCORE_TAG) ?: 0
        binding.thumbUp.setOnClickListener { score++ }
        binding.thumbDown.setOnClickListener {
            if (score > 0) {
                score--
                binding.countLikes.text = score.toString()
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SCORE_TAG, score)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        private const val SCORE_TAG = "SCORE"
    }
}