//import androidx.compose.material3.Text
//
////package com.example.beginnerapplication.ui.theme
////
////import androidx.compose.foundation.layout.Arrangement
////import androidx.compose.foundation.layout.Column
////import androidx.compose.foundation.layout.Row
////import androidx.compose.foundation.layout.fillMaxSize
////import androidx.compose.foundation.layout.fillMaxWidth
////import androidx.compose.foundation.layout.size
////import androidx.compose.foundation.lazy.LazyColumn
////import androidx.compose.foundation.text.KeyboardOptions
////import androidx.compose.material3.Button
////import androidx.compose.material3.Card
////import androidx.compose.material3.OutlinedTextField
////import androidx.compose.material3.Text
////import androidx.compose.runtime.Composable
////import androidx.compose.runtime.mutableStateOf
////import androidx.compose.runtime.remember
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.unit.dp
////import org.intellij.lang.annotations.JdkConstants
////
////private val Product.Companion.name: Any
////
//data class Product (
//    val name: String,
//    val description: String,
//    val price: String
//)
//
////
////     var name by remember { mutableStateOf("") }
////     var description by remember { mutableStateOf("") }
////     var price by remember { mutableStateOf("0") }
//
////     var listOfProducts by remember{mutableStateOf(listOf<Product>())}
////
//////    name: Sting,
//////    description : String,
//////    price: int
//////
////    Column(
////        modifier = Modifier.fillMaxWidth()
////    ){
////        Column(
////            horizontalArrangement = Arrangement.Center,
////            modifier = Modifier.fillMaxSize()
////        ){
////            OutlinedTextField(value ="",
////                onValueChange = {text -> name = text},
////                modifier = Modifier.weight(1f))
////
////Spacer(modifier = Modifier.height(8.dp))
////            OutlinedTextField(value ="",
////                onValueChange = {text -> description = text})
////Spacer(modifier = Modifier.height(8.dp))
//
////            OutlinedTextField(value ="",
////                onValueChange = {text -> price = text},
////                keyboardOptions = KeyboardOptions.Default.keyboardType())
////Spacer(modifier = Modifier.height(8.dp))
//
////            Button(onClick={
//// val Product = listOf(name, description, price)
//if(name.isNotBlank() && description.isNotBlank() && price.isNoBlank()){
//val listOfProducts = Product(
//    name = name.trim(),
//    description = description.trim(),
//    price = price.trim()
//)
//}else{
//    Text(text = "One or more text field is blank")
//
//}
////            }, modifier = Modifier.size(250.dp) ){
////Text(text = "add product")
////        }
////              horizontalDivider()
////        ProductListView()
////    }
////}
////@Composable
////fun ProductListView(listOfProducts: List<Product>, modifier = Modifier = Modifier){
////    LazyColumn(modifier = Modifier.fillMaxSize()
////    ){
//items(listOfProducts){currentProduct ->
//}
////        Row(
////            modifier = Modifier.fillMaxSize(0.9f)
////        ){
////            Text(text = currentProduct.name, modifier = Modifier.size(20.dp))
//Text(text = currentProduct.descriptin, modifier = Modifier.size(20.dp))
//Text(text = "R {$currentProduct.price}", modifier = Modifier.size(20.dp))
////        }
////    }
////}