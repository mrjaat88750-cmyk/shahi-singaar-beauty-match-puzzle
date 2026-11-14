package com.shahisingaar.game.state

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import com.shahisingaar.game.R
import com.shahisingaar.game.model.JewelleryItem
import com.shahisingaar.game.model.LevelConfig
import kotlin.math.hypot

class GameViewModel {
    private val initialItems = listOf(
        JewelleryItem("crown", "Royal Crown", R.drawable.crown, 0.500f, 0.135f, 0.340f, 0.125f, 0f, 0.09f, 3f),
        JewelleryItem("earring_l", "Left Jhumka", R.drawable.earring_left, 0.405f, 0.335f, 0.075f, 0.065f, 0f, 0.08f, 2f),
        JewelleryItem("earring_r", "Right Jhumka", R.drawable.earring_right, 0.620f, 0.330f, 0.080f, 0.085f, 0f, 0.08f, 2f),
        JewelleryItem("necklace", "Bridal Choker", R.drawable.necklace, 0.535f, 0.440f, 0.280f, 0.130f, 0f, 0.09f, 4f),
        JewelleryItem("bangle", "Kundan Bangle", R.drawable.bangle, 0.530f, 0.700f, 0.125f, 0.095f, 0f, 0.09f, 5f)
    )

    var level by mutableStateOf(
        LevelConfig(
            id = 1,
            title = "शाही राजस्थानी दुल्हन",
            baseImageRes = R.drawable.girl_base,
            referenceImageRes = R.drawable.girl_reference,
            imageAspectRatio = 9f / 16f,
            items = initialItems
        )
    )
        private set

    var isPaused by mutableStateOf(false)
    var isSoundOn by mutableStateOf(true)
    var isDevMode by mutableStateOf(false)
    var showReferenceOverlay by mutableStateOf(false)
    var activeHintItemId by mutableStateOf<String?>(null)
    var lastSnapEffectPos by mutableStateOf<Offset?>(null)

    private val toneGen = ToneGenerator(AudioManager.STREAM_MUSIC, 85)

    val placedCount: Int get() = level.items.count { it.isPlaced }
    val totalCount: Int get() = level.items.size
    val isLevelCompleted: Boolean get() = placedCount == totalCount

    fun playAsmrSnapSound() {
        if (isSoundOn) {
            toneGen.startTone(ToneGenerator.TONE_PROP_ACK, 120)
        }
    }

    fun playReturnSound() {
        if (isSoundOn) {
            toneGen.startTone(ToneGenerator.TONE_PROP_NACK, 90)
        }
    }

    fun playWinSound() {
        if (isSoundOn) {
            toneGen.startTone(ToneGenerator.TONE_CDMA_CONFIRM, 300)
        }
    }

    fun tryPlaceItem(itemId: String, dropScreenCenter: Offset, imageBounds: Rect): Boolean {
        if (imageBounds.width <= 0f || imageBounds.height <= 0f) return false

        val item = level.items.find { it.id == itemId } ?: return false
        if (item.isPlaced) return false

        // Convert screen drop position to normalized image coordinates (0.0 .. 1.0)
        val normX = (dropScreenCenter.x - imageBounds.left) / imageBounds.width
        val normY = (dropScreenCenter.y - imageBounds.top) / imageBounds.height

        val distance = hypot(normX - item.targetX, normY - item.targetY)
        if (distance <= item.snapRadius) {
            level = level.copy(
                items = level.items.map {
                    if (it.id == itemId) it.copy(isPlaced = true) else it
                }
            )
            if (activeHintItemId == itemId) activeHintItemId = null
            lastSnapEffectPos = Offset(
                imageBounds.left + item.targetX * imageBounds.width,
                imageBounds.top + item.targetY * imageBounds.height
            )
            if (isLevelCompleted) playWinSound() else playAsmrSnapSound()
            return true
        } else {
            playReturnSound()
            return false
        }
    }

    fun triggerHint() {
        activeHintItemId = level.items.firstOrNull { !it.isPlaced }?.id
    }

    fun resetLevel() {
        level = level.copy(items = initialItems)
        activeHintItemId = null
        lastSnapEffectPos = null
        isPaused = false
    }

    fun clearSnapEffect() {
        lastSnapEffectPos = null
    }
}
