package CompetitiveProgrammingQuestions.graphs;

import java.util.HashMap;
import java.util.Map;

    /** * @author tusroy
     * Date 06/20/2015
     *
     * Video link - https://youtu.be/ID00PMy0-vE
     *
     * Disjoint sets using path compression and union by rank
     * Supports 3 operations
     * 1) makeSet
     * 2) union
     * 3) findSet
     *
     * For m operations and total n elements time complexity is O(m*f(n)) where f(n) is
     * very slowly growing function. For most cases f(n) <= 4 so effectively
     * total time will be O(m). Proof in Coreman book.
     */

public class CycleDetectionUsingPathAndRankCompression {


        private static Map<Long, Node> map = new HashMap<>();

        static class Node {
            long data;
            Node parent;
            int rank;
        }

        /**
         * Create a set with only one element.
         */
        public static void makeSet(long data) {
            Node node = new Node();
            node.data = data;
            node.parent = node;
            node.rank = 0;
            map.put(data, node);
        }

        /**
         * Combines two sets together to one.
         * Does union by rank
         *
         * @return true if data1 and data2 are in different set before union else false.
         */
        public static boolean union(long data1, long data2) {
            Node node1 = map.get(data1);
            Node node2 = map.get(data2);

            Node parent1 = findSet(node1);
            Node parent2 = findSet(node2);

            //if they are part of same set do nothing
            if (parent1.data == parent2.data) {
                return false;
            }

            //else whoever's rank is higher becomes parent of other
            if (parent1.rank >= parent2.rank) {
                //increment rank only if both sets have same rank
                parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
                parent2.parent = parent1;
            } else {
                parent1.parent = parent2;
            }
            return true;
        }

        /**
         * Finds the representative of this set
         */
        public static long findSet(long data) {
            return findSet(map.get(data)).data;
        }

        /**
         * Find the representative recursively and does path
         * compression as well.
         */
        private static Node findSet(Node node) {
            Node parent = node.parent;
            if (parent == node) {
                return parent;
            }
            node.parent = findSet(node.parent);
            return node.parent;
        }

        public static void main(String args[]) {
//            CycleDetectionUsingPathAndRankCompression ds = new CycleDetectionUsingPathAndRankCompression();
            makeSet(1);
            makeSet(2);
            makeSet(3);
            makeSet(4);
            makeSet(5);
            makeSet(6);
            makeSet(7);

            union(1, 2);
            union(2, 3);
            union(4, 5);
            union(6, 7);
            union(5, 6);
            union(3, 7);

            System.out.println(findSet(1));
            System.out.println(findSet(2));
            System.out.println(findSet(3));
            System.out.println(findSet(4));
            System.out.println(findSet(5));
            System.out.println(findSet(6));
            System.out.println(findSet(7));


            System.out.println(hasCycle(1,4));
        }

        private static boolean hasCycle(int u, int v) {
            if(findSet(u) == findSet(v)){
                return true;
            }else{
                return false;
            }
        }
    }
