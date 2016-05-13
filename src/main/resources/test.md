<h1 id="mutable-minimum-priority-map-in-scala">Mutable Minimum Priority Map in Scala</h1>

<hr>

<p>A priority map is a Min-heap with the same access API as a Map. The entries in the priority map are sorted by their <code>values</code>, rather than by their <code>keys</code> as is typical in a sorted map.</p>

<p>Calling <code>head</code> on a priority map returns the key value pair with the minimal value, as in a priority queue.</p>



<h2 id="usage">Usage</h2>

<hr>

<p>The default size for the inner heap is <em>16</em> if no value is passed as a parameter to the constructor.</p>



<pre class="prettyprint"><code class="language-scala hljs "> scala &gt; <span class="hljs-keyword">val</span> map = PriorityMap[String, Int]()
 map: priorityMap.PriorityMap[String, Int]

 scala &gt; map ++= Seq(<span class="hljs-string">"Hello"</span> -&gt; <span class="hljs-number">3</span>, <span class="hljs-string">"People"</span> -&gt; -<span class="hljs-number">2</span>, <span class="hljs-string">"Planet"</span> -&gt; <span class="hljs-number">9</span>)
 map: priorityMap.PriorityMap[String, Int] = Map(Hello -&gt; <span class="hljs-number">3</span>, People -&gt; -<span class="hljs-number">2</span>, Planet -&gt; <span class="hljs-number">9</span>)</code></pre>

<p>The PriorityMap also implements the Map trait, enabling all common map operations. Inserting a key value pair with a key that has already been inserted into the map will update the new value as in the default Scala collections Map. Ordering is re-established if necessary after any insertion into the map.</p>



<pre class="prettyprint"><code class="language-scala hljs ">  scala &gt; map.head
  res0: (String, Int) = (People,-<span class="hljs-number">2</span>)

  scala &gt; map += (<span class="hljs-string">"People"</span> -&gt; <span class="hljs-number">10</span>)
  map: priorityMap.PriorityMap[String, Int] = Map(Hello -&gt; <span class="hljs-number">3</span>, People -&gt; <span class="hljs-number">10</span>, Planet -&gt; <span class="hljs-number">9</span>)
  res1: (String, Int) = (Hello,<span class="hljs-number">3</span>)</code></pre>