package com.pucpr.pettico.cart.controller;

import com.pucpr.pettico.product_cart.model.ProductCart;
import com.pucpr.pettico.product_cart.service.ProductCartService;
import com.pucpr.pettico.products.model.Product;
import com.pucpr.pettico.cart.service.CartService;
import com.pucpr.pettico.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    final CartService cartService;
    final ProductService productService;
    final ProductCartService productCartService;

    public CartController(CartService cartService, ProductService productService, ProductCartService productCartService) {
        this.cartService = cartService;
        this.productService = productService;
        this.productCartService = productCartService;
    }

    @PostMapping
    public ResponseEntity<String> addProductToCart(@RequestBody AddBody addBody) { // nao funsiona
        Integer productId = addBody.getProductId();
        Integer quantity = addBody.getQuantity();

        if (productId == null) {
            return ResponseEntity.status(500).body("");
        }

        if (quantity == null || quantity < 0) {
            return ResponseEntity.status(500).body("");
        }
        
        Product product = productService.findById(productId);

        if (product == null) {
            return ResponseEntity.status(500).body("");
        }

        if (product.getStock() < quantity) {
            return ResponseEntity.status(500).body("");
        }


        ProductCart productCart = new ProductCart();
        productCart.setUserId(99);
        productCart.setProductId(productId);
        productCart.setQuantity(quantity);
        productCartService.save(productCart);

        return ResponseEntity.ok("");
    }

    @GetMapping
    public List<Product> find() {
        List<ProductCart> productCartList = cartService.getAllProductsInCart(1);
        return null;
    }

//    @GetMapping("/{id}")
//    public Product findById(@PathVariable("id") Integer id) {
//        return cartService.findById(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable("id") Integer id) {
//        cartService.delete(id);
//    }
}

class AddBody {
    private Integer productId, quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}