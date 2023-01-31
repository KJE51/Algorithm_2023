import java.util.Scanner;
//14890

public class Slide {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int l = sc.nextInt();

		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				arr[i][k] = sc.nextInt();
			}
		}

		// 서로 1 이상 차이 날 때
		// 1차이 날 때는 l*2 이상만큼 존재해야함
		// 오른쪽이 작을 때 : 오른쪽이 l 이상 존재
		// 오른쪽이 클 때 : 왼쪽이 l 이상 존재

		int left = 0, right = 0;
		int result = 0;
		boolean flag = true;

		// 가로 비교
		for (int i = 0; i < n; i++) {
			flag = true;
			left = 1;
			right = 0;

			// 왼쪽과 비교
			for (int k = 1; k < n; k++) {
				// 높이 같을 때
				if (arr[i][k] == arr[i][k - 1]) {
					left++;
				}

				// 오른쪽이 1만큼 클 때
				// 왼쪽의 길이 비교
				else if (arr[i][k] - arr[i][k - 1] == 1) {
					if (left < l) {
						flag = false;
						break;
					} else {
						left = 1;
					}
				}

				// 1만큼 작을 때
				// right가 l과 같을때까지
				else if (arr[i][k] - arr[i][k - 1] == -1) {
					left = 0;
					right = 1;
					// 1일때는 k++과 비교하지 않아도 되기 때문에 넘기기
					if (l != 1) {
						while (true) {
							// k 증가, right와 l 비교, arr[i][k]와 같은지 비교
							k++;
							if (k > n - 1 || arr[i][k] != arr[i][k - 1]) {
								flag = false;
								break;
							}
							right++;
							if (right == l)
								break;
						}
					}
					if (flag == false)
						break;
				}

				// 2 이상 차이 날 때
				else {
					flag = false;
					break;
				}
			}
			if (flag == true) {
				result++;
			}
		}

		// 세로 비교
		for (int k = 0; k < n; k++) {
			flag = true;
			left = 1;
			right = 0;

			// 위쪽과 비교
			for (int i = 1; i < n; i++) {
				// 높이 같을 때
				if (arr[i][k] == arr[i - 1][k]) {
					left++;
				}

				// 아래쪽이 1만큼 클 때
				// 위쪽의 길이 비교
				else if (arr[i][k] - arr[i - 1][k] == 1) {
					if (left < l) {
						flag = false;
						break;
					} else {
						left = 1;
					}
				}
				// 1만큼 작을 때
				// right가 l과 같을때까지
				else if (arr[i][k] - arr[i - 1][k] == -1) {
					left = 0;
					right = 1;
					if(l != 1) {
					while (true) {
						// k 증가, right와 l 비교, arr[i][k]와 같은지 비교
						i++;
						if (i > n - 1 || arr[i][k] != arr[i - 1][k]) {
							flag = false;
							break;
						}
						right++;
						if (right == l)
							break;
					}}
					if (flag == false)
						break;
				}
				else {
					flag = false;
					break;
				}
			}
			if (flag == true) {
				result++;
			}
		}
		System.out.println(result);
	}

}
