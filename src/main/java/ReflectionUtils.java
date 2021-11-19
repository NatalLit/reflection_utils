import java.lang.reflect.*;

public class ReflectionUtils {
//1.Метод принимает класс и возвращает созданный объект этого класса
//2.Метод принимает object и вызывает у него все методы без параметров
//3.Метод принимает object и выводит на экран все сигнатуры методов в который есть final
//4.Метод принимает Class и выводит все не публичные методы этого класса
//5.Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
//6.Метод принимает объект и меняет всего его приватные поля на их нулевые значение (null, 0, false etc)+

    public static Object create(Class clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor constructor = clazz.getConstructor();
        Object result = constructor.newInstance();
        return result;

    }

    public static void invokeMethods(Object obj) throws InvocationTargetException, IllegalAccessException {
        Class clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            Class<?>[] params = method.getParameterTypes();
            if (params.length == 0) {
                System.out.println(method.invoke(obj));
            }
        }
    }


    public static void printSignatures(Object obj) {
        Class clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            int modifiers = method.getModifiers();
            boolean isFinalModifier = Modifier.isFinal(modifiers);
            if (isFinalModifier == true) {
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters)
                    System.out.println(method.getName() + ": " + parameter.toString());
            }
        }
    }


    public static void printMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            int modifiers = method.getModifiers();
            boolean isPublicModifier = Modifier.isPublic(modifiers);
            if (isPublicModifier == false) {
                System.out.println(method.getName());
            }
        }
    }


    public static void printParentClassAndInterfaces(Class clazz) {
        for (Class super_class = clazz.getSuperclass();
             super_class != null;
             super_class = super_class.getSuperclass()) {
            System.out.println(super_class.getName());
        }
        Class[] interfaces = clazz.getInterfaces();
        for (Class ifc : interfaces) {
            System.out.println(ifc.getName());
        }
    }


    public static void changeFields(Object obj) throws IllegalAccessException {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field :
                fields) {
            int modifiers = field.getModifiers();
            boolean isPrivateModifier = Modifier.isPrivate(modifiers);
            if (isPrivateModifier == true) {
                field.setAccessible(true);
                Class fieldType = field.getType();
                if (fieldType.getName().equals("java.lang.String")) {
                    field.set(obj, null);
                } else if (fieldType.getName().equals("int")) {
                    field.set(obj, 0);
                } else if (fieldType.getName().equals("boolean")) {
                    field.set(obj, false);
                } else if (fieldType.getName().equals("double")) {
                    field.set(obj, 0.0d);
                } else if (fieldType.getName().equals("float")) {
                    field.set(obj, 0.0f);
                } else if (fieldType.getName().equals("char")) {
                    field.set(obj, '\u0000');
                }


            }
        }

    }
}
