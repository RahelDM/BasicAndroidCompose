package com.example.lemonbutton

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonbutton.ui.theme.LemonButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonButtonTheme {
                GreetingLemonApp(modifier = Modifier)
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingLemonApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Lemonade",modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                        )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xfff2eb81) // Puedes personalizar el color de fondo del banner
                ),

            )
        }
    ) { innerPadding ->
        // Contenido principal, ajustando el padding proporcionado por Scaffold
        var step by remember { mutableIntStateOf(1) };
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(step){
                1-> LemonChange(R.string.step_1,R.drawable.lemon_tree,R.string.lemon_tree);
                2-> LemonChange(R.string.step_1,R.drawable.lemon_tree,R.string.lemon_tree);
                3-> LemonChange(R.string.step_1,R.drawable.lemon_tree,R.string.lemon_tree);
                4-> LemonChange(R.string.step_1,R.drawable.lemon_tree,R.string.lemon_tree);
                5-> step = 1;
            }
        }
    }
}

@Composable
fun LemonChange(idText: Int, idImage:Int,idContentDescription:Int){
    Button(
        onClick = {},
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xffcce6b8),
        )
    ) {
        Image(
            painter = painterResource(R.drawable.lemon_tree),
            contentDescription = stringResource(R.string.lemon_tree)
        )
    }
    Spacer(modifier = Modifier.padding(18.dp))
    Text(stringResource(R.string.step_1))
}


@Preview(showBackground = true)
@Composable
fun GreetingLemonPreview() {
    LemonButtonTheme {
        GreetingLemonApp()
    }
}