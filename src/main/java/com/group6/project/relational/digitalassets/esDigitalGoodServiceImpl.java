package com.group6.project.relational.digitalassets;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class esDigitalGoodServiceImpl implements esDigitalGoodService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public DigitalGood createDigitalGood(DigitalGood digitalGood) throws IOException {
        IndexResponse response = elasticsearchClient.index(i -> i
                .index("digital_goods")
                .document(digitalGood)
                .refresh(Refresh.True) // Use Refresh.True for immediate visibility, but caution with production use
        );
        if (response.result().equals(Result.Created) || response.result().equals(Result.Updated)) {
            return digitalGood;
        } else {
            throw new IOException("Failed to create or update the digital good.");
        }
    }

    @Override
    public Iterable<DigitalGood> getAllDigitalGoods() throws IOException {
        SearchResponse<DigitalGood> response = elasticsearchClient.search(s -> s
                .index("digital_goods")
                .size(1000) // Set the size if you expect to retrieve a lot of documents
                .query(q -> q.matchAll(m -> m)), DigitalGood.class);
        return response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    @Override
    public DigitalGood getDigitalGoodById(Long id) throws IOException {
        GetResponse<DigitalGood> response = elasticsearchClient.get(g -> g
                .index("digital_goods")
                .id(id.toString()), DigitalGood.class);
        if (response.found()) {
            return response.source();
        } else {
            throw new IOException("Digital good not found with id: " + id);
        }
    }

    @Override
    public DigitalGood updateDigitalGood(Long id, DigitalGood digitalGood) throws IOException {
        UpdateResponse<DigitalGood> response = elasticsearchClient.update(u -> u
                .index("digital_goods")
                .id(id.toString())
                .doc(digitalGood)
                .refresh(Refresh.True), DigitalGood.class); // Use Refresh.True
        if (response.result().equals(Result.Updated)) {
            return digitalGood;
        } else {
            throw new IOException("Failed to update the digital good.");
        }
    }

    @Override
    public boolean deleteDigitalGood(Long id) throws IOException {
        DeleteResponse response = elasticsearchClient.delete(d -> d
                .index("digital_goods")
                .id(id.toString())
                .refresh(Refresh.True)); // Use Refresh.True for immediate effect, but caution with production use
        return response.result().equals(Result.Deleted);
    }

    @Override
    public List<DigitalGood> getDigitalGoodsByCurrency(Integer currencyId) throws IOException {
        SearchResponse<DigitalGood> response = elasticsearchClient.search(s -> s
                        .index("digital_goods")
                        .query(q -> q
                                .term(t -> t
                                        .field("currency_id")
                                        .value(currencyId))),
                DigitalGood.class);
        return response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    @Override
    public List<DigitalGood> searchDigitalGoodsByCostRange(Integer minCost, Integer maxCost) throws IOException {
        SearchResponse<DigitalGood> response = elasticsearchClient.search(s -> s
                        .index("digital_goods")
                        .query(q -> q
                                .range(r -> r
                                        .field("costs")
                                        .gte(JsonData.of(minCost))
                                        .lte(JsonData.of(maxCost)))),
                DigitalGood.class);
        return response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    @Override
    public List<DigitalGood> searchDigitalGoodsByName(String name) throws IOException {
        SearchResponse<DigitalGood> response = elasticsearchClient.search(s -> s
                        .index("digital_goods")
                        .query(q -> q
                                .match(m -> m
                                        .field("name")
                                        .query(name))),
                DigitalGood.class);
        return response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

}


