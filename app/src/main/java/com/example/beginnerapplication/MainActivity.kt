package com.example.beginnerapplication

import android.os.Build
import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beginnerapplication.ui.theme.BeginnerApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    var greenColour = Color(0xFF066623)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeginnerApplicationTheme {
                var name by remember { mutableStateOf("") }
                var description by remember { mutableStateOf("") }
                var price by remember { mutableStateOf("") }

                var listOfProducts by remember { mutableStateOf(listOf<Product>()) }

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 50.dp)
                    ) {
                        CardWithContent()
                        CenteredTitle(title = "Enter product details")
                        CustomTextField(
                            modifier = Modifier.textFieldSuccess(true),
                            value = name,
                            onValueChange = { textName -> name = textName },
                            label = "Item name",
                            placeholder = "6 Samoosa's",
                            leadingIcon = R.drawable.ic_food,
                            isSuccess = true,
                            supportingText = "*required"
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        CustomTextField(
                            modifier = Modifier.textFieldSuccess(true),
                            value = description,
                            onValueChange = { textDesc -> description = textDesc },
                            label = "Description",
                            placeholder = "Describe the product",
                            leadingIcon = R.drawable.ic_description,
                            isSuccess = true,
                            supportingText = "*required",
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        CustomTextField(
                            modifier = Modifier.textFieldSuccess(true),
                            value = price,
                            onValueChange = { textPrc -> price = textPrc },
                            label = "Price",
                            prefix = "R",
                            placeholder = "500",
                            leadingIcon = R.drawable.ic_currency,
                            isSuccess = true,
                            supportingText = "*required",
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Search
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        if (name.isBlank() || description.isBlank() || price.isBlank()) {
                            Icon(
                                painterResource(R.drawable.ic_thumb_down_24),
                                contentDescription = "Name valid icon",
                            )
                        } else {
                            Icon(
                                painterResource(R.drawable.ic_thumb_down_24),
                                contentDescription = "Name valid icon",
                                modifier = Modifier.animateIconTintOnce(true),
                                tint = greenColour
                            )
                        }



                        Spacer(modifier = Modifier.height(8.dp))
                        Column() {
                            Checkboxes()
                        }
                        Button(
                            shape = RoundedCornerShape(5.dp),

                            onClick = {
                                val priceAsFloat = price.toFloatOrNull() ?: 0f
                                if (name.isNotBlank() && description.isNotBlank() && price.isNotBlank()) {
                                    val NewProduct = Product(
                                        name = name.trim(),
                                        description = description.trim(),
                                        price = priceAsFloat
                                    )
                                    listOfProducts = listOfProducts + NewProduct


                                    name = ""
                                    description = ""
                                    price = ""
                                    NewProduct.addProduct(NewProduct)
                                }
                            }
//                            }, modifier = Modifier
//                                .width(150.dp)
                        ) {
                            Text(text = "Add product")
                        }

                        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                        Box(
                            modifier = Modifier
                                .animateIconTintOnce(true, 2500)
                                .background(Color.Green)
                        )
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(listOfProducts) { product ->
                                ProductInfoRow(product)
                            }
                        }
                    }
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


    }// 1. THE REUSABLE COMPONENT

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(showBackground = true)
    @Composable
    fun SliderAdvancedExample() {
        var sliderPosition by remember { mutableFloatStateOf(500000f) }
        val sliderInMillions = sliderPosition / 1000000
        val formattedValue = "%.3f".format(sliderInMillions)

        CenteredTitle(title = "Coverage Details")
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center,
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(vertical = 10.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp), // Your 80% width
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
    @Preview(showBackground = true)
    @Composable
    fun CardWithContent() {
        var numberOfDependents by remember { mutableIntStateOf(0) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red),
            contentAlignment = Alignment.Center,
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(vertical = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp), // Your 80% width
                    horizontalAlignment = Alignment.CenterHorizontally,


                    ) {
                    Row()
                    {
                        Icon(painterResource(R.drawable.ic_group), contentDescription = null)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "Number of dependents ", fontWeight = FontWeight.Bold)

                    }
                }

                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), // Light grey instead of Magenta for better UI
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 10.dp)//.align(Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween // Pushes text to left, controls to right
                    ) {
                        Text(text = "Spouse & children", modifier = Modifier.weight(1f))

                        // Stepper Controls
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { if (numberOfDependents > 0) numberOfDependents-- }) {
                                Icon(
                                    painterResource(R.drawable.ic_minus),
                                    contentDescription = "Decrease"
                                )
                            }

                            Text(
                                text = numberOfDependents.toString(),
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )

                            IconButton(onClick = { numberOfDependents++ }) {
                                Icon(
                                    painterResource(R.drawable.ic_add),
                                    contentDescription = "Increase"
                                )
                            }
                        }
                    }
                }
            }
        }

    }

    //}
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

    @Composable
    fun ListOfNames(
        listOfNames: List<String>,
        modifier: Modifier = Modifier
    ) {

        LazyColumn(modifier) {
//        if(listOfNames.size > 0){
//            Text(text="List of names in list")
            items(listOfNames) { currentName ->

                Text(
                    text = currentName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                HorizontalDivider(thickness = 5.dp)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(10) { i ->
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "background",
                    modifier = Modifier
                        .padding(start = 10.dp, bottom = 10.dp)
                        .size(150.dp)
                        //fillMaxWidth(.3f)
                        .background(Color.Red)
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

    @RequiresApi(Build.VERSION_CODES.Q)

    @Composable
    fun GreetingPreview() {
        BeginnerApplicationTheme {
            Greeting("Android")
        }
    }
}