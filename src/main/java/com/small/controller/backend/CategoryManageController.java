package com.small.controller.backend;

import com.small.common.ResponseCode;
import com.small.common.ServerResponse;
import com.small.pojo.User;
import com.small.service.ICategoryService;
import com.small.service.IUserService;
import com.small.util.CookUtil;
import com.small.util.JsonUtil;
import com.small.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/category/")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 添加分类
     * @param httpServletRequest
     * @param categoryName
     * @param parentId
     * @return
     */
    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId, HttpServletRequest httpServletRequest){
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        String loginToken = CookUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
//        }
//        //校验一下是否是管理员
//        if (iUserService.checkAdminRole(user).isSuccess()){
//            //是管理员
//            //增加我们处理分类的逻辑
//            return iCategoryService.addCategory(categoryName,parentId);
//        }else {
//            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
//        }
        return iCategoryService.addCategory(categoryName,parentId);
    }

    /**
     * 更新分类名称
     * @param session
     * @param categoryId
     * @param categoryName
     * @return
     */
    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session,Integer categoryId,String categoryName,HttpServletRequest httpServletRequest){
 //       User user = (User) session.getAttribute(Const.CURRENT_USER);
//        String loginToken = CookUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()){
//            return iCategoryService.updateCategoryName(categoryId,categoryName);
//        }else{
//            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
//        }
        return iCategoryService.updateCategoryName(categoryId,categoryName);
    }

    /**
     * 查询分类节点
     * @param httpServletRequest
     * @param categoryId
     * @return
     */
    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpServletRequest httpServletRequest,@RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
//        String loginToken = CookUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if(user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            //查询子节点的category信息,并且不递归,保持平级
//            return iCategoryService.getChildrenParallelCategory(categoryId);
//        }else{
//            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
//        }
        return iCategoryService.getChildrenParallelCategory(categoryId);
    }



    /**
     *深度查询分类节点
     * @param httpServletRequest
     * @param categoryId
     * @return
     */
    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpServletRequest httpServletRequest,@RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
//        String loginToken = CookUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()){
//            //查询当前节点的id和递归子节点的id
////            0->10000->100000
//            return iCategoryService.selectCategoryAndChildrenById(categoryId);
//        }else {
//            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
//        }
        return iCategoryService.selectCategoryAndChildrenById(categoryId);
    }
}
