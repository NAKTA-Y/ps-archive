## 문제풀이 시간

40분

## 성능 요약

메모리: 25568 KB

시간: 152 ms

## 문제 설명

```
지민이는 N개의 원소를 포함하고 있는 양방향 순환 큐를 가지고 있다. 지민이는 이 큐에서 몇 개의 원소를 뽑아내려고 한다.

지민이는 이 큐에서 다음과 같은 3가지 연산을 수행할 수 있다.

첫 번째 원소를 뽑아낸다. 이 연산을 수행하면, 원래 큐의 원소가 a1, ..., ak이었던 것이 a2, ..., ak와 같이 된다.
왼쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 a2, ..., ak, a1이 된다.
오른쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 ak, a1, ..., ak-1이 된다.
큐에 처음에 포함되어 있던 수 N이 주어진다. 그리고 지민이가 뽑아내려고 하는 원소의 위치가 주어진다. (이 위치는 가장 처음 큐에서의 위치이다.) 이때, 그 원소를 주어진 순서대로 뽑아내는데 드는 2번, 3번 연산의 최솟값을 출력하는 프로그램을 작성하시오.
```

## 조건

첫째 줄에 큐의 크기 N과 뽑아내려고 하는 수의 개수 M이 주어진다. N은 50보다 작거나 같은 자연수이고, M은 N보다 작거나 같은 자연수이다. 둘째 줄에는 지민이가 뽑아내려고 하는 수의 위치가 순서대로 주어진다. 위치는 1보다 크거나 같고, N보다 작거나 같은 자연수이다.

첫째 줄에 문제의 정답을 출력한다.

## 입력

> 예제 입력 1
> 

```
10 3
1 2 3
```

> 예제 입력 2
> 

```
10 3
2 9 5
```

> 예제 입력 3
> 

```
32 6
27 16 30 11 6 23
```

> 예제 입력 4
> 

```
10 10
1 6 3 2 7 9 8 4 10 5
```

## 출력

> 예제 출력 1
> 

```
0
```

> 예제 출력 2
> 

```
8
```

> 예제 출력 3
> 

```
59
```

> 예제 출력 4
> 

```
14
```

## 문제 풀이

```
문제 이해)
1. 양방향 순환 큐를 가지고 있다 (deque)
2. 3가지 연산을 수행할 수 있다. 1개 뽑기, 왼쪽 이동, 오른쪽 이동
3. 주어진 원소를 순서대로 뽑아내는 데 드는 왼쪽 이동, 오른쪽 이동 최솟값 출력
4. 1 부터 N까지 순서대로 담겨있는 상태

문제 풀이)
특정 원소를 가지고 올 때, 왼쪽이 가까운지 오른족이 가까운지 알아내는건 힘들다고 결론지었다.
따라서, 답을 구하기 위해서는 그리디 방식으로 데크 양쪽 값을 비교해서 다음 연산을 따랐다.
왼쪽에 있다면, 오른쪽 데크에 있는 값을 다시 넣어주고, 왼쪽 데크에 있는 값을 오른쪽에 넣어주면서 +1씩
오른쪽에 있다면, 왼쪽 데크에 있는 값을 다시 넣어주고, 오른쪽 데크에 있는 값을 왼쪽에 넣어주면서 +1씩하고, 최종적으로 +1
없다면 왼쪽, 오른쪽 값 둘 다 넣기
```

## 테스트케이스

> 테스트케이스 1
> 

```
10 7
2 3 4 10 9 8 5
```

> 테스트케이스 2
> 

```
5 5
5 4 3 2 1
```

## 코드

```kotlin
fun main() {
    val (n, _) = readln().split(" ").map { it.toInt() }
    val sequence = readln().split(" ").map { it.toInt() }
    val deque = ArrayDeque<Int>()
    val leftDeque = ArrayDeque<Int>()
    val rightDeque = ArrayDeque<Int>()
    var result = 0

    for (i in 1..n) deque.addLast(i)

    for (i in sequence) {
        while (true) {
            when (i) {
                // 왼쪽에 있다면, 오른쪽 데크에 있는 값을 다시 넣어주고, 왼쪽 데크에 있는 값을 오른쪽에 넣어주면서 +1씩
                deque.first() -> {
                    deque.removeFirst()

                    while (rightDeque.isNotEmpty()) {
                        deque.addLast(rightDeque.removeLast())
                    }

                    while (leftDeque.isNotEmpty()) {
                        deque.addLast(leftDeque.removeFirst())
                        result++
                    }

                    break
                }

                // 오른쪽에 있따면, 왼쪽 데크에 있는 값을 다시 넣어주고, 오른쪽 데크에 있는 값을 왼쪽에 넣어주면서 +1씩하고, 최종적으로 +1
                deque.last() -> {
                    deque.removeLast()
                    result++

                    while (leftDeque.isNotEmpty()) {
                        deque.addFirst(leftDeque.removeLast())
                    }

                    while (rightDeque.isNotEmpty()) {
                        deque.addFirst(rightDeque.removeFirst())
                        result++
                    }

                    break
                }

                // 없다면 왼쪽, 오른쪽 값 둘 다 넣기
                else -> {
                    leftDeque.addLast(deque.removeFirst())
                    rightDeque.addLast(deque.removeLast())
                }
            }
        }

    }

    println(result)
}
```

## 또다른 문제풀이

위의 방법은 데크를 3개나 써서 코드가 길어지고 중복 코드가 많아져서 코드 가독성이 낮아진다는 단점이 있다. 이를 해결하기 위해서 다른 그리디 방식으로 왼쪽 값만 뽑아내서 비교하여 원하는 값이 나오면 왼쪽이 가까운지 오른쪽이 가까운지 알아내는 방법으로 데크를 1개만 써서 풀이가 가능하다.

## 코드

```kotlin
import kotlin.math.min

fun main() {
    val (n, _) = readln().split(" ").map { it.toInt() }
    val sequence = readln().split(" ").map { it.toInt() }
    val deque = ArrayDeque<Int>()
    var result = 0

    for (i in 1..n) deque.addLast(i)

    for (i in sequence) {
        var move = 0
        while (true) {
            if (deque.first() == i) {
                result += min(move, deque.size - move)
                deque.removeFirst()
                break
            }

            deque.addLast(deque.removeFirst())
            move++
        }
    }

    println(result)
}
```
