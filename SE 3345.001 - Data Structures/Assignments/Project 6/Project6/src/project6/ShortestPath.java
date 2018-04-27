/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project6;

/**
 *
 * @author Alex
 */

// A Java program for Dijkstra's single source shortest path algorithm.
// The program is for adjacency matrix representation of the graph
import java.util.*;
import java.lang.*;
import java.io.*;
import static project6.CityArray.CITIES;
 
class ShortestPath
{
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    static final int V=4;
    int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
    
    // A utility function to print the constructed distance array
    String[] getSolution(int dist[],int dist2[], int n, String path[])
    {
        int minCost = -1;
        int minIndex = 0;
        int cost;
        for (int i = 0; i < V; i++){
            cost = dist[i];
        
            if(cost < minCost){
               minCost = cost;
               minIndex = i;
            }
        }
        String returnString[] = {path[minIndex],String.valueOf(dist[minIndex]),String.valueOf(dist2[minIndex])};
        return returnString;
    } 
    
    // A utility function to print the constructed distance array
    // Boolean path[]
    void printSolution(int dist[],int dist2[], int n, String path[])
    {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < V; i++){
            City currentCity;
            String currentCityName;
            currentCity = CITIES.get(i);
            currentCityName = currentCity.cityName;    
            
            System.out.format("%-2d%-15s%-15s%-10s%-10s", i ,currentCityName,"cost " + dist[i] , "cost2 " + dist2[i],"path " + path[i]);
            System.out.println();
        }    
    }
 
    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    String[] dijkstra(int graph[][], int src, int graph2[][])
    {
        int dist[] = new int[V]; // The output array. dist[i] will hold
                                 // the shortest distance from src to i
                                 
        int dist2[] = new int[V];                         
        String path[] = new String[V];
        
        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];
 
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
 
        // Distance of source vertex from itself is always 0
        dist[src] = 0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);
 
            // Mark the picked vertex as processed
            sptSet[u] = true;
 

            String temp = null;
            
            
            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++){
                
                //path[v] = String.valueOf(v);
                
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v]){
                    dist[v] = dist[u] + graph[u][v];
                    dist2[v] = dist2[u] + graph2[u][v];
                    //System.out.println("Adding ");
                    temp = path[v]; 
                            
                    if (Objects.equals(temp,null)){
                        path[v] = String.valueOf(u) + "|" + String.valueOf(v);

                    }
                    else{
                        //System.out.println("concate");
                        temp += "|" + String.valueOf(u) + "|" + String.valueOf(v);
                        path[v] = temp;                        
                    }
                    
                }
            }
        }
 
        // print the constructed distance array
        // sptSet
        //printSolution(dist,dist2, V, path);
        String returnString[] = getSolution(dist,dist2, V, path);
        return returnString;
    }
}
