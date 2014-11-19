package org.ibratti.sfxutils.combobox

trait ComboBoxSearchableFunctions[T] {
   def search(value: String): List[T]
   def internalToString(value: T): String = if (value == null) "" else toString(value)
   def toString(value: T): String
 }
