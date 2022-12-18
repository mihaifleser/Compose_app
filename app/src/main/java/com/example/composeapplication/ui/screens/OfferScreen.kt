package com.example.composeapplication.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.R
import com.example.composeapplication.model.OfferItemViewModel
import com.example.composeapplication.ui.ViewModel.OffersViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OffersList(viewModel: OffersViewModel) {
    val offerItems by viewModel.items.observeAsState(emptyList())

    val refreshing by viewModel.isRefreshing.observeAsState(false)
    val refresh = viewModel.refreshItems
    val state = rememberPullRefreshState(refreshing = refreshing, onRefresh = { refresh.invoke() })

    val openDialog = remember { mutableStateOf(false) }
    val selectedViewModel = remember { mutableStateOf(OfferItemViewModel()) }
    val displayMenu = remember { mutableStateOf(false) }
    Scaffold(topBar = { TopBar(displayMenu) },
        content = { padding ->
            Box(Modifier.pullRefresh(state)) {
                Column(modifier = Modifier.padding(padding)) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_padding)),
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.space_padding))
                    ) {
                        offerItems.map { item(key = it.id) { OfferListItem(viewModel = it, openDialog, selectedViewModel) } }
                    }
                    ItemAlertDialog(openDialog, selectedViewModel, viewModel)
                }
                PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
            }
        })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OfferListItem(viewModel: OfferItemViewModel, openDialog: MutableState<Boolean>, selectedViewModel: MutableState<OfferItemViewModel>) {
    Surface(
        shape = MaterialTheme.shapes.small,
        elevation = 5.dp,
        modifier = Modifier.combinedClickable(onClick = {}, onLongClick = {
            openDialog.value = true
            selectedViewModel.value = viewModel
        })
    ) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_padding))) {
            Text(text = viewModel.title, fontSize = 30.sp)
            Box {
                Image(
                    painter = painterResource(id = viewModel.image),
                    contentDescription = "image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
                Text(
                    text = viewModel.price.toString() + " EUR",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
            Text(text = viewModel.description, fontStyle = FontStyle.Italic)
        }
    }
}

@Composable
fun TopBar(displayMenu: MutableState<Boolean>) {
    TopAppBar(
        elevation = 4.dp,
        title = {
            Text(stringResource(id = R.string.offers_list))
        },
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        }, actions = {
            Button(onClick = {/* Do Something*/ }, colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)) {
                Text(text = stringResource(id = R.string.sign_out).uppercase())
            }
            IconButton(onClick = { displayMenu.value = true }) {
                Icon(Icons.Filled.MoreVert, null)
            }
            OffersListDropdown(displayMenu = displayMenu)

        })
}

@Composable
fun OffersListDropdown(displayMenu: MutableState<Boolean>) {
    DropdownMenu(expanded = displayMenu.value, onDismissRequest = { displayMenu.value = false }) {
        DropdownMenuItem(onClick = { displayMenu.value = false }) {
            Text(text = stringResource(id = R.string.reset_list))
        }
        DropdownMenuItem(onClick = { displayMenu.value = false }) {
            Text(text = stringResource(id = R.string.clear_favourites))
        }
    }
}

@Composable
fun ItemAlertDialog(openDialog: MutableState<Boolean>, selectedViewModel: MutableState<OfferItemViewModel>, viewModel: OffersViewModel) {

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = selectedViewModel.value.title)
            },
            buttons = {
                Column(
                    modifier = Modifier.padding(all = 8.dp),
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { openDialog.value = false }
                    ) {
                        Text(stringResource(id = R.string.add_offer))
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            viewModel.deleteItem.invoke(selectedViewModel.value)
                            openDialog.value = false
                        }
                    ) {
                        Text(stringResource(id = R.string.remove_offer))
                    }
                }
            }
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewList() {
    OffersList(OffersViewModel())
}