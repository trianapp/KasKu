package app.trian.kasku.ui.pages.auth

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.common.*
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.theme.KasKuTheme
import app.trian.kasku.ui.theme.fontFamily
import coil.compose.rememberImagePainter
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import kotlinx.coroutines.launch

/**
 *
 * author Trian Damai
 * created_at 16/03/22 - 01.34
 * site https://trian.app
 */

@Composable
fun PageUpdateProfile(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val ctx = LocalContext.current
    val authViewModel = hiltViewModel<AuthViewModel>()
    val scope = rememberCoroutineScope()

    val currentProfile by authViewModel.currentUserProfile.observeAsState(initial = null)


    var name by remember {
        mutableStateOf("")
    }

    var dateOfBirth by remember {
        mutableStateOf("")
    }
    var dialogPickImage by remember {
        mutableStateOf(false)
    }
    var imageProfile by remember {
        mutableStateOf<Bitmap?>(null)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract =ActivityResultContracts.TakePicturePreview(),
        onResult = {
            bitmap:Bitmap?->
            bitmap?.let {
                imageProfile = it
            }
        }
    )

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            uri: Uri? ->
            uri.getBitmap(ctx.contentResolver)?.let {
                imageProfile = it
            }


        }
    )

    DialogOptionPickImage(
        show = dialogPickImage,
        onCamera = {
            dialogPickImage = false
            scope.launch {
                cameraLauncher.launch()
            }

        },
        onGallery = {
            dialogPickImage = false
            scope.launch {
                galleryLauncher.launch("image/*")
            }
        }
    ) {
        dialogPickImage = false
    }
    SideEffect {
        currentProfile?.let {
            name = it.authUser?.displayName ?: ""
            dateOfBirth = it.user?.dateOfBirth.formatDate("dd-MMMM-yyyy") ?: ""

        }
    }
    LaunchedEffect(key1 = Unit, block = {
        authViewModel.getCurrentUserProfile()
    })

    Scaffold(
        topBar = {
            AppbarBasic(
                title = "Edit Profile",
                navigationIcon = {
                    IconToggleButton(checked = false, onCheckedChange = {
                        router.popBackStack()
                    }) {
                        Icon(
                            imageVector = Octicons.ArrowLeft24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .dashedBorder(
                        border = BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.colors.primary
                        ),
                        shape = RoundedCornerShape(20.dp),
                        on = 3.dp,
                        off = 3.dp
                    )
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        dialogPickImage = true
                    }
                    .padding(
                        all = 16.dp
                    )
            ) {
                Column(
                    modifier = modifier
                        .align(Alignment.Center)
                ) {
                    if(imageProfile == null){
                        Image(
                            painter = rememberImagePainter(data = currentProfile?.authUser?.photoUrl.toString() ?: ""),
                            contentDescription = stringResource(R.string.content_description_image_page_register),
                            modifier = modifier
                                .fillMaxWidth(fraction = 0.5f)
                                .fillMaxHeight(fraction = 0.2f)

                        )
                    }else{
                        Image(
                            bitmap = imageProfile!!.asImageBitmap(),
                            contentDescription = stringResource(R.string.content_description_image_page_register),
                            modifier = modifier
                                .fillMaxWidth(fraction = 0.5f)
                                .fillMaxHeight(fraction = 0.2f)

                        )
                    }

                    Spacer(modifier = modifier.height(50.dp))

                }
                Text(
                    text = "Select Image",
                    modifier = modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Update your profile",
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "You can update your profile",
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                FormInput(
                    placeholder = stringResource(R.string.placeholder_name),
                    label = stringResource(R.string.label_input_name),
                    singleLine = true,
                    initialValue = name,
                    onChange = {
                        name=it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                FormInputWithButton(
                    placeholder = "Date of Birth",
                    label = "Date of birth",
                    singleLine = true,
                    initialValue = dateOfBirth,
                    onChange = {
                        dateOfBirth = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {

                        }
                    )
                ){

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPageUpdateProfile() {
    KasKuTheme {
        PageUpdateProfile(router = rememberNavController())
    }
}