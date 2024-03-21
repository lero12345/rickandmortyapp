package com.barquero.rickandmorty.presentation.ui.atomic.organisms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.barquero.rickandmorty.R
import com.barquero.rickandmorty.data.api.CharacterApiModel
import com.barquero.rickandmorty.data.api.CharacterInfoApiModel
import com.barquero.rickandmorty.presentation.ui.theme.colors

@Composable
fun CharactersList(
    characterList: List<CharacterInfoApiModel>,
    lazyGridState: LazyGridState = rememberLazyGridState(),
    config: CharacterSpanSizeConfig = CharacterSpanSizeConfig(3)
) {
    val imageSize = ImageSize.getImageFixedSize()
    LazyVerticalGrid(
        modifier = Modifier.background(colors.background),
        columns = GridCells.Fixed(config.gridSpanSize),
        state = lazyGridState
    ) {
        items(characterList.size, span = { index ->
            val spinSize = when (characterList[index]) {
                is CharacterInfoApiModel -> config.characterColumnSpanSize
                else -> config.footerColumnSpanSize
            }
            GridItemSpan(spinSize)
        }) { index ->
            when (val character = characterList[index]) {
                is CharacterInfoApiModel -> CharacterItem(character, imageSize)
                else -> Loader()
            }
        }
    }

}

@Composable
private fun CharacterItem(
    character: CharacterInfoApiModel,
    imageSize: ImageSize,
    onCharacterClick: (movieId: Int) -> Unit = {}
) {
    SubcomposeAsyncImage(
        model = character.image,
        loading = { CharacterItemPlaceholder() },
        error = { CharacterItemPlaceholder() },
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(3.dp)
            .size(imageSize.width, imageSize.height)
            .clickable { }
            .clip(RoundedCornerShape(2))
    )
}

@Composable
fun CharacterItemPlaceholder() {
    Image(
        painter = painterResource(id = R.drawable.bg_image),
        contentDescription = "",
        contentScale = ContentScale.Crop,
    )
}

/**
 * @property gridSpanSize - The total number of columns in the grid.
 * @property separatorColumnSpanSize - Returns the number of columns that the item occupies.
 * @property footerColumnSpanSize - Returns the number of columns that the item occupies.
 **/
data class CharacterSpanSizeConfig(val gridSpanSize: Int) {
    val characterColumnSpanSize: Int = 1
    val separatorColumnSpanSize: Int = gridSpanSize
    val footerColumnSpanSize: Int = gridSpanSize
}

private class ImageSize(
    val width: Dp,
    val height: Dp
) {
    companion object {
        @Composable
        fun getImageFixedSize(): ImageSize {
            val configuration = LocalConfiguration.current
            val imageWidth = configuration.screenWidthDp.dp / 3
            val imageHeight = imageWidth.times(1.3f)
            return ImageSize(imageWidth, imageHeight)
        }
    }
}