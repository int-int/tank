package test;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTest {

    @Test
    public void test(){

        try {
            BufferedImage read = ImageIO.read(new File("C:/Users/DELL/Desktop/ps/沙漠.png"));
            Assert.assertNotNull(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
