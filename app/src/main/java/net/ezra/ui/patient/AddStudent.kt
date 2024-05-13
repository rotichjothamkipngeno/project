package net.ezra.ui.patient


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import net.ezra.R
import java.util.UUID
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import net.ezra.navigation.ROUTE_ADD_STUDENTS
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_PATIENT
import net.ezra.navigation.ROUTE_SIGNUP
import net.ezra.navigation.ROUTE_STUDENTLIST


var progressDialog: ProgressDialog? = null
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudents(navController: NavHostController) {
    val context = LocalContext.current

    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ){
        item (

        ){
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF00BCD4))
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .fillMaxSize(),

            ) {
                Column(

                    modifier = Modifier
                        .fillMaxSize()
//
                        .padding(5.dp),

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "upload your clinic",style = TextStyle(
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W700,
                        fontStyle = FontStyle.Normal,
                        letterSpacing = 0.1.em,
                        background = Color(0xFFC5D810),
                        textDecoration = TextDecoration.None
                    )
                    )

                    var photoUri: Uri? by remember { mutableStateOf(null) }

                    val launcher =
                        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                            photoUri = uri
                        }


                    var fullname by rememberSaveable {
                        mutableStateOf("")
                    }

                    var email by rememberSaveable {
                        mutableStateOf("")
                    }

                    OutlinedTextField(
                        value = fullname,
                        onValueChange = { fullname = it },
                        label = { Text(text = "Full Name", color = Color.Black) },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "Email", color = Color.Black) },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )


                    var workexperience by rememberSaveable {
                        mutableStateOf("")
                    }

                    var country by rememberSaveable {
                        mutableStateOf("")
                    }

                    var massage by rememberSaveable {
                        mutableStateOf("")
                    }






                    OutlinedTextField(

                        value = workexperience,
                        onValueChange = { workexperience = it },
                        label = { Text(text = "Tel", color = Color.Black) },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = country,
                        onValueChange = { country = it },
                        label = { Text(text = "Location", color = Color.Black) },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = massage,
                        onValueChange = { massage = it },
                        label = { Text(text = "Operation Time", color = Color.Black) },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()

                    )


                    OutlinedButton(
                        onClick = {
                            launcher.launch(
                                PickVisualMediaRequest(
                                    //Here we request only photos. Change this to .ImageAndVideo if you want videos too.
                                    //Or use .VideoOnly if you only want videos.
                                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                    ) {
                        Icon(imageVector = Icons.Default.AccountBox, contentDescription = "",)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Add a profile picture", color = Color.Gray)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    if (photoUri != null) {
                        //Use Coil to display the selected image
                        val painter = rememberAsyncImagePainter(
                            ImageRequest
                                .Builder(LocalContext.current)
                                .data(data = photoUri)
                                .build()
                        )

                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier
                                .background(color = Color.Transparent)
                                .padding(5.dp)
                                .size(150.dp)
                                .fillMaxWidth()
                                .border(1.dp, Color.Gray),
                            contentScale = ContentScale.Crop,

                            )
                    } else {

                        OutlinedButton(
                            onClick = {
                                launcher.launch(
                                    PickVisualMediaRequest(
                                        //Here we request only photos. Change this to .ImageAndVideo if you want videos too.
                                        //Or use .VideoOnly if you only want videos.
                                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            }
                        ) {
                            Text( "select image")
                        }
                    }

                    OutlinedButton(onClick = {

                        if (photoUri != null) {

                            progressDialog = ProgressDialog(context)
                            progressDialog?.setMessage("Uploading data...")
                            progressDialog?.setCancelable(false)
                            progressDialog?.show()

                            photoUri?.let {

                                uploadImageToFirebaseStorage(
                                    it,
                                    fullname,
                                    email,
                                    workexperience,
                                    country,massage,context)


                                fullname = ""
                                email = ""
                                workexperience = ""
                                country = ""
                                massage = ""
                                photoUri = null

                            }
                        } else if (fullname == ""){

                            Toast.makeText(context, "Please enter class", Toast.LENGTH_SHORT).show()
                        }
                        else if (email == ""){
                            Toast.makeText(context, "Please enter email", Toast.LENGTH_SHORT).show()
                        }
                        else if(workexperience == ""){
                            Toast.makeText(context, "Please enter name", Toast.LENGTH_SHORT).show()
                        }
                        else if(massage == ""){
                            Toast.makeText(context, "Please enter name", Toast.LENGTH_SHORT).show()
                        }

                        else {
                            Toast.makeText(context, "Please select an image", Toast.LENGTH_SHORT).show()
                        }



                    }) {

                        Text("Upload")


                    }
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.five),
                            contentDescription = "",
                            modifier = Modifier
                                .size(270.dp)
                                .fillMaxWidth()
                        )}
//                        Row {
//
//                            Button(onClick = {
//                                navController.navigate(ROUTE_SIGNUP) {
//                                    popUpTo(
//                                        ROUTE_ADD_STUDENTS
//                                    ) { inclusive = true }
//                                }
//                            },
//                                ) {
//                                Text(text = "sign up",
//                                    color = Color.Green,
//                                    fontSize = 20.sp)
//
//
//
//                            }
//
//
//                        }
//                    Row {
//                        Text(text = "Create an account",
//                            color = Color.Red,
//                            fontSize = 20.sp)
//                    }
                    
                    

                }




            }
        }

    }


}




fun uploadImageToFirebaseStorage(
    imageUri: Uri,
    fullname: String,
    email: String,
    workexperience: String,
    country: String,
    massage: String,
    context: Context
) {
    val storageRef = FirebaseStorage.getInstance().reference
    val imageRef = storageRef.child("images/${UUID.randomUUID()}")

    val uploadTask = imageRef.putFile(imageUri)
    uploadTask.continueWithTask { task ->
        if (!task.isSuccessful) {
            task.exception?.let {
                throw it
            }
        }
        imageRef.downloadUrl
    }.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val downloadUri = task.result
            saveToFirestore(
                downloadUri.toString(),
                fullname,
                email,
                workexperience,
                country,
                massage,context)
        } else {
            progressDialog?.dismiss()

            AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("Failed to upload image: ${task.exception?.message}")
                .setPositiveButton("OK") { _, _ ->
                    // Optional: Add actions when OK is clicked


                }
                .show()

        }
    }
}

fun saveToFirestore(
    imageUrl: String,
    fullname: String,
    email: String,
    workexperience: String,
    country: String,
    massage: String,
    context: Context,
) {
    val db = Firebase.firestore
    val imageInfo = hashMapOf(
        "imageUrl" to imageUrl,
        "First Name" to fullname,
        "Email" to email,
        "Occupation" to workexperience,
        "Country" to country,
        "Massage" to massage


    )




    db.collection("Product")
        .add(imageInfo)
        .addOnSuccessListener {
                ocumentReference ->

            progressDialog?.dismiss()

            // Show success dialog
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setTitle("Success")
                .setMessage("Data saved successfully!")
                .setPositiveButton("OK") { _, _ ->
                    // Optional: Add actions when OK is clicked
                }
                .setIcon(R.drawable.one)
                .setCancelable(false)

            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            // Customize the dialog style (optional)
            val alertDialogStyle = alertDialog.window?.attributes
            alertDialog.window?.attributes = alertDialogStyle

        }
        .addOnFailureListener {

            progressDialog?.dismiss()


            AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("Failed to save data")
                .setPositiveButton("OK") { _, _ ->
                    // Optional: Add actions when OK is clicked



                }
                .show()

        }
}






@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    AddStudents(rememberNavController())
}
