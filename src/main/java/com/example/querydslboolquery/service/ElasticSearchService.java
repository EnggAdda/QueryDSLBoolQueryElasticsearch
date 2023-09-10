package com.example.querydslboolquery.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.querydslboolquery.entity.Product;
import com.example.querydslboolquery.util.ElasticSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient  elasticsearchClient;
//matchAll
    public SearchResponse<Map> matchAllServices() throws IOException {
        Supplier<Query> supplier  = ElasticSearchUtil.supplier();
       SearchResponse<Map> searchResponse = elasticsearchClient.search(s->s.query(supplier.get()),Map.class);
        System.out.println("elasticsearch query is "+supplier.get().toString());
        return searchResponse;
    }
    //matchAllProducts video content

    public SearchResponse<Product> matchAllProductsServices() throws IOException {
        Supplier<Query> supplier  = ElasticSearchUtil.supplier();
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s->s.index("products").query(supplier.get()),Product.class);
        System.out.println("elasticsearch query is "+supplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Product> boolQueryImpl(String productName, Integer qty) throws IOException {
        Supplier<Query> supplier  = ElasticSearchUtil.supplierQueryForBoolQuery(productName, qty);
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s->s.index("products").query(supplier.get()),Product.class);
        System.out.println("elasticsearch query is "+supplier.get().toString());
        return searchResponse;
    }



}
