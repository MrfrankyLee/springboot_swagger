package com.springboot.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lixiaole
 * @date 2018/10/25
 * @Description
 */
@RequestMapping("/ES")
@RestController
@Api(tags="ElasticSearch搜索")
public class EsController {
    @Autowired
    private TransportClient transportClient;

    @ApiOperation(value="查询",notes = "查询")
    @RequestMapping(value = "/get/book/novel" , method = RequestMethod.GET)
    public ResponseEntity get(@RequestParam(name = "id",defaultValue = "")String id){
        if(id.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        GetResponse response = transportClient.prepareGet("book","novel",id).get();
        if(response.isExists()){
            return new ResponseEntity(response.getSource(),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @ApiOperation(value="新增",notes = "新增")
    @RequestMapping(value = "/add/book/novel" , method = RequestMethod.POST)
    public ResponseEntity add(
            @RequestParam(name = "title",defaultValue = "") @ApiParam(value = "标题") String title,
            @RequestParam(name = "author",defaultValue = "") @ApiParam(value = "作者") String author,
            @RequestParam(name = "word_count",defaultValue = "") @ApiParam(value = "字数") int wordCount,
            @RequestParam(name = "publish_date",defaultValue = "") @ApiParam(value = "出版日期")
                    @DateTimeFormat(pattern = "yyyy-MM-dd")
                    Date publishDate
            ){
        try {
            XContentBuilder content = XContentFactory.jsonBuilder().startObject()
                    .field("title",title)
                    .field("author",author)
                    .field("word_count",wordCount)
                    .field("publish_date",publishDate.getTime())
                    .endObject();
           IndexResponse response = transportClient.prepareIndex("book","novel")
                   .setSource(content)
                   .get();
            return new ResponseEntity(response.getId(),HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "删除",notes = "删除接口")
    @RequestMapping(value = "/delete/book/novel",method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestParam(value = "id") @ApiParam(value = "id") String id){
       DeleteResponse result =  transportClient.prepareDelete("book","novel",id).get();
        return new ResponseEntity(result.getResult().toString(),HttpStatus.OK);
    }

    @ApiOperation(value = "修改",notes = "修改接口")
    @RequestMapping(value = "/update/book/novel",method = RequestMethod.POST)
    public ResponseEntity update(@RequestParam(name = "id") @ApiParam(value = "id") String id
                                ,@RequestParam(name = "title" ,required = false) @ApiParam(value = "标题") String title
                                ,@RequestParam(name = "author",required = false)@ApiParam(value = "作者")  String author
                                ,@RequestParam(name = "word_count",required = false)@ApiParam(value = "字数")  int wordCount
                                ,@RequestParam(name = "publish_date",required = false)@ApiParam(value = "日期")  Date publish_Date
        ){
            UpdateRequest  updateRequest = new UpdateRequest("book","novel",id);

        try {
            XContentBuilder content = XContentFactory.jsonBuilder().startObject();
            if(title != null){
                content.field("titile",title);
            }
            if(author != null){
                content.field("author",author);
            }
            if(wordCount >= 0){
                content.field("word_count",wordCount);
            }
            if(publish_Date != null){
                content.field("publish_date",publish_Date);
            }
            // builder  以endObject 结尾
            content.endObject();

            // 将更新后的内容放入 updateRequest
            updateRequest.doc(content);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            // 更新操作
            UpdateResponse response = transportClient.update(updateRequest).get();
            return new ResponseEntity(response.getResult().toString(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/query/book/novel",method = RequestMethod.POST)
    @ApiOperation(value = "复合查询",notes = "复合查询接口")
    public ResponseEntity query(@RequestParam(name = "author",required = false) @ApiParam(value = "作者") String author
                ,@RequestParam(name = "gt_word_count",defaultValue = "0") @ApiParam(value = "最小字数") int gtwordCount
                ,@RequestParam(name = "lt_word_count",required = false) @ApiParam(value = "最大字数") Integer ltwordCount
                ,@RequestParam(name = "title",required = false) @ApiParam(value = "标题")  String title
                ,@RequestParam(name = "publish_date",required = false)@ApiParam(value = "出版日期") Date publishDate
    ){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(author != null){
            boolQueryBuilder.must(QueryBuilders.matchQuery("author",author));
        }

        if(title !=null){
            boolQueryBuilder.must(QueryBuilders.matchQuery("title",title));
        }

        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("word_count").from(gtwordCount);

        if(ltwordCount != null && ltwordCount>0){
            rangeQueryBuilder.to(ltwordCount);
        }
        // 结合两种查询
        boolQueryBuilder.filter(rangeQueryBuilder);
        SearchResponse searchResponse = transportClient.prepareSearch("book")
                .setTypes("novel")
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(boolQueryBuilder)
                .setFrom(0)
                .setSize(10)
                .get();

        List<Map<String,Object>> list = new ArrayList<>();
        for(SearchHit searchHit : searchResponse.getHits()){
            list.add(searchHit.getSource());
        }

        return new ResponseEntity(list ,HttpStatus.OK);
    }
}
