import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA14510 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int testCase = Integer.parseInt(str[0])+1;
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc<testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			str = br.readLine().split(" ");
			int n = Integer.parseInt(str[0]);
			int[] arr = new int[n];
			str = br.readLine().split(" ");
			int max = 0;
			for(int i = 0 ; i < n; i++) {
				arr[i] = Integer.parseInt(str[i]);
				max = Math.max(max, arr[i]);
			}
			
			int one, two;
			one = two = 0;
			for(int i = 0 ; i < n; i++) {
				arr[i] = max - arr[i];
				one += arr[i]%2;
				two += arr[i]/2;
			}
			
			if(one == two) {
				sb.append(one*2).append("\n");
				continue;
			}
			if(one > two) {
				sb.append(one*2-1).append("\n");
				continue;
			}
			
			//2가 더 많을 때
			//2 + 1 로 자랄 수 있는 것 빼기
			int days = one*2;
			//키 얼마나 남았는지 계산
			two = (two - one)*2;
			//이틀에 키 3씩 빼기
			days = days + two/3*2;
			
			//1과 2 남을때 따로 계산하기
			if(two % 3 == 1)
				days++;
			if(two%3 == 2)
				days += 2;
			sb.append(days).append('\n');
			
			
		}
		System.out.println(sb);
		

	}

}
