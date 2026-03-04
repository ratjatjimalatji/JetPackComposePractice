package com.example.beginnerapplication.widgets

import Insurer
import ads_mobile_sdk.h6
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.beginnerapplication.R
import getInsurers


//Card that displays all insurers details
@Preview
    @Composable
    fun InsurerRow(insurer: Insurer = getInsurers()[0], onItemClick: (String) -> Unit = {}) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = { onItemClick(insurer.id) }) //USE ONCLICK AND NOT.CLICKABLE
    {
        Column() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {
                Surface(
                    modifier = Modifier
                        .padding(12.dp)
                        .size(100.dp),
                    shape = RectangleShape
                )
                {
//                //coil library allows images to be retrieved from URL
//                Image(model = insurer.images[0],
//                    contentDescription = "Insurer Image",
//                   builder = {
//                       crossfade(true),
//                       transformation()
//                   } )
                    Icon(
                        painter = painterResource(R.drawable.ic_group), null,
                        modifier = Modifier.padding(20.dp)
                    )
                }
                Column(
                    modifier = Modifier.padding(2.dp)
                        .background(Color.Green)
                        .fillMaxWidth(0.9f)
                ) {
                    Text(
                        text = insurer.name,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "Head office: ${insurer.headOffice}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Category: ${insurer.category}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else
                        Icons.Filled.KeyboardArrowDown,

                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray
                )

            }
            Row(modifier = Modifier.padding(4.dp)){
                AnimatedVisibility(visible = expanded) {
                    Column() {
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            {
                                append("Insurer:")
                            }

                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 13.sp
                                )
                            )
                            {
                                append(insurer.description)
                            }
                        })
                    }
                }
            }
        }
    }
}
