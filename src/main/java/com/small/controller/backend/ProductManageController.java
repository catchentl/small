package com.small.controller.backend;

import com.google.common.collect.Maps;
import com.small.common.Const;
import com.small.common.ResponseCode;
import com.small.common.ServerResponse;
import com.small.pojo.Product;
import com.small.pojo.User;
import com.small.service.IFileService;
import com.small.service.IProductService;
import com.small.service.IUserService;
import com.small.util.CookUtil;
import com.small.util.JsonUtil;
import com.small.util.PropertiesUtil;
import com.small.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/manage/product/")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IFileService iFileService;

    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(HttpServletRequest httpServletRequest, Product product) {
//        String loginToken = CookUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
//
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            return iProductService.saveOrUpdateProduct(product);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        return iProductService.saveOrUpdateProduct(product);
    }

    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(HttpServletRequest httpServletRequest, Integer productId,Integer status){
//        String loginToken = CookUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()){
//            return iProductService.setSaleStatus(productId,status);
//        }else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        return iProductService.setSaleStatus(productId,status);
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpServletRequest httpServletRequest, Integer productId) {
//        String loginToken = CookUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
//
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            return iProductService.manageProductDetail(productId);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        return iProductService.manageProductDetail(productId);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpServletRequest httpServletRequest, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
//        String loginToken = CookUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if(user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
//
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            //填充业务
//            return iProductService.getProductList(pageNum,pageSize);
//        }else{
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        return iProductService.getProductList(pageNum,pageSize);
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse productSearch(HttpServletRequest httpServletRequest,String productName,Integer productId, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
//        String loginToken = CookUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if(user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
//
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            //填充业务
//            return iProductService.searchProduct(productName,productId,pageNum,pageSize);
//        }else{
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        return iProductService.searchProduct(productName,productId,pageNum,pageSize);
    }

    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(HttpServletRequest httpServletRequest, @RequestParam(value = "upload_file", required=false) MultipartFile file,HttpServletRequest request) {
//        String loginToken = CookUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            String path = request.getSession().getServletContext().getRealPath("upload");
//            String targetFileName = iFileService.upload(file,path);
//            String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
//
//            Map fileMap = Maps.newHashMap();
//            fileMap.put("uri",targetFileName);
//            fileMap.put("url",url);
//            return ServerResponse.createBySuccess(fileMap);
//        }else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file,path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri",targetFileName);
        fileMap.put("url",url);
        return ServerResponse.createBySuccess(fileMap);
    }

    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(HttpServletRequest httpServletRequest, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        Map resultMap = Maps.newHashMap();
//        String loginToken = CookUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)){
//            resultMap.put("success",false);
//            resultMap.put("msg","请登录管理员");
//            return resultMap;
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if(user == null){
//            resultMap.put("success",false);
//            resultMap.put("msg","请登录管理员");
//            return resultMap;
//        }
//
//        //富文本中对于返回值有自己的要求,我们使用是simditor所以按照simditor的要求进行返回
////        {
////            "success": true/false,
////                "msg": "error message", # optional
////            "file_path": "[real file path]"
////        }
//
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            String path = request.getSession().getServletContext().getRealPath("upload");
//            String targetFileName = iFileService.upload(file,path);
//            if(StringUtils.isBlank(targetFileName)){
//                resultMap.put("success",false);
//                resultMap.put("msg","上传失败");
//                return resultMap;
//            }
//            String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
//            resultMap.put("success",true);
//            resultMap.put("msg","上传成功");
//            resultMap.put("file_path",url);
//            response.addHeader("Access-Control-Allow-Headers","X-File-Name");
//            return resultMap;
//        }else{
//            resultMap.put("success",false);
//            resultMap.put("msg","无权限操作");
//            return resultMap;
//        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file,path);
        if(StringUtils.isBlank(targetFileName)){
            resultMap.put("success",false);
            resultMap.put("msg","上传失败");
            return resultMap;
        }
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
        resultMap.put("success",true);
        resultMap.put("msg","上传成功");
        resultMap.put("file_path",url);
        response.addHeader("Access-Control-Allow-Headers","X-File-Name");
        return resultMap;
    }

}
