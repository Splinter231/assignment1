# Assignment 1
**Student:** Chepurnenko Sergey  
**Group:** SE-2422
---
## 1) Architecture

**1.1) MergeSort**

Sorting by dividing the array into halves, then merging using a reusable buffer.
For small arrays, the algorithm switches to insertion sort (cutoff optimization).
Recursion depth is O(log n).

**1.2) QuickSort**

Uses randomized pivot selection to avoid worst-case inputs.
Always recurses into the smaller partition and processes the larger iteratively, keeping stack depth bounded by O(log n).
Robust against adversarial input.

**1.3) Deterministic Select (Median of Medians, MoM5)**

Finds the k-th smallest element.
The array is split into groups of 5, medians are collected, and the median of these is chosen as pivot.
Recursion proceeds only on the relevant half (and into the smaller side), ensuring linear time and bounded depth.

**1.4) Closest Pair of Points (2D)**

Points are sorted by X and divided into two halves recursively.
In the “strip” area, only 7–8 neighboring points are checked in Y-order.
Recursion depth is O(log n).

---

## 2) Recurrence Analysis

**2.1) MergeSort**
- Recurrence: T(n) = 2T(n/2) + Θ(n)
- Explanation: merging at each level takes Θ(n), and recursion depth is log n.
- Result: Θ(n log n) (Master Theorem, Case 2).

**2.2) QuickSort**
- Average case: T(n) = T(n/2) + T(n/2) + Θ(n) = Θ(n log n).
- Worst case: T(n) = T(n−1) + Θ(n) = Θ(n²).
- Randomized pivot + smaller-half recursion → expected O(n log n) time, depth O(log n).

**2.3) Deterministic Select (MoM5)**
- Recurrence: T(n) = T(n/5) + T(7n/10) + Θ(n).
- Intuition: “good pivot” discards ≥30% of elements each step.
- Solved with Akra–Bazzi: Θ(n).

**2.4) Closest Pair of Points**
- Recurrence: T(n) = 2T(n/2) + Θ(n).
- Explanation: recursive divide + linear strip check.
- Result: Θ(n log n).
---
## 3) Results and Discussion

**3.1) Time vs n**
- MergeSort and QuickSort show Θ(n log n) growth.
- QuickSort is often faster due to lower constant factors but more variable (depends on pivot).
- Deterministic Select grows linearly, though with a larger constant.
- Closest Pair matches n log n.

**3.2) Depth vs n**
- MergeSort, QuickSort, and Closest Pair stay around log n depth.
- Deterministic Select depth is linear but still efficient in practice due to discarding chunks of input each step.

**3.3) Constant factors**
- Insertion sort cutoff makes MergeSort faster for small arrays.
- Cache effects and memory access patterns make QuickSort competitive up to ~100k elements.
- Java GC sometimes adds noise in timings.

**3.4) Overall**
- The experiments align with theory:
    - MergeSort → Θ(n log n)
    - QuickSort → Θ(n log n) average, Θ(n²) worst case
    - Deterministic Select → Θ(n)
    - Closest Pair → Θ(n log n)
- Differences mostly come from constant factors, not asymptotics.

---

## 4) CSV Results

Benchmark results are exported into `bench-results.csv` in the format:  

"Benchmark","Mode","Threads","Samples","Score","Score Error (99,9%)","Unit","Param: n"
"org.example.bench.SelectVsSortBench.mergeSort","avgt",1,5,"0,120356","0,081554","ms/op",1000
"org.example.bench.SelectVsSortBench.mergeSort","avgt",1,5,"1,296242","0,234702","ms/op",10000
"org.example.bench.SelectVsSortBench.mergeSort","avgt",1,5,"17,134531","1,697708","ms/op",100000
"org.example.bench.SelectVsSortBench.quickSort","avgt",1,5,"0,139126","0,034474","ms/op",1000
"org.example.bench.SelectVsSortBench.quickSort","avgt",1,5,"1,879062","0,197161","ms/op",10000
"org.example.bench.SelectVsSortBench.quickSort","avgt",1,5,"23,194221","6,266399","ms/op",100000
  

