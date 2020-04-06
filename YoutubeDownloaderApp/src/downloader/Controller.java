package downloader;
/**
 * Created by Vamsi Charan Adari on ${DATE} at ${TIME}
 * Please email in case of any queries at adarivamsi@gmail.com
 */

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Process process;
    String youtubeDownloaderURL = new File("src/downloader/resources").getAbsolutePath();
    String[] command = {"cmd"};

    @FXML
    TextField video_url, location;

    @FXML
    Button directorySelect;

    @FXML
    AnchorPane anchorid;

    @FXML
    Label urlError, locationError;

    @FXML
    ProgressBar progress;

    @FXML
    public void closeButtonAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void deleteUrlButtonAction(ActionEvent event) {
        video_url.setText("");
    }

    @FXML
    public void choosePath(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) anchorid.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            location.setText(file.getAbsolutePath());
        }
    }

    @FXML
    public void supportEmail(ActionEvent event) throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            URI mailto = new URI("mailto:adarivamsi@gmail.com?subject=Youtube%20Downloader%20Issue");
            desktop.mail(mailto);
        }
    }

    @FXML
    public void downloadVideo(ActionEvent event) throws IOException {
        try {
            process = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(process.getErrorStream(), System.err)).start();
            new Thread(new SyncPipe(process.getInputStream(), System.out)).start();
            PrintWriter stdin = new PrintWriter(process.getOutputStream());
            if (video_url.getText().equals("")) {
                urlError.setText("Please enter the Video URL");
            } else if (location.getText().equals("")) {
                urlError.setText("");
                locationError.setText("Please choose location to save video");
            } else {
                locationError.setText("");
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        int steps = 500;
                        for (int i = 0; i < steps; i++) {
                            Thread.sleep(10);
                            updateProgress(i, steps);
                            updateMessage(String.valueOf(i));
                        }
                        return null;
                    }
                };

                task.setOnFailed(wse -> {
                    wse.getSource().getException().printStackTrace();
                });

                progress.progressProperty().bind(task.progressProperty());

                new Thread(task).start();

                stdin.println("cd \"" + location.getText() + "\"");
                stdin.println(youtubeDownloaderURL + "\\youtube-dl.exe -f (\"bestvideo[width>=1920]\"/bestvideo)+bestaudio/best " + video_url.getText());
                stdin.close();

                Runtime.getRuntime().exec("explorer.exe " + location.getText() + "\\");

                locationError.setText("");
                video_url.setText("");
                location.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void downloadAudio(ActionEvent event) throws IOException {
        try {
            process = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(process.getErrorStream(), System.err)).start();
            new Thread(new SyncPipe(process.getInputStream(), System.out)).start();
            PrintWriter stdin = new PrintWriter(process.getOutputStream());
            if (video_url.getText().equals("")) {
                urlError.setText("Please enter the Video URL");
            } else if (location.getText().equals("")) {
                urlError.setText("");
                locationError.setText("Please choose location to save audio");
            } else {
                locationError.setText("");
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        int steps = 400;
                        for (int i = 0; i < steps; i++) {
                            Thread.sleep(10);
                            updateProgress(i, steps);
                            updateMessage(String.valueOf(i));
                        }
                        return null;
                    }
                };

                task.setOnFailed(wse -> {
                    wse.getSource().getException().printStackTrace();
                });

                progress.progressProperty().bind(task.progressProperty());

                new Thread(task).start();

                stdin.println("cd \"" + location.getText() + "\"");
                stdin.println(youtubeDownloaderURL + "\\youtube-dl.exe --extract-audio --audio-format mp3 " + video_url.getText());
                stdin.close();

                Runtime.getRuntime().exec("explorer.exe " + location.getText() + "\\");

                locationError.setText("");
                video_url.setText("");
                location.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        location.setEditable(false);
    }

}
