package xyz.jayeshseth.gdgcloudchat.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.InsertPhoto
import androidx.compose.material.icons.outlined.Mood
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import xyz.jayeshseth.gdgcloudchat.util.model.ChatViewModel

private fun TextFieldValue.addText(newString: String): TextFieldValue {
    val newText = this.text.replaceRange(
        this.selection.start,
        this.selection.end,
        newString
    )
    val newSelection = TextRange(
        start = newText.length,
        end = newText.length
    )
    return this.copy(text = newText, selection = newSelection)
}

@Composable
fun UserInput(
    onMessageSent: (String) -> Unit,
    resetScroll: () -> Unit = {},
    textState: String,
    onTextChanged: (String) -> Unit,
    chatViewModel: ChatViewModel = viewModel(),
    modifier: Modifier
) {
    var currentInputSelector by rememberSaveable { mutableStateOf(InputSelector.NONE) }
    var textFieldFocusedState by remember { mutableStateOf(false) }
    val dismissKeyboard = { currentInputSelector = InputSelector.NONE }

    Surface(tonalElevation = 2.dp) {
        UserInputTextField(
            keyboardShown = currentInputSelector == InputSelector.NONE && textFieldFocusedState,
            focusState = textFieldFocusedState,
            value = textState,
            sendMessageEnabled = textState.isNotBlank(),
            onMessageSent = {
                onMessageSent(textState)
                resetScroll()
                dismissKeyboard()
            },
            onTextChanged = onTextChanged,
            onTextFieldFocused = { focused ->
                if (focused) {
                    currentInputSelector = InputSelector.NONE
                    resetScroll()
                }
                textFieldFocusedState = focused
            }
        )
    }
}

val KeyboardShownKey = SemanticsPropertyKey<Boolean>("KeyboardShownKey")
var SemanticsPropertyReceiver.keyboardShownProperty by KeyboardShownKey

@Composable
fun UserInputTextField(
    keyboardShown: Boolean,
    focusState: Boolean,
    value: String,
    sendMessageEnabled: Boolean,
    onMessageSent: () -> Unit,
    onTextChanged: (String) -> Unit,
    onTextFieldFocused: (Boolean) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(10.dp)
                .semantics {
                    contentDescription = ""
                    keyboardShownProperty = keyboardShown
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            var lastFocusState by remember { mutableStateOf(false) }
            BasicTextField(
                value = value,
                onValueChange = { onTextChanged(it) },
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1f)
                    .onFocusChanged { state ->
                        if (lastFocusState != state.isFocused) {
                            onTextFieldFocused(state.isFocused)
                        }
                        lastFocusState = state.isFocused
                    },
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = ImeAction.Send
                ),
                maxLines = 1,
                cursorBrush = SolidColor(LocalContentColor.current),
                textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current)
            ) {
                val disableContentColor =
                    MaterialTheme.colorScheme.onSurfaceVariant
                if (value.isEmpty() && !focusState) {
                    Text(
                        modifier = Modifier
                            .padding(start = 2.dp),
                        text = "hint",
                        style = MaterialTheme.typography.bodyLarge.copy(color = disableContentColor)
                    )
                }
            }

            val border = if (!sendMessageEnabled) {
                BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                )
            } else {
                null
            }

            val disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

            val buttonColors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.Transparent,
                disabledContentColor = disabledContentColor
            )
            Button(
                modifier = Modifier.height(36.dp),
                enabled = sendMessageEnabled,
                onClick = onMessageSent,
                colors = buttonColors,
                border = border,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    "send",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }

}

enum class InputSelector {
    NONE,
    DM,
    EMOJI,
    CAMERA,
    PICTURE
}

/*
    TBD
 */
@Composable
fun AdditionalFunctionalityBtn(
    icon: ImageVector,
    description: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val backgroundModifier = if (selected) {
        Modifier.background(
            color = MaterialTheme.colorScheme.secondary,
            shape = RoundedCornerShape(14.dp)
        )
    } else {
        Modifier
    }

    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(56.dp)
            .then(backgroundModifier)
    ) {
        val tint = if (selected) {
            MaterialTheme.colorScheme.onSecondary
        } else {
            MaterialTheme.colorScheme.secondary
        }
        Icon(
            imageVector = icon,
            tint = tint,
            contentDescription = description,
            modifier = Modifier.padding(16.dp)
        )
    }
}

/*
    TBD
 */
@Composable
fun AdditionalFunctionalities(
    currentInputSelector: InputSelector,
    onSelectorChange: (InputSelector) -> Unit
) {
    Row(
        modifier = Modifier
            .height(70.dp)
            .wrapContentHeight()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AdditionalFunctionalityBtn(
            icon = Icons.Outlined.Mood,
            description = "",
            selected = currentInputSelector == InputSelector.EMOJI,
            onClick = { onSelectorChange(InputSelector.EMOJI) }
        )
        AdditionalFunctionalityBtn(
            icon = Icons.Outlined.InsertPhoto,
            description = "",
            selected = currentInputSelector == InputSelector.PICTURE,
            onClick = { onSelectorChange(InputSelector.PICTURE) }
        )
        AdditionalFunctionalityBtn(
            icon = Icons.Outlined.Camera,
            description = "",
            selected = currentInputSelector == InputSelector.CAMERA,
            onClick = { onSelectorChange(InputSelector.CAMERA) }
        )
        AdditionalFunctionalityBtn(
            icon = Icons.Outlined.AlternateEmail,
            description = "",
            selected = currentInputSelector == InputSelector.DM,
            onClick = { onSelectorChange(InputSelector.DM) }
        )
    }
}