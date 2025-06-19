package com.example.wallpaper5

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wallpaper5.ui.theme.WALLPAPER5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WALLPAPER5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WallpaperSetterScreen()
                }
            }
        }
    }
}

@Composable
fun WallpaperSetterScreen() {
    val context = LocalContext.current
    val wallpapers = listOf(
        R.drawable.wallpaper1,
        R.drawable.wallpaper2,
        R.drawable.wallpaper3,
        R.drawable.wallpaper4,
        R.drawable.wallpaper5,
        R.drawable.wallpaper6,
        R.drawable.wallpaper7,
        R.drawable.wallpaper8,
        R.drawable.wallpaper9,
        R.drawable.wallpaper10
    )

    // State to track the selected image
    var selectedImage by remember { mutableIntStateOf(wallpapers[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Display the row of wallpapers
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            wallpapers.forEach { imageRes ->
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { selectedImage = imageRes }
                        .border(
                            width = if (imageRes == selectedImage) 3.dp else 1.dp,
                            color = if (imageRes == selectedImage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                            shape = MaterialTheme.shapes.medium
                        )
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Preview the selected wallpaper
        Image(
            painter = painterResource(id = selectedImage),
            contentDescription = "Selected Wallpaper",
            modifier = Modifier
                .width(300.dp)
                .height(500.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Button to set the selected wallpaper
        Button(onClick = {
            val bitmap = BitmapFactory.decodeResource(context.resources, selectedImage)
            val wallpaperManager = WallpaperManager.getInstance(context)

            try {
                wallpaperManager.setBitmap(bitmap)
                Toast.makeText(context, "Wallpaper Set!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Failed to Set Wallpaper", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Set as Wallpaper")
        }
    }
}
