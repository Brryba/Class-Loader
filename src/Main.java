import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        CustomClassLoader loader = new CustomClassLoader();
        try {
            Class<?> loadedClass = loader.loadClass("Customer");
            Object customer = loadedClass.getDeclaredConstructor(String.class).newInstance("Vova");
            Method getName = loadedClass.getDeclaredMethod("getName");
            Method setName = loadedClass.getMethod("setName", String.class);
            System.out.println(getName.invoke(customer));
            setName.invoke(customer, "Vasya");
            System.out.println(getName.invoke(customer));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}