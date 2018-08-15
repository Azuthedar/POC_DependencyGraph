var graph = new Dracula.Graph();

graph.addNode("C");
graph.addNode("C++");
graph.addNode("C#");
graph.addNode("ASP.NET");

graph.addNode("A");
graph.addNode("B");
graph.addNode("D");

graph.addEdge("C", "C++");
graph.addEdge("C", "C#");

graph.addEdge("C++", "C#");

graph.addEdge("C#", "ASP.NET");


graph.addEdge("A", "D");
graph.addEdge("A", "B");

graph.addEdge("D", "B");

var layouter = new Dracula.Layout.Spring(graph);
layouter.layout();

var renderer = new Dracula.Renderer.Raphael('#canvas', graph, 2000, 2000);
renderer.draw();