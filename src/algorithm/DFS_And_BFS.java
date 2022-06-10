package algorithm;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author VarleyT
 * @date 2022/6/9 22:46
 */
public class DFS_And_BFS {
    private static int N;
    private static int M;
    private static int[][] ints;
    private static boolean[][] flags;
    private static int min = Integer.MAX_VALUE;
    private static int x1;
    private static int y1;
    private static int x2;
    private static int y2;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        M = input.nextInt();
        ints = new int[N][M];
        flags = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ints[i][j] = input.nextInt();
            }
        }
        //起点
        x1 = input.nextInt() - 1;
        y1 = input.nextInt() - 1;
        //终点
        x2 = input.nextInt() - 1;
        y2 = input.nextInt() - 1;

        /**
         * 深度优先搜索
         */
        dfs(x1, y1, 0);

        /**
         * 广度优先搜索
         */
//        LinkedList<Point> queue = new LinkedList<>();
//        Point start = new Point(x1, y1, 0);
//        queue.add(start);
//        bfs(start,queue);



        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
        input.close();
    }

    //深度优先搜索
    private static void dfs(int x, int y, int step) {
        if (x == x2 && y == y2) {
            if (step < min) {
                min = step;
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (xx >= 0 && xx < N && yy >= 0 && yy < M) {
                if (ints[xx][yy] == 1 && flags[xx][yy] == false) {
                    flags[xx][yy] = true;
                    dfs(xx, yy, step + 1);
                    flags[xx][yy] = false;
                }
            }
        }
        return;
    }

    //广度优先搜索
    private static void bfs(Point p, LinkedList<Point> queue) {
        flags[p.x][p.y] = true;
        while (!queue.isEmpty()) {
            int x = queue.getFirst().x;
            int y = queue.getFirst().y;
            if (x == x2 && y == y2) {
                //此时一定是最短路径
//                if (queue.getFirst().step < min) {
                    min = queue.getFirst().step;
                    return;
//                }
            }
            for (int i = 0; i < 3; i++) {
                int xx = x + dx[i];
                int yy = y + dy[i];
                if (xx >= 0 && xx < N && yy >= 0 && yy < M) {
                    if (ints[xx][yy] == 1 && flags[xx][yy] == false) {
                        Point temp = new Point(xx, yy, queue.getFirst().step + 1);
                        queue.add(temp);
                        flags[xx][yy] = true;
                    }
                }
            }
            queue.remove();
        }
    }
    
    static class Point {
        int x;
        int y;
        int step;

        public Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}
