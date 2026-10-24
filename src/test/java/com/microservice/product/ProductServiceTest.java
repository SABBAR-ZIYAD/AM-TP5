package com.microservice.product;

import com.microservice.product.config.AppConfig;
import com.microservice.product.dao.IDao;
import com.microservice.product.entity.Product;
import com.microservice.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private IDao<Product> productDao;

    @Test
    public void testAddAndGetAllProducts() {
        Product product = new Product("Laptop Pro", 1299.99);
        productService.addProduct(product);

        List<Product> products = productService.getAllProducts();
        Assert.assertFalse(products.isEmpty());

        boolean found = products.stream().anyMatch(p -> "Laptop Pro".equals(p.getName()));
        Assert.assertTrue(found);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddProductWithNegativePriceThrowsException() {
        Product invalidProduct = new Product("Invalid Product", -10.0);
        productService.addProduct(invalidProduct);
    }

    @Test
    public void testGenericDaoCrudOperations() {
        Product p = new Product("Wireless Mouse", 29.99);
        productService.addProduct(p);
        Assert.assertNotNull(p.getId());

        Product found = productDao.findById(p.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals("Wireless Mouse", found.getName());

        // Update
        found.setPrice(34.99);
        productDao.update(found);

        Product updated = productDao.findById(p.getId());
        Assert.assertEquals(34.99, updated.getPrice(), 0.001);

        // Delete
        productDao.delete(updated);
        Product deleted = productDao.findById(p.getId());
        Assert.assertNull(deleted);
    }
}
