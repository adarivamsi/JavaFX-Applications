package downloader;
/**
 * Created by Vamsi Charan Adari on ${DATE} at ${TIME}
 * Please email in case of any queries at adarivamsi@gmail.com
 */
import java.io.InputStream;
import java.io.OutputStream;

 class SyncPipe implements Runnable {

    private final InputStream inputStream_;
    private final OutputStream outputStream_;

    public SyncPipe (InputStream inputStream, OutputStream outputStream) {
        inputStream_ = inputStream;
        outputStream_ = outputStream;
    }

    public void run() {

        try {
            final byte[] buffer = new byte[1024];
            for (int length = 0; (length = inputStream_.read(buffer)) != -1;) {
                outputStream_.write(buffer, 0, length);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
