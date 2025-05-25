import unittest
from graph import Graph

class TestGraph(unittest.TestCase):
    def setUp(self):
        self.g = Graph()
        self.g.add_edge("A", "B", 1, 1, 1, 1)
        self.g.add_edge("B", "C", 2, 2, 2, 2)
        self.g.add_edge("A", "C", 5, 5, 5, 5)
        self.g.rebuild_and_solve()

    def test_shortest_paths(self):
        path = self.g.get_path("A", "C")
        self.assertEqual(path, ["A", "B", "C"])
        self.assertEqual(self.g.dist[self.g.index["A"]][self.g.index["C"]], 3)

    def test_center(self):
        center, ecc = self.g.get_center()
        self.assertIn(center, {"A","B","C"})
        self.assertEqual(ecc, 3)

    def test_remove_edge(self):
        self.g.remove_edge("A", "B")
        self.g.rebuild_and_solve()
        self.assertIsNone(self.g.get_path("A", "C"))

if __name__ == "__main__":
    unittest.main()