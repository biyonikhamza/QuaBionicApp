package com.bionichamza.quabionicapp.ui.fragments.homebio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quabionicapp.databinding.FragmentHomeProsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeBioFragment : Fragment() {

    private var _binding : FragmentHomeProsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeProsBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}