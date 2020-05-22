package Objects;

import java.io.BufferedOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class saveCity {
    public saveCity() {
    }

    public static void main(String[] args) {
        Path file = Paths.get("src/Model.SaveFile.txt");
        String s = "This simulation has been saved";
        byte[] data = s.getBytes();
        BufferedOutputStream output = null;

        try {
            output = new BufferedOutputStream(Files.newOutputStream(file, StandardOpenOption.CREATE));
            output.write(data);
            output.flush();
            output.close();
        } catch (FileAlreadyExistsException var6) {
            System.out.println("File already exists");
        } catch (Exception var7) {
            System.out.println("Message: " + var7);
        }

    }
}

