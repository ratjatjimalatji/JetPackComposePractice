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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.R
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.beginnerapplication.navigation.InsuranceScreens
import com.example.beginnerapplication.screens.lifeInsurance.LifeInsuranceQuoteScreen
import com.example.beginnerapplication.screens.medicalAidSelection.MedicalAidSelection
val MetaHeightsPurple = Color(0xFF4c50d5)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsuranceApp() {
    val navController = rememberNavController()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // ... Your logo and title code ...
                    Text("Meta Heights", modifier = Modifier.padding(start = 30.dp))
                },
            )
        }
    ) { padding ->
        // Use the padding in your NavHost container
        Box(modifier = Modifier.padding(padding)) {
            NavHost(
                navController = navController,
                startDestination = InsuranceScreens.HomeScreen.name
            ) {
                // Main Selection Page
                composable(InsuranceScreens.HomeScreen.name) {
                    SelectionScreen(
                        onTypeSelected = { type ->
                            when (type) {
                                InsuranceType.VEHICLE -> navController.navigate(InsuranceScreens.VehicleInsuranceInputScreen.name)
                                InsuranceType.MEDICAL -> navController.navigate(InsuranceScreens.MedicalAidSelectionScreen.name)
                                InsuranceType.PERSONAL -> navController.navigate(InsuranceScreens.LifeInsuranceQuoteScreen.name)
                                // Add other cases as needed
                                else -> { /* Handle others */ }
                            }
                        }
                    )
                }

                // Medical Aid Selection
                composable(InsuranceScreens.MedicalAidSelectionScreen.name) {
                    MedicalAidSelection(navController = navController)
                }

                // Life Insurance / Personal
                composable(InsuranceScreens.LifeInsuranceQuoteScreen.name) {
                    LifeInsuranceQuoteScreen(navController = navController)
                }

                // Vehicle Input
//                composable(InsuranceScreens.VehicleInsuranceInputScreen.name) {
//                    VehicleInputScreen(viewModel = viewModel, onSubmit = {
//                        navController.navigate("QUOTES") // Or define in Enum
//                    })
//                }
            }
        }
    }
}

// --- SCREEN 1: Selection ---
@Composable
fun SelectionScreen(onTypeSelected: (InsuranceType) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(backgroundColor) // Set background
            .padding(24.dp), // Increased padding for an airy feel
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Header with accent underline
        Column(horizontalAlignment = Alignment.Start) {

            Text(
                "Select Coverage",
                style = MaterialTheme.typography.labelLarge,
                color = MetaHeightsPurple,
                fontWeight = FontWeight.Bold
            )
            Text(
                "What would you like to insure?",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(color = 0xFF212529) // Dark grey text
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Subtle accent line
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(3.dp)
                    .background(MetaHeightsPurple, CircleShape)
                    .align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        InsuranceType.entries.forEach { type ->
            // Using a Surface Card instead of a Button for a modern feel
            Surface(
                onClick = { onTypeSelected(type) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .height(72.dp)
                    // Soft shadow for elevation
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(16.dp),
                        spotColor = MetaHeightsPurple.copy(alpha = 0.3f)
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
                        // Icon Container with accent tint
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(MetaHeightsPurple.copy(alpha = 0.15f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = getIconForType(type),
                                contentDescription = null,
                                tint = MetaHeightsPurple,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = type.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF333333)
                        )
                    }

                    // Forward indicator
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                        contentDescription = null,
                        tint = Color.Gray.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}


@Composable
fun getIconForType(type: InsuranceType): Painter {
    return when (type) {
//        InsuranceType.VEHICLE -> painterResource(id = R.drawable.ic_car)
//        InsuranceType.MEDICAL -> painterResource//(R.drawable.ic_medical)
//        InsuranceType.HOME -> rememberVectorPainter(Icons.Default.Home)
//        InsuranceType.PERSONAL -> rememberVectorPainter(Icons.Default.Person)
//        InsuranceType.BUSINESS -> painterResource//(R.drawable.ic_building)
        InsuranceType.VEHICLE -> rememberVectorPainter(Icons.Default.Home)
        InsuranceType.MEDICAL -> rememberVectorPainter(Icons.Default.Home)
        InsuranceType.HOME -> rememberVectorPainter(Icons.Default.Home)
        InsuranceType.PERSONAL -> rememberVectorPainter(Icons.Default.Person)
        InsuranceType.BUSINESS -> rememberVectorPainter(Icons.Default.Home)
    }
}
enum class InsuranceType(val title: String) {
    VEHICLE("Vehicle Insurance"),
    HOME("Home Insurance"),
    PERSONAL("Personal Insurance"),
    BUSINESS("Business Insurance"),
    MEDICAL("Medical Insurance"),
}