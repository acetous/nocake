package de.acetous.nocake.service;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.ByteArrayOutputStream;

@Service
public class QrCodeService {

    public String generate(String content) {
        ByteArrayOutputStream stream = QRCode.from(content).withSize(300, 300).to(ImageType.GIF).stream();
        byte[] bytes = stream.toByteArray();
        String base64 = Base64Utils.encodeToString(bytes);
        return base64;
    }
}
