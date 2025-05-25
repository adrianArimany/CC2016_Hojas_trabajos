from graph import Graph

def main():
    g = Graph()
    g.read_from_file("guategrafo.txt") #or whichever file name you are using.
    g.floyd_warshall()

    print("\n--- Initial adjacency matrix (normal climate) ---")
    g.display_adjacency_matrix()

    while True:
        print("""
Options:
  1. Shortest path between two cities
  2. Show graph center
  3. Modify graph
  4. Exit
""")
        choice = input("Enter option: ").strip()
        if choice == "1":
            src = input("Source city: ").strip()
            dst = input("Destination city: ").strip()
            path = g.get_path(src, dst)
            if path is None:
                print("No path found.")
            else:
                dist = g.dist[g.index[src]][g.index[dst]]
                print(f"Shortest time: {dist:.0f}")
                print("Route:", " to ".join(path))

        elif choice == "2":
            center, ecc = g.get_center()
            print(f"Center of graph: {center} (eccentricity = {ecc:.0f})")

        elif choice == "3":
            print("""
  a) Remove connection
  b) Add connection
  c) Change climate for a connection
""")
            sub = input("Choose a/b/c: ").strip().lower()
            if sub == "a":
                u = input("From city: ").strip()
                v = input("To city: ").strip()
                g.remove_edge(u, v)
            elif sub == "b":
                u = input("From city: ").strip()
                v = input("To city: ").strip()
                times = input(
                    "Enter four times (normal rain snow storm) separated by spaces: "
                ).split()
                tn, tr, ts, tt = map(int, times)
                g.add_edge(u, v, tn, tr, ts, tt)
            elif sub == "c":
                u = input("From city: ").strip()
                v = input("To city: ").strip()
                climate = input(
                    "Enter climate (normal/rain/snow/storm): "
                ).strip().lower()
                try:
                    g.change_edge_climate(u, v, climate)
                except KeyError as e:
                    print(e)
            else:
                print("Invalid sub-option.")
            g.rebuild_and_solve()
            print("Recomputed adjacency matrix:")
            g.display_adjacency_matrix()

        elif choice == "4":
            print("Bye bye sir.")
            break
        else:
            print("Invalid option. Please choose 1 to 4.")

if __name__ == "__main__":
    main()