package com.microservice.product.service;

import com.microservice.product.dao.IDao;
import com.microservice.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IDao<Product> productDao;

    @Transactional
    @Override
    public void addProduct(Product product) {
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        productDao.save(product);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public IDao<Product> getProductDao() {
        return productDao;
    }

    public void setProductDao(IDao<Product> productDao) {
        this.productDao = productDao;
    }
}
