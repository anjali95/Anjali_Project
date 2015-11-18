package app.anjali.com.anjaliapp.util;

/**
 * Created by GB on 11/18/2015.
 */
public class UrlCreator {
    public static String ip="localhost";
    public static String port="80";

    public static String geturl(int index) {
        switch (index) {
            case 0:

                return "http://"+ip+":"+port+"/railway/station.php";



            case 1:

                return "http://"+ip+":"+port+"/railway/train.php";




            case 2:

                return "http://"+ip+":"+port+"/railway/train.php";




            case 3:
                return "http://"+ip+":"+port+"/railway/train.php";

        }

        return null;
    }
}
