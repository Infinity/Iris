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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Iris {

    /**
     * Returns a Color for an image at a given URL
     * @param url A URL to an image
     * @param defaultColor A color to return if we can't find a Vibrant or Muted Color
     * @return A Color Hex String
     * @throws IOException
     */
    public static String getColor(String url, String defaultColor) throws IOException {
        return getColor(ImageIO.read(new URL(url)), defaultColor);
    }


    /**
     * Returns a Color from a BufferedImage
     * @param image An image to try to get a vibrant color from
     * @param defaultColor A color to return if we can't find a Vibrant or Muted Color
     * @return A Color Hex String
     */
    public static String getColor(BufferedImage image, String defaultColor) {
        Palette item = Palette.generate(new Bitmap(image));
        Palette.Swatch swatch = item.getVibrantSwatch();

        if (swatch == null) {
            swatch = item.getMutedSwatch();
        }

        if (swatch == null) {
            return defaultColor;
        }

        int color = swatch.getRgb();

        return String.format("#%02x%02x%02x", Color.red(color), Color.green(color), Color.blue(color));
    }
}
