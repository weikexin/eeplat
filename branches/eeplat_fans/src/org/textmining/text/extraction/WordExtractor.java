package org.textmining.text.extraction;

import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.util.LittleEndian;

import java.util.*;
import java.io.*;

/**
 * This class extracts the text from a Word 97/2000/XP word doc
 *
 * @author Ryan Ackley
 */
public class WordExtractor
{

  /**
   * Constructor
   */
  public WordExtractor()
  {
  }

  /**
   * Gets the text from a Word document.
   *
   * @param in The InputStream representing the Word file.
   */
  public String extractText(InputStream in) throws Exception
  {
    ArrayList text = new ArrayList();
    POIFSFileSystem fsys = new POIFSFileSystem(in);

    DocumentEntry headerProps =
        (DocumentEntry)fsys.getRoot().getEntry("WordDocument");
    DocumentInputStream din = fsys.createDocumentInputStream("WordDocument");
    byte[] header = new byte[headerProps.getSize()];

    din.read(header);
    din.close();
    //Get the information we need from the header
    int info = LittleEndian.getShort(header, 0xa);

    boolean useTable1 = (info & 0x200) != 0;

    //get the location of the piece table
    int complexOffset = LittleEndian.getInt(header, 0x1a2);


    String tableName = null;
    if (useTable1)
    {
      tableName = "1Table";
    }
    else
    {
      tableName = "0Table";
    }

    DocumentEntry table = (DocumentEntry)fsys.getRoot().getEntry(tableName);
    byte[] tableStream = new byte[table.getSize()];

    din = fsys.createDocumentInputStream(tableName);

    din.read(tableStream);
    din.close();

    din = null;
    fsys = null;
    table = null;
    headerProps = null;

    int multiple = findText(tableStream, complexOffset, text);

    StringBuffer sb = new StringBuffer();
    int size = text.size();
    tableStream = null;

    for (int x = 0; x < size; x++)
    {
      WordTextPiece nextPiece = (WordTextPiece)text.get(x);
      int start = nextPiece.getStart();
      int length = nextPiece.getLength();

      boolean unicode = nextPiece.usesUnicode();
      String toStr = null;
      if (unicode)
      {
        toStr = new String(header, start, length * multiple, "UTF-16LE");
      }
      else
      {
        toStr = new String(header, start, length , "ISO-8859-1");
      }
      sb.append(toStr).append(" ");

    }
    return sb.toString();
  }

  /**
   * Goes through the piece table and parses out the info regarding the text
   * blocks. For Word 97 and greater all text is stored in the "complex" way
   * because of unicode.
   *
   * @param tableStream buffer containing the main table stream.
   * @param beginning of the complex data.
   * @throws IOException
   */
  private static int findText(byte[] tableStream, int complexOffset, ArrayList text) throws IOException
  {
    //actual text
    int pos = complexOffset;
    int multiple = 2;
    //skips through the prms before we reach the piece table. These contain data
    //for actual fast saved files
    while(tableStream[pos] == 1)
    {
        pos++;
        int skip = LittleEndian.getShort(tableStream, pos);
        pos += 2 + skip;
    }
    if(tableStream[pos] != 2)
    {
        throw new IOException("corrupted Word file");
    }
    else
    {
        //parse out the text pieces
        int pieceTableSize = LittleEndian.getInt(tableStream, ++pos);
        pos += 4;
        int pieces = (pieceTableSize - 4) / 12;
        for (int x = 0; x < pieces; x++)
        {
            int filePos = LittleEndian.getInt(tableStream, pos + ((pieces + 1) * 4) + (x * 8) + 2);
            boolean unicode = false;
            if ((filePos & 0x40000000) == 0)
            {
                unicode = true;
            }
            else
            {
                unicode = false;
                multiple = 1;
                filePos &= ~(0x40000000);//gives me FC in doc stream
                filePos /= 2;
            }
            int totLength = LittleEndian.getInt(tableStream, pos + (x + 1) * 4) -
                            LittleEndian.getInt(tableStream, pos + (x * 4));

            WordTextPiece piece = new WordTextPiece(filePos, totLength, unicode);
            text.add(piece);

        }

    }
    return multiple;
  }

}
