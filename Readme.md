<H1>Spring boot database example</h1>

<h3>pom.xml</h3>

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
        <version>1.5.6.RELEASE</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>6.0.6</version>
     </dependency>
</dependencies>
```

<h3>Database connection properties</h3>

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/example?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=qwertyui
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect

spring.jpa.hibernate.ddl-auto=create

spring.jpa.show-sql=true
```

<h3>Declare entity</h3>

```java
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer horsePower;
    private String model;
    private String colour;
    private Integer year;
}
```

<h3>Declare controller</h3>

```java
@Repository
public interface CarDTOController extends CrudRepository<Car, Long> {
}
```

<h3>Main</h3>

```java
@SpringBootApplication
public class Main implements CommandLineRunner{

    @Autowired
    private CarDTOController carController;

    public static void main(String [] args){
        SpringApplication.run(Main.class, args);
    }

    public void run(String... strings) throws Exception {
        Car car = new Car();
        car.setColour("Grey");
        car.setHorsePower(105);
        car.setModel("VW golf 16");
        car.setYear(2048);

        carController.save(car);

        Iterable<Car> cars = carController.findAll();

        for(Car c : cars){
            System.out.println("Car:"+c.getModel()+" "+c.getYear()+" "+c.getHorsePower());
            c.setHorsePower(c.getHorsePower()+10);
        }

        carController.save(cars);

        cars = carController.findAll();

        for(Car c : cars){
            System.out.println("Car:"+c.getModel()+" "+c.getYear()+" "+c.getHorsePower());
        }

        //carController.delete(cars);

        cars = carController.findAll();

        for(Car c : cars){
            System.out.println("Car:"+c.getModel()+" "+c.getYear()+" "+c.getHorsePower());
        }
    }
}
```