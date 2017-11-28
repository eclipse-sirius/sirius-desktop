/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.api.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramImportDescription;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;

/**
 * A class aggregating all the graphical queries (read-only!) having a
 * {@link DiagramDescription} as a starting point.
 * 
 * @author lredor
 * 
 */
public class DiagramDescriptionQuery {

    /**
     * The element to query.
     */
    protected DiagramDescription diagramDescription;

    /**
     * Create a new query.
     * 
     * @param diagramDescription
     *            the element to query.
     */
    public DiagramDescriptionQuery(DiagramDescription diagramDescription) {
        this.diagramDescription = diagramDescription;
    }

    /**
     * return the diagram description superTypes.
     * 
     * @return the diagram description superTypes;
     */
    public Iterator<? extends DiagramDescription> superTypes() {
        return new SuperTypesIterator(diagramDescription);
    }

    /**
     * Return the paste tools targeting the current description (or its import
     * hierarchy).
     * 
     * @return the drop tools in the mapping hierarchy.
     */
    public Collection<PasteDescription> getAllPasteTools() {
        Collection<PasteDescription> descs = new ArrayList<>();
        for (final DiagramDescription diagHier : new SuperTypes(diagramDescription)) {
            descs.addAll(diagHier.getPasteDescriptions());
        }
        descs.addAll(diagramDescription.getPasteDescriptions());
        return descs;
    }

    /**
     * return a containing Group if available.
     * 
     * @return a containing Group if available.
     */
    public Option<Group> getParentGroup() {
        EObject current = diagramDescription;
        while (current != null) {
            current = current.eContainer();
            if (current instanceof Group) {
                return Options.newSome((Group) current);
            }
        }
        return Options.newNone();
    }

    /**
     * Iterable to browse the diagram's hierarchy.
     * 
     * @author mporhel
     * 
     */
    private static class SuperTypes implements Iterable<DiagramDescription> {

        private DiagramDescription desc;

        SuperTypes(final DiagramDescription description) {
            this.desc = description;
        }

        public Iterator<DiagramDescription> iterator() {
            return new SuperTypesIterator(desc);
        }

    }

    /**
     * Iterator to browse the diagram's hierarchy.
     * 
     * @author cbrun
     * 
     */
    private static class SuperTypesIterator implements Iterator<DiagramDescription> {
        private DiagramDescription cur;

        SuperTypesIterator(DiagramDescription map) {
            this.cur = map;
        }

        public boolean hasNext() {
            return doGetNext() != null;
        }

        public DiagramDescription next() {
            cur = doGetNext();
            if (cur == null) {
                throw new NoSuchElementException();
            }
            return cur;
        }

        private DiagramDescription doGetNext() {
            DiagramDescription next = null;
            if (cur instanceof DiagramImportDescription) {
                next = ((DiagramImportDescription) cur).getImportedDiagram();
            }
            return next;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    /**
     * Return true if the header must be enabled, false otherwise.
     * 
     * @return true if the header must be enabled, false otherwise.
     */
    public boolean isHeaderSectionEnabled() {
        boolean isHeaderSectionEnabled = false;

        // Look for global preference
        String headerPrefKey = SiriusDiagramPreferencesKeys.PREF_DISPLAY_HEADER_SECTION.name();
        boolean prefHeaderEnabled = Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, headerPrefKey, false, null);
        if (prefHeaderEnabled) {
            // Look for header contributions.
            for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
                if (diagramTypeDescriptor.getDiagramDescriptionProvider().handles(diagramDescription.eClass().getEPackage())) {
                    isHeaderSectionEnabled = diagramTypeDescriptor.getDiagramDescriptionProvider().supportHeader();
                    break;
                }
            }
        }
        return isHeaderSectionEnabled;
    }
}
