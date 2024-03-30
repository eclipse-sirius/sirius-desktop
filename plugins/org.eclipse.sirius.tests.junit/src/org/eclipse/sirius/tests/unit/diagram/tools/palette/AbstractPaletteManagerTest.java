/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.tools.palette;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Common code for palette tests.
 * 
 * @author dlecan
 */
public abstract class AbstractPaletteManagerTest extends SiriusDiagramTestCase {

    /**
     * Inner class for tests.
     * 
     * @author dlecan
     */
    protected static class Entry implements Comparable<Entry> {
        String label;

        String[] labelEntries;

        public Entry(String label, String... labelEntries) {
            this.label = label;
            this.labelEntries = labelEntries;
            Arrays.sort(this.labelEntries);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object obj) {
            // Partial implementation of equals, but this is inner class, so do
            // it quickly
            Entry other = (Entry) obj;
            return label.equals(other.label) && Arrays.equals(labelEntries, other.labelEntries);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return label.hashCode() * 31 + Arrays.hashCode(labelEntries);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int compareTo(Entry o) {
            return label.compareTo(o.label);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('\n');
            sb.append(label);
            for (String labelEntry : labelEntries) {
                sb.append('\n');
                sb.append('\t');
                sb.append(labelEntry);
            }
            return sb.toString();
        }
    }

    private static final Predicate<PaletteEntry> VISIBLE_ENTRY = new Predicate<PaletteEntry>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean apply(PaletteEntry input) {
            return input.isVisible();
        }
    };

    protected static final String PATH = "/data/unit/tools/palette/";

    protected DDiagram dDiagram;

    protected Diagram diagram;

    protected EditDomain editDomain;

    protected static Entry createNewEntry(String label, String... labelEntries) {
        return new Entry(label, labelEntries);
    }

    /**
     *
     */
    public AbstractPaletteManagerTest() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Palette definition
        PaletteContainer parent = new PaletteDrawer("");
        ToolEntry defaultEntry = new ToolEntry("", "", null, null) {
            // Nothing
        };
        defaultEntry.setParent(parent);

        PaletteRoot palette = new PaletteRoot();
        palette.setDefaultEntry(defaultEntry);

        editDomain = new DefaultEditDomain(null);
        editDomain.setPaletteViewer(new PaletteViewer() {

            private ToolEntry entry;

            @Override
            public void setActiveTool(ToolEntry newMode) {
                if (newMode == null)
                    entry = getPaletteRoot().getDefaultEntry();
                entry = newMode;
            }

            @Override
            public ToolEntry getActiveTool() {
                return entry;
            }

        });
        editDomain.setPaletteRoot(palette);

    }

    /**
     * Get representation description instance name
     * 
     * @return Representation description instance name
     */
    protected abstract String getRepresentationDescriptionInstanceName();

    /**
     * Get representation description name.
     * 
     * @return Representation description name.
     */
    protected abstract String getRepresentationDescriptionName();

    protected void doContentPaletteTest(PaletteRoot paletteRoot, SortedSet<Entry> expected) {
        SortedSet<Entry> visiblePaletteEntries = getVisiblePaletteEntries(paletteRoot);

        checkEquality(visiblePaletteEntries, expected);
    }

    protected void doContentPaletteTest(SortedSet<Entry> expected) {
        doContentPaletteTest(editDomain.getPaletteViewer().getPaletteRoot(), expected);
    }

    private void checkEquality(SortedSet<Entry> value, SortedSet<Entry> expected) {
        // Don't use assertEquals, as message is unreadable

        // Don't use equals on SortedSet, TreeSet indeed, because this is
        // compareTo on each element which will be used
        // Implementation of Entry#compareTo is used to sort, not to "equal"...
        boolean equals = new HashSet<Entry>(expected).equals(new HashSet<Entry>(value));

        if (!equals) {
            String message = "Palette content is wrong :\n";
            message += "Expected :\n";
            message += expected.toString();
            message += "\n\nActual:\n";
            message += value.toString();
            fail(message);
        }

    }

    @SuppressWarnings("unchecked")
    private SortedSet<Entry> getVisiblePaletteEntries(PaletteRoot root) {
        SortedSet<Entry> result = new TreeSet<>();

        Iterable<? extends PaletteEntry> filtered = Iterables.filter(root.getChildren(), VISIBLE_ENTRY);
        for (PaletteEntry paletteEntry : filtered) {

            String[] allLabelEntries;
            if (paletteEntry instanceof PaletteContainer) {
                List<String> labelEntries = getLabelEntries(paletteEntry);
                allLabelEntries = labelEntries.toArray(new String[labelEntries.size()]);
            } else {
                allLabelEntries = new String[0];
            }

            String label = paletteEntry.getLabel();
            Entry entry = new Entry(label, allLabelEntries);
            result.add(entry);
        }

        return result;
    }

    private List<String> getLabelEntries(PaletteEntry paletteEntry) {
        List<String> result = new ArrayList<>();

        Iterable<PaletteEntry> filtered2 = Iterables.filter((List<PaletteEntry>) ((PaletteContainer) paletteEntry).getChildren(), VISIBLE_ENTRY);

        for (PaletteEntry subEntry : filtered2) {
            // If a subEntry is a PaletteContainer, it means it may
            // contain other children, but palette is flat, so they will
            // be ignored
            // So subEntry shouldn't be this type
            if (subEntry instanceof PaletteStack) {
                List<String> stackLabelEntries = getLabelEntries(subEntry);
                result.addAll(stackLabelEntries);
            } else {
                assertFalse("This entry " + subEntry + " shouldn't have this type: " + subEntry.getClass(), subEntry instanceof PaletteContainer);
                result.add(subEntry.getLabel());
            }
        }
        return result;
    }

    /**
     * Returns all the visible {@link PaletteEntry} (Sections, Tools...)
     * currently displayed by the given {@link PaletteRoot}.
     */
    protected Set<PaletteEntry> getAllVisiblePaletteEntries(PaletteRoot root) {
        TestsUtil.synchronizationWithUIThread();
        Set<PaletteEntry> paletteEntries = new LinkedHashSet<>();

        @SuppressWarnings("unchecked")
        Iterable<? extends PaletteEntry> allVisibleChildren = Iterables.filter(root.getChildren(), VISIBLE_ENTRY);
        for (PaletteEntry paletteEntry : allVisibleChildren) {
            if (paletteEntry instanceof PaletteContainer) {
                paletteEntries.addAll(getAllVisiblePaletteEntries(paletteEntry));
            } else {
                paletteEntries.add(paletteEntry);
            }
        }

        return paletteEntries;
    }

    /**
     * 
     * Returns all the visible {@link PaletteEntry} (Sections, Tools...)
     * currently displayed by the {@link PaletteEntry}.
     * 
     * @param paletteEntry
     *            the {@link PaletteEntry} to inspect
     * @return
     */
    private Collection<? extends PaletteEntry> getAllVisiblePaletteEntries(PaletteEntry paletteEntry) {
        Set<PaletteEntry> paletteEntries = new LinkedHashSet<>();

        Iterable<PaletteEntry> children = Iterables.filter((List<PaletteEntry>) ((PaletteContainer) paletteEntry).getChildren(), VISIBLE_ENTRY);

        for (PaletteEntry subEntry : children) {
            if (subEntry instanceof PaletteStack) {
                paletteEntries.addAll(getAllVisiblePaletteEntries(subEntry));
            } else {
                // If a subEntry is a PaletteContainer, it means it may
                // contain other children, but palette is flat, so they will
                // be ignored So subEntry shouldn't be this type
                assertFalse("This entry " + subEntry + " shouldn't have this type: " + subEntry.getClass(), subEntry instanceof PaletteContainer);
                paletteEntries.add(subEntry);
            }
        }
        return paletteEntries;
    }

}
