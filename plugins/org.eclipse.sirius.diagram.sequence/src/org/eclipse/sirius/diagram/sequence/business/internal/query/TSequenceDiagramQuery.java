/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.query;

import java.util.Iterator;

import org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TExecutionMapping;
import org.eclipse.sirius.diagram.sequence.template.TLifelineMapping;
import org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

/**
 * Queries starting from a {@link TSequenceDiagram}.
 * 
 * @author cbrun
 * 
 */
public class TSequenceDiagramQuery {

    private TSequenceDiagram template;

    /**
     * Create a new query.
     * 
     * @param template
     *            starting point of the query.
     */
    public TSequenceDiagramQuery(TSequenceDiagram template) {
        this.template = template;
    }

    /**
     * return any Lifeline Mapping having the given name.
     * 
     * @param name
     *            name of the element to find.
     * @return any Lifeline Mapping having the given name.
     */
    public Iterator<TLifelineMapping> getLifeLineMappings(final String name) {
        return Iterators.filter(Iterators.filter(template.eAllContents(), TLifelineMapping.class), new Predicate<TLifelineMapping>() {

            public boolean apply(TLifelineMapping input) {
                return name.equals(input.getName());
            }

        });
    }

    /**
     * return any {@link TExecutionMapping} Mapping having the given name.
     * 
     * @param name
     *            name of the element to find.
     * @return any TExecutionMapping Mapping having the given name.
     */
    public Iterator<TExecutionMapping> getExecutionMappings(final String name) {
        return Iterators.filter(Iterators.filter(template.eAllContents(), TExecutionMapping.class), new Predicate<TExecutionMapping>() {

            public boolean apply(TExecutionMapping input) {
                return name.equals(input.getName());
            }

        });
    }

    /**
     * return any {@link TBasicMessageMapping} Mapping having the given name.
     * 
     * @param name
     *            name of the element to find.
     * @return any TBasicMessageMapping Mapping having the given name.
     */
    public Iterator<TBasicMessageMapping> getBasicMessageMapping(final String name) {
        return Iterators.filter(Iterators.filter(template.eAllContents(), TBasicMessageMapping.class), new Predicate<TBasicMessageMapping>() {

            public boolean apply(TBasicMessageMapping input) {
                return name.equals(input.getName());
            }

        });
    }

}
