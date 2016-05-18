package view

import scalatags.Text.all._
import Components._
import Components.BlogComponents._

import scalatags.Text.TypedTag


/**
  */
object Blog extends View {
  val KVNode = "theNode" -> "Node"
  val HeapRules = "heapRules" -> "Rules of the Heaps"
  val FunctionalDecomp = "functionalDecomp" -> "Functional Decomposition"
  val Inserting = "insertion" -> "The += Method"
  val NewInsertion = "siftingUp" -> "New Insertion (Sifting Up)"

  val links = Seq[(String, String)](KVNode, HeapRules, FunctionalDecomp, Inserting, NewInsertion)

  def priorityMapPost = blogCard("Implementing a Priority Map in Scala (Map-like Queue) Part 1", Seq(scalaChip), Seq(
    par(
      s"""
         |I recently found myself in need of a map-like sorted data structure, with ordering determined by the values and not the keys <code>SortedMap</code>.
         |There is an implementation of an immutable
         |PriorityMap on GitHub already, though for the purposes of my assignment I would rather have mutability (I know...sue me).
         |"""),
    title3("theNode", "Node"),
    par(
      """Lets start off with a simple map node wrapper object to store our key value pairs. Since our map is sorted by
        |by an entry's value we will have to implement the <code>Ordered</code> trait as to allow ordering accordingly. We will consume an implicit
        |converter to support <code>Rich</code> basic data type conversions for Int, Long, etc...
      """
    ),
    p(scalaCode(
      """case class PMapNode[K, V](key: K, var value: V)(implicit ev: V => Ordered[V]) extends Ordered[PMapNode[K, V]] {
        |  override def compare(that: PMapNode[K, V]): Int = this.value compare that.value
        |}""".stripMargin
    )),
    par(
      """Now we can start creating the actual <code>PriorityMap</code> object. Our implementation will consist of a remix of sorts, consisting of both
        |a heap to manage the ordering of our <code>PMapNodes</code>, backed by a map for O(1) access to elements other than the head of the heap.
        |To instantiate the map we will need the <code>types</code> for the key's and values as well as an implicit conversion for the value type (V)
        |in the case that V is not covariant with Ordered[V].
      """.stripMargin
    ),
    p(scalaCode(
      """class PriorityMap[K, V](initSize: Int = 16)(implicit ev: V => Ordered[V]) extends mutable.Map[K, V] {
        |   ...
        |}
      """.stripMargin)),
    par(
      """
        |For quick access to any element in our heap we will use a <code>Map</code> that when passed a key
        |it will return the index in the heap containing the entry associated with that key.
        |This index map will be updated at every insertion and removal operation.
      """),
    par(
      """
        |To manage the heap we need know it's element <code>count</code> and ensure that the element count
        |never exceeds the size of the array containing the elements. We can now implement the values needed for our map. It is
        |also a good idea to create a type alias for long type declarations like PMapNode[K,V] to clean up our code, this is completely
        |optional of course.
      """),
    p(scalaCode(
      """ ...
        |  type MapNode = PMapNode[K, V] // type alias for the PMapNode[K,V] type
        |
        |  val ROOT_INDEX = 1
        |
        |  // The root of the heap is always in index 1
        |  private var heap = new Array[MapNode](initSize) //
        |
        |  private var population = 0
        |
        |  private var indexMap: mutable.HashMap[K, Int] = mutable.HashMap()
        |
        |  override def size = population
        |  ...""".stripMargin)),
    title3("heapRules", "Rules of the Heaps"),
    par(
      """
        |To manage the ordering of our map we will use the classical heap data structure to store our <code>PMapNodes</code>.
        |A heap consists of an <code>Array</code> used to store the entrys, backed by a set of rules that create a tree-like
        |data structure."""
    ),
    ul(
      li(raw("<code>Rule 1</code>: The root is always stored in index <code>1</code>.")),
      li(raw("<code>Rule 2</code>: For any node in index <code>k</code> the left child is stored in index <code>2 * k</code>.")),
      li(raw("<code>Rule 3</code>: For any node index <code>k</code> the right child is stored in index <code>2k + 1</code>.")),
      li(raw("<code>Rule 4</code>: A node's parent index is equal to <code>k / 2</code> where <code>k</code> is the node's current index in the heap."))
    ),
    par(
      """
        |Let's also declare a type alias for representing a MapNodes's children. We will use a pair of pairs where each element
        |in the pair will contain both the reference to the MapNode and the index of that child in the heap.
        |For a <code>ChildPair</code> the first and second element will contain the storage data for the left and right child respectively.
      """
    ),
    scalaCode(
      """type ChildPair = ((Option[MapNode], Int), (Option[MapNode], Int))
        |
        |private def getChildren(index: Int): ChildPair = {
        |  val leftInd = index * 2
        |  val rightInd = leftInd + 1
        |
        |  (index * 2 <= population, index * 2 + 1 <= population) match { // Rule 2 and 3 for obtaining the child nodes.
        |    case (hasLeft, hasRight) =>
        |      val leftChild = hasLeft match {
        |        case true => Some(heap(leftInd))
        |        case false => None
        |      }
        |      val rightChild = hasRight match {
        |        case true => Some(heap(rightInd))
        |        case false => None
        |      }
        |
        |      ((leftChild, leftInd), (rightChild, rightInd))
        |  }
        |
        |}
        |
        |private def parentIndex(index: Int) = index / 2
      """.stripMargin),
    title3("functionalDecomp", "Functional Decomposition"),
    par(
      """
        |I prefer to functionally decompose an operation before I write out the final sequence calls needed to complete
        |said operation. Let's think of a problem as if it was a Ninja Warrior obstacle course, where each section of the course requires a unique set of skills
        |to be completed successfully. That's functional decomposition as I have come to understand it, we first create the methods that
        |form the arsenal of sub methods that our main method requires to compelte it's goal.
        |We will use this technique for the first major operation in our collection.
      """
    ),
    title3("insertion", "Inserting AKA +='ing"),
    par(
      """Before we insert an element into our map we must first check if the array representing the heap is full, if it is then we double the size.
        |      """.stripMargin),
    scalaCode(
      """
        |private def tryReload() = if (shouldReload) reload()
        |
        |private def shouldReload: Boolean = population.toDouble == heap.length.toDouble
        |
        |private def reload(): Unit = {
        |    heap ++= new Array[MapNode](heap.length)
        |}
        | """.stripMargin),
    par(
      """We'll try to reload before every insertion to be safe. When a new entry is inserted into our map it's key may
        |already be associated with a value, in which case we will update the value currently associated with said key.
        |If the key does not have a value already associated with it, we will add it to our collection as a new MapNode.
        |In either case we must maintain ordering by either <code>sifting up</code> or <code>sifting down</code> the MapNode to a
        |location in our heap where it's parent is ordered higher than itself.
        |Thanks to pattern matching we can represent both branches rather cleanly.
      """.stripMargin),
    scalaCode(
      """override def +=(kv: (K, V)): PriorityMap.this.type = {
        |   tryReload()
        |   indexMap.contains(kv._1) match {
        |      case true =>
        |         update(indexMap(kv._1), kv._2)
        |      case false =>
        |         val insertIndex = appendIndex
        |         heap(insertIndex) = PMapNode[K, V](kv._1, kv._2)
        |         indexMap += (kv._1 -> insertIndex)
        |         population += 1
        |         siftUp(insertIndex)
        |    }
        |    this
        |  }""".stripMargin),
    title3("siftingUp", "New Insertion (Sifting Up)"),
    par(
      """
        |Elements added into a binary heap are placed in the next available index in the array.
        |We then continuously promote the new node up the heap until we reach the case where it's parent is of a higher
        |order than itself. We'll call this function <code>siftUp</code>, and use it to
        |handle the case where a new key is being added to the map.
      """),
    scalaCode(
      """@tailrec
        |private def siftUp(targetInd: Int): Unit = {
        |  parentIndex(targetInd) match {
        |    case parentInd if parentInd != THE_ROOF =>
        |      val parentNode = heap(parentInd)
        |      val currentNode = heap(targetInd)
        |
        |      currentNode < parentNode match {
        |        case true =>
        |          swap(targetInd, (parentNode, parentInd))
        |          siftUp(parentInd)
        |        case false => ()
        |      }
        |    case _ => ()
        |  }
        |}
      """.stripMargin),
    par(
      """
        |That's it for <code>Part 1</code> of our <code>PriorityMap</code> implementation. In the next part of this tutorial
        |I will walk us through the <code>Update</code> operation where we update the value of a pre-existing key in our map as well
        |as the <code>Remove (-=)</code> <code>Map</code> trait method.
      """
    )
  ),
    Seq("Github" -> "https://github.com/rators/PriorityMap/tree/master/src/main/scala/rafcollections/map/sorted"))


  override def render: String = Main.mainLayout("Raf's Blog", Seq(priorityMapPost), Seq(tableOfContents(links))).render

  override def contents: Option[TypedTag[String]] = Some(tableOfContents(links))
}
