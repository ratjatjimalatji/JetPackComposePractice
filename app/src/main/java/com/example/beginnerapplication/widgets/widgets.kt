package com.example.beginnerapplication.widgets

import Insurer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.beginnerapplication.R
import com.example.beginnerapplication.screens.home.CenteredTitle
import com.example.beginnerapplication.screens.home.DependentsScreen
import com.example.beginnerapplication.screens.home.ReusableContentHolderRow

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
        Column(modifier = Modifier.background(Color(0xFFF5F5F5))) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {
                Box( contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(12.dp)
                        .size(100.dp).background(color = Color.White, shape = RoundedCornerShape(15.dp))
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
                        painter = painterResource(R.drawable.ic_group), contentDescription = null,
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxSize()
                    )
                }
                Column(
                    modifier = Modifier.padding(2.dp)
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
                        }, modifier = Modifier.padding(4.dp))
                        Divider(modifier = Modifier.padding(3.dp))
                        Text(text = "Year established: ${insurer.yearEstablished}")
                        Text(text = "Contact details: ${insurer.contactNumber}")
                    }
                }
            }
        }
    }
}
//Life Insurance Quote
@Composable
fun LifeInsuranceQuoteContent() {
    // Wrapped in a LazyColumn  to handle scrolling
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var list = listOf(
            TextSwitch("Smoker status", "Smoked in the last 12 month"),
            TextSwitch("Dangerous Hobbies", "Skydiving, racing, etc.")
        )

        item { ActivePlan(count = 1, typeOfPlan = "medical") }
        item { SliderAdvancedExample() }
        item { TextWithSwitch(list) }
        item { OccupationDropDown() }
    }
}
@Composable
fun OccupationDropDown() {
    ReusableContentHolderRow(
        "Occupation",R.drawable.ic_briefcase,
    ) {

        val dropDownOptions = remember {
            listOf(
                "Office Administrator", "Human Resources Coordinator", "Project Manager", "Accountant", "Business Analyst", "Digital Marketing Specialist", "Legal Advisor", "Systems Administrator", "Financial Planner", "Operations Manager",
 "Retail Sales Assistant", "Cashier", "Call Centre Operator", "Warehouse Worker", "Security Guard"
            )
        }
        DropdownMenu("Select occupation", dropDownOptions, )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SliderAdvancedExample() {
    var sliderPosition by remember { mutableFloatStateOf(500000f) }
    val sliderInMillions = sliderPosition / 1000000
    val formattedValue = "%.2f".format(sliderInMillions)

    CenteredTitle(title = "Coverage Details")
    ReusableContentHolderRow("Coverage Details", R.drawable.ic_shield_blank) {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center,
        ) {
            Card(modifier = Modifier) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFFF5F5F5))
                        .padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row()
                    {
                        Text(text = "How much cover do you need? ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(5.dp))

                    }
                    Text(
                        text = "R$formattedValue m",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF823199),

                        )
                    Slider(
                        modifier = Modifier,
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        valueRange = 500000f..10000000f,
                        steps = 189,
                        colors = SliderDefaults.colors(
                            activeTickColor = Color(0xFF823199),
                            inactiveTickColor = Color(0xFF823199),
                            thumbColor = Color(0xFF823199),
                            activeTrackColor = Color(0xFF927199),
                            inactiveTrackColor = Color.White,
                            disabledThumbColor = Color.Gray,
                        )
                    )
                    Text(
                        text = "Range: R500K - R10m",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF823199)
                    )
                }
            }
        }
    }

}

    data class TextSwitch(val header:String, val description:String)
    @Composable
    fun TextWithSwitch( lifeStyleRiskSwitchList: List<TextSwitch>) {
ReusableContentHolderRow("Lifestyle & risk", R.drawable.ic_heart) {
        for (i in lifeStyleRiskSwitchList) {
            var checked by remember { mutableStateOf(false) }

            Row(modifier = Modifier.padding(4.dp).background(Color.White)) {
                Column(modifier = Modifier.fillMaxWidth(0.9f)) {
                    Column() { Text(i.header, fontWeight = FontWeight.Bold) }
                    Column() { Text(i.description) }
                }
                Column() {
                    Switch(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = Color(0xFF823199)
                        , uncheckedTrackColor = Color(0xFFF5F5F5))
                    )
                }
            }
            if(lifeStyleRiskSwitchList.last() != i)
                HorizontalDivider()
        }
    }}

//Medical Aid Selection
@Composable
fun MedicalAidSelectionContent() {
    // Wrapped in a LazyColumn  to handle scrolling
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        //contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { ActivePlan(count = 1, typeOfPlan = "medical") }
        item { DependentsScreen() }
        item { MonthlyIncomeBracket() }
        item { GroupOfSquareIconsWithText() }
    }
}

@Composable
fun ActivePlan(
    typeOfPlan: String,
    count: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .clickable { },
        elevation = CardDefaults.cardElevation()
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
    HorizontalDivider(modifier = Modifier.fillMaxWidth(0.9f).padding(vertical = 5.dp))
}

data class IconText(
    val iconRes: Int,
    val text: String,
    val description: String
)

@Composable
fun GroupOfSquareIconsWithText() {

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
    ReusableContentHolderRow(
        "Insurance Type",
        R.drawable.ic_shield
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
                            .padding(top =5.dp, start = 5.dp, end = 5.dp)
                            .weight(1f)
                            .fillMaxHeight()
                            ,
                        verticalArrangement = Arrangement.Center
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
                                        .size(70.dp) //circle size
                                        .background(color = Color.LightGray, shape = CircleShape),
                                    contentAlignment = Alignment.Center // Centers the Icon inside the Circle
                                ) {
                                    Icon(
                                        painter = painterResource(item.iconRes),
                                        contentDescription = null,
                                        tint = Color(0xFF823199),
                                        modifier = Modifier
                                            .size(40.dp)
                                    )
                                }
                                // The label below the circle w/ Icon
                                Text(
                                    text = item.text,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        }
                    }
                }
            }
            // Description Area
            selectedItem?.let { item ->
                Column(modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp)) {
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
    ReusableContentHolderRow(
        "Monthly Income Bracket", R.drawable.ic_currency,
    ) {

        val dropDownOptions = remember {
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
        DropdownMenu("Select Monthly Income", dropDownOptions, )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(dropDownLabel: String, dropDownOptions: List<String>) {
    // 1. Cleaner naming conventions
    var isExpanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(dropDownOptions[2]) } // Default to first item or empty

    Column(Modifier.fillMaxWidth(0.9f)) {
        // 2. Use the specialized Dropdown Container
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            //
            OutlinedTextField(
                readOnly = true,
                value = selectedOption,
                onValueChange = {},
                label = { Text(dropDownLabel) },
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
                dropDownOptions.forEach { monthlyIncome ->
                    DropdownMenuItem(
                        text = { Text(monthlyIncome) },
                        onClick = {
                            selectedOption = monthlyIncome
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


