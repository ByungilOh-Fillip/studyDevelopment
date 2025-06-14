# 리펙터링 1주차 

## 코드 리뷰 사항에서 리펙토링 기법 찾아보기

``` java
// TrafficService
 private static final String TRAFFIC_REDIS_KEY = "traffic:info";

    private final CustomTrafficRepositoryImpl customTrafficRepository;
    private final TrafficRedisRepository trafficRedisRepository;
    private final TrafficRepository trafficRepository;
    private final RedisTemplate<Object, Object> redisTemplate;

    public List<TrafficResponse> searchAndSaveTraffic(Double lat, Double lng, int radius){

        log.debug("주변 보행등 정보 - lat = {}, lng = {}, radius = {}", lat, lng, radius);
        List<TrafficResponse> responseDB;

        boolean exists = Boolean.TRUE.equals(redisTemplate.hasKey("traffic:info"));
        boolean exists = Boolean.TRUE.equals(redisTemplate.hasKey(TRAFFIC_REDIS_KEY));

```

### 받은 리뷰 사항
@DongminL
TrafficRedisRepository로 Redis 로직을 분리하셨는데, 다시 TrafficService에 Redis 로직을 넣는 건 분리한 의미가 사라지는 것 같아요!

## 코드 리뷰를 토대로 변경한 코드 

```java
// TrafficService
    private final CustomTrafficRepositoryImpl customTrafficRepository;
    private final TrafficRedisRepository trafficRedisRepository;
    private final TrafficRepository trafficRepository;

    public List<TrafficResponse> searchAndSaveTraffic(Double lat, Double lng, int radius){

        log.debug("주변 보행등 정보 - lat = {}, lng = {}, radius = {}", lat, lng, radius);
        List<TrafficResponse> responseDB;

        if (trafficRedisRepository.isExist()) {
            double kiloRadius = (double) radius/1000;
            List<TrafficResponse> responseRedis = trafficRedisRepository.findNearbyTraffics(lat, lng, kiloRadius);

            log.debug("redis 주변 보행등 데이터 : redis data 갯수 = {} ", responseRedis.size());
            return responseRedis;
        }

// TrafficRedisRepository
    public boolean isExist(){
        return hashOperations.hasKey(KEY_HASH, KEY_GEO);
    }
```

### 변경한 단계 - 리펙토링 2판 개념 기준으로 서술 

1. 우변을 함수로 추출 
``` java
   boolean exists = Boolean.TRUE.equals(redisTemplate.hasKey("traffic:info")); // 우변 추출
```

2. 변수 인라인 + 함수 선언 바꾸기 -> 함수 생성하는 것으로 대체 
```java
    //boolean을 리턴하는 함수 생성
    public boolean isExist(){
        return hashOperations.hasKey(KEY_HASH, KEY_GEO);
    }
```

3. 변경된 내용으로 적용
```java

    // edis 직접 접근 삭제 및 isExist함수 사용
    private final CustomTrafficRepositoryImpl customTrafficRepository;
    private final TrafficRedisRepository trafficRedisRepository;
    private final TrafficRepository trafficRepository;

    public List<TrafficResponse> searchAndSaveTraffic(Double lat, Double lng, int radius){

        log.debug("주변 보행등 정보 - lat = {}, lng = {}, radius = {}", lat, lng, radius);
        List<TrafficResponse> responseDB;

        if (trafficRedisRepository.isExist()) { // isExist함수 사용
            double kiloRadius = (double) radius/1000;
            List<TrafficResponse> responseRedis = trafficRedisRepository.findNearbyTraffics(lat, lng, kiloRadius);

            log.debug("redis 주변 보행등 데이터 : redis data 갯수 = {} ", responseRedis.size());
            return responseRedis;
        }
```

4. Test 코드 변경 사항 적용 

```java
    @Test
    @DisplayName("주변 보행등 정보 redis 테스트")
    void testSearchAndSaveTrafficRedisExists() {

        // Given
        when(trafficRedisRepository.findNearbyTraffics(lat, lng, 1.0)).thenReturn(expected);
        when(trafficRedisRepository.isExist()).thenReturn(true);

        // When
        List<TrafficResponse> result = trafficService.searchAndSaveTraffic(lat, lng, 1000);

        // Then
        assertThat(result).isEqualTo(expected);
    }
```


### 컴파일-테스트-커밋