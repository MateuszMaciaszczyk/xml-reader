package uploadxml;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileServiceTest {

    @Test
    public void testProcessFile1() throws IOException {
        byte[] xmlContent = Files.readAllBytes(Path.of("src/test/resources/test1.xml"));
        MultipartFile file = new MockMultipartFile("file", "test1.xml", "text/xml", xmlContent);

        FileService fileService = new FileService();
        Products products = fileService.processFile(file);
        Product product = products.getProducts().get(0);

        assertEquals(3, products.productsAmount());
        assertEquals(1, product.getId());
        assertEquals("apple", product.getName());
        assertEquals("fruit", product.getCategory());
        assertEquals("2303-E1A-G-M-W209B-VM", product.getPartNumberNR());
        assertEquals("FruitsAll", product.getCompanyName());
        assertTrue(product.isActive());
    }

    @Test
    public void testProcessFile2() throws IOException {
        byte[] xmlContent = Files.readAllBytes(Path.of("src/test/resources/test2.xml"));
        MultipartFile file = new MockMultipartFile("file", "test2.xml", "text/xml", xmlContent);

        FileService fileService = new FileService();
        Products products = fileService.processFile(file);
        Product product = products.getProducts().get(9);

        assertEquals(10, products.productsAmount());
        assertEquals(10, product.getId());
        assertEquals("couch", product.getName());
        assertEquals("furniture", product.getCategory());
        assertEquals("2222-C3H-G-M-N321L-UV", product.getPartNumberNR());
        assertEquals("HomeHome", product.getCompanyName());
        assertFalse(product.isActive());
    }

    @Test
    public void testIfFileContainProductByName() throws IOException {
        byte[] xmlContent = Files.readAllBytes(Path.of("src/test/resources/test2.xml"));
        MultipartFile file = new MockMultipartFile("file", "test2.xml", "text/xml", xmlContent);

        FileService fileService = new FileService();
        Products products = fileService.processFile(file);

        Product expected = new Product(9, "carrot", "vegetable", "3333-C4T-V-M-P876A-QW", "VeggieLand", true);
        Product product = products.findProduct("carrot");
        Product product2 = products.findProduct("book");

        assertEquals(expected, product);
        assertNull(product2);
    }
}