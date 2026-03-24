package com.example.beginnerapplication.screens.home


import android.annotation.SuppressLint
import com.example.beginnerapplication.R
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.alpha
import androidx.navigation.NavController
import androidx.room.util.copy
import androidx.room3.util.copy
import com.example.beginnerapplication.navigation.InsuranceScreens
import com.example.beginnerapplication.widgets.customPurple
import getInsurers
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.images),
                        contentDescription = "MetaHeights logo",
                        modifier = Modifier
                            .size(25.dp)
                            .padding(start = 8.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Kotlin Insurance", modifier = Modifier.padding(start = 30.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF5F5F5),
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp), // Increased padding for an airy feel
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Header with accent underline
            Column(horizontalAlignment = Alignment.Start) {

                Text(
                    text = "Select Coverage",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(customPurple),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "What would you like to insure?",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212529) // Dark grey text
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Subtle accent line
                Box(
                    modifier = Modifier
                        .background(Color(customPurple), CircleShape)
                        .width(100.dp)
                        .height(3.dp)

                        .align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            InsuranceTypeNavigationButton(
                title = "Vehicle Insurance",
                icon = painterResource(id = R.drawable.ic_car),
                onClick = {
                    //TODO()
                }
            )

            InsuranceTypeNavigationButton(
                title = "Medical Insurance",
                icon = painterResource(id = R.drawable.ic_medical),
                onClick = {
                    navController.navigate(InsuranceScreens.MedicalAidSelectionScreen.name)
                }
            )

            InsuranceTypeNavigationButton(
                title = "Life Insurance",
                icon = painterResource(id = R.drawable.ic_heart),
                onClick = {
                    navController.navigate(InsuranceScreens.LifeInsuranceQuoteScreen.name)
                }
            )


            InsuranceTypeNavigationButton(
                title = "Home Insurance",
                icon = painterResource(id = R.drawable.ic_home),
                onClick = {
                    //navController.navigate(InsuranceScreens.VehicleInsuranceInputScreen.name)
                }
            )

            InsuranceTypeNavigationButton(
                title = "Business Insurance",
                icon = painterResource(id = R.drawable.ic_business),
                onClick = {
                    //navController.navigate(InsuranceScreens.LifeInsuranceQuoteScreen.name)
                }
            )

        }
    }
}

@Composable
private fun InsuranceTypeNavigationButton(
    title: String,
    icon: Painter, // Use Painter for general icons
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .height(72.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = Color(customPurple).copy(alpha = 0.3f)
            ),
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(customPurple).copy(alpha = 0.15f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        tint = Color(customPurple),
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333)
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = null,
                tint = Color.Gray.copy(alpha = 0.6f)
            )
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


