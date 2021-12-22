//program to do DFS traversal of directed graph
#include <bits/stdc++.h>
using namespace std;

class Graph
{
    int V;
    vector<int> *adj;

public:
    Graph(int);
    void add_edge(int, int);
    void display();
    void DFS(int);

    //maintaining a bool visited vector for DFS traversal
    bool *visited;
};

Graph::Graph(int V)
{
    this->V = V;
    adj = new vector<int>[V];
    visited = new bool[V];

    //initially all nodes must not be visited
    for (int i = 0; i < V; i++)
        visited[i] = false;
}

void Graph::add_edge(int u, int v)
{
    adj[u].push_back(v);
}

void Graph::display()
{
    for (int i = 0; i < V; i++)
    {
        cout << "\nAdjacency List of vertex " << i << "\nHead";
        for (int x : adj[i])
            cout << " -> " << x;
        cout << endl;
    }
}

void Graph::DFS(int s)
{
    //mark the current source node as visited and print it
    visited[s] = true;
    cout << s << " ";

    // Recur for all the vertices adjacent to this vertex
    for (auto it = adj[s].begin(); it != adj[s].end(); it++)
        if (!visited[*it])
            DFS(*it);
}

int main()
{
    Graph g(4);

    g.add_edge(0, 1);
    g.add_edge(0, 2);
    g.add_edge(1, 2);
    g.add_edge(2, 0);
    g.add_edge(2, 3);
    g.add_edge(3, 3);

    g.display();

    cout << "\nFollowing is Depth First Traversal (starting from vertex 2)" << endl;
    cout << "DFS ";
    g.DFS(2);

    return 0;
}

/*
    Time complexity: O(V + E), where V is the number of vertices and E is the number of edges in the graph.
    Space Complexity: O(V).
    Since an extra visited array is needed of size V.
*/
