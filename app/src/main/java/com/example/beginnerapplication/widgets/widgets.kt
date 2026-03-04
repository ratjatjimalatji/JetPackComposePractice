package com.example.beginnerapplication.widgets

import Insurer
import ads_mobile_sdk.h6
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.beginnerapplication.R
import com.example.beginnerapplication.screens.home.DependentsScreen
import com.example.beginnerapplication.screens.home.ReusableOuterCard
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


//Medical Aid Selection

@Composable
fun MedicalAidSelectionContent() {
    // Wrapped in a LazyColumn  to handle scrolling
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item { ActivePlan(count = 1, typeOfPlan = "medical") }
        item { DependentsScreen() }
        item { MonthlyIncomeBracket() }
        item { IconTextSquare() }
    }
}

@Composable
fun ActivePlan(
    typeOfPlan: String,
    count: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clickable { },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .size(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // The Circle with Icon inside of it
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(5.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(R.drawable.ic_shield),
                        contentDescription = null,
                        tint = Color(0xFF823199),
                    )
                }
            }

            Column() {
                Column() {
                    Text(
                        text = "$count active $typeOfPlan plan",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                Column() { Text(text = "Tap to view coverage details") }
            }

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                Icon(
                    painterResource(R.drawable.ic_arrow_right),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

data class IconText(
    val iconRes: Int,
    val text: String,
    val description: String
)

@Composable
fun IconTextSquare() {

    var selectedItem by remember { mutableStateOf<IconText?>(null) }

    val listOfIcons = listOf(
        IconText(
            R.drawable.ic_hospital,
            "Hospital",
            "This plan is designed for individuals who are generally healthy and looking to mitigate the high costs of catastrophic events. It focuses strictly on in-patient care, covering costs incurred while you are admitted to a hospital (such as surgeries, ward fees, and theater costs)."
        ),
        IconText(
            R.drawable.ic_comprehensive,
            "Comprehensive",
            "Our highest level of risk transfer. This plan offers extensive cover for both major hospital events and day-to-day healthcare. It often includes above-threshold benefits, meaning if your savings run out, the insurer continues to pay for essential services. It also typically provides richer benefits for chronic medication and specialized treatments."
        ),
        IconText(
            R.drawable.ic_save,
            "Saving",
            "A middle-ground approach that combines \"peace of mind\" for hospital stays with a dedicated personal savings account. A portion of your monthly premium is set aside to cover day-to-day medical expenses like GP visits, prescribed medicine, and optometry. Once your savings are depleted, you typically pay for out-of-hospital costs yourself"
        )
    )
    ReusableOuterCard(
        "Insurance Type",
        R.drawable.ic_android
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
            ) {

                for (item in listOfIcons) {
                    // 1. The outer Column handles the positioning of 1 CARD
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(5.dp)
                    ) {
                        val isSelected = selectedItem == item // checks if the card is selected
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { selectedItem = item },
                            border = if (isSelected) BorderStroke(
                                2.dp,
                                Color(0xFF823199)
                            ) else null,
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) Color.White else Color(0xFFF5F5F5)
                            )
                        ) {
                            // 2. Column that stacks the icon and text vertically
                            Column(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                // The Circle with Icon inside of it
                                Box(
                                    modifier = Modifier
                                        .size(40.dp) //circle size
                                        .background(color = Color.LightGray, shape = CircleShape),
                                    contentAlignment = Alignment.Center // Centers the Icon inside the Circle
                                ) {
                                    Icon(
                                        painter = painterResource(item.iconRes),
                                        contentDescription = null,
                                        tint = Color(0xFF823199),
                                    )
                                }
                                // The label below the circle w/ Icon
                                Text(
                                    text = item.text,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    minLines = 2,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                }
            }
            // Description Area
            selectedItem?.let { item ->
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = item.text.uppercase(),
                        color = Color(0xFF823199),
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = item.description,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}


@Composable
fun MonthlyIncomeBracket() {
    ReusableOuterCard(
        "Monthly Income Bracket", R.drawable.ic_currency,
    ) {
        DropdownDemo()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownDemo() {
    // 1. Cleaner naming conventions
    var isExpanded by remember { mutableStateOf(false) }
    val monthlyIncomes = remember {
        listOf(
            "R0 - R5,000",
            "R5,001 - R10,000",
            "R10,001 - R20,000",
            "R20,001 - R40,000",
            "R40,001 - R60,000",
            "R60,001 - R80,000",
            "R80,001 - R100,000",
            "Over R100,000"
        )
    }
    var selectedMonthlyIncome by remember { mutableStateOf(monthlyIncomes[2]) } // Default to first item or empty

    Column(Modifier.fillMaxWidth(0.9f)) {
        // 2. Use the specialized Dropdown Container
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            //
            OutlinedTextField(
                readOnly = true,
                value = selectedMonthlyIncome,
                onValueChange = {},
                label = { Text("Select Monthly Income") },
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                // 4. Use the built-in trailing icon that animates automatically
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                monthlyIncomes.forEach { monthlyIncome ->
                    DropdownMenuItem(
                        text = { Text(monthlyIncome) },
                        onClick = {
                            selectedMonthlyIncome = monthlyIncome
                            isExpanded = false
                        },
                        // 5. Highlighting the selection (Best Practice)
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}


