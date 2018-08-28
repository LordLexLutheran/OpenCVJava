import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class CVTest {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        // sets up webcam
        VideoCapture cap = new VideoCapture(0);
        cap.set(Videoio.CAP_PROP_FRAME_WIDTH, 640);
        cap.set(Videoio.CAP_PROP_FRAME_HEIGHT, 480);
        
        System.out.println("Opened capture");
        
        while (cap.isOpened()) {
                // create image storage
                Mat image = Mat.zeros(640, 480, CvType.CV_8UC3);
                
                System.out.println("New frame");
                
                // take picture and store in image
                cap.grab();
                cap.retrieve(image);
                
                // color to grayscale
                Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);
                
                // grayscale to black and white
                Imgproc.threshold(image, image, 100, 255, Imgproc.THRESH_BINARY);
                
                // display the image in a window
                HighGui.imshow("Image", image);
                
                // check for any keypresses
                int key = HighGui.waitKey(33);
                if (key == 27) {
                    // quit when escape key pressed
                    break;
                }
        }
        cap.release();
          HighGui.destroyAllWindows();
          System.out.println("Quitting");
          System.exit(0);
    }
}
