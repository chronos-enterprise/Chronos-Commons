package ch.hades.commons.compose.settings

import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import ch.hades.commons.compose.extensions.MyDevices
import ch.hades.commons.compose.theme.AppThemeSurface
import ch.hades.commons.compose.theme.divider_grey

@Composable
fun SettingsHorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = divider_grey,
    thickness: Dp = DividerDefaults.Thickness,
) {
    HorizontalDivider(modifier, color = color, thickness = thickness)
}


@Composable
@MyDevices
private fun SettingsHorizontalDividerPreview() {
    AppThemeSurface {
        SettingsHorizontalDivider()
    }
}
