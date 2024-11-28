package test;

import test.annotation.postProcessor.TestManager;
import test.model.Test;

/**
 * В связи с тем, что в задании запрещено использовать сторонние библиотеки
 * фразой "Никаких сторонних библиотек использовать не нужно", но код тестами покрыть нужно,
 * Об этом говорит фраза: "Если знакомы с JUnit — покройте свой код тестами"
 * тесты будут выглядеть вот так.
 *
 * Все тесты проверяют работу фильтров, описанных в классе Filter. Используются базовые конструкции Java
 * и JUnit для проверки функциональности. Обратите внимание, что в задании не указано, какие именно
 * библиотеки можно использовать, поэтому для покрытия тестами мы используем стандартные возможности Java.
 */

public class TestLauncher {

    public static void main(String[] args) {
        TestManager.getTestList();

        for (Test test : TestManager.getTestList()) {
            System.out.println("Запуск теста: " + test.getClass().getSimpleName());
            test.test();
            System.out.println("Тест завершен: " + test.getClass().getSimpleName());
            System.out.println();
        }
    }
}
