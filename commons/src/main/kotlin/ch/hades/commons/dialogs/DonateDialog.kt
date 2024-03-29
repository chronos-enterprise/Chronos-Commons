package ch.hades.commons.dialogs

import android.app.Activity
import android.text.Html
import android.text.method.LinkMovementMethod
import ch.hades.commons.R
import ch.hades.commons.databinding.DialogDonateBinding
import ch.hades.commons.extensions.*

class DonateDialog(val activity: Activity) {
    init {
        val view = DialogDonateBinding.inflate(activity.layoutInflater,null, false).apply {
            dialogDonateImage.applyColorFilter(activity.getProperTextColor())
            dialogDonateText.text = Html.fromHtml(activity.getString(R.string.donate_short))
            dialogDonateText.movementMethod = LinkMovementMethod.getInstance()
            dialogDonateImage.setOnClickListener {
                activity.launchViewIntent(R.string.thank_you_url)
            }
        }

        activity.getAlertDialogBuilder()
            .setPositiveButton(R.string.purchase) { _, _ -> activity.launchViewIntent(R.string.thank_you_url) }
            .setNegativeButton(R.string.later, null)
            .apply {
                activity.setupDialogStuff(view.root, this, cancelOnTouchOutside = false)
            }
    }
}
