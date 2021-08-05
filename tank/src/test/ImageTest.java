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
            BufferedImage image = ImageIO.read(new File("C:/Users/DELL/Desktop/ps/沙漠.png"));
            Assert.assertNotNull(image);

            BufferedImage image1 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            Assert.assertNotNull(image1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
