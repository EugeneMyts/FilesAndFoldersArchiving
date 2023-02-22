import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        String path = "C:\\Users\\User\\Desktop\\";
        String in = path + "folder";
        String out = path + "archive.zip";

        try {


            FileOutputStream outputStream = new FileOutputStream(out);
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            writeFileToZip(new File(in), zipOutputStream, "");



            zipOutputStream.flush();
            zipOutputStream.close();
            outputStream.close();


        } catch (Exception e) {
            e.getMessage();
        }


    }

    public static void writeFileToZip (File file, ZipOutputStream zipOutputStream,
                                       String path) throws Exception {

        if(file.isDirectory()) {
            String folder = path + file.getName() + "/";
            ZipEntry entry = new ZipEntry(folder);
            zipOutputStream.putNextEntry(entry);
            zipOutputStream.closeEntry();
            File[] files = file.listFiles();
            for (File subfile : files ) {
                writeFileToZip(subfile, zipOutputStream, folder);
            }
            return;
        }
        ZipEntry entry = new ZipEntry(path + file.getName());
        zipOutputStream.putNextEntry(entry);
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        zipOutputStream.write(bytes);


    }



}
