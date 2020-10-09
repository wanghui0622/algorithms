package uf;

/**
 * 连通性
 *
 * @author 王辉
 * @create 2020-07-08 23:01
 * @Description union find
 *
 */
public class UF {
    private final int[] parent;  // 索引代表触点，触点的父连接点
    private final byte[] rank;   // 索引代表触点为根节点的分量大小
    private int count;          // 连通分量个数

    /**
     * 初始化构造UF
     * 连通分量数：触点数
     * 父连接点：默认自身
     * 分量数：0
     * @param n 触点数
     */
    public UF(int n) {
        if (n < 0) throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * 获取P的父连接点
     *
     * @param p 触点P
     * @return P的父连接点
     * @throws IllegalArgumentException 当 p大于n或者小于0
     */
    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }

    /**
     * 分量数
     * @return 1到n之间的任意数
     */
    public int count() {
        return count;
    }

    /**
     * 两个触点是否连通
     *
     * @param p 触点1
     * @param q 触点2
     * @return 连通返回true，不连通返回false
     * @throws IllegalArgumentException 当p或者q大于n或者小于0
     * @deprecated 可以直接调 {@link #find(int)}代替该方法
     */
    @Deprecated
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 连接连两个触点
     * 如果，当两个触点已经是连通的，不做任何操作直接返回
     * 否则，
     * @param p 触点1
     * @param q 触点2
     * @throws IllegalArgumentException  当p或者q大于n或者小于0
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // 小连通分量并入大连通分量
        if (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
        else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        //分量数减1
        count--;
    }

    // 有效字段验证
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    /**
     * Reads an integer {@code n} and a sequence of pairs of integers
     * (between {@code 0} and {@code n-1}) from standard input, where each integer
     * in the pair represents some element;
     * if the elements are in different sets, merge the two sets
     * and print the pair to standard output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        UF uf = new UF(10);
        int[][] aa = new int[][]{{4, 3}, {3, 8}, {6, 5}, {9, 4}, {2, 1}, {8, 9}, {5, 0}, {7, 2}, {6, 5}, {6, 1}, {1, 0}, {6, 7}};

        for (int[] a : aa) {
            int p = a[0], q = a[1];
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
        }
        System.out.println(uf.count() + " components");
        System.out.println("1,8是否连通：" + (uf.find(1) == uf.find(8)? "连通" : "不连通"));
    }
}
