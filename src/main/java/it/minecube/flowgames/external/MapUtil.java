
package it.minecube.flowgames.external;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MapUtil {


    public static String mapToString(Map<Object,Object> map){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(bos);
        xmlEncoder.writeObject(map);
        xmlEncoder.flush();
        return bos.toString();
    }

    public static Map<Object,Object> stringToMap(String map){
        XMLDecoder xmlDecoder = new XMLDecoder(new ByteArrayInputStream(map.getBytes()));
        return (Map<Object,Object>) xmlDecoder.readObject();
    }
}
