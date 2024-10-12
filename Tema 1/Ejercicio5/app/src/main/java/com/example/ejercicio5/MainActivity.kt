package com.example.ejercicio5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicio5.ui.theme.Ejercicio5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio5Theme {
                Surface() {
                    Greeting(
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFc7edc7))
    ) {
        Row(
            Modifier.weight(2f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.android_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = modifier
                        .background(Color(0xFF013801))
                        .width(150.dp)
                        .height(170.dp)
                )
                Text(text = stringResource(R.string.name),
                    fontSize = 30.sp,
                    modifier = modifier.padding(15.dp))
                Text(text = stringResource(R.string.title),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF006d00)
                )
            }
        }
        Row(
            Modifier
                .weight(1f)
                .padding(0.dp, 30.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center) {
            Column(horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center) {
                IconWithText(text="+34 647792151",icon=Icons.Filled.Call)
                IconWithText(text="raquel.arez.arez@gmail.com",icon=Icons.Filled.Email)
                IconWithText(text="@RahelDM",icon=Icons.Filled.Share)

            }
        }
    }
}

@Composable
fun IconWithText(text:String, icon:ImageVector){
    Row(modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.padding(20.dp, 0.dp),
                Color(0xFF013801)
            )

            Text(text = text)

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ejercicio5Theme {
        Greeting()
    }
}