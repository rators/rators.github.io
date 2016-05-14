package view

import scalatags.Text.all._
import Components._
import Components.BlogComponents._

import scalatags.Text.TypedTag


/**
  * Created by rotor on 5/5/2016.
  */
object Blog extends View {
  val KVNode = "theNode" -> "Node"
  val HeapRules = "heapRules" -> "Rules of the Heaps"
  val FunctionalDecomp = "functionalDecomp" -> "Functional Decomposition"
  val Inserting = "insertion" -> "The += Method"
  val NewInsertion = "siftingUp" -> "New Insertion (Sifting Up)"

  val links = Seq[(String, String)](KVNode, HeapRules, FunctionalDecomp, Inserting, NewInsertion)

  def view: Modifier =
    Seq(
      h1(cls := "header center-align left-on-large-only"),
      priorityMapPost
    )

  def priorityMapPost = card("Implementing a Priority Map in Scala (Map-like Queue) Part 1", Seq(scalaChip), Seq(
    par(
      s"""
         |I recently found myself in need of a map-like sorted data structure, with ordering determined by the values and not the keys <code>SortedMap</code>.
         |There is an implementation of an immutable
         |PriorityMap on GitHub already, though for the purposes of my assignment I would rather have mutability (I know...sue me).
         |"""),
    title3("theNode", "Node"),
    par(
      """Lets start off with a simple map node wrapper object to store our key value pairs. Since our map is sorted by
        |by the value of it's key value pair we will have to implement the <code>Ordered</code> trait as well. We will consume an implicit
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
        |a heap to manage the ordering of our <code>PMapNodes</code>, backed by a map for linear read of the elements. To instantiate the map we will need the <code>types</code>
        |for the key's and values as well as an implicit conversion for the Value type in the case that Value type V is not covariant with Ordered[V].
      """.stripMargin
    ),
    p(scalaCode(
      """ class PriorityMap[K, V](initSize: Int = 16)(implicit ev: V => Ordered[V]) extends mutable.Map[K, V] {
        |   ...
        |   }
      """.stripMargin)),
    title3("heapRules", "Rules of the Heaps"),
    par(
      """
        |To manage the ordering of our map we will use the classical heap data structure to store our <code>PMapNodes</code>.
        |A heap consists of an <code>Array</code> of elements with three basic rules which create a <code>Binary Tree</code>.
        |Rule 1: The root is always stored in index <code>1</code>.
        |Rule 2: For any node in index <code>k</code> the left child is stored in index <code>2 * k</code>.
        |Rule 3: For any node index <code>k</code> the right child is stored in index <code>2k + 1</code>.
        |Rule 4: A node's parent index is equal to <code>k / 2</code> where <code>k</code> is the node's current index
        |in the heap. Let's make some helper methods to help us manage these rules. It's also a good idea to make a type alias for the
        |tuple of children so we don't forget what the tuple represents later on.
      """),
    scalaCode(
      """type ChildPair = ((Option[MapNode], Int), (Option[MapNode], Int))
        |
        |private def getChildren(index: Int): ChildPair = {
        |  val leftInd = index * 2
        |  val rightInd = leftInd + 1
        |
        |  (index * 2 <= population, index * 2 + 1 <= population) match {
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
    par(
      """
        |Like a regular Binary Tree, the left child is smaller than its parent, and the right child is large than it's parent. We still have one issue though, accessing elements other than the
        |first ordered element. To solve our access problem we will use a <code>Map</code> as an index map, with the same K type as our <code>PriorityMap</code> constructor argument and V typed as an Int.
        |The <em>index</em> map's value set will match a Key to it's respective <code>PMapNode</code> in the heap. This index map will be updated at every insertion and removal operation.
      """),
    par(
      """
        |To manage the heap we need know it's element <code>count</code> and ensure that the element count
        |never exceeds the size of the array containing the elements. We can now implement the values needed for our map.
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
    title3("functionalDecomp", "Functional Decomposition"),
    par(
      """
        |I prefer to functionally decompose an operation before I write out the final sequence calls needed to complete
        |said operation. Let's think of a problem as if it was a Ninja Warrior obstacle course, where each section of the course requires a unique set of skills
        |to be completed successfully. That's the functional decomposition in a nut shell, now lets start applying it to the operations we need to on
        |this <code>Map</code>, starting with <code>Insertion</code> (the <code>+=</code> method in Scala-lingo).
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
      """We'll try to reload before every insertion to be safe. Now that we know it is safe to insert
        |into our heap we must determine whether we are <code>updating</code> the value of a pre-existing key,
        |or <code>inserting</code> a new key value pair into our map. Thanks to pattern matching we can represent
        |both branches rather cleanly.
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
        |Elements added into a binary heap are placed at index <code>n + 1</code> where <code>n</code> is the
        |rightmost element in the heap. We then continuously promote the new node up the heap until it's met
        |with a parent with a higher order than itself. We'll call this function <code>siftUp</code>, and use it to
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
        |That's it for the first part of our <code>PriorityMap</code> implementation. In the next part of this tutorial
        |I will walk through the <code>update</code> operation where we update the value of a pre-existing Key and sift it to it's correct position in the heap
        |using <code>sift up</code> or <code>sift down</code>.
      """
    )
  ),
    Seq("Github" -> "https://github.com/rators/PriorityMap/tree/master/src/main/scala/rafcollections/map/sorted"))


  override def render: String = Main.mainLayout("Blog", Seq(view), Seq(tableOfContents(links))).render
  override def contents: Option[TypedTag[String]] = Some(tableOfContents(links))
}
