package cn.makisekurisu.util;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

/**
 * Created by ym on 2017/2/19 0019.
 */
public class EntityUtil {

    private EntityUtil() {}

    /**
     * 通过properties文件将属性注入bean中
     *
     * */
    public static <T> void initEntity(Properties properties, T entity) {
        if(entity == null)
            return;

        // 使用反射、内省来实现给对象的每个属性赋值
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass()); // entity对象的beanInfo实例
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors(); // 获取T类的所有javaBean的属性描述符对象

            for (PropertyDescriptor pd : pds) { // 遍历每个属性描述符对象
                String name = pd.getName(); // 获取javaBean的属性名

                if (name.equals("class") || // 每个类都有getClass方法，则再获取该属性名后，要进行判断，是class则跳过这次循环
                        !properties.containsKey(name)) // 或者properties中不含有这个字段
                    continue;

                Object value = properties.get(name); // 获取properties中对应的值

                if(value == null)
                    continue;

                Method writeMethod = pd.getWriteMethod(); // 获取该对象的写方法
                setValue(entity, writeMethod, value);

            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private static <T> void setValue(T entity, Method writeMethod, Object value)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?>[] paramTypes = writeMethod.getParameterTypes();
        if(paramTypes.length != 1)	// 如果参数不为1个，则不执行
            return;

        Class<?> paramType = paramTypes[0];

        if(int.class.equals(paramType))
            value = Integer.parseInt(value.toString());
        else if(byte.class.equals(paramType))
            value = Byte.parseByte(value.toString());
        else if(short.class.equals(paramType))
            value = Short.parseShort(value.toString());
        else if(long.class.equals(paramType))
            value = Long.parseLong(value.toString());
        else if(float.class.equals(paramType))
            value = Float.parseFloat(value.toString());
        else if(double.class.equals(paramType))
            value = Double.parseDouble(value.toString());
        else if(char.class.equals(paramType))
            value = value.toString().charAt(0);
        else if(boolean.class.equals(paramType))
            value = Boolean.parseBoolean(value.toString());

        writeMethod.invoke(entity, value); // 将properties中的值写入entity对象中
    }

    // 将map集合转换为Entity对象
    public static <T> T toEntity(Map<String, Object> mEntity, Class<T> clazz)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        T entity = clazz.newInstance();

        // 使用反射、内省来实现给entity对象的每个属性赋值
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass()); // 获取entity类的beanInfo实例
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors(); // 获取entity类的所有javaBean的属性描述符对象

            for (PropertyDescriptor pd : pds) { // 遍历每个属性描述符对象
                String name = pd.getName(); // 获取javaBean的属性名

                if (name.equals("class")) // 每个类都有getClass方法，则再获取该属性名后，要进行判断，是class则跳过这次循环
                    continue;

                Object value = mEntity.get(name); // 获取Map中对应的值

                if (value != null) { // 如果map中存在这个值的话
                    Method writeMethod = pd.getWriteMethod(); // 获取该对象的写方法
                    writeMethod.invoke(entity, value); // 将map中的值写入entity对象中
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return entity;
    }
}