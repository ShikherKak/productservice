package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.*;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;
//import dev.naman.productservice.security.JwtObject;
//import dev.naman.productservice.security.TokenValidator;
import dev.naman.productservice.services.ProductService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
//    @Autowired
    // field injection
    private ProductService productService;
//    private TokenValidator tokenValidator;
    // ....;
    // ...;
    @Autowired
    private RestTemplate restTemplate;


    // constructor injection
//    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
//        this.tokenValidator = tokenValidator;
    }
//

    // setter injection
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    // GET /products {}
    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts() {
        List<GenericProductDto> productDtos = productService.getAllProducts();
        if (productDtos.isEmpty()) {
            return new ResponseEntity<>(
                    productDtos,
                    HttpStatus.NOT_FOUND
            );
        }

        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for (GenericProductDto gpd: productDtos) {
            genericProductDtos.add(gpd);
        };

//        genericProductDtos.remove(genericProductDtos.get(0));

        return new ResponseEntity<>(genericProductDtos, HttpStatus.OK);

//        productDtos.get(0).setId(1001L);
//
//        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id
    , HttpServletRequest request) throws NotFoundException {

//        request.getRemoteAddr()

//        request.ge
//        System.out.println(authToken);
//        Optional<JwtObject> authTokenObjOptional;
//        JwtObject authTokenObj = null;
//
//        if (authToken != null) {
//            authTokenObjOptional = tokenValidator.validateToken(authToken);
//            if (authTokenObjOptional.isEmpty()) {
//                // ignore
//            }
//
//            authTokenObj = authTokenObjOptional.get();
//        }

        GenericProductDto productDto = productService.getProductById(id);
        if (productDto == null) {
            throw new NotFoundException("Product Doesn't Exist");
        }

//        GenericProductDto genericProductDto = new GenericProductDto();
//        genericProductDto.setTitle(productDto.getTitle());
        return productDto;

//        Comparator
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
//        System.out.println(product.name);
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public void updateProductById() {

    }

    @PostMapping("/addProductSD")
    public ResponseEntity<Product> addProductSD(@RequestBody SDRequestDto sdRequestDto)
    {
        Product product = new Product();
        product.setTitle(sdRequestDto.getTitle());
        product.setCategory(new Category());
        product.setDescription("Whatever");
        product.setPrice(new Price());

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail(sdRequestDto.getEmail());

//        String userName = "";

        Optional<String> userName = restTemplate.postForObject("http://userservice/getUserEmail",emailDTO,String.class).describeConstable();

        String name = userName.get();

        if(name.equals("Not Found"))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            Product savedProduct = productService.addProduct(product);
            ResponseEntity<Product> productResponseEntity = new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
//                    productResponseEntity.

            return productResponseEntity;
        }


    }
}
