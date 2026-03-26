package com.example.cacau3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Navegacao()
            }
        }
    }
}

data class Message(
    val author: String,
    val body: String
)

@Composable
fun MessageCard(msg: Message, image: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "imagem do perfil",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(text = msg.author)
            Text(text = msg.body)
        }
    }
}

@Composable
fun TelaA(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MessageCard(
            msg = Message("Stitch", "Ohana 💙"),
            image = R.drawable.stitch
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.navigate("tela_b") }) {
            Text("Ir para Tela B")
        }
    }
}

@Composable
fun TelaB(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MessageCard(
            msg = Message("Lilo", "Ohana significa família 🌺"),
            image = R.drawable.lilo
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar para Tela A")
        }
    }
}

@Composable
fun Navegacao() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "tela_a"
    ) {
        composable("tela_a") {
            TelaA(navController)
        }

        composable("tela_b") {
            TelaB(navController)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTelaA() {
    MaterialTheme {
        TelaA(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaB() {
    MaterialTheme {
        TelaB(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNavegacao() {
    MaterialTheme {
        Navegacao()
    }
}