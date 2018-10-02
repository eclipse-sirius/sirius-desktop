/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
