package com.arvi.pokemondb.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val pokeId: String = "",
    val name: String = "",
) : Parcelable
