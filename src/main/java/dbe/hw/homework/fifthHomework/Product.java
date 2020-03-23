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
                        int actualLength = field.get(object).toString().length();
                        if (length > actualLength) {
                            if (!string.equals("")) {
                                stringObjectMap.put(string, field.get(object));
                            } else {
                                stringObjectMap.put(field.getName(), field.get(object));
                            }
                        }
                        else {
                            if (!string.equals("")) {
                                stringObjectMap.put(string, actualLength - length);
                            } else {
                                stringObjectMap.put(field.getName(), actualLength - length);
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        );

        StringBuilder stringBuilder = new StringBuilder("{");
        for (Map.Entry<String, Object> map: stringObjectMap.entrySet()){
            stringBuilder.append("\"").append(map.getKey()).append("\":");
            if(map.getValue().getClass() != String.class) {
                stringBuilder.append(map.getValue());
            }
            else {
                stringBuilder.append("\"").append(map.getValue()).append("\"");
            }
        }
        return stringBuilder.append("}").toString();
    }
}