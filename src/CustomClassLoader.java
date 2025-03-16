import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name, boolean resolve) {
        Class<?> cls = findLoadedClass(name);
        if (cls == null) {
            try {
                cls = super.loadClass(name, resolve);
            } catch (ClassNotFoundException e) {
                cls = loadMyClass(name);
            }
        }
        return cls;
    }

    private Class<?> loadMyClass(String name)  {
        byte[] classBytes;
        try (FileInputStream fis = new FileInputStream("classes/" + name + ".class")) {
            classBytes = fis.readAllBytes();
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            System.err.println("Error loading class " + name);
            return null;
        }
    }
}
