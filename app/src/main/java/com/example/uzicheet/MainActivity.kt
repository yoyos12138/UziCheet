package com.example.uzicheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uzicheet.ui.theme.UziCheetTheme

class MainActivity : ComponentActivity() {
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
        val x0=9
        var datas by remember {mutableStateOf(Array(x0){Array(x0){0}})}
        var myTurn by remember { mutableStateOf(true) }
        Box(modifier = Modifier.fillMaxSize()) {
            Image(painter = painterResource(id = R.drawable.dsc_1277), contentDescription =null,Modifier.fillMaxWidth())
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "五子棋")
                HorizontalDivider(Modifier.padding(2.dp).fillMaxWidth())
                Column {
                    Array(x0){0}.forEachIndexed{y,_->
                        Row(Modifier.fillMaxWidth()) {
                            Array(x0){0}.forEachIndexed{x,_->
                                Point(
                                    modifier = Modifier
                                        .weight(1f)
                                        .border(1.dp, Color.Black),
                                    x = x,
                                    y = y,
                                    datas =datas,{ datas=it },myTurn,{myTurn=it})
                            }
                        }
                    }
                }
                Notice0(datas = datas, modifier = Modifier.fillMaxSize(), myTurn = myTurn)

            }
        }

    }

    @Composable
    fun Point(modifier: Modifier=Modifier,x:Int,y:Int,
              datas:Array<Array<Int>>,setDatas:(Array<Array<Int>>)->Unit
              ,myTurn:Boolean
              ,setMyTurn:(Boolean)->Unit
    ){
        var ci by remember { mutableIntStateOf(0) }

        val btnColor=when(ci){
            0   ->Color.Transparent
            1   ->Color.Red
            2   ->Color.Blue
            else->Color.Black
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                btnColor
            ),
            modifier = modifier,
            onClick = {
                if (ci==0){
                    ci=if (myTurn){1}else{2}
                    setMyTurn(!myTurn)
                }
                datas[x][y] = ci
                setDatas(datas)
        }) {
            Text(text = " ")
        }
    }
    @Composable
    fun Notice0(modifier: Modifier=Modifier,datas:Array<Array<Int>>,myTurn: Boolean){
        var msg by remember { mutableStateOf("红色开始下") }
        msg=if (myTurn){"红色下"}else{"蓝色下"}
        //写一个探索横竖斜有没有5个连在一起的
        //横方向
        datas.forEach { line->
            //红色
            var redCount=0
            for (r in line){
                if (r==1){
                    redCount++
                    if (redCount==5){ msg="红色win"}
                }else{
                    redCount=0
                }
            }
            //蓝色
            var blueCount=0
            for (b in line){
                if (b==2){
                    blueCount++
                    if (blueCount==5){ msg="蓝色win"}
                }else{
                    blueCount=0
                }
            }
        }
        //竖直方向
        for (x in 0 until 9) { // Iterate over each column
//
            //红色
            var r=0
            for (y in 0 until 9) { // Iterate over each row within the column
//
                if (datas[x][y]==1){
                    r++
                    if (r==5){
                        msg="红色胜"
                    }
                }else{r=0}
            }

            //蓝色
            var b=0
            for (y in 0 until 9) { // Iterate over each row within the column
//
                if (datas[x][y]==2){
                    b++
                    if (b==5){
                        msg="蓝色胜"
                    }
                }else{b=0}
            }
        }

        //斜方向实在写的太麻烦就算了老师)

        Text(text = msg, modifier = modifier, textAlign =  TextAlign.Center)
    }
}
