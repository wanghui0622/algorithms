# algorithms
算法学习，写的一些例子
这些算法案例都是在学习算法4的时候，随书练习，并且添加了个人的理解。
如果你想整体学习算法的话，建议访问https://algs4.cs.princeton.edu/home/
算法易掉发，入坑需慎重！

## 算法基础
常用的数据结构,每种数据结构都有他特定的应用场景，在datastructure包中包中，实现了三种常用的数据结构： 包、堆栈、队列。
* **包**：只进不出的数据结构,一个用于收集数据，然后遍历数据的数据结构，不能移除数据
* **堆栈**：一种后进先出(LIFO)数据结构
* **队列**：一种先进先出(FIFO)的数据结构
在datastructure.demo中列举了一些列数据结构的场景,其中queue包中是队列的用例，stack中是堆栈的用例。  
**queue包**  

|类名|算法含义|
|:-:|:-:|
| DogCatQueueAlg.java |猫狗队列|
| Josephus.java |约瑟夫斯问题|
| !!!MultiwordSearch.java |多字搜索|
| QueueWithTwoStacks.java |两个堆栈实现一个队列|
| ResizingArryQueue.java |数组大小调整队列|
| SlidingWindowMaxArray.java |生成窗口最大值|
| TwoStacksImplementQueue.java |两个堆栈生成一个队列|

**stack包**  

|类名|算法含义|
|:-:|:-:|
| Evaluate.java |堆栈实现算数计算器|
| EvaluateDeluxe.java |按优先级计算(中缀表达式计算器)|
|EvaluatePostfix.java| 后缀表达式计算(算式必须是完全括号表达式)|
|FixedCapacityStack.java|固定容量的通用堆栈|
|FixedCapacityStackOfStrings.java|固定容量的字符串堆栈|
|GetMinStack.java|实现栈的基本功能，并且可以返回栈中最小元素时间复杂度O(1)|
|HanoiStack.java|汉诺塔，提供递归和堆栈两种算法|
|Parentheses.java|平衡字符串|
|ResizingArrayStack.java|数组大小调整堆栈|
|ReverseStackUsingRecursive.java|使用一个栈和递归实现堆的转置|
|StackSortStack.java | 栈排序|
