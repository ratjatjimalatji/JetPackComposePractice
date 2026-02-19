package com.example.beginnerapplication.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo

class ComposeItems {
}
@Composable
fun IconText(
    iconRes: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = text, // Good for accessibility
            modifier = Modifier.size(48.dp) // Optional: set a standard size
        )

        // Add a small gap between icon and text
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun RowOfIcons(
    //modifier: Modifier = Modifier, listOfIconText: List<IconText>

){
    HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(top=8.dp))
    //List of options


    HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(bottom=8.dp))
}

@Composable
fun IconSelectionOptions(modifier: Modifier = Modifier.fillMaxSize(),
                         ){
//    Row(){
//        Column(modifier = Modifier.weight(1f))
//    }
}