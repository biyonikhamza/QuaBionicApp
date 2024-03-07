package com.bionichamza.quabionicapp.ui.fragments.inspiration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quabionicapp.R
import com.example.quabionicapp.databinding.FragmentInspirationBinding

class InspirationFragment : Fragment() {

    private var _binding : FragmentInspirationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInspirationBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}