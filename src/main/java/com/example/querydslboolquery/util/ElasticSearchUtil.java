package com.example.querydslboolquery.util;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ElasticSearchUtil {

    public static Supplier<Query> supplier(){
        Supplier<Query> supplier = ()->Query.of(q->q.matchAll(matchAllQuery()));
        return supplier;
    }

    public static MatchAllQuery matchAllQuery(){
        val  matchAllQuery = new MatchAllQuery.Builder();
        return matchAllQuery.build();
    }

    public static Supplier<Query> supplierQueryForBoolQuery(String productName, Integer qty){
        Supplier<Query> supplier = ()->Query.of(q->q.bool(boolQuery(productName,qty)));
        return supplier;
    }

     public static BoolQuery boolQuery(String productName, Integer qty){

        val boolQuery  = new BoolQuery.Builder();
        return boolQuery.filter(termQuery(productName)).must(matchQuery(qty)).build();
     }

      public static List<Query> termQuery(String productName){
        final List<Query> terms = new ArrayList<>();
        val termQuery = new TermQuery.Builder();
        terms.add(Query.of(q->q.term(termQuery.field("name").value(productName).build())));
        return terms;
      }

    public static List<Query> matchQuery(Integer qty){
        final List<Query> matches = new ArrayList<>();
        val matchQuery = new MatchQuery.Builder();
        matches.add(Query.of(q->q.match(matchQuery.field("qty").query(qty).build())));
        return matches;
    }
}
