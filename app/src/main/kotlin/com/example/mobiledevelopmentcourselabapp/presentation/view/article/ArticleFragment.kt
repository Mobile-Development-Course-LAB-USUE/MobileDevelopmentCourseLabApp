package com.example.mobiledevelopmentcourselabapp.presentation.view.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private var like = 0
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.thumbUp.setOnClickListener {
            like++
            updateLikeCount()
        }

        binding.thumbDown.setOnClickListener {
            if(like == 0)
            {

            }
            else{
                like--
            }

            updateLikeCount()
        }
        // Обращайся к элементам View здесь

        return root
    }


    private fun updateLikeCount() {
        binding.likeResult.text = like.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}