package com.example.myapplication.component.upload

import androidx.lifecycle.ViewModel
import io.github.vinceglb.filekit.core.PlatformFiles

class UploadDocumentComponentViewModel : ViewModel() {
    fun onFilePicked(files: PlatformFiles?) {
        // do something and update state
        println(files)
    }
}