## 문제풀이 시간

5분

## 성능 요약

메모리: 33544 KB

시간: 192 ms

## 문제 설명

N장의 카드가 있다. 각각의 카드는 차례로 1부터 N까지의 번호가 붙어 있으며, 1번 카드가 제일 위에, N번 카드가 제일 아래인 상태로 순서대로 카드가 놓여 있다.

이제 다음과 같은 동작을 카드가 한 장 남을 때까지 반복하게 된다. 우선, 제일 위에 있는 카드를 바닥에 버린다. 그 다음, 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.

예를 들어 N=4인 경우를 생각해 보자. 카드는 제일 위에서부터 1234 의 순서로 놓여있다. 1을 버리면 234가 남는다. 여기서 2를 제일 아래로 옮기면 342가 된다. 3을 버리면 42가 되고, 4를 밑으로 옮기면 24가 된다. 마지막으로 2를 버리고 나면, 남는 카드는 4가 된다.

N이 주어졌을 때, 제일 마지막에 남게 되는 카드를 구하는 프로그램을 작성하시오.

## 조건

첫째 줄에 정수 N(1 ≤ N ≤ 500,000)이 주어진다.

첫째 줄에 남게 되는 카드의 번호를 출력한다.

## 입력

> 예제 입력 1
> 

```
6
```

## 출력

> 예제 출력 1
> 

```
4
```

## 문제 풀이

```
문제 정리)
1. 차례대로 1부터 N까지의 카드
2. 1번 카드는 제일 위에, N번 카드는 제일 아래 순서
3. 카드가 한 장 남을 떄 까지 반복
4. 제일 위 카드 버림 -> 제일 위 카드 마지막으로 옮김

문제 풀이)
시뮬레이션 문제에 가까움
큐 자료구조에 최적화된 문제
```

## 테스트케이스

> 테스트케이스 1
> 

```
1
```

> 테스트케이스 2
> 

```
500000
```

## 코드

```kotlin
fun main() {
    val n = readln().toInt()
    val queue = ArrayDeque<Int>()

    for (i in 1..n) queue.addLast(i)

    while (queue.size != 1) {
        queue.removeFirst()
        queue.addLast(queue.removeFirst())
    }

    println(queue.removeFirst())
}
```
