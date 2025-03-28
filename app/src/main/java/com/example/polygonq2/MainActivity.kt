package com.example.polygonq2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    PolygonQ2Screen()
                }
            }
        }
    }
}

@Composable
fun PolygonQ2Screen() {
    val hikingTrailCoordinates = listOf(
        LatLng(42.258914955213136, -70.87478652962417),
        LatLng(42.25891065679569, -70.87474587515563),
        LatLng(42.258895865314024, -70.87469198582501),
        LatLng(42.25888105255355, -70.87464083851285),
        LatLng(42.25886623978958, -70.87454743907324)
    )
    val parkAreaCoordinates = listOf(
        LatLng(42.25926677700545, -70.87528121728366),
        LatLng(42.25940848866527, -70.87528121728366),
        LatLng(42.25940848866527, -70.8749841022114),
        LatLng(42.25926677700545, -70.8749841022114)
    )
    var polylineColor by remember { mutableStateOf(Color.Blue) }
    var polylineWidth by remember { mutableStateOf(5f) }
    var polygonStrokeColor by remember { mutableStateOf(Color.Red) }
    var polygonFillColor by remember { mutableStateOf(Color.Red) }
    var polygonStrokeWidth by remember { mutableStateOf(3f) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(42.258954089514475, -70.87494793361235), 18f)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.weight(1f),
            cameraPositionState = cameraPositionState,
        ) {
            Polyline(
                points = hikingTrailCoordinates,
                color = polylineColor,
                width = polylineWidth,
                clickable = true,
            )
            Polygon(
                points = parkAreaCoordinates,
                strokeColor = polygonStrokeColor,
                strokeWidth = polygonStrokeWidth,
                fillColor = polygonFillColor,
                clickable = true,
            )
        }
        Box(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Customize Map Overlays", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    if (polylineColor == Color.Blue) {
                        polylineColor = Color.Green
                    } else {
                        polylineColor = Color.Blue
                    }
                }) {
                    Text("Toggle Polyline Color")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    if (polylineWidth < 10f) {
                        polylineWidth += 5f
                    } else {
                        polylineWidth = 5f
                    }
                }) {
                    Text("Adjust Polyline Width")
                }
                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    if (polygonStrokeColor == Color.Red) {
                        polygonStrokeColor = Color.Yellow
                    } else {
                        polygonStrokeColor = Color.Red
                    }
                }) {
                    Text("Toggle Polygon Stroke Color")
                }
                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    if (polygonFillColor == Color.Red) {
                        polygonFillColor = Color.Black
                    } else {
                        polygonFillColor = Color.Red
                    }
                }) {
                    Text("Toggle Polygon Fill Color")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    if (polygonStrokeWidth < 10f) {
                        polygonStrokeWidth += 2f
                    } else {
                        polygonStrokeWidth = 3f
                    }
                }) {
                    Text("Adjust Polygon Stroke Width")
                }
            }
        }
    }
}