import math
INF = math.inf

"""
Part of the algorithm was adapted from: 
Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2009). 
Introduction to Algorithms (3rd ed.). MIT Press.
"""

class Graph:
    def __init__(self):
        self.vertices = []
        self.index = {}
        self.times = {}
        self.dist = []
        self.next = []

    def add_vertex(self, v):
        if v not in self.index:
            self.index[v] = len(self.vertices)
            self.vertices.append(v)

    def read_from_file(self, filename):
        """
        Reads lines of the form:
        Ciudad1 Ciudad2 tiempoNormal tiempoLluvia tiempoNieve tiempoTormenta
        """
        with open(filename, 'r') as f:
            for line in f:
                parts = line.strip().split()
                if len(parts) != 6:
                    continue
                u, v = parts[0], parts[1]
                t_normal, t_rain, t_snow, t_storm = map(int, parts[2:])
                self.add_vertex(u)
                self.add_vertex(v)
                self.times[(u, v)] = {
                    'normal': t_normal,
                    'rain':   t_rain,
                    'snow':   t_snow,
                    'storm':  t_storm
                }
        self._initialize_matrices()

    
    def get_center(self):
        """
        Graph center = vertex with minimal eccentricity.
        Eccentricity of u = max distance from u to any v (infinity if unreachable).
        """
        ecc = {}
        n = len(self.vertices)
        for i, u in enumerate(self.vertices):
            maxd = max(self.dist[i][j] for j in range(n))
            ecc[u] = maxd
        center = min(ecc, key=lambda u: ecc[u])
        return center, ecc[center]

    def floyd_warshall(self):
        """Runs Floyd Warshall, updating self.dist and self.next."""
        n = len(self.vertices)
        for k in range(n):
            for i in range(n):
                for j in range(n):
                    if self.dist[i][k] + self.dist[k][j] < self.dist[i][j]:
                        self.dist[i][j] = self.dist[i][k] + self.dist[k][j]
                        self.next[i][j] = self.next[i][k]

    def get_path(self, src, dst):
        """Reconstructs shortest path from src to dst, or returns None."""
        if src not in self.index or dst not in self.index:
            return None
        u, v = self.index[src], self.index[dst]
        if self.next[u][v] is None:
            return None
        path = [src]
        while u != v:
            u = self.next[u][v]
            path.append(self.vertices[u])
        return path
    def change_edge_climate(self, u, v, climate):
        """
        Updates the active weight of edge u -> v to the specified climate.
        climate ∈ {'normal','rain','snow','storm'}.
        """
        if (u, v) not in self.times:
            raise KeyError(f"No edge {u}→{v}")
        self.times[(u, v)]['_active'] = self.times[(u, v)][climate]
        orig = self.times[(u, v)]['normal']
        self.times[(u, v)]['normal'] = self.times[(u, v)][climate]
        self._initialize_matrices()
    
    def display_adjacency_matrix(self):
        """Prints distance matrix with vertex labels."""
        hdr = [""] + self.vertices
        row_fmt = "{:>12}" * (len(hdr))
        print(row_fmt.format(*hdr))
        for i, u in enumerate(self.vertices):
            row = [u] + [
                f"{self.dist[i][j]:.0f}" if self.dist[i][j] < INF else "infity"
                for j in range(len(self.vertices))
            ]
            print(row_fmt.format(*row))
            
    def _initialize_matrices(self):
        n = len(self.vertices)
        self.dist = [[INF]*n for _ in range(n)]
        self.next = [[None]*n for _ in range(n)]

        for i in range(n):
            self.dist[i][i] = 0

        for (u, v), times in self.times.items():
            i, j = self.index[u], self.index[v]
            self.dist[i][j] = times['normal']
            self.next[i][j] = j
            
    def remove_edge(self, u, v):
        """
        Deletes the direct edge u to v and any direct edges u to w
        where w is reachable from v (i.e. a 'descendant' of v).
        This ensures that removing the A to B link also removes
        A to C if B to C existed.
        """
        descendants = set()
        stack = [v]
        while stack:
            x = stack.pop()
            for (p, q) in list(self.times):
                if p == x and q not in descendants:
                    descendants.add(q)
                    stack.append(q)

        if (u, v) in self.times:
            del self.times[(u, v)]

        for w in descendants:
            if (u, w) in self.times:
                del self.times[(u, w)]

        self._initialize_matrices()

    def rebuild_and_solve(self):
        """Reinitialize matrices and rerun Floyd Warshall."""
        self._initialize_matrices()
        self.floyd_warshall()
        
    def add_edge(self, u, v, t_normal, t_rain, t_snow, t_storm):
        """Adds a directed edge u -> v with all four climate times."""
        self.add_vertex(u)
        self.add_vertex(v)
        self.times[(u, v)] = {
            'normal': t_normal, 'rain': t_rain,
            'snow':   t_snow,  'storm': t_storm
        }
        self._initialize_matrices()