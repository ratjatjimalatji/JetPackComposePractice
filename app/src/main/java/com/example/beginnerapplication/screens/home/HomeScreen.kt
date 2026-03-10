package com.example.beginnerapplication.screens.home

import Insurer
import com.example.beginnerapplication.R
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
                        text = "Insurance Application",
                        style = MaterialTheme.typography.titleLarge,

                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF5F5F5),
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        // innerPadding ensures content starts below the TopAppBar
        Box( modifier = Modifier.background(Color.White)
            .padding(innerPadding)) {

            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

                Text("Select a Service", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(16.dp))

                // 1. Button for Insurers
                Button(
                    onClick = {
                        navController.navigate(InsuranceScreens.InsurerListScreen.name)
                    },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text("View Insurers")
                }

                // 2. Button for Medical Aid
                Button(
                    onClick = { navController.navigate(InsuranceScreens.MedicalAidSelectionScreen.name) },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text("Medical Aid Selection")
                }

                // 3. Button for Life Insurance
                Button(
                    onClick = { navController.navigate(InsuranceScreens.LifeInsuranceQuoteScreen.name) },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text("Life Insurance Quote")
                }

                // 4. Button for Vehicle (Example of 4th button)
                Button(
                    onClick = { navController.navigate(InsuranceScreens.VehicleInsuranceInputScreen.name) },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text("Vehicle Insurance")
                }
            }
        }
        }
    }

//product input composables
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

                verticalAlignment = CenterVertically,

                ) {
                rowItems.forEach { info ->
                    // Find the actual index in the original list to update state correctly
                    val originalIndex = checkboxOptions.indexOf(info)

                    Row(
                        verticalAlignment = CenterVertically,
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
            verticalAlignment = CenterVertically
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


