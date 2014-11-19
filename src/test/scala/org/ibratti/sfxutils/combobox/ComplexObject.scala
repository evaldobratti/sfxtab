package org.ibratti.sfxutils.combobox

object ComplexObject {
   def apply(stringField: String) = new ComplexObject(stringField)
 }

class ComplexObject(val stringField: String)