package com.example.querydslboolquery.repo;

import com.example.querydslboolquery.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepo extends ElasticsearchRepository<Product,Integer> {



        }
