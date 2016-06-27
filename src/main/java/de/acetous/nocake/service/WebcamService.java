package de.acetous.nocake.service;

import com.github.sarxos.webcam.Webcam;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class WebcamService {

    private final Webcam webcam;

    public WebcamService() {
        webcam = Webcam.getDefault();
    }

    public byte[] capture() throws IOException {
        try(ByteArrayOutputStream os = new ByteArrayOutputStream();) {
            webcam.open();
            ImageIO.write(webcam.getImage(), "PNG", os);
            webcam.close();

            return os.toByteArray();
        }
    }

    public String captureBase64() throws IOException {
        byte[] capture = capture();
        return Base64Utils.encodeToString(capture);
    }
}
