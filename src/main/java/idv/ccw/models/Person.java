package idv.ccw.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    private String name;
    private String age;
    private List<Car> cars = new AutoArrayList<>(Car.class);
    private List<String> phones = new AutoArrayList<>(String.class);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Car getCar(int index) {
        System.out.println("getCar, index=" + index);
         return this.cars.get(index);
    }

    public void setCar(int index, Car car) {
        System.out.println("setCar, index=" + index + ", Car=" + car);
        this.cars.set(index, car);
    }

    public String getPhone(int index) {
        System.out.println("getPhone, index=" + index);
        return this.phones.get(index);
    }

    public void setPhone(int index, String phone) {
        System.out.println("setPhone, index=" + index + ", Phone=" + phone);
        this.phones.set(index, phone);
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age='" + age + '\'' + ", cars=" + cars + ", phones=" + phones
                + '}';
    }

    private static class AutoArrayList<E> extends ArrayList<E> {
        private Class<E> itemClass;

        public AutoArrayList(Class<E> itemClass) {
            this.itemClass = itemClass;
        }

        @Override
        public E get(int index) {
            while (index >= size()) {
                add(createInstance(itemClass));
            }
            return super.get(index);
        }

        @Override
        public E set(int index, E element) {
            while (index >= size()) {
                add(createInstance(itemClass));
            }
            return super.set(index, element);
        }

        private E createInstance(Class<E> clazz) {
            if (clazz == null) {
                throw new IllegalStateException("Class was not specified");
            }

            try {
                return clazz.newInstance();
            } catch (InstantiationException ex) {
                throw new RuntimeException("Is it an abstract class?", ex);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException("Is the constructor accessible?", ex);
            }
        }
    }
}