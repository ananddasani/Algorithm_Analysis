//Program to do BFS traversal in directed graph
#include <bits/stdc++.h>
using namespace std;

class Graph
{
private:
    int V;
    vector<int> *adj;

public:
    Graph(int);
    void add_edge(int, int);
    void display();
    void BFS(int);
};

Graph::Graph(int v)
{
    this->V = v;
    adj = new vector<int>[V];
}

void Graph::add_edge(int u, int v)
{
    adj[u].push_back(v);
}

void Graph::display()
{
    for (int i = 0; i < V; i++)
    {
        cout << "Adjacency List of vertex " << i << "\nHead";
        for (int x : adj[i])
            cout << " -> " << x;
        cout << endl;
    }
}

void Graph::BFS(int s)
{
    //mark all vertices as not visited
    bool *visited = new bool[V];
    for (int i = 0; i < V; i++)
        visited[i] = false;

    //make a list/queue to store levels
    queue<int> q;

    //mark the source node as visited and enqueue it
    visited[s] = true;
    q.push(s);

    while (!q.empty())
    {
        //1. get first vertex, print, dequeue it
        s = q.front();
        cout << s << " ";
        q.pop();

        //2 enqueue all the adj vertices of s
        for (auto it = adj[s].begin(); it != adj[s].end(); it++)
        {
            if (!visited[*it])
            {
                visited[*it] = true;
                q.push(*it);
            }
        }
    }
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

    cout << "\nBFS traversal of Graph starting form vertex 2" << endl;
    cout << "\nBFS :: ";
    g.BFS(2);

    return 0;
}

/*
See the graph pictorial representation here
https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
*/
