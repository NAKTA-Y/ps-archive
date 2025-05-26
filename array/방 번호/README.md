## 문제풀이 시간

15분

## 성능 요약

메모리: 14212KB

시간: 104ms

## 문제 설명

다솜이는 은진이의 옆집에 새로 이사왔다. 다솜이는 자기 방 번호를 예쁜 플라스틱 숫자로 문에 붙이려고 한다.

다솜이의 옆집에서는 플라스틱 숫자를 한 세트로 판다. 한 세트에는 0번부터 9번까지 숫자가 하나씩 들어있다. 다솜이의 방 번호가 주어졌을 때, 필요한 세트의 개수의 최솟값을 출력하시오. (6은 9를 뒤집어서 이용할 수 있고, 9는 6을 뒤집어서 이용할 수 있다.)

## 입력 조건

첫째 줄에 다솜이의 방 번호 N이 주어진다. N은 1,000,000보다 작거나 같은 자연수이다.

## 입력

> 예제 입력 1
> 

9999

> 예제 입력 2
> 

122

> 예제 입력 3
> 

12635

> 예제 입력 4
> 

888888

## 출력

> 예제 출력 1
> 

2

> 예제 출력 2
> 

2

> 예제 출력 3
> 

1

> 예제 출력 4
> 

6

## 문제 풀이

방 번호의 6과 9는 뒤집어서 사용할 수 있기 때문에 6 또는 9가 2개일 경우에는 1개의 숫자 세트로 표현이 가능하다.

따라서, 6과 9의 총 개수를 더하고 / 2를 한 후, 올림 작업을 해서 6과 9를 표현하기 위해 필요한 숫자 세트의 수를 구할 수 있다.

하나의 숫자 세트에서 6과 9를 제외한 나머지 숫자는 1개만 사용이 가능하므로 방 번호에서 각각의 숫자 갯수를 카운트하고, 6과 9는 위의 계산 작업을 통해 값을 통일시켜 최소로 필요한 숫자 세트를 구한다.

## 테스트케이스

> 테스트케이스 1
> 

6699

> 테스트케이스 2
> 

699

## 코드

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int max = 1;
            int[] numberCounts = new int[10];
            String roomNumber = br.readLine();

            for (char number : roomNumber.toCharArray()) {
                numberCounts[number - '0']++;
            }

            numberCounts[6] = (int) Math.ceil(( numberCounts[6] + numberCounts[9] ) / 2.0);
            numberCounts[9] = numberCounts[6];

            for (int count : numberCounts) {
                max = Math.max(max, count);
            }

            System.out.println(max);
        }
    }
}
```
