package ch.hades.commons.dialogs

import android.app.Activity
import android.text.Html
import android.text.method.LinkMovementMethod
import ch.hades.commons.R
import ch.hades.commons.databinding.DialogPurchaseThankYouBinding
import ch.hades.commons.extensions.*

class PurchaseThankYouDialog(val activity: Activity) {
    init {
        val view = DialogPurchaseThankYouBinding.inflate(activity.layoutInflater, null, false).apply {
            var text = activity.getString(R.string.purchase_thank_you)
            if (activity.baseConfig.appId.removeSuffix(".debug").endsWith(".pro")) {
                text += "<br><br>${activity.getString(R.string.shared_theme_note)}"
            }

            purchaseThankYou.text = Html.fromHtml(text)
            purchaseThankYou.movementMethod = LinkMovementMethod.getInstance()
            purchaseThankYou.removeUnderlines()
        }

        activity.getAlertDialogBuilder()
            .setPositiveButton(R.string.purchase) { _, _ -> activity.launchPurchaseThankYouIntent() }
            .setNegativeButton(R.string.later, null)
            .apply {
                activity.setupDialogStuff(view.root, this, cancelOnTouchOutside = false)
            }
    }
}
