package com.arvi.pokemondb.ui.screen.landing.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arvi.pokemondb.R
import com.arvi.pokemondb.ui.screen.landing.view.PokemonRow.IMAGE_PADDING_SIZE
import com.arvi.pokemondb.ui.screen.landing.view.PokemonRow.IMAGE_SIZE
import com.arvi.pokemondb.ui.screen.landing.view.PokemonRow.PADDING_ROW

@Composable
fun PokemonRow(
    modifier: Modifier = Modifier,
    pokemonName: String = "",
) {
    Row(
        modifier = modifier.padding(PADDING_ROW.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = stringResource(R.string.list_pokemon_image_description),
            modifier = modifier
                .size(IMAGE_SIZE.dp)
                .padding(IMAGE_PADDING_SIZE.dp)
        )
        Column {
            Text(
                text = pokemonName,
                style = TextStyle.Default,
            )
        }
    }
}

object PokemonRow {
    const val PADDING_ROW = 16
    const val IMAGE_SIZE = 60
    const val IMAGE_PADDING_SIZE = 8
}

@Preview
@Composable
fun PokemonRowPreview() {
    PokemonRow(pokemonName = "Pikachu")
}
