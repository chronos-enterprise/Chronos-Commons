package ch.hades.commons.dialogs

import ch.hades.commons.R
import ch.hades.commons.activities.BaseSimpleActivity
import ch.hades.commons.databinding.DialogOpenDeviceSettingsBinding
import ch.hades.commons.extensions.getAlertDialogBuilder
import ch.hades.commons.extensions.openDeviceSettings
import ch.hades.commons.extensions.setupDialogStuff

class OpenDeviceSettingsDialog(val activity: BaseSimpleActivity, message: String) {

    init {
        activity.apply {
            val view = DialogOpenDeviceSettingsBinding.inflate(layoutInflater, null, false)
            view.openDeviceSettings.text = message
            getAlertDialogBuilder()
                .setNegativeButton(R.string.close, null)
                .setPositiveButton(R.string.go_to_settings) { _, _ ->
                    openDeviceSettings()
                }.apply {
                    setupDialogStuff(view.root, this)
                }
        }
    }
}
