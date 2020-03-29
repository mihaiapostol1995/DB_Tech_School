package dbe.hw.homework.fifthHomework;

import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.*;

@AllArgsConstructor
public class Product {

    @JsonField(name = "code")
    public int id;

    @JsonField
    public String name;

    @JsonField(lengthToTrimString = 20)
    public String description;

    public String storeName;

    public static void main(String[] args) {
        Product product = new Product(100, "Coca-Cola", "A tasty delicious drink", "Carrefour");

        String json = toJson(product);
        System.out.println(json); // { "code": 100, "name": "Coca-Cola", "description": 5}
    }

    public static String toJson(Object object)  {
        Class<?> productClass = object.getClass();
        Field[] fields = productClass.getFields();
        Map<String, Object> stringObjectMap = new LinkedHashMap<>();
        Arrays.stream(fields).forEach(field ->
                {if (field.getAnnotation(JsonField.class) != null) {
                    try {
                        String string = field.getAnnotation(JsonField.class).name();
                        int length = field.getAnnotation(JsonField.class).lengthToTrimString();
                        Object o = field.get(object);
                        int actualLength = o.toString().length();
                        int min = Math.min(length, actualLength);

                        checkForString(stringObjectMap, field, string, o.toString().substring(0, min));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        );

        StringBuilder stringBuilder = new StringBuilder("{ ");
        Set<Map.Entry<String, Object>> entries = stringObjectMap.entrySet();
        for (Map.Entry<String, Object> map: entries){
            stringBuilder.append("\"").append(map.getKey()).append("\":");
            if(map.getValue().getClass() != String.class) {
                stringBuilder.append(map.getValue());
            }
            else {
                stringBuilder.append("\"").append(map.getValue()).append("\"");
            }
            stringBuilder.append(" ");
        }
        return stringBuilder.append("}").toString();
    }

    private static void checkForString(Map<String, Object> stringObjectMap, Field field, String string, Object o) {
        if (!string.equals("")) {
            stringObjectMap.put(string, o);
        } else {
            stringObjectMap.put(field.getName(), o);
        }
    }
}