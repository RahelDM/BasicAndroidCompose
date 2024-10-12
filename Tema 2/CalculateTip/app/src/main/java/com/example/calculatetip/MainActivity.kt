package com.example.calculatetip


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatetip.ui.theme.CalculateTipTheme
import java.text.NumberFormat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculateTipTheme {
                Surface(
                ) {
                    TipTimeLayout()
                }
                }
            }
        }
    }


@Composable
fun TipTimeLayout() {
    //MutableState<String> para que compose sepa que debe hacer seguimiento...
    var amountInput by remember { mutableStateOf("") };
    var porcentageInput by remember { mutableStateOf("") };
    var roundUp by remember { mutableStateOf(false) }

    //convierte el string en double or null. si es null -> 0.0, operado Elvis
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val porcentage = porcentageInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount,porcentage,roundUp)

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 8.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(modifier = Modifier.padding(bottom = 15.dp).fillMaxSize(1f),
            label=R.string.bill_amount, value = amountInput, onValueChange = {amountInput = it},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next),
            leadingIcon = R.drawable.money,
        );
        EditNumberField(modifier = Modifier.padding(bottom = 15.dp).fillMaxSize(1f),
            label=R.string.how_was_the_service,value = porcentageInput, onValueChange = {porcentageInput = it},
            keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done),
            leadingIcon = R.drawable.percent);

        RoundTheTipRow(modifier=Modifier,
            roundUp = roundUp,
            onRoundUpChanged = { roundUp = it })

        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Composable
fun EditNumberField(modifier: Modifier = Modifier,keyboardOptions: KeyboardOptions,value:String,
                    @StringRes label:Int, onValueChange: (String) -> Unit,
                    @DrawableRes leadingIcon:Int) {
    TextField(
        label = { Text(stringResource(label)) },
        value = value,
        leadingIcon = {Icon(painter = painterResource(id = leadingIcon), null)},
        //siendo it el valor nuevo:
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        )
}

@Composable
fun RoundTheTipRow(modifier: Modifier = Modifier, roundUp:Boolean, onRoundUpChanged: (Boolean) -> Unit) {
    Row(modifier = modifier.fillMaxWidth(1f),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = stringResource(R.string.round_up_tip))
        Switch(checked = roundUp, onCheckedChange = onRoundUpChanged)
    }
}

private fun calculateTip(amount: Double, tipPercent: Double = 15.0,roundUp: Boolean): String {
    var tip = tipPercent / 100 * amount
    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculateTipTheme {
        TipTimeLayout()
    }
}