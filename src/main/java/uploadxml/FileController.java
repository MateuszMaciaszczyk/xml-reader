package uploadxml;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;
    private Products products;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) return ResponseEntity.badRequest().body("No file to load");

        try {
            products = fileService.processFile(file);
            return ResponseEntity.ok("File uploaded. Number of records: " + products.productsAmount());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the file");
        }
    }

    @GetMapping("/products")
    public ResponseEntity<Products> getProducts() {
        if (products == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<Product> getProduct(@PathVariable("name") String name) {
        Product product = products.findProduct(name);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}