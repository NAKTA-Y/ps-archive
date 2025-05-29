## 문제풀이 시간

37분

## 성능 요약

메모리: 137268 KB

시간: 1064 ms

## 문제 설명

KOI 통신연구소는 레이저를 이용한 새로운 비밀 통신 시스템 개발을 위한 실험을 하고 있다. 실험을 위하여 일직선 위에 N개의 높이가 서로 다른 탑을 수평 직선의 왼쪽부터 오른쪽 방향으로 차례로 세우고, 각 탑의 꼭대기에 레이저 송신기를 설치하였다. 모든 탑의 레이저 송신기는 레이저 신호를 지표면과 평행하게 수평 직선의 왼쪽 방향으로 발사하고, 탑의 기둥 모두에는 레이저 신호를 수신하는 장치가 설치되어 있다. 하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신이 가능하다.

예를 들어 높이가 6, 9, 5, 7, 4인 다섯 개의 탑이 수평 직선에 일렬로 서 있고, 모든 탑에서는 주어진 탑 순서의 반대 방향(왼쪽 방향)으로 동시에 레이저 신호를 발사한다고 하자. 그러면, 높이가 4인 다섯 번째 탑에서 발사한 레이저 신호는 높이가 7인 네 번째 탑이 수신을 하고, 높이가 7인 네 번째 탑의 신호는 높이가 9인 두 번째 탑이, 높이가 5인 세 번째 탑의 신호도 높이가 9인 두 번째 탑이 수신을 한다. 높이가 9인 두 번째 탑과 높이가 6인 첫 번째 탑이 보낸 레이저 신호는 어떤 탑에서도 수신을 하지 못한다.

탑들의 개수 N과 탑들의 높이가 주어질 때, 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지를 알아내는 프로그램을 작성하라.

## 조건

첫째 줄에 탑의 수를 나타내는 정수 N이 주어진다. N은 1 이상 500,000 이하이다. 둘째 줄에는 N개의 탑들의 높이가 직선상에 놓인 순서대로 하나의 빈칸을 사이에 두고 주어진다. 탑들의 높이는 1 이상 100,000,000 이하의 정수이다.

첫째 줄에 주어진 탑들의 순서대로 각각의 탑들에서 발사한 레이저 신호를 수신한 탑들의 번호를 하나의 빈칸을 사이에 두고 출력한다. 만약 레이저 신호를 수신하는 탑이 존재하지 않으면 0을 출력한다.

## 입력

> 예제 입력 1
> 

```
5
6 9 5 7 4
```

## 출력

> 예제 출력 1
> 

```
0 0 2 2 4
```

## 문제 풀이

전형적인 스택 활용 문제이다.

문제에서 원하는건 레이저를 일직선으로 왼쪽으로 쐈을 때 해당 신호를 받는 타워의 위치를 나타내야 하기 때문에, 우선 ‘Tower’ 클래스를 만들어 위치 속성을 가지게 했다.

그리고. 각 타워를 순회하면서 스택이 비어있거나 스택 최상단 값보다 큰지 비교하고, 만약 해당 타워의 높이보다 스택 최상단 타워의 높이가 낮다면, pop을 수행하고 이를 반복한다.

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
5 1 2 3 4
```

## 코드

```kotlin
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class Tower (val position: Int, val height: Int)

fun main() {
    BufferedReader(InputStreamReader(System.`in`)).use {
        val n = readln().toInt()

        val towerHeights = readln().split(" ").map { it.toInt() }
        val towers = ArrayList<Tower>()
        for (i in 0 until n) {
            towers.add(Tower(i+1, towerHeights[i]))
        }

        val result = StringBuilder()
        val stack: Stack<Tower> = Stack()

        for (tower in towers) {
            while (stack.isNotEmpty() && tower.height > stack.peek().height) {
                stack.pop()
            }

            if (stack.isEmpty()) result.append(0).append(" ")
            else result.append(stack.peek().position).append(" ")
            stack.push(tower)
        }

        println(result)
    }
}
```
