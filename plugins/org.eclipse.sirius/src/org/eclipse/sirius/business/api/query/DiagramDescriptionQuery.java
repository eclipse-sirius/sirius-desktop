/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.query;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramImportDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;

import com.google.common.collect.Lists;

/**
 * A class aggregating all the queries (read-only!) having a DiagramDescription
 * as a starting point.
 * 
 * @author cbrun
 * 
 */
public class DiagramDescriptionQuery {

    private DiagramDescription description;

    /**
     * Create a new query.
     * 
     * @param description
     *            the element to query.
     */
    public DiagramDescriptionQuery(DiagramDescription description) {
        this.description = description;
    }

    /**
     * return the diagram description superTypes.
     * 
     * @return the diagram description superTypes;
     */
    public Iterator<? extends DiagramDescription> superTypes() {
        return new SuperTypesIterator(description);
    }

    /**
     * Return the paste tools targeting the current description (or its import
     * hierarchy).
     * 
     * @return the drop tools in the mapping hierarchy.
     */
    public Collection<PasteDescription> getAllPasteTools() {
        Collection<PasteDescription> descs = Lists.newArrayList();
        for (final DiagramDescription diagHier : new SuperTypes(description)) {
            descs.addAll(diagHier.getPasteDescriptions());
        }
        descs.addAll(description.getPasteDescriptions());
        return descs;
    }

    /**
     * return a containing Group if available.
     * 
     * @return a containing Group if available.
     */
    public Option<Group> getParentGroup() {
        EObject current = description;
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

        public SuperTypes(final DiagramDescription description) {
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

        public SuperTypesIterator(DiagramDescription map) {
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
}
