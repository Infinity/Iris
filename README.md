# Iris

Iris is used to get colors from images. It can get vibrant colors, muted colors, etc.

It's a pure Java port of the Android Palette library with no external dependencies.
See the [Android Palette Docs](https://developer.android.com/reference/android/support/v7/graphics/Palette.html) for more information on what Palette does.

## Usage

You can use Palette directly (again, see the [Palette docs](https://developer.android.com/reference/android/support/v7/graphics/Palette.html) for more info).

There is also an Iris class that has some commonly used functions:

`getColor(BufferedImage image, String defaultColor)` tries to get the most vibrant color from `image`. If there isn't one, it tries to get a muted color. If it can't do that either, it returns `defaultColor`.

`getColor(String url, String defaultColor)` gets an image from a URL and calls the above method.

## License

Apache 2.0

<img src="http://i.imgur.com/fV5E45O.png" width="50px" height="50px"/>
