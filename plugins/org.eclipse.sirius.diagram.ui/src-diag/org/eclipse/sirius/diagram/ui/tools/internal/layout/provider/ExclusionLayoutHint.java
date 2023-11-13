/*******************************************************************************
 * Copyright (c) 2023 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.EditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;

/**
 * A layout hint to exclude specified elements from the layout algorithm.
 * 
 * @author scosta
 *
 */
public class ExclusionLayoutHint implements IAdaptable {

    ArrayList<EditPart> excluded;

    Optional<IAdaptable> parentLayoutHint;

    /**
     * Create a layout hint to exclude elements of <code>excluded</code> collection from the layout algorithm.
     * 
     * @param excluded
     *            The EditPart collection to exclude
     */
    public ExclusionLayoutHint(Collection<? extends EditPart> excluded) {
        this.excluded = createExclusionList(excluded);
        this.parentLayoutHint = Optional.empty();
    }

    /**
     * Create a layout hint to exclude elements of <code>excluded</code> collection from the layout algorithm.
     * 
     * @param excluded
     *            The EditPart collection to exclude
     * @param parentLayoutHint
     *            Other hint for the layout algorithm
     */
    public ExclusionLayoutHint(Collection<? extends EditPart> excluded, IAdaptable parentLayoutHint) {
        this.excluded = createExclusionList(excluded);
        this.parentLayoutHint = Optional.of(parentLayoutHint);
    }

    private ArrayList<EditPart> createExclusionList(Collection<? extends EditPart> excludedCollection) {
        return excludedCollection.stream() //
                .filter(IDiagramElementEditPart.class::isInstance) //
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (Collection.class.equals(adapter)) {
            // Here we know that T=Collection, so there's no need to check the type for cast.
            return (T) this.excluded;
        } else {
            return this.parentLayoutHint.map(hint -> hint.getAdapter(adapter)).orElse(null);
        }
    }

}
