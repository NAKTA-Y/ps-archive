## 문제풀이 시간

20분

## 성능 요약

메모리: 32600 KB

시간: 356 ms

## 문제 설명

n개의 서로 다른 양의 정수 a1, a2, ..., an으로 이루어진 수열이 있다. ai의 값은 1보다 크거나 같고, 1000000보다 작거나 같은 자연수이다. 자연수 x가 주어졌을 때, ai + aj = x (1 ≤ i < j ≤ n)을 만족하는 (ai, aj)쌍의 수를 구하는 프로그램을 작성하시오.

## 입력 조건

첫째 줄에 수열의 크기 n이 주어진다. 다음 줄에는 수열에 포함되는 수가 주어진다. 셋째 줄에는 x가 주어진다. (1 ≤ n ≤ 100000, 1 ≤ x ≤ 2000000)

## 입력

> 예제 입력 1
> 

```
9
5 12 7 10 9 1 2 3 11
13
```

## 출력

> 예제 출력 1
> 

```
3
```

## 문제 풀이

수열의 크기가 최대 100,000이기 때문에, 배열의 모든 두 수의 경우의 수를 탐색하는 O(n^2) 방법은 시간 초과가 날 것이기 때문에, 투 포인터 방법을 활용하기로 했다.

먼저 투 포인터를 이용하기 위해서는 배열을 정렬을 수행하고, 인덱스의 첫번째 자리와 마지막 자리에 인덱스 포인터를 위치시키고 목표값을 찾는다.

두 개의 포인터가 가르키는 배열의 요소 값의 합이 목표값보다 크다면 오른쪽 자리의 포인터를 왼쪽으로 한칸 이동시키고,

목표값보다 작다면 왼쪽 자리의 포인터를 오른쪽으로 한칸 이동시키고,

목표값과 같다면, 왼쪽, 오른쪽 포인터를 모두 한칸 이동시킨다.

이러한 과정을 두 개의 포인터가 엇갈리지 않을 때 까지 반복한다.

## 테스트케이스

> 테스트케이스 1
> 

```
10
1 2 3 4 5 6 7 8 9 10
20
```

## 코드

```java
import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int result = 0;

            // 입력값 받기
            int len = Integer.parseInt(br.readLine());
            int[] numbers = new int[len];

            // to int array
            String[] numberStr = br.readLine().split(" ");
            for (int i = 0; i < len; i++) {
                numbers[i] = Integer.parseInt(numberStr[i]);
            }

            int target = Integer.parseInt(br.readLine());

            // 배열 정렬 O(nlogn)
            Arrays.sort(numbers);

            // 투 포인터 준비
            int left = 0;
            int right = numbers.length - 1;

            // 포인터가 엇갈리지 않을 떄까지 O(n)
            while (left < right) {
                if (numbers[left] + numbers[right] == target) {
                    left++;
                    right--;
                    result++;
                } else if (numbers[left] + numbers[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }

            System.out.println(result);
        }
    }
}
```

## 또 다른 문제 풀이

O(nlogn) 방식이 아닌 O(n) 방식으로 푸는 방법이 있다.

다만, 이 방법은 공간복잡도가 O(1000000)으로 O(n)보다 크다.

입력값을 배열에 저장하고, 그 입력값을 인덱스로 가지는 boolean형 배열을 만든다.

해당 입력값이 있는지 판별하기 위해서 입력값을 그대로 저장한 배열을 탐색하며 [목표값 - 배열 요소]를 boolean형 배열에 있는지 없는지를 판별하고 경우의 수를 더하는 방식이다.

### 코드

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int result = 0;
            int[] numbers = new int[100001];
            boolean[] exists = new boolean[1000001];

            // 입력값 받기
            int len = Integer.parseInt(br.readLine());

            // to int array
            String[] numberStr = br.readLine().split(" ");
            for (int i = 0; i < len; i++) {
                numbers[i] = Integer.parseInt(numberStr[i]);
                exists[numbers[i]] = true;
            }

            int target = Integer.parseInt(br.readLine());

            for (int i = 0; i < len; i++) {
                exists[numbers[i]] = false;
                int pair = target - numbers[i];
                if (pair > 0 && pair <= 1_000_000 && exists[pair]) {
                    result++;
                }
            }

            System.out.println(result);
        }
    }
}
```
