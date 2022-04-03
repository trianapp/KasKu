package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.common.hideKeyboard
import app.trian.kasku.ui.theme.*
import compose.icons.Octicons
import compose.icons.octicons.ArrowRight24
import compose.icons.octicons.Eye16
import compose.icons.octicons.EyeClosed16

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 14.16
 * site https://trian.app
 */
@Composable
fun FormInput(
    initialValue:String="",
    label:String="",
    placeholder:String="",
    modifier: Modifier=Modifier,
    showPasswordObsecure:Boolean=false,
    singleLine:Boolean=true,
    maxLine:Int=1,
    maxLength:Int=500,
    error:Boolean=false,
    masked:VisualTransformation= VisualTransformation.None,
    keyboardType: KeyboardType= KeyboardType.Text,
    imeAction:ImeAction = ImeAction.Done,
    leading:@Composable (() -> Unit)? = null,
    onChange:(String)->Unit ={},
) {
    val ctx = LocalContext.current

    var visibleObsecure by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = modifier.height(10.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.caption.copy(
                color = MaterialTheme.colors.onSurface
            )
        )
        TextField(
            value = initialValue,
            onValueChange = {
                if(it.length <= maxLength) {
                    onChange(it)
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                errorIndicatorColor = ExpensesColor,
            ),
            textStyle =MaterialTheme.typography.body2.copy(
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold
            ),
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            maxLines = maxLine,
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    ctx.hideKeyboard()
                }
            ),
            leadingIcon = leading,
            trailingIcon = {
                if(showPasswordObsecure){
                    IconToggleButton(checked = visibleObsecure, onCheckedChange = {
                        visibleObsecure = !visibleObsecure
                    }) {
                        Icon(
                            imageVector = if(visibleObsecure) Octicons.Eye16 else Octicons.EyeClosed16,
                            contentDescription = "",
                            tint = if (visibleObsecure) DisableContentColor else MaterialTheme.colors.primary
                        )
                    }
                }
            },
            visualTransformation=
            if(showPasswordObsecure)
                if(visibleObsecure)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation()
            else masked,
            isError = error
        )
    }
}

@Composable
fun FormInputWithButton(
    initialValue:String="",
    label:String="",
    placeholder:String="",
    modifier: Modifier=Modifier,
    onChange:(String)->Unit ={},
    showPasswordObsecure:Boolean=false,
    icon:ImageVector = Octicons.ArrowRight24,
    buttonEnabled:Boolean=true,
    singleLine:Boolean=true,
    maxLine:Int=1,
    error:Boolean=false,
    masked:VisualTransformation=VisualTransformation.None,
    keyboardType:KeyboardType= KeyboardType.Text,
    imeAction:ImeAction = ImeAction.Send,
    maxLength:Int=500,
    leading:@Composable (() -> Unit)? = null,
    onSubmit:()->Unit={}
) {
    val ctx =  LocalContext.current
    var visibleObsecure by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = modifier.height(10.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.caption.copy(
                color = MaterialTheme.colors.onSurface
            )
        )
        Row (
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            TextField(
                value =initialValue,
                onValueChange = {
                    if(it.length <= maxLength) {
                        onChange(it)
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    errorIndicatorColor = ExpensesColor
                ),
                textStyle =MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold
                ),
                modifier = modifier.fillMaxWidth(fraction = 0.8f),
                placeholder = {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.body2.copy(
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                maxLines = maxLine,
                singleLine = singleLine,
                keyboardOptions = KeyboardOptions(
                    imeAction = imeAction,
                    keyboardType = keyboardType
                ),
                keyboardActions = KeyboardActions (
                    onSend={
                        ctx.hideKeyboard()
                        onSubmit()
                    }
                ),
                leadingIcon = leading,
                trailingIcon = {
                    if(showPasswordObsecure){
                        IconToggleButton(checked = visibleObsecure, onCheckedChange = {
                            visibleObsecure = !visibleObsecure
                        }) {
                            Icon(
                                imageVector = if(visibleObsecure) Octicons.Eye16 else Octicons.EyeClosed16,
                                contentDescription = "",
                                tint = if (visibleObsecure) DisableContentColor else MaterialTheme.colors.primary
                            )
                        }
                    }
                },
                visualTransformation=
                if(showPasswordObsecure)
                    if(visibleObsecure)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation()
                else masked,
                isError = error
            )
            ButtonIcon(
                icon = icon,
                onClick = {
                        ctx.hideKeyboard()
                        onSubmit()
                },
                    enabled = buttonEnabled
            )
        }
        }
}

@Composable
fun FormPickerWithButton(
    initialValue:String="",
    label:String="",
    placeholder:String="",
    modifier: Modifier=Modifier,
    icon:ImageVector = Octicons.ArrowRight24,
    buttonEnabled:Boolean=true,
    masked:VisualTransformation=VisualTransformation.None,
    keyboardType:KeyboardType=KeyboardType.Text,
    imeAction: ImeAction=ImeAction.Send,
    leading:@Composable (() -> Unit)? = null,
    onClick:()->Unit={},
    onSubmit:()->Unit={}
) {
    val ctx =  LocalContext.current

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = modifier.height(10.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.caption.copy(
                color = MaterialTheme.colors.onSurface
            )
        )
        Row (
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
                TextField(
                    value = TextFieldValue(
                        text = initialValue
                    ),
                    onValueChange = {

                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                    ),
                    textStyle =MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = modifier.fillMaxWidth(fraction = 0.8f).clickable {
                        onClick()
                    },
                    placeholder = {
                        Text(
                            text = placeholder,
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onSurface,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    },
                    maxLines = 100,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send,
                        keyboardType = keyboardType
                    ),
                    keyboardActions = KeyboardActions (
                        onSend={
                            ctx.hideKeyboard()
                            onSubmit()
                        }
                    ),
                    leadingIcon = leading,
                    visualTransformation= masked,
                    readOnly = true,
                    enabled = false
                )


            ButtonIcon(
                icon = icon,
                onClick = onSubmit,
                enabled = buttonEnabled
            )
        }
    }
}

@Composable
fun FormInputPickColor(
    modifier: Modifier = Modifier,
    label:String="",
    selected:GradientColor?=null,
    onSelect:(GradientColor)->Unit={_->}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density


    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.caption.copy(
                color = MaterialTheme.colors.onSurface
            )
        )
        Spacer(modifier = modifier.height(10.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            listGradient.forEach {
                color->
                Box(
                    modifier = modifier
                        .size(currentWidth / 7 - 20.dp)
                        .clickable {
                            onSelect(color)
                        }
                        .clip(CircleShape)
                        .border(
                            width = if (selected?.let { it.first == color.first } == true) 2.dp else 0.dp,
                            color = if (selected?.let { it.first == color.first } == true) Color.Black else Color.Transparent,
                            shape = CircleShape
                        )
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    HexToJetpackColor.getColor(color.first),
                                    HexToJetpackColor.getColor(color.second)
                                )
                            )
                        )
                ){

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewFormInput() {
    KasKuTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            FormInput(
                placeholder = "name@trian.app"
            )
            FormInputWithButton(
                placeholder = "*****",
            )
            FormInputPickColor(
                label = "Select color"
            )
        }
    }
}