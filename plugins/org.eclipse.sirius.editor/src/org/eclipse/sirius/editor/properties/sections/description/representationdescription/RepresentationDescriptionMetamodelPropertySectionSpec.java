/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.editor.properties.sections.description.representationdescription;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * A {@link AbstractSiriusPropertySection} for the metamodels tab.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RepresentationDescriptionMetamodelPropertySectionSpec extends AbstractMetamodelPropertySectionSpec {

    @Override
    protected EStructuralFeature getFeature() {
        return DescriptionPackage.eINSTANCE.getRepresentationDescription_Metamodel();
    }

}
