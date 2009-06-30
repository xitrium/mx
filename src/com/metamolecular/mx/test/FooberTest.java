/*
 * MX Cheminformatics Tools for Java
 * 
 * Copyright (c) 2007-2009 Metamolecular, LLC
 * 
 * http://metamolecular.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.metamolecular.mx.test;

import com.metamolecular.mx.walk.Foober;
import com.metamolecular.mx.model.Atom;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 * @author Richard L. Apodaca <rapodaca at metamolecular.com>
 */
public class FooberTest extends TestCase
{
  private Foober foober;
  private Collection<String> paths;
  private Atom atom;
  private Map<Atom, String> dictionary;

  @Override
  protected void setUp() throws Exception
  {
    atom = mock(Atom.class);
    paths = mock(Set.class);
    foober = null;
    dictionary = new HashMap();

    when(atom.getSymbol()).thenReturn(".");
  }

  public void testItWritesCarbonAtomAfterWalkEnd()
  {
    doNew();
    foober.atomFound(atom);
    foober.walkEnd(atom);

    verify(paths, times(1)).add(".");
  }

  public void testItWritesCarbonAtomAfterBranchEnd()
  {
    doNew();
    foober.atomFound(atom);
    foober.branchEnd(atom);

    verify(paths, times(1)).add(".");
  }

  public void testItWritesAtomInDictionaryAfterWalkEnd()
  {
    doNew();
    dictionary.put(atom, ".%");
    foober.setDictionary(dictionary);
    foober.atomFound(atom);
    foober.walkEnd(atom);

    verify(paths, times(1)).add(".%");
  }

  private void doNew()
  {
    foober = new Foober(paths);
  }
}