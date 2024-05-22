package com.example.demo.service;

import com.example.demo.dto.favourites.MostFavouriteProducts;
import com.example.demo.dto.favourites.UserFavouriteList;
import com.example.demo.repository.FavouriteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class FavouriteService {

    @Autowired
    private FavouriteRepository favouriteRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public FavouriteService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Oracle PL/SQL prosedürleri çağır
    public void callAddToFavouriteProcedure(Integer userId, Integer productId) {
        String sql = "{call FAVOURITESMANAGER.AddToFavourites(?, ?)}";
        jdbcTemplate.update(sql, userId, productId);

    }

    public void callRemoveToFavouriteProcedure(Integer userId, Integer productId) {
        String sql = "{call FAVOURITESMANAGER.RemoveFromFavourites(?, ?)}";
        jdbcTemplate.update(sql, userId, productId);

    }

    public List<UserFavouriteList> callViewUserFavouritesProcedure(Integer userId) {
        String sql = "{call FAVOURITESMANAGER.ViewUserFavourites(?, ?)}";

        // Call stored procedure and get the results
        List<UserFavouriteList> favouriteItems = jdbcTemplate.execute(
                (Connection conn) -> {
                    CallableStatement cs = conn.prepareCall(sql);
                    cs.setInt(1, userId);
                    cs.registerOutParameter(2, OracleTypes.CURSOR);
                    return cs;
                },
                (CallableStatement cs) -> {
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(2);

                    List<UserFavouriteList> items = new ArrayList<>();
                    while (rs.next()) {
                        UserFavouriteList item = new UserFavouriteList(
                                rs.getInt("productId"),
                                rs.getString("productName"),
                                rs.getInt("productPrice")

                        );
                        items.add(item);
                    }
                    return items;
                }
        );

        return favouriteItems;
    }

    public List<MostFavouriteProducts> callMostFavouritesProcedure() {
        String sql = "{call FAVOURITESMANAGER.ListMostFavouritedProducts(?)}";

        // Call stored procedure and get the results
        List<MostFavouriteProducts> favouriteItems = jdbcTemplate.execute(
                (Connection conn) -> {
                    CallableStatement cs = conn.prepareCall(sql);
                    cs.registerOutParameter(1, OracleTypes.CURSOR);
                    return cs;
                },
                (CallableStatement cs) -> {
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(1);

                    List<MostFavouriteProducts> items = new ArrayList<>();
                    while (rs.next()) {
                        MostFavouriteProducts item = new MostFavouriteProducts(
                                rs.getInt("productId"),
                                rs.getString("productName"),
                                rs.getInt("productPrice"),
                                rs.getInt("favCount")
                        );
                        items.add(item);
                    }
                    return items;
                }
        );

        return favouriteItems;
    }
}
