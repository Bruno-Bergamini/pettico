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

        ProductCart productCart = new ProductCart();
        productCart.setUserId(userId);
        productCart.setProductId(productId);
        productCart.setQuantity(quantity);
        productCartService.save(productCart);
        return ResponseEntity.ok("Product has been added to cart");
    }

    @PostMapping("removeProduct")
    public ResponseEntity<String> removeProductFromCart(@RequestBody AddBody addBody, @RequestParam Integer userId) {
        Integer productId = addBody.getProductId();
        Integer quantity = addBody.getQuantity();

        List<ProductCart> productCartList = productCartService.findByUserId(userId);
        for (ProductCart productCart : productCartList) {
            if (!productId.equals(productCart.getProductId())) {
                continue;
            }

            if (quantity != null && quantity < productCart.getQuantity()) {
                productCart.setQuantity(productCart.getQuantity() - quantity);
                productCartService.save(productCart);
                break;
            }

            productCartService.delete(productCart.getId());
            break;
        }

        return ResponseEntity.ok("Product has been removed from cart");
    }

    @PostMapping("buy")
    public ResponseEntity<String> buyProduct(@RequestBody AddBody addBody, @RequestParam Integer userId) {
        Integer productId = addBody.getProductId();
        Integer quantity = addBody.getQuantity();

        List<ProductCart> productCartList = productCartService.findByUserId(userId);
        for (ProductCart productCart : productCartList) {
            if (!productId.equals(productCart.getProductId())) {
                continue;
            }

            // todo
        }

        return ResponseEntity.ok("Product has been removed from cart");
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
            totalPrice += productService.findById(productCart.getProductId()).getPrice();
        }

        return totalPrice;
    }

    @PostMapping("/buyAll")
    public ResponseEntity buyAllProductsInCart(@RequestParam Integer userId) {
        List<ProductCart> productCartList = productCartService.findByUserId(userId);

        Purchase purchase = new Purchase();
        purchase.setUserId(userId);
        purchase.setDate(new Date(System.currentTimeMillis()));
        purchase = purchaseService.save(purchase);

        for (ProductCart productCart : productCartList) {
            Product product = productService.findById(productCart.getProductId());

            if (product.getStock() < productCart.getQuantity()) {
                return ResponseEntity.status(500).body("Could not finish purchase, product: '" + product.getName() + "' is out of stock.");
            }

            PurchaseProduct purchaseProduct = new PurchaseProduct();
            purchaseProduct.setPurchaseId(purchase.getId());
            purchaseProduct.setProductId(product.getId());
            purchaseProduct.setQuantity(productCart.getQuantity());
            purchaseProduct.setTotalPrice(product.getPrice() * productCart.getQuantity());
            purchaseProductService.save(purchaseProduct);

            product.setStock(product.getStock() - purchaseProduct.getQuantity());
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