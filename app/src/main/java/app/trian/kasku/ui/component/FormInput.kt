package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.ui.theme.DisableContentColor
import app.trian.kasku.ui.theme.KasKuTheme
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
    onChange:(String)->Unit ={},

) {
    var value by remember {
        mutableStateOf(TextFieldValue(text = initialValue))
    }
    var visibleObsecure by remember {
        mutableStateOf(!showPasswordObsecure)
    }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.caption.copy(
                color = MaterialTheme.colors.onSurface
            )
        )
        Spacer(modifier = modifier.height(10.dp))
        TextField(
            value = value,
            onValueChange = {
                value = it
                onChange(it.text)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
            ),
            textStyle =MaterialTheme.typography.body2.copy(
                color = MaterialTheme.colors.onBackground
            ),
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
            },
            maxLines = maxLine,
            singleLine = singleLine,
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
            visualTransformation=if(visibleObsecure) VisualTransformation.None else PasswordVisualTransformation(),
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
    onSubmit:()->Unit={}
) {

    var value by remember {
        mutableStateOf(TextFieldValue(text = initialValue))
    }
    var visibleObsecure by remember {
        mutableStateOf(!showPasswordObsecure)
    }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.caption.copy(
                color = MaterialTheme.colors.onSurface
            )
        )
        Spacer(modifier = modifier.height(10.dp))
        Row (
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            TextField(
                value = value,
                onValueChange = {
                    value = it
                    onChange(it.text)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                ),
                textStyle =MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.onBackground
                ),
                modifier = modifier.fillMaxWidth(fraction = 0.8f),
                placeholder = {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.body2.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                },
                maxLines = maxLine,
                singleLine = singleLine,
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
                visualTransformation=if(visibleObsecure) VisualTransformation.None else PasswordVisualTransformation(),
            )
            ButtonIcon(
                icon = icon,
                onClick = onSubmit,
                enabled = buttonEnabled
            )
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
        }
    }
}