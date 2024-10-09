package com.vvv.lab2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.awt.Image;
import java.util.regex.Pattern;

import static jdk.nashorn.internal.objects.NativeMath.round;


public class HelloController {

    @FXML
    private Button SelDir;
    @FXML
    private TableView<FInfo> tableFiles;

    public ObservableList<FInfo> dataTables = FXCollections.observableArrayList();

    @FXML
    public void initialize(){

        TableColumn nameFileCol,sizePictCol,deptPictCol,ratioPictCol,sizeFileCol;
        nameFileCol  = new TableColumn("Имя файла");nameFileCol.setMinWidth(220);
        sizeFileCol  = new TableColumn("Размер файла");sizeFileCol.setMinWidth(150);
        sizePictCol  = new TableColumn("Размер изобр.");sizePictCol.setMinWidth(150);
        deptPictCol  = new TableColumn("Глубина");deptPictCol.setMinWidth(150);
        ratioPictCol = new TableColumn("Сжатие");ratioPictCol.setMinWidth(150);

        nameFileCol.setCellValueFactory(new PropertyValueFactory<>("nameFile"));
        sizeFileCol.setCellValueFactory(new PropertyValueFactory<>("sizeFile"));
        sizePictCol.setCellValueFactory(new PropertyValueFactory<>("sizePict"));
        deptPictCol.setCellValueFactory(new PropertyValueFactory<>("depthPict"));
        ratioPictCol.setCellValueFactory(new PropertyValueFactory<>("ratioPict"));

        tableFiles.getColumns().addAll(nameFileCol,sizeFileCol,sizePictCol,deptPictCol,ratioPictCol);

    }
    public  void AddTables(String fPath) throws IOException {
        String imageH="",depth="",sizeFile="";

        BufferedImage image;
        image=ImageIO.read(Files.newInputStream(Paths.get(fPath)));
        if(image==null) return;

        imageH= image.getHeight() + " x "+ image.getWidth();
        depth=image.getColorModel().getPixelSize()+" Bit";
        double ratio = (double) image.getWidth() / image.getHeight();

        String sratio=String.format("%.1f",ratio);
        File file = new File(fPath);
        sizeFile=file.length()/1024+" kB";

        dataTables.addAll(new FInfo(fPath,sizeFile, imageH, depth, sratio));


    }


    //  Нажатие на кнопку мыши
    public void onSelDir() throws IOException {


        Pattern imageMIMEPattern = Pattern.compile("image/.*");
        DirectoryChooser chooser = new DirectoryChooser();

        File f = chooser.showDialog(null);
            if (f != null) {
        Path p = f.toPath();
        try {
                        // find all files with mime type image/... in subdirectories up to a depth of 10
                    Files.find(p, 10, (Path, attributes) ->
                    {
                      String contentType;
                            try {
                                    contentType = Files.probeContentType(Path);

                                    if(contentType!=null)
                                        AddTables(Path.toString());
                                    } catch (IOException ex) {
                            return false;
                            }

                            return contentType != null && imageMIMEPattern.matcher(contentType).matches();

                             }).forEach(System.out::println);
                            } catch (IOException ex) {
                            throw new RuntimeException(ex);
                     }
                 }
                tableFiles.setItems(dataTables);
             }

}


