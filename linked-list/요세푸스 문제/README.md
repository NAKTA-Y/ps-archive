## 문제풀이 시간

37분

## 성능 요약

메모리: 17840 KB

시간: 208 ms

## 문제 설명

요세푸스 문제는 다음과 같다.

1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(≤ N)가 주어진다. 이제 순서대로 K번째 사람을 제거한다. 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다. 이 과정은 N명의 사람이 모두 제거될 때까지 계속된다. 원에서 사람들이 제거되는 순서를 (N, K)-요세푸스 순열이라고 한다. 예를 들어 (7, 3)-요세푸스 순열은 <3, 6, 2, 7, 5, 1, 4>이다.

N과 K가 주어지면 (N, K)-요세푸스 순열을 구하는 프로그램을 작성하시오.

## 조건

첫째 줄에 N과 K가 빈 칸을 사이에 두고 순서대로 주어진다. (1 ≤ K ≤ N ≤ 5,000)

## 입력

> 예제 입력 1
> 

```
7 3
```

## 출력

> 예제 출력 1
> 

```
<3, 6, 2, 7, 5, 1, 4>
```

## 문제 풀이

문제를 보자마자 원형 연결리스트가 생각났다. 처음에는 LinkedList 내부에 구현되어 있는 ListIterator를 활용하여 커서를 구현하려고 했지만, ListIterator 내부에는 현재 요소 사이를 개념으로 구현되어 있기 때문에 값을 가져오는 로직이 구현되어 있지 않아 초반에 시간을 잡아먹었다.

직접 원형 연결리스트를 구현하고, 커서가 가르킨 이전 값을 가르키는 노드를 만들어 문제가 요구하는 로직대로 수행하면 해결된다.

## 테스트케이스

> 테스트케이스 1
> 

```
3 1
```

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Node {
    Node next;
    int value;

    Node(int value) {
        this.value = value;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Node head = new Node(1);
            Node cursor = head;
            Node previous = cursor;

            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);
            List<Integer> permutation = new ArrayList<>();

            for (int i = 2; i <= n; i++) {
                cursor.next = new Node(i);
                cursor = cursor.next;
            }
            previous = cursor;
            cursor.next = head;

            cursor = head;
            while (cursor != null) {
                // move
                for (int i = 0; i < k-1; i++) {
                    previous = cursor;
                    cursor = cursor.next;
                }

                // remove
                previous.next = cursor.next;
                permutation.add(cursor.value);
                if (cursor == cursor.next) cursor = null;
                else cursor = cursor.next;
            }

            String result = permutation.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "<", ">"));

            System.out.println(result);
        }
    }
}
```

## 또다른 문제풀이

자료구조 큐를 활용하여 풀이할 수 있다.

우선 1부터 N까지 수를 큐에 넣어놓고, K번째 요소를 삭제하기 위해서 K-1번째 까지 큐에서 꺼내서 다시 큐에 넣은 후 K번째 요소를 큐에서 꺼내 없애버린다면, 위의 코드보다 훨씬 가독성이 좋고 간결하게 나타낼 수 있다.

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);
            List<Integer> permutation = new ArrayList<>();
            Queue<Integer> queue = new ArrayDeque<>();

            for (int i = 1; i <= n; i++) {
                queue.add(i);
            }

            while (!queue.isEmpty()) {
                for (int i = 0; i < k-1; i++) {
                    queue.add(queue.poll());
                }
                permutation.add(queue.poll());
            }

            String result = permutation.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "<", ">"));

            System.out.println(result);
        }
    }
}
```
