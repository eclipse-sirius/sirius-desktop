/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.editor.properties.sections.properties.viewextensiondescription;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.editor.properties.sections.description.representationdescription.AbstractMetamodelPropertySectionSpec;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * An {@link AbstractMetamodelPropertySectionSpec} for the metamodels tab of the
 * properties view description.
 * 
 * @author sbegaudeau
 */
public class ViewExtensionDescriptionMetamodelsPropertySectionSpec extends AbstractMetamodelPropertySectionSpec {

    @Override
    protected EStructuralFeature getFeature() {
        return PropertiesPackage.eINSTANCE.getViewExtensionDescription_Metamodels();
    }

}
