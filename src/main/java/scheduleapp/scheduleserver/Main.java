package scheduleapp.scheduleserver;

import lombok.Data;
import scheduleapp.scheduleserver.projection_views.ScheduleView;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {

//        Fox fox = new Fox();
//
//        Map<String, Map<String, String>> result = entityToFormat(fox);
//        System.out.println(result);
    }

//    public static Map<String, Map<String, String>> entityToFormat(Fox fox) throws IllegalAccessException {
//        Map<String, Map<String, String>> resultMap = new HashMap<>();
//        Map<String, String> attributes = new HashMap<>();
//
//        Field[] fields = fox.getClass().getDeclaredFields();
//        for(Field field : fields){
//            attributes.put(field.getName(), field.get(fox).toString());
//        }
//        resultMap.put(fox.getId().toString(), attributes);
//
//        return resultMap;
//    }
//
//    @Data
//    static class Fox{
//        private Long id = 123L;
//        private String name = "Foxy";
//        private int age = 19;
//    }
}
