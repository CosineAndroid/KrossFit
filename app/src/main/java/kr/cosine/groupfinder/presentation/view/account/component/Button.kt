package kr.cosine.groupfinder.presentation.view.account.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.cosine.groupfinder.R

private const val CLICK_INTERVAL = 1000

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    text: String,
    onClick: () -> Unit,
) {
    var cooldown by rememberSaveable {
        mutableLongStateOf(System.currentTimeMillis() + CLICK_INTERVAL)
    }
    Button(
        enabled = isEnabled,
        onClick = {
            val currentTime = System.currentTimeMillis()
            if (cooldown > currentTime) return@Button
            cooldown = currentTime + CLICK_INTERVAL
            onClick()
        },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.account_enable_button_background),
            disabledContainerColor = colorResource(R.color.account_disable_button_background)
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(
                horizontal = 30.dp
            )
    ) {
        BaseText(
            text = text,
            fontSize = 17.sp,
            color = if (isEnabled) {
                colorResource(R.color.white)
            } else {
                colorResource(R.color.account_disable_button_text)
            }
        )
    }
}