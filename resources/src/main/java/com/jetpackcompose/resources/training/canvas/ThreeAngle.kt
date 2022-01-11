package com.jetpackcompose.resources.training.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun ThreeAngle(modifier: Modifier) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val path = Path()
        /**  step 1
         *   Y0 Y1 Y2 Y3
         *   ---------
         *X0|         |
         *X1|         |
         *X2|*        |
         *X3|_________|
         */
        path.lineTo(x = 0f, y = canvasHeight)

        /**  step 2
         *   Y0 Y1 Y2 Y3
         *   ---------
         *X0|        *|
         *X1|         |
         *X2|         |
         *X3|_________|
         */
        path.lineTo(x = canvasWidth, y = 0f)

        /**  step 3
         *   Y0 Y1 Y2 Y3
         *   ---------
         *X0|         |
         *X1|         |
         *X2|        *|
         *X3|_________|
         */
        path.lineTo(x = canvasWidth, y = canvasHeight)


        /**  step 4
         *   Y0 Y1 Y2 Y3
         *   ---------
         *X0|         |
         *X1|         |
         *X2|*        |
         *X3|_________|
         */
        path.lineTo(x = 0f, y = canvasHeight)

        /** finally draw the points
         *   Y0 Y1 Y2 Y3
         *   ---------
         *X0|        *|
         *X1|         |
         *X2|*       *|
         *X3|_________|
         */
        drawPath(path = path, color = Color.Blue)
    }
}