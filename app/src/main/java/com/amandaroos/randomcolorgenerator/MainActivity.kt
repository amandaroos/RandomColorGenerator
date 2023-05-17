package com.amandaroos.randomcolorgenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amandaroos.randomcolorgenerator.ui.theme.RandomColorGeneratorTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomColorGeneratorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RandomColorScreen()
                }
            }
        }
    }
}

@Composable
fun RandomColorScreen() {

    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    var backgroundColor by remember { mutableStateOf(generateRandomColor()) }
    var hexValue = "${backgroundColor.toHex()}"
    var RGBValue = "${backgroundColor.toRgb()}"

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(96.dp))

            Button(
                onClick = {
                    backgroundColor = generateRandomColor()
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                //Text(text = "Generate Random Color", fontSize = 16.sp)
                Image(
                    painter = painterResource(id = R.drawable.baseline_refresh_24),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }

            Button(
                onClick = {
                    clipboardManager.setText(AnnotatedString(hexValue))
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_content_copy_24),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
                Text(text = hexValue,
                    style = TextStyle(color = Color.White),)
            }

            Button(
                onClick = {
                    clipboardManager.setText(AnnotatedString(RGBValue))
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_content_copy_24),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
                Text(text = "$RGBValue",
                    style = TextStyle(color = Color.White),)
            }
        }
    }
}

fun generateRandomColor(): Color {
    val random = Random(System.currentTimeMillis())
    return Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))
}

fun Float.toRgb(): Int {
    return (this * 255).toInt()
}

fun Color.toRgb(): String {
    return "(${red.toRgb()}, ${green.toRgb()}, ${blue.toRgb()})"
}

fun Float.toHex(): String {
    // Convert the float value to an Int representation
    val intColor = (this * 255).toInt()

    // Convert the Int value to a hex string representation
    return Integer.toHexString(intColor).padStart(2, '0')
}

fun Color.toHex(): String {
    return "#${red.toHex()}${green.toHex()}${blue.toHex()}"
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RandomColorGeneratorTheme {
        RandomColorScreen()
    }
}