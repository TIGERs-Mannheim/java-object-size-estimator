package com.joey.utils;

/**
 * A shell pseudo-node implementation for an array class.
 * 
 * @author (C) <a
 *         href="http://www.javaworld.com/columns/jw-qna-index.shtml">Vlad
 *         Roubtsov</a>, 2003
 */
final class ArrayShellProfileNode extends AbstractShellProfileNode {

  public String name() {
    return "<shell: "
        + ObjectProfiler.typeName(m_type, ObjectProfiler.SHORT_TYPE_NAMES)
        + ", length=" + m_length + ">";
  }

  ArrayShellProfileNode(final IObjectProfileNode parent, final Class type,
      final int length) {
    super(parent);

    m_type = type;
    m_length = length;
  }

  private final Class m_type;
  private final int m_length;

} // end of class
