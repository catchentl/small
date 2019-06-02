package com.small.util;

import com.google.common.collect.Lists;
import com.small.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,false);

        //忽略空Bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);

        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));

        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    public static <T> String obj2String(T obj){
        if (obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            log.warn("Parse object to String errror");
            return null;
        }
    }

    public static <T> String obj2StringPretty(T obj){
        if (obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }catch (Exception e){
            log.warn("Parse object to String errror");
            return null;
        }
    }

    public static <T> T string2Obj(String str,Class<T> clazz){
        if (StringUtils.isBlank(str)|| clazz == null){
            return null;
        }
        try {
            return clazz.equals(String.class)? (T) str : objectMapper.readValue(str,clazz);
        } catch (Exception e) {
            log.warn("Parse String to object errror");
            return null;
        }
    }



    public static <T> T string2Obj(String str, TypeReference<T> tTypeReference){
        if (StringUtils.isBlank(str)|| tTypeReference == null){
            return null;
        }
        try {
            return (T)(tTypeReference.getType().equals(String.class)?  str : objectMapper.readValue(str,tTypeReference));
        } catch (Exception e) {
            log.warn("Parse String to object errror");
            return null;
        }
    }

    public static <T> T string2Obj(String str,Class<?> collectionClass,Class<?>... elementClass){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass,elementClass);
        try {
            return objectMapper.readValue(str,javaType);
        } catch (IOException e) {
            log.warn("Parse String to Object error",e);
            return null;
        }
    }


    public static void main(String[] args) {
        User u1 = new User();
        u1.setId(1);
        u1.setEmail("small@sm.com");

        User u2 = new User();
        u2.setId(2);
        u2.setEmail("smallu2@sm.com");

        String user1Json = JsonUtil.obj2String(u1);

        String user1JsonPetty = JsonUtil.obj2StringPretty(u1);

        log.info("user1Json:{}",user1Json);
        log.info("user1JsonPetty:{}",user1JsonPetty);
        User user = JsonUtil.string2Obj(user1Json,User.class);

        log.info("user:{}",user);

        List<User> userList = Lists.newArrayList();
        userList.add(u1);
        userList.add(u2);

        String userListStr = JsonUtil.obj2String(userList);
        log.info("userListStr:{}",userListStr);

        List<User> userList1 = JsonUtil.string2Obj(userListStr, new TypeReference<List<User>>() {
        });

        log.info("userList1:{}",userList1);

        List<User> userList2 = JsonUtil.string2Obj(userListStr,List.class,User.class);

        log.info("userList2:{}",userList2);
    }
}
