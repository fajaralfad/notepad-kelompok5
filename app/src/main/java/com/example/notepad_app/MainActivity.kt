package com.example.notepad_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.notepad_app.data.NoteDatabase
import com.example.notepad_app.presentation.addNoteScreen
import com.example.notepad_app.presentation.noteScreen
import com.example.notepad_app.presentation.noteViewModel
import com.example.notepad_app.ui.theme.NotepadappTheme

class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "notes.db"
        ).build()
    }

    private val viewModel: noteViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return noteViewModel(database.dao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotepadappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    val state by viewModel.state.collectAsState()
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "NoteScreen",
                        modifier = Modifier.padding(paddingValues) // Apply padding here
                    ) {
                        composable("NoteScreen") {
                            noteScreen(state = state, navController = navController, onEvent = viewModel::onEvent)
                        }
                        composable("addNoteScreen") {
                            addNoteScreen(state = state, navController = navController, onEvent = viewModel::onEvent)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotepadappTheme {
        Greeting("Android")
    }
}
