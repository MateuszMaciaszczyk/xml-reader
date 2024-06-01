package uploadxml;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

@Service
public class FileService {

    public Products processFile(MultipartFile file) throws IOException {
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Products products = (Products) jaxbUnmarshaller.unmarshal(new StringReader(content));
            return products;
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to parse XML file", e);
        }
    }
}