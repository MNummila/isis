package org.nakedobjects.persistence.file;

import org.nakedobjects.object.NakedObjectSpecification;
import org.nakedobjects.object.persistence.SerialOid;


/**
 * A logical collection of elements of a specified type
 */
public class CollectionData extends Data {
   private ReferenceVector elements;

   public CollectionData(NakedObjectSpecification type, SerialOid oid) {
      super(type, oid);
      elements = new ReferenceVector(oid);
   }
   
   public void addElement(SerialOid elementOid) {
     	elements.add(elementOid);
   }
   
   public void removeElement(SerialOid elementOid) {
   	elements.remove(elementOid);	
   }
   
   public ReferenceVector references() {
   	return elements;	
   }

   public String toString() {
      return "CollectionData[type=" + getClassName() + ",elements=" + elements + "]";
   }
}


/*
Naked Objects - a framework that exposes behaviourally complete
business objects directly to the user.
Copyright (C) 2000 - 2005  Naked Objects Group Ltd

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

The authors can be contacted via www.nakedobjects.org (the
registered address of Naked Objects Group is Kingsway House, 123 Goldworth
Road, Woking GU21 1NR, UK).
*/
