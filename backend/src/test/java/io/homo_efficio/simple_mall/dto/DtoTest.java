package io.homo_efficio.simple_mall.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import io.homo_efficio.simple_mall.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-17
 */
@RunWith(JUnit4.class)
public class DtoTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void readAsProduct() throws IOException {
        Product product = new Product();
        product.setProductCode("1");
        product.setProductName("제품1");
        product.setProductImage("이미지경로1");
        product.setProductPrice("10000");

        String productJson = mapper.writeValueAsString(product);

        Product readProduct = mapper.readValue(productJson, Product.class);

        assertThat(readProduct.getProductName()).isEqualTo("제품1");
    }

    @Test
    public void readAsArgumentsDto() throws IOException {
        ArgumentsDto argumentsDto = new ArgumentsDto();
        argumentsDto.setSearchKeyword("키보드");
        argumentsDto.setOption("청축");
        argumentsDto.setSortCode("가격");

        String json = mapper.writeValueAsString(argumentsDto);

        ArgumentsDto readArgumentsDto = mapper.readValue(json, ArgumentsDto.class);

        assertThat(readArgumentsDto.getSearchKeyword()).isEqualTo("키보드");
        assertThat(readArgumentsDto.getOption()).isEqualTo("청축");
        assertThat(readArgumentsDto.getSortCode()).isEqualTo("가격");
    }

    @Test
    public void readAsRequestDto() throws IOException {
        ArgumentsDto argumentsDto = new ArgumentsDto();
        argumentsDto.setSearchKeyword("키보드");
        argumentsDto.setOption("청축");
        argumentsDto.setSortCode("가격");

        String processingTime = "0.05 sec";

        RequestDto requestDto = new RequestDto();
        requestDto.setArguments(argumentsDto);
        requestDto.setProcessingTime(processingTime);

        String json = mapper.writeValueAsString(requestDto);

        RequestDto readRequestDto = mapper.readValue(json, RequestDto.class);

        assertThat(readRequestDto.getArguments().getOption()).isEqualTo("청축");
    }

    @Test
    public void readAsProductSearchResponseDto() throws IOException {
        ArgumentsDto argumentsDto = new ArgumentsDto();
        argumentsDto.setSearchKeyword("키보드");
        argumentsDto.setOption("청축");
        argumentsDto.setSortCode("가격");

        String processingTime = "0.05 sec";

        RequestDto requestDto = new RequestDto();
        requestDto.setArguments(argumentsDto);
        requestDto.setProcessingTime(processingTime);


        Product product = new Product();
        product.setProductCode("1");
        product.setProductName("제품1");
        product.setProductImage("이미지경로1");
        product.setProductPrice("10000");

        ProductsDto productsDto = new ProductsDto();
        productsDto.setProduct(Arrays.asList(product));
        productsDto.setTotalCount("12345");

        ProductSearchResponseDto productSearchResponseDto = new ProductSearchResponseDto();
        productSearchResponseDto.setProducts(productsDto);
        productSearchResponseDto.setRequest(requestDto);

        String json = mapper.writeValueAsString(productSearchResponseDto);

        ProductSearchResponseDto readProductSearchResultDto = mapper.readValue(json, ProductSearchResponseDto.class);

        assertThat(readProductSearchResultDto.getProducts().getProduct().get(0).getProductName()).isEqualTo("제품1");
        assertThat(readProductSearchResultDto.getProducts().getTotalCount()).isEqualTo("12345");
        assertThat(readProductSearchResultDto.getRequest().getArguments().getSearchKeyword()).isEqualTo("키보드");
        assertThat(readProductSearchResultDto.getRequest().getProcessingTime()).isEqualTo("0.05 sec");
    }

    @Test
    public void readAsSearchResultContainerDto() throws IOException {
        ArgumentsDto argumentsDto = new ArgumentsDto();
        argumentsDto.setSearchKeyword("키보드");
        argumentsDto.setOption("청축");
        argumentsDto.setSortCode("가격");

        String processingTime = "0.05 sec";

        RequestDto requestDto = new RequestDto();
        requestDto.setArguments(argumentsDto);
        requestDto.setProcessingTime(processingTime);


        Product product = new Product();
        product.setProductCode("1");
        product.setProductName("제품1");
        product.setProductImage("이미지경로1");
        product.setProductPrice("10000");

        ProductsDto productsDto = new ProductsDto();
        productsDto.setProduct(Arrays.asList(product));
        productsDto.setTotalCount("12345");

        ProductSearchResponseDto productSearchResponseDto = new ProductSearchResponseDto();
        productSearchResponseDto.setProducts(productsDto);
        productSearchResponseDto.setRequest(requestDto);

        SearchResultContainerDto searchResultContainerDto = new SearchResultContainerDto();
        searchResultContainerDto.setProductSearchResponse(productSearchResponseDto);

        String json = mapper.writeValueAsString(searchResultContainerDto);
        System.out.println(json);

        SearchResultContainerDto readSearchResultContainerDto = mapper.readValue(json, SearchResultContainerDto.class);

        assertThat(readSearchResultContainerDto.getProductSearchResponse().getProducts().getProduct().get(0).getProductName()).isEqualTo("제품1");
        assertThat(readSearchResultContainerDto.getProductSearchResponse().getRequest().getProcessingTime()).isEqualTo("0.05 sec");
        assertThat(readSearchResultContainerDto.getProductSearchResponse().getRequest().getArguments().getOption()).isEqualTo("청축");
    }

    @Test(expected = UnrecognizedPropertyException.class)
    public void readRealJsonAsSearchResultContainerDto_대소문자구별() throws IOException {
        String json = "{\"ProductSearchResponse\":{\"Request\":{\"Arguments\":{\"searchKeyword\":\"anime\",\"sortCode\":\"\",\"option\":\"\"},\"ProcessingTime\":\"0.554 sec\"},\"Products\":{\"TotalCount\":\"26375\",\"Product\":[{\"ProductCode\":\"1718279979\",\"ProductName\":\"남성 헬스 ANIMAL 애니멀 나시티 피트니스\",\"ProductPrice\":\"20000\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"KC_몰\",\"Seller\":\"kcmall1\",\"SellerGrd\":\"5\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1718279979\",\"SalePrice\":\"20000\",\"Delivery\":\"무료\",\"ReviewCount\":\"6\",\"BuySatisfy\":\"87\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"0\",\"Mileage\":\"0\"}},{\"ProductCode\":\"1757096446\",\"ProductName\":\"(MST 프리미엄 키즈 백팩 Animal Dino(S)_미국내 대표\",\"ProductPrice\":\"79620\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"행복공방2\",\"Seller\":\"mine201502\",\"SellerGrd\":\"3\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1757096446\",\"SalePrice\":\"78830\",\"Delivery\":\"조건부무료\",\"ReviewCount\":\"0\",\"BuySatisfy\":\"0\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"790\",\"Mileage\":\"0\",\"Point\":\"0\",\"InFree\":\"3개월\"}},{\"ProductCode\":\"1757096648\",\"ProductName\":\"(MST 프리미엄 키즈 백팩 Animal Kitty(S)_미국내 대\",\"ProductPrice\":\"79620\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"행복공방2\",\"Seller\":\"mine201502\",\"SellerGrd\":\"3\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1757096648\",\"SalePrice\":\"78830\",\"Delivery\":\"조건부무료\",\"ReviewCount\":\"0\",\"BuySatisfy\":\"0\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"790\",\"Mileage\":\"0\",\"Point\":\"0\",\"InFree\":\"3개월\"}},{\"ProductCode\":\"1757096503\",\"ProductName\":\"(MST 프리미엄 키즈 백팩 Animal Elephant(S)_미국내\",\"ProductPrice\":\"79620\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"행복공방2\",\"Seller\":\"mine201502\",\"SellerGrd\":\"3\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1757096503\",\"SalePrice\":\"78830\",\"Delivery\":\"조건부무료\",\"ReviewCount\":\"0\",\"BuySatisfy\":\"0\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"790\",\"Mileage\":\"0\",\"Point\":\"0\",\"InFree\":\"3개월\"}},{\"ProductCode\":\"1757096775\",\"ProductName\":\"(MST 프리미엄 키즈 백팩 Animal Owl(S)_미국내 대표\",\"ProductPrice\":\"79620\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"행복공방2\",\"Seller\":\"mine201502\",\"SellerGrd\":\"3\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1757096775\",\"SalePrice\":\"78830\",\"Delivery\":\"조건부무료\",\"ReviewCount\":\"0\",\"BuySatisfy\":\"0\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"790\",\"Mileage\":\"0\",\"Point\":\"0\",\"InFree\":\"3개월\"}},{\"ProductCode\":\"1757096560\",\"ProductName\":\"(MST 프리미엄 키즈 백팩 Animal Fox(S)_미국내 대표\",\"ProductPrice\":\"79620\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"행복공방2\",\"Seller\":\"mine201502\",\"SellerGrd\":\"3\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1757096560\",\"SalePrice\":\"78830\",\"Delivery\":\"조건부무료\",\"ReviewCount\":\"0\",\"BuySatisfy\":\"0\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"790\",\"Mileage\":\"0\",\"Point\":\"0\",\"InFree\":\"3개월\"}},{\"ProductCode\":\"1757096724\",\"ProductName\":\"(MST 프리미엄 키즈 백팩 Animal Monkey(S)_미국내 대\",\"ProductPrice\":\"79620\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"행복공방2\",\"Seller\":\"mine201502\",\"SellerGrd\":\"3\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1757096724\",\"SalePrice\":\"78830\",\"Delivery\":\"조건부무료\",\"ReviewCount\":\"0\",\"BuySatisfy\":\"0\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"790\",\"Mileage\":\"0\",\"Point\":\"0\",\"InFree\":\"3개월\"}}]}}}";

        SearchResultContainerDto dto = mapper.readValue(json, SearchResultContainerDto.class);

        assertThat(dto.getProductSearchResponse().getProducts().getTotalCount()).isEqualTo("12345");
    }

    @Test
    public void readRealJsonAsSearchResultContainerDto_대소문자무관() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        String json = "{\"ProductSearchResponse\":{\"Request\":{\"Arguments\":{\"searchKeyword\":\"anime\",\"sortCode\":\"\",\"option\":\"\"},\"ProcessingTime\":\"0.554 sec\"},\"Products\":{\"TotalCount\":\"26375\",\"Product\":[{\"ProductCode\":\"1718279979\",\"ProductName\":\"남성 헬스 ANIMAL 애니멀 나시티 피트니스\",\"ProductPrice\":\"20000\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/2\\/7\\/9\\/9\\/7\\/9\\/UySgK\\/1718279979_B.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"KC_몰\",\"Seller\":\"kcmall1\",\"SellerGrd\":\"5\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1718279979\",\"SalePrice\":\"20000\",\"Delivery\":\"무료\",\"ReviewCount\":\"6\",\"BuySatisfy\":\"87\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"0\",\"Mileage\":\"0\"}},{\"ProductCode\":\"1757096446\",\"ProductName\":\"(MST 프리미엄 키즈 백팩 Animal Dino(S)_미국내 대표\",\"ProductPrice\":\"79620\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/0\\/9\\/6\\/4\\/4\\/6\\/EvfHb\\/1757096446_L300.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"행복공방2\",\"Seller\":\"mine201502\",\"SellerGrd\":\"3\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1757096446\",\"SalePrice\":\"78830\",\"Delivery\":\"조건부무료\",\"ReviewCount\":\"0\",\"BuySatisfy\":\"0\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"790\",\"Mileage\":\"0\",\"Point\":\"0\",\"InFree\":\"3개월\"}},{\"ProductCode\":\"1757096648\",\"ProductName\":\"(MST 프리미엄 키즈 백팩 Animal Kitty(S)_미국내 대\",\"ProductPrice\":\"79620\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/0\\/9\\/6\\/6\\/4\\/8\\/JStJb\\/1757096648_L300.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"행복공방2\",\"Seller\":\"mine201502\",\"SellerGrd\":\"3\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1757096648\",\"SalePrice\":\"78830\",\"Delivery\":\"조건부무료\",\"ReviewCount\":\"0\",\"BuySatisfy\":\"0\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"790\",\"Mileage\":\"0\",\"Point\":\"0\",\"InFree\":\"3개월\"}},{\"ProductCode\":\"1757096503\",\"ProductName\":\"(MST 프리미엄 키즈 백팩 Animal Elephant(S)_미국내\",\"ProductPrice\":\"79620\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/0\\/9\\/6\\/5\\/0\\/3\\/YlZkP\\/1757096503_L300.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"행복공방2\",\"Seller\":\"mine201502\",\"SellerGrd\":\"3\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1757096503\",\"SalePrice\":\"78830\",\"Delivery\":\"조건부무료\",\"ReviewCount\":\"0\",\"BuySatisfy\":\"0\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"790\",\"Mileage\":\"0\",\"Point\":\"0\",\"InFree\":\"3개월\"}},{\"ProductCode\":\"1757096775\",\"ProductName\":\"(MST 프리미엄 키즈 백팩 Animal Owl(S)_미국내 대표\",\"ProductPrice\":\"79620\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/0\\/9\\/6\\/7\\/7\\/5\\/tjhGD\\/1757096775_L300.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"행복공방2\",\"Seller\":\"mine201502\",\"SellerGrd\":\"3\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1757096775\",\"SalePrice\":\"78830\",\"Delivery\":\"조건부무료\",\"ReviewCount\":\"0\",\"BuySatisfy\":\"0\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"790\",\"Mileage\":\"0\",\"Point\":\"0\",\"InFree\":\"3개월\"}},{\"ProductCode\":\"1757096560\",\"ProductName\":\"(MST 프리미엄 키즈 백팩 Animal Fox(S)_미국내 대표\",\"ProductPrice\":\"79620\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/0\\/9\\/6\\/5\\/6\\/0\\/XPlFP\\/1757096560_L300.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"행복공방2\",\"Seller\":\"mine201502\",\"SellerGrd\":\"3\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1757096560\",\"SalePrice\":\"78830\",\"Delivery\":\"조건부무료\",\"ReviewCount\":\"0\",\"BuySatisfy\":\"0\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"790\",\"Mileage\":\"0\",\"Point\":\"0\",\"InFree\":\"3개월\"}},{\"ProductCode\":\"1757096724\",\"ProductName\":\"(MST 프리미엄 키즈 백팩 Animal Monkey(S)_미국내 대\",\"ProductPrice\":\"79620\",\"ProductImage\":\"http:\\/\\/i.011st.com\\/t\\/080\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage100\":\"http:\\/\\/i.011st.com\\/t\\/100\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage110\":\"http:\\/\\/i.011st.com\\/t\\/110\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage120\":\"http:\\/\\/i.011st.com\\/t\\/120\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage130\":\"http:\\/\\/i.011st.com\\/t\\/130\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage140\":\"http:\\/\\/i.011st.com\\/t\\/140\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage150\":\"http:\\/\\/i.011st.com\\/t\\/150\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage170\":\"http:\\/\\/i.011st.com\\/t\\/170\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage200\":\"http:\\/\\/i.011st.com\\/t\\/200\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage250\":\"http:\\/\\/i.011st.com\\/t\\/250\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage270\":\"http:\\/\\/i.011st.com\\/t\\/270\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"ProductImage300\":\"http:\\/\\/i.011st.com\\/t\\/300\\/pd\\/17\\/0\\/9\\/6\\/7\\/2\\/4\\/lwkcB\\/1757096724_L300.jpg\",\"Text1\":\"\",\"Text2\":\"\",\"SellerNick\":\"행복공방2\",\"Seller\":\"mine201502\",\"SellerGrd\":\"3\",\"Rating\":\"0\",\"DetailPageUrl\":\"http:\\/\\/www.11st.co.kr\\/product\\/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1757096724\",\"SalePrice\":\"78830\",\"Delivery\":\"조건부무료\",\"ReviewCount\":\"0\",\"BuySatisfy\":\"0\",\"MinorYn\":\"Y\",\"Benefit\":{\"Discount\":\"790\",\"Mileage\":\"0\",\"Point\":\"0\",\"InFree\":\"3개월\"}}]}}}";

        SearchResultContainerDto dto = mapper.readValue(json, SearchResultContainerDto.class);

        assertThat(dto.getProductSearchResponse().getProducts().getTotalCount()).isEqualTo("26375");
    }
}