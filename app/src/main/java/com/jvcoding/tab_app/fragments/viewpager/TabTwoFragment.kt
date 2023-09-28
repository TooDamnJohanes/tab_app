package com.jvcoding.tab_app.fragments.viewpager

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.github.dhaval2404.imagepicker.ImagePicker
import com.jvcoding.tab_app.databinding.FragmentTabTwoBinding
import com.jvcoding.tab_app.utils.TabUtils.requireBinding

class TabTwoFragment : Fragment() {
    private var _binding: FragmentTabTwoBinding? = null
    private val binding get() = _binding!!

    private lateinit var getContentUri: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabTwoBinding.inflate(inflater, container, false)
        initActivityResult()
        initObservers()
        return binding.root
    }

    private fun initActivityResult() = requireBinding(binding) {
        getContentUri = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri: Uri? = result.data?.data
                pickerImage.setImageURI(uri)
                Toast.makeText(requireContext(), "Imagem selecionada: $uri", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Tarefa cancelada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initObservers() = requireBinding(binding) {
        gallery.setOnClickListener {
            ImagePicker.with(requireActivity())
                .galleryOnly()
                .galleryMimeTypes(arrayOf("image/*"))
                .crop()
                .maxResultSize(400, 400)
                .createIntent { intent ->
                    getContentUri.launch(intent)
                }

        }
        camera.setOnClickListener {
            ImagePicker.with(requireActivity())
                .cameraOnly()
                .crop()
                .createIntent { intent ->
                    getContentUri.launch(intent)
                }
        }
    }
}