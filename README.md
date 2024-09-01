# EEntitySelectors
>Для корректной работы необходимо использовать ядро [Paper](https://papermc.io/downloads/paper) или [Spigot](https://www.spigotmc.org/) версии не ниже 1.21

Данная библиотека позволит вам использовать комплексные операции с выбором целевых сущностей в командах консоли. Например, вы сможете выбрать всех игроков с количеством здоровья менее 50% или всех овец с именем "jeb_".
## Добавление зависимости
Для добавления зависимости в Gradle вам потребуется добавить репозиторий и зависимость в ваш `build.gradle`:
```groovy
repositories {  
    maven { url 'https://jitpack.io' }
}  
  
dependencies {  
	implementation 'com.github.Vladislav117:EEntitySelectors:-SNAPSHOT'
}
```
Если вы используете Maven, то вам потребуется добавить репозиторий и зависимость в ваш `pom.xml`:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
	<groupId>com.github.Vladislav117</groupId>
	<artifactId>EEntitySelectors</artifactId>
	<version>-SNAPSHOT</version>
</dependency>
```
## Принцип работы
Для объяснения принципа работы необходимо определить следующие термины:
- **Выборка** - множество сущностей, с которым проводятся операции
- **Операция** - действие, которое применяет к *выборке* некоторое подмножество сущностей (например, удаляет из *выборки* некоторое подмножество сущностей)
- **Источник** - источник, который выдаёт некоторое подмножество сущностей (например, сущности из конкретного мира)
- **Селектор** - определяет, подходит ли сущность из *источника*, или нет

Выборка состоит из шагов. Шаг выборки содержит операцию, источник и селектор. Выборка выполняет шаги поочерёдно.

Рассмотрим как пример один шаг выборки:
- Операция: **удаление**
- Источник: **все миры**
- Селектор: **здоровье меньше 50%**

Допустим, в *выборке* есть какие-либо сущности. Таким образом, *источник* выдаёт нам сущностей из всех миров. Из них *селектор* берёт только тех, у кого здоровье меньше 50%. Затем *операция* удаляет всех полученных сущностей из *выборки*.
### Запись выборки
Запись выборки проста: через запятую перечисляются шаги выборки:
```
<шаг1>,<шаг2>,<шаг3>
```
Шаги состоят из трёх частей:
```
<операция><источник><селектор>
```
Примеры записей будут указаны ниже.
## Виды операций, источников и селекторов
> Всё, указанное в этом разделе используется в среде `StandardEEntitySelectorsEnvironment` из пакета `ru.vladislav117.eentityselectors.standardcollection.environments`. Если вы создаёте свою среду, то ваш синтаксис и лексика могут отличаться.

### Стандартные операции
- `+` - добавление подмножества сущностей в выборку
- `-` - удаление подмножества сущностей из выборки
- `&` - пересечение подмножества сущностей и выборки (останутся только те сущности, которые есть и в выборке, и в подмножестве)
### Стандартные источники
- `@` - сущности из всех миров
- `#` - сущности из мира инициатора (того, кто отправил команду)
- `/` - сущности из выборки
### Стандартные селекторы
Селекторы можно подразделить на несколько видов:
- Фиксированные - записываются одним словом и не могут быть сконфигурированы. Например, селектор `players`, который выберет только игроков.
- Текстовые свойства - проверяют некоторое свойство сущности и сравнивают его с указанным(и) в селекторе. Например, селектор `name=Vladislav117` который выберет только сущностей с именем "Vladislav117". Или же селектор `type=player|pig|zombie`, который выберет только игроков, свиней и зомби.
- Числовые свойства - проверяют некоторые свойства сущности и сравнивают его с указанным по указанному знаку. Например, селектор `health<10` выберет сущностей с количеством здоровья меньше 10. Так же числовые свойства поддерживают процентные соотношения - селектор `health>50%` выберет всех сущностей, у которых больше половины здоровья. Все операции сравнения указаны ниже.

Операции сравнения числовых свойств (в качестве свойства выбрано `health` - здоровье сущности):
- `health<10` - здоровье сущности должно быть меньше 10
- `health<=10`- здоровье сущности должно быть меньше или равно 10
- `health>10`- здоровье сущности должно быть больше 10
- `health>=10`- здоровье сущности должно быть больше или равно 10
- `health=10`- здоровье сущности должно быть равно 10
- `health!=10`- здоровье сущности должно быть не равно 10
- `health><5;10`- здоровье сущности должно быть от 5 до 10
- `health<>5;10`- здоровье сущности должно быть меньше 5 или больше 10

Все указанные операции сравнения применимы и к процентным соотношениям, например `health>50%`, `health><33%,66%`.

Селекторы (у некоторых есть синонимы):
- `e` (`any`) - любая сущность
- `le` (`living`) - "живая" сущность (LivingEntity)
- `players` - игроки
- `type=...` - текстовое свойство, тип сущности. Не чувствительно к регистру. Примеры: `type=cow`, `type=zombie|skeleton|spider`
- `name=...` - текстовое свойство, имя сущности. Не чувствительно к регистру. Примеры: `name=Vladislav117`, `name=Vladislav117|Wikomir|BLAB2`
- `x...` - числовое свойство, x-координата сущности. Примеры: `x<0`, `x><-32;32`
- `y...` - числовое свойство, y-координата сущности. Примеры: `y<0`, `y><-32;32`
- `z...` - числовое свойство, z-координата сущности. Примеры: `z<0`, `z><-32;32`
- `health...` - числовое свойство, здоровье сущности. Примеры: `health<50%`, `health=10`
- `fire...` - числовое свойство, количество тиков огня. Примеры: `fire>0`, `fire!=0`
- `freeze...` - числовое свойство, количество тиков заморозки. Примеры: `freeze>0`, `freeze!=0`
- `passengers...` - числовое свойство, количество пассажиров на сущности. Примеры: `passengers>0`, `passengers!=0`

### Значение по умолчанию
- Если вы не указали операцию, то будет выбрана операция добавления
- Если вы не указали источник, то будут выбраны сущности из всех миров
- Если вы не указали селектор, то будет выбран ник игрока
## Примеры выборок
```
#any,-/players
```
В этой выборке берутся все сущности из мира инициатора, а затем из неё убираются все игроки.

```
type=cow|pig,&health>50%
```
В этой выборке берутся все коровы и свиньи во всех мирах, а затем остаются только те, у кого больше половины здоровья

```
Vladislav117
```
В этой выборке берётся игрок с ником Vladislav117

```
#type=zombie|skeleton,fire>0
```
В этой выборке берутся зомби и скелеты из мира инициатора и остаются только те, которые горят.
## Свои операции, источники и селекторы
Добавлять свои операции, источники и селекторы можно в созданные **среды выполнения** (`EEntitySelectorsEnvironment`) через методы `add()`

Пример добавления операции, источника и селектора:
```java
EEntitySelectorsEnvironment environment = new EEntitySelectorsEnvironment();  
  
// Операция "?", которая добавляет сущности селектора в выборку с шансом 50%.  
environment.add(new SimplePrefixOperationType("your_operation_id", "?", new Operation() {  
    @Override  
    public EntitySet apply(EntitySet entities, EntitySet operand, Selection selection) {  
        if (new Random().nextBoolean()) entities.add(operand);  
        return entities;  
    }  
}));  
  
// Источник "$", который даёт сущностей в мире "world".  
environment.add(new SimplePrefixSourceType("your_source_id", "$", new Source() {  
    @Override  
    public @Nullable EntitySet getEntities(Selection selection) {  
        return new EntitySet(Bukkit.getWorld("world").getEntities());  
    }  
}));  
  
// Селектор "admin", выбирает только тех сущностей, которые являются оператором.  
environment.add(new SimpleWordSelectorType("your_selector_id", "admin", new Selector() {  
    @Override  
    public @Nullable Boolean isSuitable(Entity entity, Selection selection) {  
        return entity.isOp();  
    }  
}));
```
Подробнее вы можете узнать в документации кода.