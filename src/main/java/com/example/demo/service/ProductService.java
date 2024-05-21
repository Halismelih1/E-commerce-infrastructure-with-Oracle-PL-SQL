package com.example.demo.service;

import com.example.demo.dto.favourites.UserFavouriteList;
import com.example.demo.dto.product.FilterProdRes;
import com.example.demo.entities.Products;
import com.example.demo.repository.ProductRepository;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    private JdbcTemplate jdbcTemplate;

    public ProductService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Products> getAllProd() {
        return productRepository.findAll();
    }

    public Optional<Products> getProdById(Integer prodId) {
        return productRepository.findById(prodId);
    }

    //Oracle PL/SQL prosedürleri çağır
    public void callAddProductProcedure(String productName, Integer productPrice, String productDesc, Integer subCategoryId) {
        String sql = "{call productCrud.addProduct(?, ?, ?, ?)}";
        jdbcTemplate.update(sql, productName, productPrice, productDesc, subCategoryId);

    }

    public void callDeleteProductProcedure(int id) {
        String sql = "{call productCrud.deleteProduct(?)}";
        jdbcTemplate.update(sql, id);

    }

    public void callUpdateProductProcedure(int id, String productName, Integer productPrice, String productDesc, Integer subCategoryId) {
        String sql = "{call productCrud.updateProduct(?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, id,productName, productPrice, productDesc, subCategoryId);

    }

    public List<FilterProdRes> callFilterProdProcedure(Integer subcategoryId, Integer minPrice, Integer maxPrice, String productName) {
        String sql = "{call productCrud.FILTERPRODUCTS(?, ?, ?, ?, ?)}";

        List<FilterProdRes> filteredProducts = jdbcTemplate.execute(
                (Connection conn) -> {
                    CallableStatement cs = conn.prepareCall(sql);
                    cs.setObject(1, subcategoryId);
                    cs.setObject(2, minPrice);
                    cs.setObject(3, maxPrice);
                    cs.setString(4, productName);
                    cs.registerOutParameter(5, OracleTypes.CURSOR);
                    return cs;
                },
                (CallableStatement cs) -> {
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(5);

                    List<FilterProdRes> items = new ArrayList<>();
                    while (rs.next()) {
                        FilterProdRes item = new FilterProdRes(
                                rs.getInt("productId"),
                                rs.getString("productName"),
                                rs.getInt("productPrice")
                        );
                        items.add(item);
                    }
                    return items;
                }
        );

        return filteredProducts;
    }

}
