package com.raf.restdemo.controller;

import com.raf.restdemo.dto.OrderCreateDto;
import com.raf.restdemo.dto.ProductCreateDto;
import com.raf.restdemo.dto.ProductDto;
import com.raf.restdemo.dto.ProductUpdateDto;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.service.ProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private String orderDestination;

    public ProductController(ProductService productService, JmsTemplate jmsTemplate, MessageHelper messageHelper,
                             @Value("${destination.createOrder}") String orderDestination) {
        this.productService = productService;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.orderDestination = orderDestination;
    }


    @ApiOperation(value = "Get all products")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAll(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(productService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> add(@RequestBody @Valid ProductCreateDto productCreateDto) {
        return new ResponseEntity<>(productService.add(productCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable("id") Long id
            , @RequestBody @Valid ProductUpdateDto productUpdateDto) {

        return new ResponseEntity<>(productService.update(id, productUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/order")
    public ResponseEntity<Void> order(@PathVariable("id") Long id, @RequestParam("count") Integer count) {
        jmsTemplate.convertAndSend(orderDestination, messageHelper.createTextMessage(new OrderCreateDto(count, id)));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
