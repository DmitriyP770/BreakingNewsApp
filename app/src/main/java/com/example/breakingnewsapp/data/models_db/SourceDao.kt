package com.example.breakingnewsapp.data.models_db

import android.os.Parcelable
import com.example.breakingnewsapp.domain.entity.Source
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceDao(
    val id: String?,
    val name: String?
) : Parcelable{
    fun toSource(): Source{
        return Source(id, name)
    }
}