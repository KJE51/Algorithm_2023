import java.util.Scanner;

//1107 리모컨
public class RemoteControl {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		// 고장난 버튼은 true
		boolean[] brokenN = new boolean[10];

		for (int i = 0; i < m; i++) {
			int tmp = sc.nextInt();
			brokenN[tmp] = true;
		}

		// 채널 바꾸지 않았을 때
		int min = Math.abs(n - 100);
		// 0 눌렀을 때
		if (brokenN[0] == false) {
			min = Math.min(min, n + 1);
		}

		for (int i = 1; i < 1000000; i++) {
			int tmp = i;
			// 버튼 누른 횟수
			int cnt = 0;
			// 고장난 버튼 눌렀는지
			boolean flag = false;
			while (tmp > 0) {
				if (brokenN[tmp % 10] == true) {
					flag = true;
					break;
				} else {
					cnt++;
					tmp = tmp / 10;
				}
			}

			if (flag == false) {
				cnt = cnt + Math.abs(i - n);
				min = Math.min(min, cnt);
			}
		}
		System.out.println(min);
	}

}
