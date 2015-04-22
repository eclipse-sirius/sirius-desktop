package org.eclipse.sirius.table.ui.tools.internal.paperclips;

/**
 * 
 * 
 */
public interface LayerEntryIterator {

	PrintIterator getTarget();

	int getAlignment();

	LayerEntryIterator copy();
}