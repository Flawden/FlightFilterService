# Flight Filter Service

Проект предоставляет гибкую систему фильтров для обработки и фильтрации рейсов по различным критериям. В проекте используется паттерн **фильтр**, что позволяет легко создавать новые фильтры для работы с рейсами, а также эффективно тестировать их с помощью универсального механизма запуска тестов.

## Описание

Этот проект позволяет легко добавлять новые фильтры для рейсов, создавая их как отдельные классы, реализующие интерфейс `Filter`. Благодаря этому можно легко комбинировать фильтры и запускать их все одновременно, что упрощает тестирование и интеграцию новых функциональностей.

### Основные компоненты:

- **Интерфейс Filter**: Это основной интерфейс для всех фильтров. Каждый фильтр должен реализовывать метод `filter(List<Flight> flights)`, который принимает список рейсов и возвращает отфильтрованный список.
  
- **Метод runAllTests**: Удобный механизм для запуска всех тестов для каждого фильтра в системе. Этот метод позволяет протестировать фильтры в комплексе, гарантируя, что все они работают правильно.

