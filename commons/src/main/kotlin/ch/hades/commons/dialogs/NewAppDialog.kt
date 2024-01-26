package ch.hades.commons.dialogs

import android.app.Activity
import android.text.Html
import android.text.method.LinkMovementMethod
import ch.hades.commons.R
import ch.hades.commons.databinding.DialogTextviewBinding
import ch.hades.commons.extensions.getAlertDialogBuilder
import ch.hades.commons.extensions.setupDialogStuff

class NewAppDialog(val activity: Activity, val packageName: String, val title: String, val packageName2: String, val title2: String) {
    init {
        val view = DialogTextviewBinding.inflate(activity.layoutInflater, null, false).apply {
            val text = String.format(
                activity.getString(R.string.new_app),
                "https://play.google.com/store/apps/details?id=$packageName", title,
                "https://play.google.com/store/apps/details?id=$packageName2", title2
            )

            textView.text = Html.fromHtml(text)
            textView.movementMethod = LinkMovementMethod.getInstance()
        }

        activity.getAlertDialogBuilder()
            .setPositiveButton(R.string.ok, null)
            .apply {
                activity.setupDialogStuff(view.root, this, cancelOnTouchOutside = false)
            }
    }
}
