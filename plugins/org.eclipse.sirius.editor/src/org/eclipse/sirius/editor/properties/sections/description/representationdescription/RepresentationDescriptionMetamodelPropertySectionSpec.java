/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
