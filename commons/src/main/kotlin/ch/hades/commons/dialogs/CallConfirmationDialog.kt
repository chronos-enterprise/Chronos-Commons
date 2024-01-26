package ch.hades.commons.dialogs

import android.view.animation.AnimationUtils
import ch.hades.commons.R
import ch.hades.commons.activities.BaseSimpleActivity
import ch.hades.commons.databinding.DialogCallConfirmationBinding
import ch.hades.commons.extensions.applyColorFilter
import ch.hades.commons.extensions.getAlertDialogBuilder
import ch.hades.commons.extensions.getProperTextColor
import ch.hades.commons.extensions.setupDialogStuff

class CallConfirmationDialog(val activity: BaseSimpleActivity, val callee: String, private val callback: () -> Unit) {
    private var view = DialogCallConfirmationBinding.inflate(activity.layoutInflater, null, false)

    init {
        view.callConfirmPhone.applyColorFilter(activity.getProperTextColor())
        activity.getAlertDialogBuilder()
            .setNegativeButton(R.string.cancel, null)
            .apply {
                val title = String.format(activity.getString(R.string.confirm_calling_person), callee)
                activity.setupDialogStuff(view.root, this, titleText = title) { alertDialog ->
                    view.callConfirmPhone.apply {
                        startAnimation(AnimationUtils.loadAnimation(activity, R.anim.shake_pulse_animation))
                        setOnClickListener {
                            callback.invoke()
                            alertDialog.dismiss()
                        }
                    }
                }
            }
    }
}
