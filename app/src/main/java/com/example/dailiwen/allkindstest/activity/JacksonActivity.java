package com.example.dailiwen.allkindstest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dailiwen.allkindstest.R;
import com.example.dailiwen.allkindstest.activity.Jackson.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 利用Jackson，将json进行转换或反转换
 * @author dailiwen
 * @date 2018/02/27
 */
public class JacksonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jackson);
    }

    public void javaToJson(View view) throws ParseException {
        SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");

        User user1 = new User();
        user1.setName("dai");
        user1.setEmail("4503267@qq.com");
        user1.setAge(20);
        user1.setBirthday(dateformat.parse("10/28/1997"));

        User user2 = new User();
        user2.setName("li");
        user2.setEmail("13275349@qq.com");
        user2.setAge(20);
        user2.setBirthday(dateformat.parse("10/14/1997"));

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        // User类转JSON
        String json = objectToJson(user1);
        // User类转JSON : {"name":"dai","birthday":"1997年10月27日","mail":"450326779@qq.com"}
        Log.d("javaToJson","User类转JSON : " + json);

        // Map转JSON
        Map<String, User> map = new HashMap<>();
        map.put("dai", user1);
        String jsonmap = objectToJson(map);
        // Map转JSON : {"dai":{"name":"dai","birthday":"1997年10月27日","mail":"450326779@qq.com"}}
        Log.d("javaToJson","Map转JSON : " + jsonmap);

        // List转JSON
        String jsonlist = objectToJson(users);
        // List转JSON : [{"name":"dai","birthday":"1997年10月27日","mail":"450326779@qq.com"},{"name":"li","birthday":"1997年10月13日","mail":"1327534945@qq.com"}]
        Log.d("javaToJson","List转JSON : " + jsonlist);
    }

    /**
     * 将一个object转换为json, 可以使一个java对象，也可以使集合
     *
     * @param object
     * @return
     */
    private String objectToJson(Object object) {
        /**
         * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
         * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
         * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
         * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
         * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
         * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
         */
        ObjectMapper mapper = new ObjectMapper();

        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    public void jsonToJava(View view) {
        String json = "{\"age\":2,\"birthday\":1412092800000,\"mail\":\"silion@qq.com\",\"name\":\"silion\"}";
        String jsonmap = "{\"silion\":{\"birthday\":\"2014年09月30日\",\"name\":\"silion\",\"mail\":\"silion@qq.com\"}}";
        String jsonlist = "[{\"age\":2,\"birthday\":1412092800000,\"mail\":\"silion@qq.com\",\"name\":\"silion\"},{\"age\":2,\"birthday\":1420041600000,\"mail\":\"silion@qq.com\",\"name\":\"silion\"}]";

        /**
         * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
         */

        // JSON转Class
        User user = jsonToClass(json, User.class);
        // JSON转Class : User{name='silion', age=0, birthday=Wed Oct 01 00:00:00 GMT+08:00 2014, email='silion@qq.com'}
        Log.d("jsonToJava","JSON转Class : " + user);

        // JSON转Map
        Map<String, User> map = jsonToMap(jsonmap);
        // JSON转Map : {silion=User{name='silion', age=0, birthday=Tue Sep 30 08:00:00 GMT+08:00 2014, email='silion@qq.com'}}
        Log.d("jsonToJava","JSON转Map : " + map);

        // JSON转List
        List<User> users = jsonToList(jsonlist);
        // JSON转List : [User{name='silion', age=0, birthday=Wed Oct 01 00:00:00 GMT+08:00 2014, email='silion@qq.com'}, User{name='silion', age=0, birthday=Thu Jan 01 00:00:00 GMT+08:00 2015, email='silion@qq.com'}]
        Log.d("jsonToJava","JSON转List : " + users);

        // JSON转List2
        List<User> users2 = jsonToList2(jsonlist);
        // JSON转List2 : [User{name='silion', age=0, birthday=Wed Oct 01 00:00:00 GMT+08:00 2014, email='silion@qq.com'}, User{name='silion', age=0, birthday=Thu Jan 01 00:00:00 GMT+08:00 2015, email='silion@qq.com'}]
        Log.d("jsonToJava","JSON转List2 : " + users2);

    }

    private <T> T jsonToClass(String json, Class<T> c) {
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(json, c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    private Map<String, User> jsonToMap(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, User> map = null;
        try {
            map = mapper.readValue(json, new TypeReference<Map<String, User>>(){
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private List<User> jsonToList(String json) {
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, User.class);
            users = mapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private List<User> jsonToList2(String jsonlist) {
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = null;
        try {
            users = mapper.readValue(jsonlist, new TypeReference<List<User>>(){
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
