package ch.hades.commons.dialogs

import androidx.appcompat.app.AlertDialog
import ch.hades.commons.R
import ch.hades.commons.activities.BaseSimpleActivity
import ch.hades.commons.databinding.DialogEnterPasswordBinding
import ch.hades.commons.extensions.*

class EnterPasswordDialog(
    val activity: BaseSimpleActivity,
    private val callback: (password: String) -> Unit,
    private val cancelCallback: () -> Unit
) {

    private var dialog: AlertDialog? = null
    private val view: DialogEnterPasswordBinding = DialogEnterPasswordBinding.inflate(activity.layoutInflater, null, false)

    init {
        activity.getAlertDialogBuilder()
            .setPositiveButton(R.string.ok, null)
            .setNegativeButton(R.string.cancel, null)
            .apply {
                activity.setupDialogStuff(view.root, this, R.string.enter_password) { alertDialog ->
                    dialog = alertDialog
                    alertDialog.showKeyboard(view.password)
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                        val password = view.password.value

                        if (password.isEmpty()) {
                            activity.toast(R.string.empty_password)
                            return@setOnClickListener
                        }

                        callback(password)
                    }

                    alertDialog.setOnDismissListener {
                        cancelCallback()
                    }
                }
            }
    }

    fun dismiss(notify: Boolean = true) {
        if (!notify) {
            dialog?.setOnDismissListener(null)
        }
        dialog?.dismiss()
    }

    fun clearPassword() {
        view.password.text?.clear()
    }
}
