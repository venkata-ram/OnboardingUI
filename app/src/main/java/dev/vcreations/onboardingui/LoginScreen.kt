package dev.vcreations.onboardingui

import android.graphics.drawable.Icon
import android.text.SpannableString
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vcreations.onboardingui.ui.theme.HeaderColor
import dev.vcreations.onboardingui.ui.theme.LightBlueColor

@Composable
fun LoginScreen(){
    var emailState by rememberSaveable {
        mutableStateOf("")
    }

    var passwordState by rememberSaveable {
        mutableStateOf("")
    }

    var passwordVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    val onVisibilityClick = {
        passwordVisibility = !passwordVisibility
    }

    val onEmailChange = { value : String ->
        emailState = value
    }

    val onPasswordChange = { value : String ->
        passwordState = value
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        val headerModifier = Modifier.weight(.3f)
        val contentModifier = Modifier.weight(.4f)
        val footerModifier = Modifier.weight(.3f)
        Header(
            modifier = headerModifier
        )
        Content(
            emailState = emailState,
            passwordState = passwordState,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            passwordVisibility = passwordVisibility,
            onVisibilityClick = onVisibilityClick,
            modifier = contentModifier
        )
        Footer(
            modifier = footerModifier
        )
    }


}

@Composable
fun Header(
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(HeaderColor)
            .padding(start = 30.dp, top = 16.dp, bottom = 16.dp)
            .then(modifier),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign in to your\nAccount",
            color = Color.White,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                lineHeight = 40.sp
            )

        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Sign in to your Account",
            color = Color(0xFF99a2aa)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
    emailState : String,
    passwordState : String,
    onEmailChange : (String) -> Unit,
    onPasswordChange : (String) -> Unit,
    passwordVisibility : Boolean,
    onVisibilityClick : () -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        .then(modifier)
    ) {


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
            ,
            value = emailState,
            onValueChange = onEmailChange,
            label = { Text(
                text = "Email",
                color = LightBlueColor
            ) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = LightBlueColor,
                unfocusedBorderColor = LightBlueColor,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon",
                    tint = Color.Black
                )
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
            ,
            value = passwordState,
            onValueChange = onPasswordChange,
            label = { Text(
                text = "Password",
                color = Color(0xFF99a2aa)
            ) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF99a2aa),
                unfocusedBorderColor = Color(0xFF99a2aa),
            ),
            visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                val icon = if(passwordVisibility) {
                   Icons.Filled.VisibilityOff
                }else{
                    Icons.Filled.Visibility
                }

                IconButton(
                    onClick = { onVisibilityClick() },
                ) {
                    Icon(imageVector = icon, contentDescription = "")
                }
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Forgot Password?",
            color = Color(0xFF80ab38),
            modifier = Modifier.align(End)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = {  },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFc0e863)
            )
        ) {
            Text(
                text = "Login",
                color = Color.Black
            )
        }
    }
}

@Composable
fun Footer(
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier.then(modifier)
    ) {
        LoginWith()
    }

}

@Composable
fun LoginWith(){
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            Divider(
                modifier = Modifier.weight(weight = 1f),
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier.offset(y = (-10).dp),
                text = "Or login with"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Divider(
                modifier = Modifier.weight(weight = 1f),
                color = Color.Gray
            )
        }

        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            SocialLoginButton(
                image = R.drawable.google_logo,
                name = "Google",
                description = "google logo",
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            SocialLoginButton(
                image = R.drawable.facebook_logo,
                name = "Facebook",
                description = "facebook logo",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
        Text(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 16.dp),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black
                    )
                ) {
                    append("Don't have an account?")
                }

                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF80ab38)
                    )
                ){
                    append("  Register")
                }
            }

        )
    }

}

@Composable
fun SocialLoginButton(
    image : Int,
    name : String,
    description : String,
    modifier: Modifier = Modifier
){
    OutlinedButton(
        modifier = Modifier
            .then(modifier),
        onClick = {
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = LightBlueColor)
    ) {
        Image(
            modifier = Modifier
                .size(20.dp),
            painter = painterResource(id = image),
            contentDescription = description
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = name,
            color = Color.Black
        )
    }

}

@Preview(showBackground = true)
@Composable
fun LoginPreview(){
    LoginScreen()
}



