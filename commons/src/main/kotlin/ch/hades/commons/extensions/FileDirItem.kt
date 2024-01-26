package ch.hades.commons.extensions

import android.content.Context
import ch.hades.commons.models.FileDirItem

fun FileDirItem.isRecycleBinPath(context: Context): Boolean {
    return path.startsWith(context.recycleBinPath)
}
