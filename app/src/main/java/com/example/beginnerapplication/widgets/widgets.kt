package com.example.beginnerapplication.widgets

import Insurer
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import androidx.navigation.NavController
import com.example.beginnerapplication.R
import com.example.beginnerapplication.model.DescriptiveTextWithIcon
import com.example.beginnerapplication.model.TextSwitch
import com.example.beginnerapplication.navigation.InsuranceScreens


import getInsurers

const val customPurple: Long = 0xFF823199
const val lightGray: Long = 0xFFF5F5F5

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableContentHolderRow(
    title: String,
    iconRes: Int? = null,
    content: @Composable ColumnScope.() -> Unit // Allows children to use Column features
) {
    Row( //Changed from card to row
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(bottom = 5.dp)
        //Uncomment below if you want to revert to a card
//        , colors = CardDefaults.cardColors(
//            containerColor = Color.White // Card background color
//        ), elevation = CardDefaults.cardElevation(
//            defaultElevation = 8.dp
//        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            //.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // CENTERS ALL CHILDREN
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 10.dp),
                verticalAlignment = CenterVertically,
            ) {
                iconRes?.let { Icon(painterResource(it), contentDescription = null, tint = Color(0xFF823199)) }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title, fontWeight = FontWeight.Bold)
            }

            // This is where your inner card (or anything else) will be injected
            content()
            Spacer(modifier = Modifier.height(2.dp))
            HorizontalDivider(modifier = Modifier.fillMaxWidth(0.95f).background(Color.LightGray))
        }
    }
}

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
        Column(modifier = Modifier.background(Color(lightGray))) {
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
                        HorizontalDivider(modifier = Modifier.padding(3.dp))
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
        val list = listOf(
            TextSwitch("Smoker status", "Smoked in the last 12 month"),
            TextSwitch("Dangerous Hobbies", "Skydiving, racing, etc.")
        )

        item { ActivePlan(count = 1, typeOfPlan = "medical") }
        item { AdvancedSlider() }
        item { DescriptiveSwitch(list) }
        item { OccupationDropDown() }
        item { ButtonWithText("Compare 5 plans") }
    }
}
@Composable
fun OccupationDropDown() {
    ReusableContentHolderRow(
        "Occupation",R.drawable.ic_briefcase,
    ) {
        val dropDownOptions = remember {
            listOf("Office Administrator", "Human Resources Coordinator", "Project Manager", "Accountant", "Business Analyst", "Digital Marketing Specialist", "Legal Advisor", "Systems Administrator", "Financial Planner", "Operations Manager",
                    "Retail Sales Assistant", "Cashier", "Call Centre Operator", "Warehouse Worker", "Security Guard")
        }
        DropdownMenu("Select occupation", dropDownOptions, )
    }
}
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AdvancedSlider() {
    var sliderPosition by remember { mutableFloatStateOf(500000f) }
    val sliderInMillions = sliderPosition / 1000000
    val formattedValue = "%.2f".format(sliderInMillions)

        ReusableContentHolderRow("Coverage Details", R.drawable.ic_shield_blank) {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center,
        ) {
            Card(modifier = Modifier) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(lightGray))
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
                        color = Color(customPurple),)

                    Slider(
                        modifier = Modifier,
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        valueRange = 500000f..10000000f,
                        steps = 189,
                        colors = SliderDefaults.colors(
                            activeTickColor = Color(customPurple),
                            inactiveTickColor = Color(customPurple),
                            thumbColor = Color(customPurple),
                            activeTrackColor = Color(customPurple),
                            inactiveTrackColor = Color.White,
                            disabledThumbColor = Color.Gray,
                        )
                    )
                    Text(
                        text = "Range: R500K - R10m",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(customPurple)
                    )
                }
            }
        }
    }

}

    @Composable
    fun DescriptiveSwitch(lifeStyleRiskSwitchList: List<TextSwitch>) {
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
                            checkedTrackColor = Color(customPurple)
                        , uncheckedTrackColor = Color(lightGray))
                    )
                }
            }
            if(lifeStyleRiskSwitchList.last() != i)
                HorizontalDivider()
        }
    }
    }

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
        item { GroupOfSquareIconsWithAnimationForText() }
        item { ButtonWithText("Compare 8 plans") }
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
            .clickable { TODO()},
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
                // The Grey box with icon inside of it
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(lightGray), shape = RoundedCornerShape(5.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(R.drawable.ic_shield),
                        contentDescription = null,
                        tint = Color(customPurple),
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

@Composable
fun InsurerListContent(
    navController: NavController,
    insurersList: List<Insurer> = getInsurers()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items = insurersList) {
                InsurerRow(insurer = it) { insurer ->
                    navController.navigate(route = InsuranceScreens.DetailsScreen.name+"/$insurer")
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
        }
    }
}


@Composable
fun DependentStepper(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row(
        //colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        modifier = Modifier
            .fillMaxWidth(0.9f)
    ) {

        Row(
            modifier = Modifier
            //.background(Color.Red),
            ,
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Start // Pushes text to left, controls to right
        ) {

            Text(text = "Spouse & children", modifier = Modifier.weight(1f))
            // Increment number of dependents Controls
            Row(verticalAlignment = CenterVertically) {
                IconButton(onClick = onDecrement) {
                    Icon(
                        painterResource(R.drawable.ic_minus),
                        contentDescription = "Decrease"
                    )
                }

                Text(
                    text = count.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                IconButton(onClick = onIncrement) {
                    Icon(
                        painterResource(R.drawable.ic_add),
                        contentDescription = "Increase"
                    )
                }
            }
        }
    }
}

@Composable
fun DependentsScreen() {
    var count by remember { mutableIntStateOf(0) }
    ReusableContentHolderRow(
        title = "Number of dependents",
        iconRes = R.drawable.ic_group
    ) {
        DependentStepper(
            count = count,
            onIncrement = { count++ },
            onDecrement = { if (count > 0) count-- }
        )
    }
}
@Composable
fun GroupOfSquareIconsWithAnimationForText() {

    var selectedItem by remember { mutableStateOf<DescriptiveTextWithIcon?>(null) }

    val listOfIcons = listOf(
        DescriptiveTextWithIcon(
            R.drawable.ic_hospital,
            "Hospital",
            "This plan is designed for individuals who are generally healthy and looking to mitigate the high costs of catastrophic events. It focuses strictly on in-patient care, covering costs incurred while you are admitted to a hospital (such as surgeries, ward fees, and theater costs)."
        ),
        DescriptiveTextWithIcon(
            R.drawable.ic_comprehensive,
            "Comprehensive",
            "Our highest level of risk transfer. This plan offers extensive cover for both major hospital events and day-to-day healthcare. It often includes above-threshold benefits, meaning if your savings run out, the insurer continues to pay for essential services. It also typically provides richer benefits for chronic medication and specialized treatments."
        ),
        DescriptiveTextWithIcon(
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
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        val isSelected = selectedItem == item // checks if the card is selected
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { selectedItem = item },
                            border = if (isSelected) BorderStroke(
                                2.dp,
                                Color(customPurple)
                            ) else null,
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) Color.White else Color(lightGray)
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
                                        tint = Color(customPurple),
                                        modifier = Modifier
                                            .size(40.dp)
                                    )
                                }
                                // The text label below the circle w/ Icon
                                Text(
                                    text = item.heading,
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
            // Description area that appears after a card is selected
            selectedItem?.let { item ->
                Column(modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp)) {
                    Text(
                        text = item.heading.uppercase(),
                        color = Color(customPurple),
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

@Composable
fun ButtonWithText(text: String) {
    Box(
        modifier = Modifier
            .background(Color(customPurple), shape = RoundedCornerShape(5.dp))
            .fillMaxWidth(0.8f)
            .clickable{ TODO()}
            .padding(10.dp),
        contentAlignment = Alignment.Center,

        ) {
        Row() {
            Text(text, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "forward arrow",
                tint = Color.White
            )
        }
    }
}