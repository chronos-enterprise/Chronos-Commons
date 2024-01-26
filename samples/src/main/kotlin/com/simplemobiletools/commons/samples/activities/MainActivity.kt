package ch.hades.commons.samples.activities

import android.os.Bundle
import ch.hades.commons.activities.BaseSimpleActivity
import ch.hades.commons.dialogs.BottomSheetChooserDialog
import ch.hades.commons.extensions.appLaunched
import ch.hades.commons.extensions.toast
import ch.hades.commons.extensions.viewBinding
import ch.hades.commons.helpers.LICENSE_AUTOFITTEXTVIEW
import ch.hades.commons.models.FAQItem
import ch.hades.commons.models.SimpleListItem
import ch.hades.commons.samples.BuildConfig
import ch.hades.commons.samples.R
import ch.hades.commons.samples.databinding.ActivityMainBinding

class MainActivity : BaseSimpleActivity() {
    override fun getAppLauncherName() = getString(R.string.smtco_app_name)

    override fun getAppIconIDs(): ArrayList<Int> {
        val ids = ArrayList<Int>()
        ids.add(R.mipmap.commons_launcher)
        return ids
    }

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        appLaunched(BuildConfig.APPLICATION_ID)

        updateMaterialActivityViews(binding.mainCoordinator, binding.mainHolder, useTransparentNavigation = true, useTopSearchMenu = false)
        setupMaterialScrollListener(binding.mainNestedScrollview, binding.mainToolbar)

        binding.mainColorCustomization.setOnClickListener {
            startCustomizationActivity()
        }
        binding.bottomSheetChooser.setOnClickListener {
            launchAbout()
        }
    }

    private fun launchAbout() {
        val licenses = LICENSE_AUTOFITTEXTVIEW

        val faqItems = arrayListOf(
            FAQItem(ch.hades.commons.R.string.faq_1_title_commons, ch.hades.commons.R.string.faq_1_text_commons),
            FAQItem(ch.hades.commons.R.string.faq_1_title_commons, ch.hades.commons.R.string.faq_1_text_commons),
            FAQItem(ch.hades.commons.R.string.faq_4_title_commons, ch.hades.commons.R.string.faq_4_text_commons)
        )

        if (!resources.getBoolean(ch.hades.commons.R.bool.hide_google_relations)) {
            faqItems.add(FAQItem(ch.hades.commons.R.string.faq_2_title_commons, ch.hades.commons.R.string.faq_2_text_commons))
            faqItems.add(FAQItem(ch.hades.commons.R.string.faq_6_title_commons, ch.hades.commons.R.string.faq_6_text_commons))
        }

        startAboutActivity(R.string.smtco_app_name, licenses, BuildConfig.VERSION_NAME, faqItems, true)
    }

    private fun launchBottomSheetDemo() {
        BottomSheetChooserDialog.createChooser(
            fragmentManager = supportFragmentManager,
            title = ch.hades.commons.R.string.please_select_destination,
            items = arrayOf(
                SimpleListItem(1, ch.hades.commons.R.string.record_video, ch.hades.commons.R.drawable.ic_camera_vector),
                SimpleListItem(
                    2,
                    ch.hades.commons.R.string.record_audio,
                    ch.hades.commons.R.drawable.ic_microphone_vector,
                    selected = true
                ),
                SimpleListItem(4, ch.hades.commons.R.string.choose_contact, ch.hades.commons.R.drawable.ic_add_person_vector)
            )
        ) {
            toast("Clicked ${it.id}")
        }
    }

    override fun onResume() {
        super.onResume()
        setupToolbar(binding.mainToolbar)
    }
}
