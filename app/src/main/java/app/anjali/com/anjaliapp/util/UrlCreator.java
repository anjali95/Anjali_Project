package app.anjali.com.anjaliapp.util;

/**
 * Created by GB on 11/18/2015.
 */
public class UrlCreator {
    public static String ip="192.168.137.1";
    public static String port="80";

    public static String geturl(int index) {
        switch (index) {
            case 0:

                return "http://"+ip+":"+port+"/railway/login.php";

            case 1:

                return "http://"+ip+":"+port+"/railway/register.php";


            case 2:

                return "http://"+ip+":"+port+"/railway/station.php";



            case 3:

                return "http://"+ip+":"+port+"/railway/train.php";




            case 4:

                return "http://"+ip+":"+port+"/railway/traininfo.php";




            case 5:
                return "http://"+ip+":"+port+"/railway/enquiry.php";

        }

        return null;
    }
}
