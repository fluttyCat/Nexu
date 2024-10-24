package com.nexu.feature.todohome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nexu.android.core.data.model.TodoResource
import com.nexu.android.core.ui.NexuLoading

@Composable
fun AddTodoScreen(
    viewModel: TodoHomeViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    onAddTodo: (String, String, Boolean) -> Unit,
    onNavigateBack: () -> Unit,
    refreshHomeScreen: () -> Unit
) {
    val onEventSent = viewModel::handleEvents
    val context = LocalContext.current
    val addTodo by viewModel.addTodoResponse.collectAsStateWithLifecycle()

    LaunchedEffect(addTodo, snackbarHostState) {
        when (addTodo) {
            is TodoHomeContract.UiState.Success -> {
                refreshHomeScreen()
                snackbarHostState.showSnackbar(
                    "Todo added successfully!",
                    duration = SnackbarDuration.Short
                )
                onNavigateBack()
            }

            is TodoHomeContract.UiState.Failure -> {
                snackbarHostState.showSnackbar("Failed to add Todo.")
            }

            is TodoHomeContract.UiState.Loading -> {

            }
        }
    }

    Column {
        AddTodoScreenContent { title, description, isCompleted ->
            onEventSent(
                TodoHomeContract.Event.AddTodo(
                    TodoResource(title = title, description = description, isDone = isCompleted)
                )
            )
        }
    }


}

@Composable
fun AddTodoScreenContent(
    onAddTodo: (String, String, Boolean) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isCompleted by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                onAddTodo(title, description, isCompleted)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Add Todo")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddTodoScreen() {

}

