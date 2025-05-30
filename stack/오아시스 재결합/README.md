## 문제풀이 시간

46분

## 성능 요약

메모리: 62132 KB

시간: 444 ms

## 문제 설명

오아시스의 재결합 공연에 N명이 한 줄로 서서 기다리고 있다.

이 역사적인 순간을 맞이하기 위해 줄에서 기다리고 있던 백준이는 갑자기 자기가 볼 수 있는 사람의 수가 궁금해졌다.

두 사람 A와 B가 서로 볼 수 있으려면, 두 사람 사이에 A 또는 B보다 키가 큰 사람이 없어야 한다.

줄에 서 있는 사람의 키가 주어졌을 때, 서로 볼 수 있는 쌍의 수를 구하는 프로그램을 작성하시오.

## 조건

첫째 줄에 줄에서 기다리고 있는 사람의 수 N이 주어진다. (1 ≤ N ≤ 500,000)

둘째 줄부터 N개의 줄에는 각 사람의 키가 나노미터 단위로 주어진다. 모든 사람의 키는 231 나노미터 보다 작다.

사람들이 서 있는 순서대로 입력이 주어진다.

서로 볼 수 있는 쌍의 수를 출력한다.

## 입력

> 예제 입력 1
> 

```
7
2
4
1
2
2
5
1
```

## 출력

> 예제 출력 1
> 

```
10
```

## 문제 풀이

스택 활용 문제로 같은 키의 사람의 경우의 수를 잘 고려해야 하는 문제였다. 입력 받은 순서대로 스택이 비어있지 않고, 스택 최상단 값이 해당 사람의 키보다 크거나 같으면 pop을 해주는 형식인데, 여기서 해당 사람의 키와 같은 사람들의 수를 따로 저장해두어야 한다.

그 이유는  **두 사람 A와 B가 서로 볼 수 있으려면, 두 사람 사이에 A 또는 B보다 키가 큰 사람이 없어야 한다.** 라는 조건을 살펴봤을 때, 이전에 같은 키의 사람을 모두 pop한 상태일 때, 이후에 같은 키의 사람이 입력값으로 주어진다면 이전의 같은 키의 사람들의 수 만큼 볼 수 있는데도 불구하고 그 값을 알아내기 어렵기 때문에 ‘Human’ 이라는 클래스를 새로 정의해서 이전의 같은 키의 사람의 수를 따로 저장해서 답을 얻어낼 수 있었다.

그리고 조건에서 N의 범위는 **(1 ≤ N ≤ 500,000)** 이기 때문에, 모두 같은 키를 가지는 조건이라면, 경우의 수는 1 ~ 499,999를 모두 합한 값인 124,999,750,000 이기 때문에 int 범위를 초과하므로 주의해야한다.

## 테스트케이스

> 테스트케이스 1
> 

```
5
1
1
1
1
1
```

> 테스트케이스 1
> 

```
5
1
2
3
4
5
```

> 테스트케이스 1
> 

```
5
5
1
5
3
5
```

> 테스트케이스 1
> 

```
10
10
9
1
3
8
6
7
8
5
8
```

## 코드

```kotlin
import java.util.ArrayDeque

class Human (val height: Int, var sameHeightCount: Int)

fun main() {
    val n = readln().toInt()
    val stack = ArrayDeque<Human>()
    var result = 0L

    repeat(n) {
        val height = readln().toInt()
        val human = Human(height, 1)

        while (stack.isNotEmpty() && height >= stack.peekLast().height) {
            if (height == stack.peekLast().height)
                human.sameHeightCount += stack.peekLast().sameHeightCount

            result += stack.peekLast().sameHeightCount
            stack.pollLast()
        }

        if (stack.isNotEmpty()) result ++

        stack.addLast(human)
    }

    println(result)
}
```
