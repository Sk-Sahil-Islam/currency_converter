package com.example.currencyconverter.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.TestViewModel
import com.example.currencyconverter.presentation.home.componants.DropDown
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: TestViewModel = hiltViewModel()
) {

    var selectedText1 by remember { mutableStateOf("Select") }
    var selectedText2 by remember { mutableStateOf("Select") }
    var enteredAmount by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Currency Converter :",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.size(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                DropDown(
                    modifier = Modifier.weight(1f),
                    onClick = { selectedText1 = it },
                    selectedText = selectedText1
                )

                IconButton(
                    onClick = {
                        selectedText1 = selectedText2.also { selectedText2 = selectedText1 }
                    }
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_sync_alt),
                        contentDescription = "switch currency"
                    )
                }

                DropDown(
                    modifier = Modifier.weight(1f),
                    onClick = { selectedText2 = it },
                    selectedText = selectedText2
                )
            }
            Spacer(modifier = Modifier.size(24.dp))

            OutlinedTextField(
                value = enteredAmount,
                onValueChange = {
                    enteredAmount = it
                },
                maxLines = 1,
                singleLine = true,
                label = { Text("Enter Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done),
                shape = RoundedCornerShape(12.dp)
            )

            Text(
                text = "Converted Amount : "
            )
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
private fun HomeScreenPreview() {
    CurrencyConverterTheme {
        HomeScreen()
    }
}