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

import org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping;
import org.eclipse.sirius.diagram.sequence.description.ExecutionMapping;
import org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping;
import org.eclipse.sirius.diagram.sequence.description.MessageMapping;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

/**
 * Queries from a {@link SequenceDiagramDescription}.
 * 
 * @author cbrun
 * 
 */
public class SequenceDiagramDescriptionQuery {

    private SequenceDiagramDescription diag;

    /**
     * Create the queries.
     * 
     * @param diag
     *            the source of the queries.
     */
    public SequenceDiagramDescriptionQuery(SequenceDiagramDescription diag) {
        this.diag = diag;
    }

    /**
     * return any InstanceRoleMapping contained in the diagram having the given
     * name.
     * 
     * @param name
     *            name of the searched element.
     * @return any InstanceRoleMapping contained in the diagram having the given
     *         name.
     */
    public Iterator<InstanceRoleMapping> getInstanceRoleMappings(final String name) {
        return Iterators.filter(Iterators.filter(diag.eAllContents(), InstanceRoleMapping.class), new Predicate<InstanceRoleMapping>() {

            public boolean apply(InstanceRoleMapping input) {
                return name.equals(input.getName());
            }

        });
    }

    /**
     * return any ExecutionMapping contained in the diagram having the given
     * name.
     * 
     * @param name
     *            name of the searched element.
     * @return any ExecutionMapping contained in the diagram having the given
     *         name.
     */
    public Iterator<ExecutionMapping> getExecutionMappings(final String name) {
        return Iterators.filter(Iterators.filter(diag.eAllContents(), ExecutionMapping.class), new Predicate<ExecutionMapping>() {

            public boolean apply(ExecutionMapping input) {
                return name.equals(input.getName());
            }

        });
    }

    /**
     * return any {@link EndOfLifeMapping} contained in the diagram having the
     * given name.
     * 
     * @param name
     *            name of the searched element.
     * @return any EndOfLifeMapping contained in the diagram having the given
     *         name.
     */
    public Iterator<EndOfLifeMapping> getEndOfLifeMappings(final String name) {
        return Iterators.filter(Iterators.filter(diag.eAllContents(), EndOfLifeMapping.class), new Predicate<EndOfLifeMapping>() {

            public boolean apply(EndOfLifeMapping input) {
                return name.equals(input.getName());
            }

        });
    }

    /**
     * return any {@link MessageMapping} contained in the diagram having the
     * given name.
     * 
     * @param name
     *            name of the searched element.
     * @return any MessageMapping contained in the diagram having the given
     *         name.
     */
    public Iterator<MessageMapping> getMessageMappings(final String name) {
        return Iterators.filter(Iterators.filter(diag.eAllContents(), MessageMapping.class), new Predicate<MessageMapping>() {

            public boolean apply(MessageMapping input) {
                return name.equals(input.getName());
            }

        });
    }
}
