## 문제풀이 시간

33분

## 성능 요약

메모리: 76344 KB

시간:  716 ms

## 문제 설명

히스토그램은 직사각형 여러 개가 아래쪽으로 정렬되어 있는 도형이다. 각 직사각형은 같은 너비를 가지고 있지만, 높이는 서로 다를 수도 있다. 예를 들어, 왼쪽 그림은 높이가 2, 1, 4, 5, 1, 3, 3이고 너비가 1인 직사각형으로 이루어진 히스토그램이다.

![image](https://github.com/user-attachments/assets/72712a83-0e98-4838-aa40-67c2f5339190)

히스토그램에서 가장 넓이가 큰 직사각형을 구하는 프로그램을 작성하시오.

## 조건

입력은 테스트 케이스 여러 개로 이루어져 있다. 각 테스트 케이스는 한 줄로 이루어져 있고, 직사각형의 수 n이 가장 처음으로 주어진다. (1 ≤ n ≤ 100,000) 그 다음 n개의 정수 h1, ..., hn (0 ≤ hi ≤ 1,000,000,000)가 주어진다. 이 숫자들은 히스토그램에 있는 직사각형의 높이이며, 왼쪽부터 오른쪽까지 순서대로 주어진다. 모든 직사각형의 너비는 1이고, 입력의 마지막 줄에는 0이 하나 주어진다.

각 테스트 케이스에 대해서, 히스토그램에서 가장 넓이가 큰 직사각형의 넓이를 출력한다.

## 입력

> 예제 입력 1
> 

```
7 2 1 4 5 1 3 3
4 1000 1000 1000 1000
0
```

## 출력

> 예제 출력 1
> 

```
8
4000
```

## 문제 풀이

```
문제 이해)
1. 너비가 1이고 높이가 다른 직사각형 배열이 주어진다.
2. 만들 수 있는 가장 큰 직사각형 넓이를 구해야한다.

알고리즘 유형)
스택

조건)
1 <= n (직사각형 갯수) <= 100_000
0 <= h (직사각형 높이) <= 1_000_000_000

답은 int 범위를 초과할 수 있음.

풀이 방법)
1. 값과 자신의 높이로 만들 수 있는 최대 갯수를 왼쪽, 오른쪽으로 나누어 할 수 있는 클래스 정의.
2. 스택을 비우고 왼쪽, 오른쪽 총 2번 탐색 O(n)
3. 각 요소를 탐색할 때 마다 스택이 비어있지 않고 자신의 높이보다 스택 최상단의 사각형 높이가 크거나 같다면 pop을 수행
4. pop을 수행할 때 마다, 탐색 중인 요소의 cnt 추가
5. 왼쪽, 오른쪽 cnt를 모두 구하고 각각의 요소를 탐색하여 value * (leftcnt + rightCnt - 1) 값이 제일 높은값 찾기

시뮬레이션)
2 1 4 5 1 3 3

왼쪽 탐색
[Square[0]] value = 2, leftCnt = 1, rightCnt = 1, stack = [2]
[Square[1]] value = 1, leftCnt = 2, rightCnt = 1, stack = [1]
[Square[2]] value = 4, leftCnt = 1, rightCnt = 1, stack = [1, 4]
[Square[3]] value = 5, leftCnt = 1, rightCnt = 1, stack = [1, 4, 5]
[Square[4]] value = 1, leftCnt = 5, rightCnt = 1, stack = [1]
[Square[5]] value = 3, leftCnt = 1, rightCnt = 1, stack = [1, 3]
[Square[6]] value = 3, leftCnt = 2, rightCnt = 1, stack = [1, 3]

오른쪽 탐색
[Square[6]] value = 3, leftCnt = 2, rightCnt = 1, stack = [3]
[Square[5]] value = 3, leftCnt = 1, rightCnt = 2, stack = [3]
[Square[4]] value = 1, leftCnt = 5, rightCnt = 3, stack = [1]
[Square[3]] value = 5, leftCnt = 1, rightCnt = 1, stack = [1, 5]
[Square[2]] value = 4, leftCnt = 1, rightCnt = 2, stack = [1, 4]
[Square[1]] value = 1, leftCnt = 2, rightCnt = 6, stack = [1]
[Square[0]] value = 2, leftCnt = 1, rightCnt = 1, stack = [1, 2]

Square[2]에 해당하는 값이 value = 4, leftCnt = 1, rightCnt = 2
value * (leftcnt + rightCnt - 1) = 8로 가장 큰 직사각형 넓이

```

## 테스트케이스

> 테스트케이스 1
> 

```
5 1 1 2 2 1
5 1 1 5 1 1
5 1 1 0 2 2
0
```

## 코드

```kotlin
import java.util.*
import kotlin.math.max

class Square (val value: Long, var leftCnt: Int, var rightCnt: Int)

fun main() {
    while (true) {
        val input = readln().split(" ").map { it.toInt() }
        val n = input[0]
        val squares = arrayOfNulls<Square>(n+1)
        val stack = ArrayDeque<Square>()
        var result = 0L

        // break condition
        if (n == 0) break

        // init
        for (i in 1..n) {
            squares[i] = Square(input[i].toLong(), 1, 1)
        }

        // calculate cnt from left
        for (i in 1..n) {
            val square = squares[i]!!
            while (stack.isNotEmpty() && stack.peekLast().value >= square.value) {
                val pollSquare = stack.pollLast()
                square.leftCnt += pollSquare.leftCnt
            }

            stack.addLast(square)
        }

        stack.clear()
        // calculate cnt from right
        for (i in n downTo 1) {
            val square = squares[i]!!
            while (stack.isNotEmpty() && stack.peekLast().value >= square.value) {
                val pollSquare = stack.pollLast()
                square.rightCnt += pollSquare.rightCnt
            }

            stack.addLast(square)
        }

        // calculate both cnt
        for (i in 1..n) {
            val square = squares[i]!!
            val area = square.value * (square.leftCnt + square.rightCnt - 1)
            result = max(result, area)
        }

        println(result)
    }
}

```

## 또다른 문제풀이

위의 문제를 분할정복 방식으로 풀이하는 방법이 있다.

binary search와 같이 가운데를 기준으로 왼쪽 오른쪽으로 나눠 너비가 1일 때 까지 분할을 진행하고, 분할 기준으로 왼쪽, 오른쪽, 가운데 겹치는 부분에 대하여 직사각형 넓이를 계산한다. 전체 과정을 돌 때, 두 개로 분할하는 과정은 총 2T(N/2)가 되고 면적 탐색 과정은 O(N) 이므로 시간복잡도는 O(NlogN)이 된다.

## 코드

```kotlin
import kotlin.math.max
import kotlin.math.min

fun main() {
    while (true) {
        val input = readln().split(" ").map { it.toLong() }
        val n = input[0].toInt()
        if (n == 0) break
        println(getArea(input, 1, n))
    }
}

fun getArea(histogram: List<Long>, lo: Int, hi: Int): Long {
    if (lo == hi) return histogram[lo]

    val mid = (lo + hi) / 2

    val leftArea = getArea(histogram, lo, mid)
    val rightArea = getArea(histogram, mid + 1, hi)

    var max = max(leftArea, rightArea)

    max = max(max, getMidArea(histogram, lo, hi, mid))

    return max
}

fun getMidArea(histogram: List<Long>, lo: Int, hi: Int, mid: Int): Long {
    var toLeft = mid
    var toRight = mid

    var height = histogram[mid]

    var maxArea = height

    while (lo < toLeft && toRight < hi) {

        if (histogram[toLeft - 1] < histogram[toRight + 1]) {
            toRight++
            height = min(height, histogram[toRight])
        } else {
            toLeft--
            height = min(height, histogram[toLeft])
        }

        maxArea = max(maxArea, height * (toRight - toLeft + 1))
    }

    while (toRight < hi) {
        toRight++
        height = min(height, histogram[toRight])
        maxArea = max(maxArea, height * (toRight - toLeft + 1))
    }

    while (lo < toLeft) {
        toLeft--
        height = min(height, histogram[toLeft])
        maxArea = max(maxArea, height * (toRight - toLeft + 1))
    }

    return maxArea
}
```
