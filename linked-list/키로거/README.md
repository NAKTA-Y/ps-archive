## 문제풀이 시간

30분

## 성능 요약

메모리: 133704 KB

시간: 480 ms

## 문제 설명

창영이는 강산이의 비밀번호를 훔치기 위해서 강산이가 사용하는 컴퓨터에 키로거를 설치했다. 며칠을 기다린 끝에 창영이는 강산이가 비밀번호 창에 입력하는 글자를 얻어냈다.

키로거는 사용자가 키보드를 누른 명령을 모두 기록한다. 따라서, 강산이가 비밀번호를 입력할 때, 화살표나 백스페이스를 입력해도 정확한 비밀번호를 알아낼 수 있다.

강산이가 비밀번호 창에서 입력한 키가 주어졌을 때, 강산이의 비밀번호를 알아내는 프로그램을 작성하시오. 강산이는 키보드로 입력한 키는 알파벳 대문자, 소문자, 숫자, 백스페이스, 화살표이다.

## 조건

첫째 줄에 테스트 케이스의 개수가 주어진다. 각 테스트 케이스는 한줄로 이루어져 있고, 강산이가 입력한 순서대로 길이가 L인 문자열이 주어진다. (1 ≤ L ≤ 1,000,000) 강산이가 백스페이스를 입력했다면, '-'가 주어진다. 이때 커서의 바로 앞에 글자가 존재한다면, 그 글자를 지운다. 화살표의 입력은 '<'와 '>'로 주어진다. 이때는 커서의 위치를 움직일 수 있다면, 왼쪽 또는 오른쪽으로 1만큼 움직인다. 나머지 문자는 비밀번호의 일부이다. 물론, 나중에 백스페이스를 통해서 지울 수는 있다. 만약 커서의 위치가 줄의 마지막이 아니라면, 커서 및 커서 오른쪽에 있는 모든 문자는 오른쪽으로 한 칸 이동한다.

각 테스트 케이스에 대해서, 강산이의 비밀번호를 출력한다. 비밀번호의 길이는 항상 0보다 크다.

## 입력

> 예제 입력 1
> 

```
2
<<BP<A>>Cd-
ThIsIsS3Cr3t
```

## 출력

> 예제 출력 1
> 

```
BAPC
ThIsIsS3Cr3t
```

## 문제 풀이

양방향 연결리스트 구현 문제이다.

왼쪽 이동, 오른쪽 이동, 요소 추가, 요소 삭제를 수행하는 로직을 짜면 되는 문제이다. 

이동은 문제가 되지 않는데, 요소 추가, 삭제에 있어서 현재 커서 위치가 어디에 있는지를 고려해서 NPE 처리를 해주는게 이 문제의 핵심인 것 같다.

## 테스트케이스

> 테스트케이스 1
> 

```
D----
```

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class Node {
    Node previous;
    Node next;
    char c;

    Node(char c) {
        this.c = c;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(br.readLine());

            for (int i = 0; i < testCases; i++) {
                Node head = new Node(' ');
                Node cursor = head;
                char[] inputs = br.readLine().toCharArray();

                for (char input : inputs) {
                    switch (input) {
                        case '<' -> {
                            // 리스트가 비어있지 않고 이전 노드가 존재하는 경우
                            if (cursor.previous != null) cursor = cursor.previous;
                        }

                        case '>' -> {
                            // 리스트가 비어있지 않고 다음 노드가 존재하는 경우
                            if (cursor.next != null) cursor = cursor.next;
                        }

                        case '-' -> {
                            // 연결 리스트 삭제
                            if (cursor != head) {
                                cursor.previous.next = cursor.next;
                                if (cursor.next != null) cursor.next.previous = cursor.previous;
                                cursor = cursor.previous;
                            }
                        }

                        default -> {
                            // 연결 리스트 추가
                            Node newNode = new Node(input);
                            newNode.next = cursor.next;
                            cursor.next = newNode;
                            newNode.previous = cursor;

                            if (newNode.next != null) newNode.next.previous = newNode;
                            cursor = cursor.next;
                        }
                    }
                }

                StringBuilder sb = new StringBuilder();
                cursor = head.next;
                while (cursor != null) {
                    sb.append(cursor.c);
                    cursor = cursor.next;
                }

                System.out.println(sb);
            }
        }
    }
}
```
