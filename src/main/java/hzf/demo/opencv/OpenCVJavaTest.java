package hzf.demo.opencv;

import org.opencv.core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.FlannBasedMatcher;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * Created by better on 2014/10/4.
 *
 */
public class OpenCVJavaTest{

    static
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void run2(String inFile, String templateFile, String outFile, int match_method)
    {
        FeatureDetector fd = FeatureDetector.create(FeatureDetector.SIFT);


    }

    public static void run(String inFile, String templateFile, String outFile, int match_method)
    {
        Mat img = imread(inFile);
        Mat templ = imread(templateFile);

        // / Create the result matrix
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

        // / Do the Matching and Normalize
        Imgproc.matchTemplate(img, templ, result, match_method);



        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // / Localizing the best match with minMaxLoc
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
        Point matchLoc;
        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
            matchLoc = mmr.minLoc;
        } else {
            matchLoc = mmr.maxLoc;
        }
        // / Show me what you got

        if (mmr.maxVal >= 1000)
        {
            System.out.println("minVal: " + mmr.minVal);
            System.out.println("maxVal: " + mmr.maxVal);

            Point rectAngle = new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows());
            Imgproc.rectangle(img, matchLoc, rectAngle, new Scalar(0, 255, 0));

            System.out.println(matchLoc.x);
            System.out.println(matchLoc.y);

            System.out.println(templ.cols());
            System.out.println(templ.rows());

            System.out.println(rectAngle.x);
            System.out.println(rectAngle.y);



            // Save the visualized detection.
            System.out.println("Writing "+ outFile);

            imwrite(outFile, img);
        }


    }

    private static Mat imread(String inFile)
    {
        return Imgcodecs.imread(inFile);
    }

    private static boolean imwrite(String outFile, Mat mat)
    {
        return Imgcodecs.imwrite(outFile, mat);
    }

    public static void main(String[] args)
    {
        String test = "G:\\data\\test.png";
        String testOut = "G:\\data\\test2.png";
//        String muban = "G:\\data\\guaji.png";
//        String muban = "G:\\data\\changshoucun.png";
//        String muban = "G:\\data\\boluo.png";
        String muban = "G:\\data\\baoguo2.png";

//        run(test, muban, testOut, Imgproc.TM_CCOEFF_NORMED);

        Mat img = imread(test);
        Mat templ = imread(muban);
        find_temlate(img, templ, Imgproc.TM_CCOEFF_NORMED);

        System.out.println(0 % 5);

    }


    public static void find_temlate(Mat im_source, Mat im_search, int match_method)
    {
        double threshold = 0.5;
        double maxcnt = 1;

        Mat gray_source = new Mat();
        Mat gray_search = new Mat();


        Imgproc.cvtColor(im_source, gray_source, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(im_search, gray_search , Imgproc.COLOR_BGR2GRAY);

        // / Create the result matrix
        int result_cols = im_source.cols() - im_search.cols() + 1;
        int result_rows = im_source.rows() - im_search.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

        Imgproc.matchTemplate(gray_source, gray_search, result, match_method);

        int w = im_search.width();
        int h = im_search.height();

        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

        System.out.println("confidence : " + mmr.maxVal);
        if (mmr.maxVal < threshold)
        {
            return;
        }

        Point top_left;
        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED)
        {
            top_left = mmr.minLoc;
        }
        else
        {
            top_left = mmr.maxLoc;
        }

        Point middle = new Point();

        middle.x = top_left.x + w / 2;
        middle.y = top_left.y + h / 2;

        System.out.println(middle.x);
        System.out.println(middle.y);
    }

}