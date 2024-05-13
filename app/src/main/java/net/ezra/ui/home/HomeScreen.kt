package net.ezra.ui.home

import android.annotation.SuppressLint
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.navigation.ROUTE_ABOUT
import net.ezra.navigation.ROUTE_HOME


import net.ezra.R
import net.ezra.navigation.ROUTE_ADD_STUDENTS
import net.ezra.navigation.ROUTE_STUDENTLIST

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {



    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(containerColor = Color.Yellow)
                ,title = {
                    Text("DENTALCARE BOOKING SERVICES",
                        fontFamily = FontFamily.Serif,

                        )

                },
//                navigationIcon = {
//                    IconButton(onClick = {}) {
//                        Icon(Icons.Filled.ArrowBack, "backIcon")
//                    }
//                },
                actions = {
                    IconButton(onClick = { navController.navigate(ROUTE_ADD_STUDENTS)
                    {popUpTo(ROUTE_HOME) {inclusive = true} }

                    },


                        ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description",



                            )





                    }



                }

            )
        },
        content = {
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier
                            .background(Color(0xFF134941))
                            .fillMaxSize()
                            .fillMaxHeight()
                            .padding(1.dp)

                    ) {



                        Image(
                            painter = painterResource(id = R.drawable.one),
                            contentDescription = "",
                            modifier = Modifier
                                .height(250.dp)
                                .fillMaxWidth()

                        )
                        Text(text = "WE OFFER A PLATFORM WHERE ONE CAN BOOK" +
                                " AN APPOINTMENT WITH A CLIENT",
                            color = Color.Yellow)
                        Row (modifier = Modifier

                            .fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){

                           Card(
                               modifier = Modifier.height(290.dp)
                           ) {


                               Image(
                                   painter = painterResource(id = R.drawable.three),
                                   contentDescription = "",
                                   modifier = Modifier
                                       .height(200.dp)
                                       .size(250.dp)
                                       .width(100.dp)
                                       .background(Color.Gray)
                               )
                               Spacer(modifier = Modifier.height(10.dp))
                                Text(text = " YOUR SMILE MATTERS",
                                    fontSize = 20.sp)
                           }

                            Spacer(modifier = Modifier.width(1.dp))


                            Card (


                            ){


                            Image(
                                painter = painterResource(id = R.drawable.two),
                                contentDescription = "",
                                modifier = Modifier
                                    .height(250.dp)
                                    .size(460.dp)
                                    .width(100.dp)
//
                                    .background(Color(0xFF236F92))
                            )
                                Text(text = "GET IT AT YOUR OWN AFFORDABLITY")
                            }
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        Row (
                            horizontalArrangement = Arrangement.SpaceBetween

                        ){



                                Image(
                                    painter = painterResource(id = R.drawable.four),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(230.dp)
                                        .width(500.dp)
                                        .height(500.dp)
                                        .background(Color(0xff236F92))
                                        .padding(10.dp)
                                )

                            Text(text = "Personalized & Comfortable." +
                                    "from the best dentist." +
                                    "Get the top Certified Dentist." +
                                    "A Dental clinic you can trust",
                                fontSize = 30.sp, fontFamily = FontFamily.Cursive,
                                color = Color.White
                            )


                        }

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {


                            Image(painter = painterResource(id = R.drawable.five),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(450.dp)
                                    .fillMaxWidth()
                                    .height(300.dp)
                            )

                            }

                    }

                }


            }






        },

        bottomBar = { BottomBar(navController)}


    )

}



@Composable
fun BottomBar(navController: NavHostController) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp,
        contentColor = Color(0xff0DA1B0),
        backgroundColor = Color(0xff0DA1B0)


    ) {
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home,"",
                modifier = Modifier.clickable { navController.navigate(ROUTE_STUDENTLIST)
                {popUpTo(ROUTE_HOME){inclusive = true} } }




            )
        },
            label = { Text(text = "Available clinics") }, selected = (selectedIndex.value == 0), onClick = {
                selectedIndex.value = 0
            })

    }
}



@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    HomeScreen(rememberNavController())



}







