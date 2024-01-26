package ch.hades.commons.dialogs

import android.app.Activity
import android.view.LayoutInflater
import ch.hades.commons.R
import ch.hades.commons.databinding.DialogWhatsNewBinding
import ch.hades.commons.extensions.getAlertDialogBuilder
import ch.hades.commons.extensions.setupDialogStuff
import ch.hades.commons.models.Release

class WhatsNewDialog(val activity: Activity, val releases: List<Release>) {
    init {
        val view = DialogWhatsNewBinding.inflate(LayoutInflater.from(activity), null, false)
        view.whatsNewContent.text = getNewReleases()

        activity.getAlertDialogBuilder()
            .setPositiveButton(R.string.ok, null)
            .apply {
                activity.setupDialogStuff(view.root, this, R.string.whats_new, cancelOnTouchOutside = false)
            }
    }

    private fun getNewReleases(): String {
        val sb = StringBuilder()

        releases.forEach {
            val parts = activity.getString(it.textId).split("\n").map(String::trim)
            parts.forEach {
                sb.append("- $it\n")
            }
        }

        return sb.toString()
    }
}
