## 문제풀이 시간

70분

## 성능 요약

메모리: 715404 KB

시간: 2544 ms

## 문제 설명

```
N개의 수 A1, A2, ..., AN과 L이 주어진다.

Di = Ai-L+1 ~ Ai 중의 최솟값이라고 할 때, D에 저장된 수를 출력하는 프로그램을 작성하시오. 이때, i ≤ 0 인 Ai는 무시하고 D를 구해야 한다.
```

## 조건

첫째 줄에 N과 L이 주어진다. (1 ≤ L ≤ N ≤ 5,000,000)

둘째 줄에는 N개의 수 Ai가 주어진다. (-109 ≤ Ai ≤ 109)

첫째 줄에 Di를 공백으로 구분하여 순서대로 출력한다.

## 입력

> 예제 입력 1
> 

```
12 3
1 5 2 3 6 2 3 7 3 5 2 6
```

## 출력

> 예제 출력 1
> 

```
1 1 1 2 2 2 2 2 3 3 2 2
```

## 문제 풀이

문제 이해)

1. N개의 수가 주어진다.
2. D(i) = A(i-L+1) ~ A(i) 구간 중 최솟값을 출력한다

위 문제는 슬라이딩 윈도우로 지정된 크기의 윈도우를 한 칸씩 이동하며 최솟값을 출력하는 문제이다. 슬라이딩 윈도우 크기가 커지면 커질수록 내부에 들어가는 값이 많아지므로, 최솟값을 구하기 까다로워져서 난항을 겪어 시간을 많이 소모했다.

풀이를 봤을 때, 인덱스 값을 저장한다면 아주 간단하게 풀릴 문제라는 것을 알았다. 슬라이딩 윈도우에 (인덱스, 값)을 쌍으로 저장하는 방식으로 풀이를 할 수 있다.

여기서 인덱스를 보는 이유는 슬라이딩 윈도우 크기 범위를 벗어나는지 확인하기 위해서이다. 배열을 선형적으로 탐색하면서 매번 peekFirst로 인덱스를 확인 하여 해당 값이 범위를 벗어났는지 아닌지에 따라 pop을 결정하게 된다.

예를 들어서,L = 3이고 i = 3일 때, 인덱스가 0인 값은 범위를 벗어나게 되므로 조건은 i - peek.First.index ≥ L 을 만족해야 한다.

그리고 슬라이딩 윈도우 내부에는 해당 범위의 최솟값 후보만 두기 위해서 while문 조건으로 슬라이딩 윈도우 내부 값보다 탐색 중인 값이 더 작으면 모두 pop을 수행한다.

## 테스트케이스

> 테스트케이스 1
> 

```
10 1
1 2 3 4 5 6 7 8 9 10
```

> 테스트케이스 2
> 

```
10 10
1 2 3 4 5 6 7 8 9 1
```

> 테스트케이스 3
> 

```
10 10
10 9 8 7 6 5 4 3 2 1
```

## 코드

```kotlin
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.isNotEmpty

private data class Node (val index: Int, val value: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val L = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val deque = ArrayDeque<Node>()

    for (index in 0 until N) {
        val value = st.nextToken().toInt()
        if (deque.isNotEmpty() && index - deque.first().index >= L) deque.removeFirst()

        while (deque.isNotEmpty() && deque.last().value > value) {
            deque.removeLast()
        }

        deque.addLast(Node(index, value))
        bw.write("${deque.first().value} ")
    }

    readln()

    br.close()
    bw.flush()
    bw.close()
}
```
