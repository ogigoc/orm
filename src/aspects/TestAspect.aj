package aspects;

public aspect TestAspect {
    pointcut beforeTest() : call(public void entities.TestEntity.test());

    before(): beforeTest() {
        System.out.printf("pre testa\n");
    }
}
