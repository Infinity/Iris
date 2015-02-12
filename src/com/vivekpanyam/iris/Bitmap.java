/*
 * Copyright 2015 Vivek Panyam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vivekpanyam.iris;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * "Polyfill" for Android Bitmap using BufferedImage.
 */
public class Bitmap {
    private BufferedImage source;
    public Bitmap(BufferedImage image) {
        source = image;
    }

    public int[] getPixels() {
        return convertTo2DUsingGetRGB(source);
    }

    public int getWidth() {
        return source.getWidth();
    }

    public int getHeight() {
        return source.getHeight();
    }

    private static int[] convertTo2DUsingGetRGB(BufferedImage image) {
        int size = image.getWidth() * image.getHeight();
        int[] result = new int[size];

        for (int row = 0; row < image.getHeight(); row++) {
            for (int col = 0; col < image.getWidth(); col++) {
                result[row * image.getWidth() + col] = image.getRGB(col, row);
            }
        }

        return result;
    }

    public static Bitmap createScaledBitmap(Bitmap b, float scaleRatio) {
        return new Bitmap(scaleImageDown(b.source, scaleRatio));
    }

    private static BufferedImage scaleImageDown(BufferedImage bitmap, float scaleRatio) {

        BufferedImage after = new BufferedImage((int) (bitmap.getWidth() * scaleRatio),
                (int) (bitmap.getHeight() * scaleRatio), BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scaleRatio, scaleRatio);
        AffineTransformOp scaleOp =
                new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return scaleOp.filter(bitmap, after);
    }
}
