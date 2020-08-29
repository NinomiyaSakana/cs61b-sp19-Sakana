public class UnionFind {

    private int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    //创建一个包含n顶点的UnionFind数据结构。最初，所有顶点都是不交集的。
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1; //-1的话是根节点
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    //如果v1不是有效索引，则引发异常。
    private void validate(int vertex) {
        int n = parent.length;
        if (vertex < 0 || vertex >= n) {
            throw new IllegalArgumentException("Not a valid index.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    //返回集合v1所属的大小。
    public int sizeOf(int v1) {
        return -parent(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    //返回的v1的parent。如果v1是树的根，则返回为其根的树的负大小v1。
    public int parent(int v1) {
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    //如果节点v1和v2连接，则返回true 。
    public boolean connected(int v1, int v2) {
        return find(v1)==find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    //连接两个元件v1和v2在一起。v1并且v2可以是任何有效元素，
    // 并且使用按大小合并的启发式方法。如果集合的大小相等，
    // 则通过将v1'的根连接到'的根来打破平局v2。
    // 将顶点与其自身或已经连接的顶点合并在一起不应更改集合，
    // 但可能会更改数据结构的内部结构。
    public void union(int v1, int v2) {
        if (!connected(v1,v2)){
            if (sizeOf(v1) > sizeOf(v2)) {
                parent[find(v1)] -= sizeOf(v2);
                parent[find(v2)] = find(v1); //v2挂到v1上
            } else if (sizeOf(v1) <= sizeOf(v2)) {
                parent[find(v2)] -= sizeOf(v1);
                parent[find(v1)] = find(v2);
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    //返回集合v1所属的根。采用路径压缩，可加快搜索时间。
    public int find(int vertex) {
        validate(vertex); //先看vertex是否满足条件
        int root=vertex;

        while(parent(root)>-1){
            root=parent(root); //将root的parent付给root 直到rppt小于-1
        }

        int temp;
        while(vertex!=root){
            temp=parent(vertex);
            parent[vertex]=root;
            vertex=temp;
        }
        return root;
    }

}
