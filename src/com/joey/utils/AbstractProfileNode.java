package com.joey.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedList;

/**
 * Abstract base class for all node implementations in this package.
 * 
 * @author (C) <a
 *         href="http://www.javaworld.com/columns/jw-qna-index.shtml">Vlad
 *         Roubtsov</a>, 2003
 */
abstract class AbstractProfileNode implements IObjectProfileNode, Comparable {

  public final int size() {
    return size;
  }

  public final IObjectProfileNode parent() {
    return parent;
  }

  public final IObjectProfileNode[] path() {
    IObjectProfileNode[] path = this.path;
    if (path != null)
      return path;
    else {
      final LinkedList /* IObjectProfileNode */_path = new LinkedList();
      for (IObjectProfileNode node = this; node != null; node = node.parent())
        _path.addFirst(node);

      path = new IObjectProfileNode[_path.size()];
      _path.toArray(path);

      this.path = path;
      return path;
    }
  }

  public final IObjectProfileNode root() {
    IObjectProfileNode node = this;
    for (IObjectProfileNode parent = parent(); parent != null; node = parent, parent = parent
        .parent())
      ;

    return node;
  }

  public final int pathlength() {
    final IObjectProfileNode[] path = this.path;
    if (path != null)
      return path.length;
    else {
      int result = 0;
      for (IObjectProfileNode node = this; node != null; node = node.parent())
        ++result;

      return result;
    }
  }

  public final String dump() {
    final StringWriter sw = new StringWriter();
    final PrintWriter out = new PrintWriter(sw);

    final INodeVisitor visitor = ObjectProfileVisitors.newDefaultNodePrinter(
        out, null, null, ObjectProfiler.SHORT_TYPE_NAMES);
    traverse(null, visitor);

    out.flush();
    return sw.toString();
  }

  public final int compareTo(final Object obj) {
    return ((AbstractProfileNode) obj).size - size;
  }

  public String toString() {
    return super.toString() + ": name = " + name() + ", size = " + size();
  }

  AbstractProfileNode(final IObjectProfileNode parent) {
    this.parent = parent;
  }

  int size;

  static final IObjectProfileNode[] EMPTY_OBJECTPROFILENODE_ARRAY = new IObjectProfileNode[0];

  private final IObjectProfileNode parent;
  private transient IObjectProfileNode[] path;

} // end of class
