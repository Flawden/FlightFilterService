package test.annotation.postProcessor;

import test.annotation.TestClass;
import test.model.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestManager {

    private static final List<Test> testList = new ArrayList<>();

    static {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            File dir = new File("out/production/Test/test/impl");
            if (dir.exists() && dir.isDirectory()) {
                for (File file : Objects.requireNonNull(dir.listFiles())) {

                    if (file.getName().endsWith(".class")) {
                        String className = "test.impl." + file.getName().replace(".class", "");
                        System.out.println("Загружаем класс: " + className);

                        try {
                            Class<?> clazz = classLoader.loadClass(className);
                            if (clazz.isAnnotationPresent(TestClass.class) && Test.class.isAssignableFrom(clazz)) {
                                testList.add((Test) clazz.getDeclaredConstructor().newInstance());
                            }
                        } catch (ClassNotFoundException e) {
                            System.out.println("Класс не найден: " + className);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                System.out.println("Не удается найти директорию с классами!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Test> getTestList() {
        return testList;
    }
}
