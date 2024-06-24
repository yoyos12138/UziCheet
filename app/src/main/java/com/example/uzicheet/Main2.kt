package com.example.uzicheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.uzicheet.ui.theme.UziCheetTheme

class Main2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UziCheetTheme {
                MyApp()
            }
        }
    }
    @Composable
    @Preview(showSystemUi = true)
    fun MyAppView(){
        UziCheetTheme {
            MyApp()
        }
    }
    @Composable
    fun MyApp(){
        var count by remember { mutableIntStateOf(1) }
        Row {
            Son(count = count){count=it}
            Son(count = count){count=it}
        }
        
    }
    @Composable
    fun Son(count:Int,setCount:(Int)->Unit){
        Button(onClick = { setCount(count+1)}) {
            Text(text = "$count")
        }
    }


}
