package idv.ccw;

import idv.ccw.models.Person;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class PopulateTest {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException,
            IllegalAccessException {
        BeanInfo info = Introspector.getBeanInfo(Person.class);
        PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
        for (int i = 0; i < descriptors.length; i++) {
            System.out.println(descriptors[i].getClass().getName() + ":" + descriptors[i].getName());
        }

        Map<String, String> map = new HashMap<>();
        map.put("name", "John");
        map.put("age", "23");

        //array property accessed as an indexed property.
        map.put("car[0].name", "GTR");//Resulting method call: person.getCar(0).setName("GTR")
        map.put("car[0].color", "Red");//Resulting method call: person.getCar(0).setColor("Red")
        map.put("car[1].name", "Z1");//Resulting method call: person.getCar(1).setName("Z1")
        map.put("car[1].color", "Yellow");//Resulting method call: person.getCar(1).setColor("Yellow")
        map.put("phone[0]", "00001");//Resulting method call: person.setPhone(0, "00001")
        map.put("phone[1]", "00002");//Resulting method call: person.setPhone(1, "00002")

        Person p = new Person();
        BeanUtils.populate(p, map);

        System.out.println(p.toString());
    }
}
