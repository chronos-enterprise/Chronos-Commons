package ch.hades.commons.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import ch.hades.commons.compose.extensions.AdjustNavigationBarColors
import ch.hades.commons.compose.extensions.TransparentSystemBars
import ch.hades.commons.compose.screens.FAQScreen
import ch.hades.commons.compose.theme.AppThemeSurface
import ch.hades.commons.helpers.APP_FAQ
import ch.hades.commons.models.FAQItem
import kotlinx.collections.immutable.toImmutableList

class FAQActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            TransparentSystemBars()
            AppThemeSurface {
                var canScroll by remember { mutableStateOf<Boolean?>(null) }
                AdjustNavigationBarColors(canScroll)
                val faqItems = remember { intent.getSerializableExtra(APP_FAQ) as ArrayList<FAQItem> }
                FAQScreen(
                    goBack = ::finish,
                    faqItems = faqItems.toImmutableList(),
                    canScroll = { canPerformScroll -> canScroll = canPerformScroll }
                )
            }
        }
    }
}
