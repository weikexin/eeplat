package org.textmining.text.extraction;

/**
 * This class stores info about the data structure describing a run of text.
 *
 * @author
 */

class WordTextPiece
{
  private int _fcStart;
  private boolean _usesUnicode;
  private int _length;

  public WordTextPiece(int start, int length, boolean unicode)
  {
    _usesUnicode = unicode;
    _length = length;
    _fcStart = start;
  }
   public boolean usesUnicode()
  {
      return _usesUnicode;
  }

  public int getStart()
  {
      return _fcStart;
  }
  public int getLength()
  {
    return _length;
  }



}
