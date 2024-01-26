package ch.hades.commons.compose.extensions

import android.content.Context
import ch.hades.commons.helpers.BaseConfig

val Context.config: BaseConfig get() = BaseConfig.newInstance(applicationContext)
