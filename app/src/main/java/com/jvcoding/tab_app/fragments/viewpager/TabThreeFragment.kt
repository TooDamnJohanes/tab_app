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
import com.jvcoding.tab_app.components.ModalBottomSheetDialogFragment
import com.jvcoding.tab_app.components.ModalBottomSheetDialogFragment.BottomSheetActions
import com.jvcoding.tab_app.databinding.FragmentTabThreeBinding
import com.jvcoding.tab_app.utils.TabUtils.requireBinding

class TabThreeFragment : Fragment() {

    private lateinit var binding: FragmentTabThreeBinding
    private lateinit var bottomSheetActions: BottomSheetActions
    private var uriList: MutableList<Uri> = mutableListOf()
    private lateinit var bottomSheetDialogFragment: ModalBottomSheetDialogFragment
    private lateinit var getContentUri: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabThreeBinding.inflate(inflater, container, false)
        bottomSheetDialogFragment = ModalBottomSheetDialogFragment.newInstance()
        initBottomSheetListeners()
        setListeners()
        initActivityResult()
        return binding.root
    }

    private fun initActivityResult() = requireBinding(binding) {
        getContentUri = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri: Uri? = result.data?.data
                uri?.let {
                    uriList.add(it)
                    bottomSheetDialogFragment.updateUriList(uriList)
                }
                Toast.makeText(requireContext(), "Imagem selecionada: $uri", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Tarefa cancelada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setListeners() = requireBinding(binding) {
        textViewSlider.setOnClickListener {
            bottomSheetDialogFragment.show(childFragmentManager, ModalBottomSheetDialogFragment::class.java.canonicalName)
            bottomSheetDialogFragment.setupPhotoUriList(uriList)
            bottomSheetDialogFragment.setupListener(bottomSheetActions)
        }
    }

    private fun initBottomSheetListeners() {
        bottomSheetActions = object : BottomSheetActions {
            override fun onCameraIconClick() {
                ImagePicker.with(requireActivity())
                    .cameraOnly()
                    .crop()
                    .createIntent { intent ->
                        getContentUri.launch(intent)
                    }
            }

            override fun onGalleryIconClick() {
                Toast.makeText(requireContext(), "Clicamos na galeria", Toast.LENGTH_SHORT).show()
            }

            override fun onImageDeleted(photoUri: Uri) {
                uriList.remove(photoUri)
                bottomSheetDialogFragment.updateUriList(uriList)
            }

        }
    }

}