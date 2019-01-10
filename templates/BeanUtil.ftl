package ${packageName};

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * 处理符合javabean规范的对象
 * */
public class BeanUtil {
    private BeanUtil() {}

    /**
     * 将一个对象转换为另一个对象，只转换相同字段名
     * @param source 需要转换的源对象
     * @param target 转换到的目标对象
     * */
    public static void convert(Object source, Object target) {
        if(source == null || target == null)
            return;

        try {
            // 获取源对象的属性描述
            BeanInfo sourceInfo = Introspector.getBeanInfo(source.getClass());
            PropertyDescriptor[] sourcePds = sourceInfo.getPropertyDescriptors();

            // 获取目标对象的属性描述
            BeanInfo targetInfo = Introspector.getBeanInfo(target.getClass());
            PropertyDescriptor[] targetPds = targetInfo.getPropertyDescriptors();

            // 将源对象的属性描述转换为map
            Map<String, PropertyDescriptor> sourcePdMap = toMap(sourcePds);

            for(PropertyDescriptor targetPd : targetPds) {
                String name = targetPd.getName();

                // 如果是class属性或者在源对象中没有这个属性
                if("class".equals(name) || !sourcePdMap.containsKey(name))
                    continue;

                // 获取源对象中目标字段的值
                Object value = sourcePdMap.get(name).getReadMethod().invoke(source);

                if(value == null)
                    continue;

                setValue(target, targetPd.getWriteMethod(), value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将PropertyDescriptor数组转换为以属性名为键，PropertyDescriptor为值的map
     * */
    private static Map<String, PropertyDescriptor> toMap(PropertyDescriptor[] pds) {
        Map<String, PropertyDescriptor> result = new HashMap<String, PropertyDescriptor>();

        for(PropertyDescriptor pd : pds)
            result.put(pd.getName(), pd);

        return result;
    }

    /**
     * 为对象进行set操作
     * */
    private static void setValue(Object entity, Method writeMethod, Object value)
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

    /**
     * 将对象的boolean类型的值进行取反
     * */
    public static <T> void negateValue(T obj, String booleanProperty) {
        if(obj == null)
            return;

        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(booleanProperty, obj.getClass());
            boolean value = (boolean) propertyDescriptor.getReadMethod().invoke(obj);
            propertyDescriptor.getWriteMethod().invoke(obj, !value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
