import java.awt.*;
import java.io.*;
import java.net.URL;

public class HTMLGrab {

    static BufferedReader reader;
    static BufferedWriter writer;
    static FileWriter filewriter;
    static URL url;

    public static void grabHtml(String sourceUrl, String outputFile) throws Exception {

        System.out.println("grabHTML speichert die URL: " + sourceUrl + " in der Datei: " + outputFile);

        url = new URL(sourceUrl);
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        filewriter = new FileWriter(outputFile);
        writer = new BufferedWriter(filewriter);
        String line = "";

        while ((line = reader.readLine()) != null) {
            writer.write(line + "\n");
        }

        reader.close();
        writer.flush();
        writer.close();

        String nativeDir = outputFile.substring(0, outputFile.lastIndexOf(File.separator));
        Desktop.getDesktop().open(new File(nativeDir));
        System.out.println("Datei erfolgreich erstellt!");

    }

}
