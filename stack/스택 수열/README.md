## 문제풀이 시간

25분

## 성능 요약

메모리: 51744 KB

시간: 1332 ms

## 문제 설명

스택 (stack)은 기본적인 자료구조 중 하나로, 컴퓨터 프로그램을 작성할 때 자주 이용되는 개념이다. 스택은 자료를 넣는 (push) 입구와 자료를 뽑는 (pop) 입구가 같아 제일 나중에 들어간 자료가 제일 먼저 나오는 (LIFO, Last in First out) 특성을 가지고 있다.

1부터 n까지의 수를 스택에 넣었다가 뽑아 늘어놓음으로써, 하나의 수열을 만들 수 있다. 이때, 스택에 push하는 순서는 반드시 오름차순을 지키도록 한다고 하자. 임의의 수열이 주어졌을 때 스택을 이용해 그 수열을 만들 수 있는지 없는지, 있다면 어떤 순서로 push와 pop 연산을 수행해야 하는지를 알아낼 수 있다. 이를 계산하는 프로그램을 작성하라.

## 조건

첫 줄에 n (1 ≤ n ≤ 100,000)이 주어진다. 둘째 줄부터 n개의 줄에는 수열을 이루는 1이상 n이하의 정수가 하나씩 순서대로 주어진다. 물론 같은 정수가 두 번 나오는 일은 없다.

입력된 수열을 만들기 위해 필요한 연산을 한 줄에 한 개씩 출력한다. push연산은 +로, pop 연산은 -로 표현하도록 한다. 불가능한 경우 NO를 출력한다.

## 입력

> 예제 입력 1
> 

```
8
4
3
6
8
7
5
2
1
```

> 예제 입력 2
> 

```
5
1
2
5
3
4
```

## 출력

> 예제 출력 1
> 

```
+
+
+
+
-
-
+
+
-
+
+
-
-
-
-
-
```

> 예제 출력 2
> 

```
NO
```

## 문제 풀이

스택의 특성을 잘 활용한 문제라고 볼 수 있다.

1부터 n까지의 수를 넣으면서 주어진 수열 값에 대응되는 값이 나올때까지 스택에 있는 값을 하나씩 pop 해나가면 된다. 만약 하나씩 pop을 하다가 스택이 비어버린다면, 그 수열은 스택으로 만들 수 없는 수열이라는 것을 알 수 있다. 

풀이 코드에서는 반복문 탈출 문법을 사용하지 않고, 1부터 n까지 모든 연산이 끝나고, 스택 내부에 값이 남아있을 경우에는 “NO”, 그렇지 않다면 연산자 값들을 모두 나열하는 방식을 사용하였다.

## 테스트케이스

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

> 테스트케이스 2
> 

```
5
5
4
3
2
1
```

> 테스트케이스 3
> 

```
5
1
4
2
3
5
```

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            int[] sequence = new int[n];
            List<Character> commands = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < n; i++) {
                int value = Integer.parseInt(br.readLine());
                sequence[i] = value;
            }

            int index = 0;
            for (int i = 1; i <= n; i++) {
                stack.push(i);
                commands.add('+');

                // 스택 peek 값과 인덱스 값이 같다면 pop (while)
                while (!stack.isEmpty() && stack.peek() == sequence[index]) {
                    stack.pop();
                    index++;
                    commands.add('-');
                }
            }

            if (!stack.isEmpty()) System.out.println("NO");
            else {
                for (Character command : commands) {
                    System.out.println(command);
                }
            }
        }
    }
}

```
