# Automation Project (TestNG | API Automation | Web Automation)

## Learning Path

### Day 1 - QnA

### Day 2 - TestNG

#### Anotation TestNG Lifecycle
```
@BeforeSuite
    @BeforeTest
        @BeforeClass
            @BeforeGroups
                @BeforeMethod
                    @Test
                @AfterMethod
            @AfterGroups
        @AfterClass
    @AfterTest
@AfterSuite
```

#### Data Provider
```src/test/java/testng/DataProviderTest.java```

#### Test Suite
```src/test/resources/testng_simple_suite.xml```
```src/test/resources/testng_simple_suite.xml```

#### Assertion
```src/test/java/testng/AssertTest.java```

#### IRetryAnalyzer
```src/test/java/testng/RetryTest.java```

reference: https://testng.org/

### Day 3 - RestAssured

#### Sample RestAssured + store data in DB
```src/test/java/api_test_integrate_db/EmployeeLoginApiTest.java```

#### POJO
```src/main/java/com/demo/testng/program/pojo/User.java```

#### DB Connection pool
```src/main/java/com/demo/testng/program/connection/PostgresConnectionPool.java```

#### DB Query
```sql
CREATE TABLE IF NOT EXISTS test_automation (
    id SERIAL PRIMARY KEY,
    test_id VARCHAR(255) NOT NULL UNIQUE,
    test_group VARCHAR(255) NOT NULL,
    endpoint VARCHAR(255) NOT NULL,
    http_method VARCHAR(10) NOT NULL,
    request_header_data_json TEXT NOT NULL,
    request_body_data_json TEXT NOT NULL,
    response_expectation_json TEXT NOT NULL,
    note TEXT,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS test_automation_test_group_idx ON public.test_automation (test_group);

CREATE TABLE IF NOT EXISTS test_user_automation (
    id SERIAL PRIMARY KEY,
    test_group VARCHAR(255) NOT NULL,
    email_user VARCHAR(255) NOT NULL,
    password_user VARCHAR(255) NOT NULL,
    note TEXT,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS test_user_automation_test_group_idx ON public.test_user_automation (test_group);

```