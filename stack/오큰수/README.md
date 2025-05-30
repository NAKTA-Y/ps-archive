## 문제풀이 시간

23분

## 성능 요약

메모리: 282960 KB

시간: 1084 ms

## 문제 설명

크기가 N인 수열 A = A1, A2, ..., AN이 있다. 수열의 각 원소 Ai에 대해서 오큰수 NGE(i)를 구하려고 한다. Ai의 오큰수는 오른쪽에 있으면서 Ai보다 큰 수 중에서 가장 왼쪽에 있는 수를 의미한다. 그러한 수가 없는 경우에 오큰수는 -1이다.

예를 들어, A = [3, 5, 2, 7]인 경우 NGE(1) = 5, NGE(2) = 7, NGE(3) = 7, NGE(4) = -1이다. A = [9, 5, 4, 8]인 경우에는 NGE(1) = -1, NGE(2) = 8, NGE(3) = 8, NGE(4) = -1이다.

## 조건

첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄에 수열 A의 원소 A1, A2, ..., AN (1 ≤ Ai ≤ 1,000,000)이 주어진다.

총 N개의 수 NGE(1), NGE(2), ..., NGE(N)을 공백으로 구분해 출력한다.

## 입력

> 예제 입력 1
> 

```
4
3 5 2 7
```

> 예제 입력 2
> 

```
4
9 5 4 8
```

## 출력

> 예제 출력 1
> 

```
5 7 7 -1
```

> 예제 출력 2
> 

```
-1 8 8 -1
```

## 문제 풀이

오른쪽에 있으면서 자신의 원소보다 큰 수에 대해서 기록을 하는 것이므로, 간단하게 입력 받았던 값의 역순으로 탐색을 진행하면서 스택에 값이 비어있지 않고 자신의 값보다 스택 최상단에 있는 값이 더 작다면 pop을 해주는 방식으로 풀이를 진행한다면 자신보다 큰 수가 스택 내에 존재하지 않거나 최상단의 값이 자신의 값보다 큰 수 이므로 값을 기입해주면 답을 구할 수 있다.

## 테스트케이스

> 테스트케이스 1
> 

```
5
1 2 3 4 5
```

> 테스트케이스 2
> 

```
5
5 4 3 2 1
```

> 테스트케이스 3
> 

```
5
1 1 1 1 1
```

## 코드

```java
import java.util.ArrayDeque

fun main() {
    val n = readln().toInt()
    val sequence = readln().split(" ").map { it.toInt() }
    val stack = ArrayDeque<Int>()
    val result = IntArray(n)

    for (i in n-1 downTo 0) {
        while (stack.isNotEmpty() && sequence[i] >= stack.peekLast()) {
            stack.pollLast()
        }

        if (stack.isEmpty()) result[i] = -1
        else result[i] = stack.peekLast()

        stack.addLast(sequence[i])
    }

    println(result.joinToString(" "))
}
```
