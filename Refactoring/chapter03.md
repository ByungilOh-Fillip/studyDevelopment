# 리펙터링 3주차 
## 코드에서 나는 악취
### 1. 기이한 이름
- 함수 선언 바꾸기
- 변수 이름 바꾸기
- 필드 이름 바꾸기

이름을 이쁘게 (가독성 좋게) 변경한다.

```java
private static final String KEY_VALUE = "traffic:info"; 
// KEY_INFO -> KEY_VALUE로 변수 명 변경
```

redis를 이용하는 함수끼리의 충돌로 에러가 나는 상황이 있어 교체
이용 시 컨벤션 및 변수 공유 문서가 필요할 거 같다.

### 2. 중복 코드 
코드가 중복 됐을 경우 통합해서 더 나은 코드가 되도록 리펙토링

- 함수 추출하기 
- 문장 슬라이스 하기
- 매서드 올리기

#### 예제
```java
public class ShippingCalculator {

    public double calculateShippingFee(String customerType, double amount) {
        if (customerType.equals("Regular")) {
            double fee = amount * 0.05;
            System.out.println("Shipping fee for Regular: " + fee);
            return fee;
        }

        if (customerType.equals("VIP")) {
            double fee = amount * 0.02;
            System.out.println("Shipping fee for VIP: " + fee);
            return fee;
        }

        if (customerType.equals("VVIP")) {
            double fee = amount * 0.0;
            System.out.println("Shipping fee for VVIP: " + fee);
            return fee;
        }

        throw new IllegalArgumentException("Unknown customer type: " + customerType);
    }
}

//고객의 등급에 따라 배송비를 계산
```

1. 함수를 새로 만들기
- 이름에 어떤 기능인지 직관적으로 들어나게
```java
    shippingFeeByRank(){

    }
```

2. 원본함수에서 추출할 코드 복사하기
```java
    shippingFeeByRank(){
        if (customerType.equals("Regular")) {
            double fee = amount * 0.05;
            System.out.println("Shipping fee for Regular: " + fee);
            return fee;
        }
        /**  Regular = 0.05
         *   VIP     = 0.02
         *   VVIP    = 0.00
        */
    }
```

3. 지역함수를 참조하거나 유효범위를 벗어나는 변수는 없는지 검사 있다면 매개변수로

```java
    shippingFeeByRank(String rank, double amount){
        if (customerType.equals("Regular")) {
            double fee = amount * 0.05;
            System.out.println("Shipping fee for Regular: " + fee);
            return fee;
        }
        /**
         * rank(cumstomerType) / amount
        */
    }
```

4. 변수 처리 후 컴파일 해보기 (컴파일 되는 언어의 경우)
- return 타입 및 코드 정리
```java
public double shippingFeeByRank(String rank, double amount){
        double fee=0;
        double feeByRank =0;

        if (rank.equals("Regular")) {
            feeByRank = 0.05;
        } else if (rank.equals("VIP")){
            feeByRank = 0.02;
        } else if (rank.equals("VVIP")){
            feeByRank = 0.00;
        }

        fee = amount * feeByRank;

        return fee;
    }
```

5. 추출한 코드 부분을 새로 만든 함수를 호출하는 문장에 넣어 바꾼다.
```java
public class ShippingCalculator {

    public double calculateShippingFee(String customerType, double amount) {
        double fee = shippingFeeByRank(customerType, amount);

        System.out.println("Shipping fee for " + customerType + ": " + fee);

        return fee;
    }

    public double shippingFeeByRank(String rank, double amount){
        double feeRate;

        if (rank.equals("Regular")) {
            feeRate = 0.05;
        } else if (rank.equals("VIP")) {
            feeRate = 0.02;
        } else if (rank.equals("VVIP")) {
            feeRate = 0.00;
        } else {
            throw new IllegalArgumentException("Unknown customer type: " + rank);
        }

        return amount * feeRate;
    }
}
```

6. 테스트
```java
@SpringJUnitConfig
@DisplayName("배송비 계산기 테스트")
class ShippingCalculatorTest {

    private ShippingCalculator shippingCalculator;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        shippingCalculator = new ShippingCalculator();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("일반 고객 배송비 계산 테스트")
    void calculateShippingFee_Regular_Customer() {
        // given
        String customerType = "Regular";
        double amount = 1000.0;
        double expectedFee = 50.0; // 1000 * 0.05

        // when
        double actualFee = shippingCalculator.calculateShippingFee(customerType, amount);

        // then
        assertEquals(expectedFee, actualFee, 0.01);
        assertTrue(outputStreamCaptor.toString().contains("Shipping fee for Regular: 50.0"));
    }

    @Test
    @DisplayName("VIP 고객 배송비 계산 테스트")
    void calculateShippingFee_VIP_Customer() {
        // given
        String customerType = "VIP";
        double amount = 1000.0;
        double expectedFee = 20.0; // 1000 * 0.02

        // when
        double actualFee = shippingCalculator.calculateShippingFee(customerType, amount);

        // then
        assertEquals(expectedFee, actualFee, 0.01);
        assertTrue(outputStreamCaptor.toString().contains("Shipping fee for VIP: 20.0"));
    }

    @Test
    @DisplayName("VVIP 고객 배송비 계산 테스트")
    void calculateShippingFee_VVIP_Customer() {
        // given
        String customerType = "VVIP";
        double amount = 1000.0;
        double expectedFee = 0.0; // 1000 * 0.00

        // when
        double actualFee = shippingCalculator.calculateShippingFee(customerType, amount);

        // then
        assertEquals(expectedFee, actualFee, 0.01);
        assertTrue(outputStreamCaptor.toString().contains("Shipping fee for VVIP: 0.0"));
    }

    @Test
    @DisplayName("알 수 없는 고객 타입 예외 테스트")
    void calculateShippingFee_Unknown_Customer_Type() {
        // given
        String customerType = "Premium";
        double amount = 1000.0;

        // when & then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> shippingCalculator.calculateShippingFee(customerType, amount)
        );

        assertEquals("Unknown customer type: Premium", exception.getMessage());
    }
}
```

7. 다른 같은 코드 찾기 => 바로 중복코드 3개를 전부 변경했기 때문에 생략
