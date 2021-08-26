/*******************************************************************************
 * Copyright (c) 2011; 2021 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableDescriptionImpl;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;

/**
 * Specialization of the default implementation for
 * {@link EditionTableDescriptionImpl}.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class EditionTableDescriptionSpec extends EditionTableDescriptionImpl {
    /**
     * {@inheritDoc}
     */
    @Override
    public EList<CreateLineTool> getAllCreateLine() {
        List<CreateLineTool> result = new ArrayList<>();
        result.addAll(this.getOwnedCreateLine());
        result.addAll(this.getReusedCreateLine());
        return unionReference(DescriptionPackage.eINSTANCE.getTableDescription_AllCreateLine(), result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<RepresentationCreationDescription> getAllRepresentationCreationDescriptions() {
        List<RepresentationCreationDescription> result = new ArrayList<>(); 
        result.addAll(this.getOwnedRepresentationCreationDescriptions());
        result.addAll(this.getReusedRepresentationCreationDescriptions());
        return unionReference(DescriptionPackage.eINSTANCE.getTableDescription_AllRepresentationCreationDescriptions(), result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<RepresentationNavigationDescription> getAllRepresentationNavigationDescriptions() {
        List<RepresentationNavigationDescription> result = new ArrayList<>();
        result.addAll(this.getOwnedRepresentationNavigationDescriptions());
        result.addAll(this.getReusedRepresentationNavigationDescriptions());
        return unionReference(DescriptionPackage.eINSTANCE.getTableDescription_AllRepresentationNavigationDescriptions(), result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<LineMapping> getAllLineMappings() {
        List<LineMapping> result = new ArrayList<>();
        result.addAll(this.getOwnedLineMappings());
        result.addAll(this.getReusedLineMappings());
        return unionReference(DescriptionPackage.eINSTANCE.getTableDescription_AllLineMappings(), result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<FeatureColumnMapping> getAllColumnMappings() {
        List<FeatureColumnMapping> result = new ArrayList<>();
        result.addAll(this.getOwnedColumnMappings());
        result.addAll(this.getReusedColumnMappings());
        return unionReference(DescriptionPackage.eINSTANCE.getEditionTableDescription_AllColumnMappings(), result);
    }
    
    private <T> EList<T> unionReference(EStructuralFeature feature, List<T> values) {
        return new EcoreEList.UnmodifiableEList<T>(this, feature, values.size(), values.toArray());
    }
}
