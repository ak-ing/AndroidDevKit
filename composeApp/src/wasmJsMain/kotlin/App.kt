import androiddevkit.composeapp.generated.resources.Res
import androiddevkit.composeapp.generated.resources.compose_multiplatform
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.screen.HostRouter
import ui.screen.HostScreen
import ui.theme.getAppTypography

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val textStyle = LocalTextStyle.current
    var fontFamily by remember { mutableStateOf(textStyle.fontFamily ?: FontFamily.Default) }
    var fontsLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val regular = Font("NotoSansTC-Regular", Res.readBytes("font/NotoSansSC-Regular.ttf"))
        val bold = Font("NotoSansTC-Bold", Res.readBytes("font/NotoSansSC-Bold.ttf"))
        fontFamily = FontFamily(regular, bold)
        fontFamilyResolver.preload(fontFamily)
        fontsLoaded = true
    }

    if (fontsLoaded) {
        // 设置字体
        MaterialTheme(typography = getAppTypography(fontFamily)) {
            Surface(modifier = Modifier.fillMaxWidth()) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = HostRouter) {
                    composable(HostRouter) {
                        HostScreen { navController.navigate(it) }
                    }
                }
            }
        }
    }
}

@Composable
private fun AboutApp() {
    var showContent by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { showContent = !showContent }) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
}