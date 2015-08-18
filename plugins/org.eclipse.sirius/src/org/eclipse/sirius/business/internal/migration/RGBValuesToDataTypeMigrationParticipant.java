/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.osgi.framework.Version;
import org.xml.sax.Attributes;

/**
 * A {@link AbstractRepresentationsFileMigrationParticipant} that retrieves
 * red,blue and green xml coming from the former RGBValues EObject into the
 * corresponding information as a datatype instance.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 * 
 */
public class RGBValuesToDataTypeMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The VP version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("10.0.0.201502132000"); //$NON-NLS-1$

    /**
     * 
     * {@inheritDoc}
     * 
     * @see fr.obeo.dsl.viewpoint.business.api.migration.IMigrationParticipant#getMigrationVersion()
     */
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postXMLEndElement(Object doneObject, Attributes xmlAttributes, String uri, String localName, String name, String loadedVersion) {
        if (doneObject instanceof EObject && xmlAttributes != null && xmlAttributes.getLength() > 0) {
            EObject peek = (EObject) doneObject;
            EStructuralFeature colorFeature = peek.eClass().getEStructuralFeature(localName);
            if (colorFeature != null && colorFeature.getEType() == ViewpointPackage.Literals.RGB_VALUES) {

                // Do not init the color with the new default values
                // ((RGBValues) peek.eGet(colorFeature);) but with the previous
                // default serialized values: the RGBValues EClass had 0 as
                // default value for its red, green and blue attributes (no
                // serialized attribute was equivalent to 0 for this attribute).
                // for example: <labelColor xmi:type="viewpoint:RGBValues"
                // xmi:id="_R3-bME_NEeCF36t5vBAODQ"/> was a black label.
                int red = 0;
                int green = 0;
                int blue = 0;

                int redIndex = xmlAttributes.getIndex("red"); //$NON-NLS-1$
                int greenIndex = xmlAttributes.getIndex("green"); //$NON-NLS-1$
                int blueIndex = xmlAttributes.getIndex("blue"); //$NON-NLS-1$
                if (redIndex != -1) {
                    red = Integer.valueOf(xmlAttributes.getValue(redIndex));
                }
                if (greenIndex != -1) {
                    green = Integer.valueOf(xmlAttributes.getValue(greenIndex));
                }
                if (blueIndex != -1) {
                    blue = Integer.valueOf(xmlAttributes.getValue(blueIndex));
                }
                peek.eSet(colorFeature, RGBValues.create(red, green, blue));
            }
        }
    }

}
