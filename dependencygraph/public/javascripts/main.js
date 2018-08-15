//DOCS - https://www.graphdracula.net/
var dracula_graph = new Dracula.Graph();

dracula_graph.addEdge("s", "c");
dracula_graph.addEdge("s", "a");
dracula_graph.addEdge("s", "t");

dracula_graph.addEdge("t", "a");
dracula_graph.addEdge("t", "k");

dracula_graph.addEdge("c", "a");
dracula_graph.addEdge("c", "k");

var layout = new Dracula.Layout.Spring(dracula_graph);

layout.layout();

var renderer = new Dracula.Renderer.Raphael('canvas', dracula_graph, 800, 800);
renderer.draw();