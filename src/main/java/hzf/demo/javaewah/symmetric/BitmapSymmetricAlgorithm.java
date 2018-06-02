package hzf.demo.javaewah.symmetric;

import hzf.demo.javaewah.BitmapStorage;
import hzf.demo.javaewah.EWAHCompressedBitmap;

/**
 * Generic interface to compute symmetric Boolean functions.
 *
 * @author Daniel Lemire
 * @see <a
 * href="http://en.wikipedia.org/wiki/Symmetric_Boolean_function">http://en.wikipedia.org/wiki/Symmetric_Boolean_function</a>
 * @since 0.8.0
 */
public interface BitmapSymmetricAlgorithm {
    /**
     * Compute a Boolean symmetric query.
     *
     * @param f   symmetric boolean function to be processed
     * @param out the result of the query
     * @param set the inputs
     */
    void symmetric(UpdateableBitmapFunction f, BitmapStorage out, EWAHCompressedBitmap... set);
}
