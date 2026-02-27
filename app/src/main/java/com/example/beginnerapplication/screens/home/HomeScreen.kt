package com.example.beginnerapplication.screens.home

import Insurer
import com.example.beginnerapplication.R
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.beginnerapplication.navigation.InsuranceScreens
import com.example.beginnerapplication.widgets.InsurerRow
import getInsurers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Medical Aid Screen",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        // innerPadding ensures content starts below the TopAppBar
        Box(modifier = Modifier.padding(innerPadding)) {

            InsurerListContent(navController = navController)

            //MedicalAidSelectionContent()

            //LifeInsuranceQuoteContent()
        }
    }
}


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
fun LifeInsuranceQuoteContent() {
    // Wrapped in a LazyColumn  to handle scrolling
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item { ActivePlan(count = 1, typeOfPlan = "medical") }
        item { SliderAdvancedExample() }
    }
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


@Composable
fun CenteredTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium, // Optional styling
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth() // Fills width to allow centering
            .padding(horizontal = 4.dp, vertical = 8.dp) // Optional padding
    )
}

data class ToggleableInfo(
    val isChecked: Boolean,
    val text: String
)

@Composable
private fun Checkboxes() {
    val checkboxOptions = remember {
        mutableStateListOf(
            ToggleableInfo(false, "Starters"),
            ToggleableInfo(false, "Sides"),
            ToggleableInfo(false, "Main Course"),
            ToggleableInfo(false, "Drink"),
            ToggleableInfo(false, "Dessert"),
        )
    }

    // Use a Column to stack our "chunks" vertically
    Column(modifier = Modifier.fillMaxWidth()) {
        // chunked(2) splits the list into groups: [[Starters, Sides], [Main, Drink], [Dessert]]
        checkboxOptions.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically,

                ) {
                rowItems.forEach { info ->
                    // Find the actual index in the original list to update state correctly
                    val originalIndex = checkboxOptions.indexOf(info)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        //modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.End// Ensure equal width for both items
                    ) {
                        Checkbox(
                            checked = info.isChecked,
                            onCheckedChange = { isChecked ->
                                if (originalIndex != -1) {
                                    checkboxOptions[originalIndex] =
                                        info.copy(isChecked = isChecked)
                                }
                            }
                        )
                        Text(text = info.text)
                    }
                }

                // If a row has only 1 item (like Dessert), add a spacer so it doesn't stretch
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(12.dp))

    SliderAdvancedExample()


}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SliderAdvancedExample() {
    var sliderPosition by remember { mutableFloatStateOf(500000f) }
    val sliderInMillions = sliderPosition / 1000000
    val formattedValue = "%.2f".format(sliderInMillions)

    CenteredTitle(title = "Coverage Details")
    ReusableOuterCard("Coverage Details", R.drawable.ic_android) {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center,
        ) {
            Card() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row()
                    {
                        Text(text = "How much cover do you need? ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "R$formattedValue m",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF823199)
                        )
                    }

                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        valueRange = 500000f..10000000f,
                        steps = 189
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

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    supportingText: String? = null,
    leadingIcon: Int? = null,
    prefix: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    isSuccess: Boolean = true,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label?.let { { Text(it) } },
        placeholder = placeholder?.let { { Text(it) } },
        supportingText = supportingText?.let { { Text(it) } },
        leadingIcon = leadingIcon?.let {
            { Icon(painterResource(id = it), contentDescription = null) }
        },
        prefix = prefix?.let { { Text(it) } },
        textStyle = LocalTextStyle.current.copy(
            shadow = Shadow(Color.Gray),
            textAlign = TextAlign.Left
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        singleLine = singleLine,
        modifier = modifier.textFieldSuccess(isSuccess)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableOuterCard(
    title: String,
    iconRes: Int,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit // Allows children to use Column features
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.95f)
            .padding(vertical = 10.dp), colors = CardDefaults.cardColors(
            containerColor = Color.White // Card background color
        ), elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // CENTERS ALL CHILDREN
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(painterResource(iconRes), contentDescription = null, tint = Color(0xFF823199))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title, fontWeight = FontWeight.Bold)
            }
            // This is where your inner card (or anything else) will be injected
            content()
        }
    }
}

@Composable
fun DependentStepper(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        modifier = Modifier
            .fillMaxWidth(0.9f)
    ) {

        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start // Pushes text to left, controls to right
        ) {

            Text(text = "Spouse & children", modifier = Modifier.weight(1f))
            // Increment number of dependents Controls
            Row(verticalAlignment = Alignment.CenterVertically) {
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
    ReusableOuterCard(
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

// 2. THE DEDICATED PREVIEW
@Preview(showBackground = true, name = "Form Example")
@Composable
fun CustomTextFieldPreview() {
    // Inside a preview, we pass the specific strings we want to visualize
    CustomTextField(
        value = "Default value",
        onValueChange = {}, // Empty lambda for static preview
        label = "Label",
        placeholder = "Place holder",
        supportingText = "*supporting text",
        prefix = "Prefix",
        leadingIcon = R.drawable.ic_launcher_foreground
    )
}


//composed is used when using state
fun Modifier.animateIconTintOnce(
    trigger: Boolean,
    duration: Int = 1000
): Modifier = composed {
    val color = remember { Animatable(Color.Black) }
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(trigger) {
        if (trigger) {
            launch {
                rotation.animateTo(180f, tween(duration))
            }
            launch {
                color.animateTo(Color(0xFF066623), tween(duration))
            }
        }
    }

    this
        .graphicsLayer(
            rotationZ = rotation.value,
            // Offscreen allows the BlendMode to work against the
            // icon's pixels rather than the background behind it.
            compositingStrategy = CompositingStrategy.Offscreen
        )
        .drawWithCache {
            onDrawWithContent {
                drawContent()
                drawRect(
                    color = color.value,
                    blendMode = BlendMode.SrcAtop
                )
            }
        }
}

fun Modifier.textFieldSuccess(
    trigger: Boolean,
    duration: Int = 1000
): Modifier = composed {
    val color = remember { Animatable(Color.Black) }
//

    this
        .graphicsLayer(
            // Offscreen allows the BlendMode to work against the
            // icon's pixels rather than the background behind it.
            compositingStrategy = CompositingStrategy.Offscreen
        )
        .drawWithCache {
            onDrawWithContent {
                drawContent()
                drawRect(
                    color = color.value,
                    blendMode = BlendMode.SrcAtop
                )
            }
        }
}

@Composable
fun ProductInfoRow(productItem: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = productItem.name, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold
                )
                Text(text = "R ${productItem.price}")
            }
            Text(
                text = productItem.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

open class Product(
    val name: String,
    val description: String,
    val price: Float
) {
    fun addProduct(product: Product) {
        println("$product.name (${product.price}) was added to the list")
    }
}

class Food(category: String, name: String, description: String, price: Float) :
    Product(name, description, price) {
    // override fun addProduct(name: String, description:String, price:Float, category: String)
}

var fries = Food("sides", "French fries", "Sweet and salty", 35.00F)


