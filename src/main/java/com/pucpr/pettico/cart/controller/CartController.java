package com.pucpr.pettico.cart.controller;

import com.pucpr.pettico.product_cart.model.ProductCart;
import com.pucpr.pettico.product_cart.service.ProductCartService;
import com.pucpr.pettico.product.model.Product;
import com.pucpr.pettico.cart.service.CartService;
import com.pucpr.pettico.product.service.ProductService;
import com.pucpr.pettico.purchase_product.model.PurchaseProduct;
import com.pucpr.pettico.purchase_product.service.PurchaseProductService;
import com.pucpr.pettico.purchase.model.Purchase;
import com.pucpr.pettico.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    final CartService cartService;
    @Autowired
    final ProductService productService;
    final ProductCartService productCartService;
    final PurchaseService purchaseService;
    final PurchaseProductService purchaseProductService;

    public CartController(
            CartService cartService,
            ProductService productService,
            ProductCartService productCartService,
            PurchaseService purchaseService,
            PurchaseProductService purchaseProductService
    ) {
        this.cartService = cartService;
        this.productService = productService;
        this.productCartService = productCartService;
        this.purchaseService = purchaseService;
        this.purchaseProductService = purchaseProductService;
    }

    @PostMapping("addProduct")
    public ResponseEntity<String> addProductToCart(@RequestBody AddBody addBody,
                                                   @RequestParam Integer userId) {
        Integer productId = addBody.getProductId();
        Integer quantity = addBody.getQuantity();

        Assert.notNull(productId, "Product ID is needed.");
        Assert.notNull(quantity, "Quantity is needed.");
        Assert.isTrue(quantity > 0, "Quantity must be greater than zero.");
        
        Product product = productService.findById(productId);

        if (product == null) {
            return ResponseEntity.status(404).body("Product not found");
        }

        if (product.getStock() < quantity) {
            return ResponseEntity.status(500).body("");
        }

        List<ProductCart> productCartList = productCartService.findByUserId(userId);

        for (ProductCart productCart : productCartList) {
            if (productId.equals(productCart.getProductId())) {
                productCart.setQuantity(productCart.getQuantity() + quantity);
                productCartService.save(productCart);
                return ResponseEntity.ok("Product has been added to cart");
            }
        }

        productCartService.save(new ProductCart(userId, productId, quantity));
        return ResponseEntity.ok("Product has been added to cart");
    }

    @PostMapping("removeProduct")
    public ResponseEntity<String> removeProductFromCart(@RequestBody AddBody addBody, @RequestParam Integer userId) {
        Integer productId = addBody.getProductId();
        Integer quantity = addBody.getQuantity();

        ProductCart productCart = findProductInCart(productId, userId);

        if (productCart == null) {
            return ResponseEntity.status(404).body("Product was not found on this user's cart..");
        }

        if (quantity != null && quantity < productCart.getQuantity()) {
            productCart.setQuantity(productCart.getQuantity() - quantity);
            productCartService.save(productCart);
        } else {
            productCartService.delete(productCart.getId());
        }

        return ResponseEntity.ok("Product has been removed from cart");
    }

    @PostMapping("buy")
    public ResponseEntity<String> buyProduct(@RequestBody AddBody addBody, @RequestParam Integer userId) {
        Integer productId = addBody.getProductId();

        ProductCart productCart = findProductInCart(productId, userId);

        if (productCart == null) {
            return ResponseEntity.status(404).body("Product was not found on this user's cart..");
        }

        Product product = productService.findById(productCart.getProductId());

        Purchase purchase = new Purchase(userId, new Date());
        purchase = purchaseService.save(purchase);

        Integer purchaseId = purchase.getId();
        Integer quantity = productCart.getQuantity();
        Float totalPrice = product.getPrice() * productCart.getQuantity();
        purchaseProductService.save(new PurchaseProduct(purchaseId, productId, quantity, totalPrice));

        product.setStock(product.getStock() - quantity);
        productService.save(product);

        productCartService.delete(productCart.getId());

        return ResponseEntity.ok("Product has been removed from cart");
    }

    private ProductCart findProductInCart(Integer productId, Integer userId) {
        List<ProductCart> productCartList = productCartService.findByUserId(userId);
        for (ProductCart productCart : productCartList) {
            if (productId.equals(productCart.getProductId())) {
                return productCart;
            }
        }
        return null;
    }

    @GetMapping
    public List<ProductCart> find(@RequestParam Integer userId) {
        return cartService.getAllProductsInCart(userId);
    }

    @GetMapping("/totalPrice")
    public Float getCartTotalPrice(@RequestParam Integer userId) {
        List<ProductCart> productCartList = productCartService.findByUserId(userId);

        Float totalPrice = 0f;

        for (ProductCart productCart : productCartList) {
            totalPrice += productService.findById(productCart.getProductId()).getPrice() * productCart.getQuantity();
        }

        return totalPrice;
    }

    @PostMapping("/buyAll")
    public ResponseEntity buyAllProductsInCart(@RequestParam Integer userId) {
        List<ProductCart> productCartList = productCartService.findByUserId(userId);

        Purchase purchase = new Purchase(userId, new Date());
        purchase = purchaseService.save(purchase);

        for (ProductCart productCart : productCartList) {
            Product product = productService.findById(productCart.getProductId());

            if (product.getStock() < productCart.getQuantity()) {
                return ResponseEntity.status(500).body("Product: '" + product.getName() + "' is out of stock.");
            }

            Integer purchaseId = purchase.getId();
            Integer productId = product.getId();
            Integer quantity = productCart.getQuantity();
            Float totalPrice = product.getPrice() * productCart.getQuantity();
            purchaseProductService.save(new PurchaseProduct(purchaseId, productId, quantity, totalPrice));

            product.setStock(product.getStock() - quantity);
            productService.save(product);

            productCartService.delete(productCart.getId());
        }

        return ResponseEntity.ok("Products were bought successfully.");
    }

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