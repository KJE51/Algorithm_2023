import java.util.Scanner;

// 3085 사탕 게임
// acmicpc.net/problem/3085

public class CandyGame {
	static char[][] arr;
	static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new char[n][n];
		String str;

		// 입력받기
		for (int i = 0; i < n; i++) {
			str = sc.next();
			for (int k = 0; k < n; k++) {
				arr[i][k] = str.charAt(k);
			}
		}

		int max = 0;

		// 원상태에서 최대 길이 구하기
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				max = Math.max(max, checkColumn(i, k));
				max = Math.max(max, checkRow(i, k));
				if (max == n)
					break;
			}
			if (max == n)
				break;
		}

		char tmp;
		// 교환 후 최대 길이 구하기-오른쪽과 교환
		if (max != n) {
			for (int i = 0; i < n; i++) {
				for (int k = 0; k < n - 1; k++) {
					tmp = arr[i][k];
					if (tmp != arr[i][k + 1]) {
						arr[i][k] = arr[i][k + 1];
						arr[i][k + 1] = tmp;

						max = Math.max(max, checkColumn(i, k));
						max = Math.max(max, checkColumn(i, k + 1));
						max = Math.max(max, checkRow(i, k));
						max = Math.max(max, checkRow(i, k + 1));

						// 되돌리기
						arr[i][k + 1] = arr[i][k];
						arr[i][k] = tmp;
					}
					if (max == n)
						break;
				}
				if (max == n)
					break;
			}
		}

		// 교환 후 최대 길이 구하기-세로 교환
		if (max != n) {
			for (int i = 0; i < n - 1; i++) {
				for (int k = 0; k < n; k++) {
					tmp = arr[i][k];
					if (tmp != arr[i + 1][k]) {
						arr[i][k] = arr[i + 1][k];
						arr[i + 1][k] = tmp;

						max = Math.max(max, checkColumn(i, k));
						max = Math.max(max, checkColumn(i + 1, k));
						max = Math.max(max, checkRow(i, k));
						max = Math.max(max, checkRow(i + 1, k));

						// 되돌리기
						arr[i + 1][k] = arr[i][k];
						arr[i][k] = tmp;
					}
				}
			}
		}
		System.out.println(max);
	}

	private static int checkRow(int x, int y) {
		int a = 1;
		int result = 1;
		while (y - a > -1) {
			if (arr[x][y] == arr[x][y - a]) {
				result++;
			} else {
				break;
			}
			a++;
		}
		a = 1;
		while (y + a < n) {
			if (arr[x][y] == arr[x][y + a]) {
				result++;
			} else {
				break;
			}
			a++;
		}
		return result;
	}

	static int checkColumn(int x, int y) {
		int a = 1;
		int result = 1;
		while (x - a > -1) {
			if (arr[x][y] == arr[x - a][y]) {
				result++;
			} else {
				break;
			}
			a++;
		}
		a = 1;
		while (x + a < n) {
			if (arr[x][y] == arr[x + a][y]) {
				result++;
			} else {
				break;
			}
			a++;
		}
		return result;
	}
}
