package com.tracer.kiosk.presentation.components.navigation

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix

object QrCodeGenerator {

    /**
     * Generates a QR code bitmap from the provided payload string.
     *
     * Example payload:
     * tracer://navigate?start=N1&destination=mr_a_v_kolaki&floor=ground&version=1.5
     */
    fun generate(
        payload: String,
        size: Int = 512
    ): Bitmap {

        val hints = mapOf(
            EncodeHintType.MARGIN to 1
        )

        val bitMatrix: BitMatrix = MultiFormatWriter().encode(
            payload,
            BarcodeFormat.QR_CODE,
            size,
            size,
            hints
        )

        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)

        for (x in 0 until size) {
            for (y in 0 until size) {
                bitmap.setPixel(
                    x,
                    y,
                    if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                )
            }
        }

        return bitmap
    }
}