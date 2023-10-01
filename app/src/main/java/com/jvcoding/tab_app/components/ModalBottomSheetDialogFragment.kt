package com.jvcoding.tab_app.components

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jvcoding.tab_app.adapter.GridRecyclerViewAdapter
import com.jvcoding.tab_app.adapter.OnPhotoDeleteClickListener
import com.jvcoding.tab_app.databinding.BottomsheetLayoutBinding
import com.jvcoding.tab_app.utils.TabUtils.requireBinding

class ModalBottomSheetDialogFragment: BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = ModalBottomSheetDialogFragment()
    }

    private var _bottomSheetListener: BottomSheetActions? = null
    private lateinit var binding: BottomsheetLayoutBinding
    private var photosUriList: List<Uri> = listOf()
    private lateinit var onPhotoDeleteClickListener: OnPhotoDeleteClickListener
    private lateinit var recyclerViewAdapter: GridRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomsheetLayoutBinding.inflate(inflater, container, false)
        initDialog()
        initListeners()
        initOnPhotoDeleteClickListener()
        initRecyclerView()
        return binding.root
    }

    fun setupListener(bottomSheetActions: BottomSheetActions) {
        _bottomSheetListener = bottomSheetActions
    }

    fun setupPhotoUriList(newPhotosUriList: MutableList<Uri>) {
        photosUriList = newPhotosUriList
    }

    fun updateUriList(newUriList: List<Uri>) {
        photosUriList = newUriList
        recyclerViewAdapter.updateList(newUriList)
    }

    private fun initOnPhotoDeleteClickListener() {
        onPhotoDeleteClickListener = object : OnPhotoDeleteClickListener {
            override fun onPhotoDelete(uri: Uri) {
                _bottomSheetListener?.onImageDeleted(uri)
            }

        }
    }

    private fun initRecyclerView() = requireBinding(binding) {
        recyclerViewAdapter = GridRecyclerViewAdapter(photosUriList, onPhotoDeleteClickListener)
        recyclerViewBottomSheetComponentImages.adapter = recyclerViewAdapter
        recyclerViewBottomSheetComponentImages.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun initDialog() = requireBinding(binding) {
        requireDialog().window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireDialog().window?.statusBarColor = requireContext().getColor(com.google.android.material.R.color.mtrl_btn_transparent_bg_color)
    }

    private fun initListeners() = requireBinding(binding) {
        constraintLayoutBottomSheetComponentTakePictureContainer.setOnClickListener {
            _bottomSheetListener?.onCameraIconClick()
        }

        constraintLayoutBottomSheetComponentPickGalleryContainer.setOnClickListener {
            _bottomSheetListener?.onGalleryIconClick()
        }
    }

    interface BottomSheetActions {
        fun onCameraIconClick()
        fun onGalleryIconClick()
        fun onImageDeleted(photoUri: Uri)
    }
}
