/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.model.business.internal.description.spec;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl;

/**
 * The implementation of ContainerMapping.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class LineMappingSpec extends LineMappingImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getAllSubLines()
     */
    @Override
    public EList<LineMapping> getAllSubLines() {
        List<LineMapping> result = new ArrayList<>();
        result.addAll(this.getOwnedSubLines());
        result.addAll(this.getReusedSubLines());
        return unionReference(DescriptionPackage.eINSTANCE.getLineMapping_AllSubLines(), result);
    }
    
    private <T> EList<T> unionReference(EStructuralFeature feature, List<T> values) {
        return new EcoreEList.UnmodifiableEList<T>(this, feature, values.size(), values.toArray());
    }
}
