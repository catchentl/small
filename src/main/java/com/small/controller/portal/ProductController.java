package com.small.controller.portal;

import com.github.pagehelper.PageInfo;
import com.small.common.ServerResponse;
import com.small.service.IProductService;
import com.small.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;


    /**
     * 获取商品详情接口
     * @param productId
     * @return
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<ProductDetailVo> detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }

    //RESRful 格式接口展示
    @RequestMapping(value = "/{productId}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<ProductDetailVo> detailRESTful(@PathVariable Integer productId){
        return iProductService.getProductDetail(productId);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword",required = false)String keyword,
                                         @RequestParam(value = "categoryId",required = false)Integer categoryId,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderBy",defaultValue = "") String orderBy){
        return iProductService.getProductByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
    }

    @RequestMapping(value = "/category/{categoryId}/{pageNum}/{pageSize}/{orderBy}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> listRESTful(@PathVariable(value = "categoryId")Integer categoryId,
                                         @PathVariable(value = "pageNum") Integer pageNum,
                                         @PathVariable(value = "pageSize") Integer pageSize,
                                         @PathVariable(value = "orderBy") String orderBy){

        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize = 10;
        }
        if (orderBy == null){
            orderBy = "price_asc";
        }
        return iProductService.getProductByKeywordCategory("",categoryId,pageNum,pageSize,orderBy);
    }

    @RequestMapping(value = "/keyword/{keyword}/{pageNum}/{pageSize}/{orderBy}",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> listRESTful(@PathVariable(value = "keyword")String keyword,
                                         @PathVariable(value = "pageNum") Integer pageNum,
                                         @PathVariable(value = "pageSize") Integer pageSize,
                                         @PathVariable(value = "orderBy") String orderBy){
        if (pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize = 10;
        }
        if (orderBy == null){
            orderBy = "price_asc";
        }
        return iProductService.getProductByKeywordCategory(keyword,null,pageNum,pageSize,orderBy);
    }
}
