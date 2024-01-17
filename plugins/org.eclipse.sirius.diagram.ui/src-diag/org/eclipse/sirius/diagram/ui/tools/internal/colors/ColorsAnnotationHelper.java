/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.colors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.swt.graphics.RGB;

/**
 * This helper provides utility methods to manage colors category persisted in aird file.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public final class ColorsAnnotationHelper {

    /**
     * The separator between each integer value of RGB color.
     */
    private static final String RGB_VALUES_SEPARATOR = ","; //$NON-NLS-1$

    /**
     * The current sirius session.
     */
    private final Session session;

    /**
     * The optional {@link DAnalysis} associated to the sirius session.
     */
    private Optional<DAnalysis> optDanalysis;

    /**
     * Creates an instance of {@link ColorsAnnotationHelper}.
     * 
     * @param session
     *            the current sirius session.
     */
    public ColorsAnnotationHelper(final Session session) {
        this.session = session;
        if (session != null) {
            this.optDanalysis = this.session.getSharedMainDAnalysis();
        }
    }

    /**
     * Gets the {@link DAnnotationEntry} for the specified source annotation or create it if not found.<br/>
     * It does not change the session model.
     * 
     * @param colorsAnnotationSourceName
     *            the source annotation used to retrieve the associated {@link DAnnotationEntry}.
     * @return the {@link DAnnotationEntry} associated to the specified color category.
     */
    private DAnnotationEntry getOrCreateColorAnnotationEntry(String colorsAnnotationSourceName) {
        DAnnotationEntry dAnnotationEntry = null;
        if (this.optDanalysis.isPresent()) {
            dAnnotationEntry = getColorAnnotationEntry(colorsAnnotationSourceName).orElseGet(() -> {
                DAnnotationEntry annotationEntry = DescriptionFactory.eINSTANCE.createDAnnotationEntry();
                annotationEntry.setSource(colorsAnnotationSourceName);
                return annotationEntry;
            });
        }
        return dAnnotationEntry;
    }

    /**
     * Gets the {@link DAnnotationEntry} for the specified source annotation.
     * 
     * @param colorsAnnotationSourceName
     *            the source annotation used to retrieve the associated {@link DAnnotationEntry}.
     * @return the optional {@link DAnnotationEntry} associated to the specified color category.
     */
    private Optional<DAnnotationEntry> getColorAnnotationEntry(final String colorsAnnotationSourceName) {
        if (this.optDanalysis.isPresent()) {
            final List<DAnnotationEntry> sessionAnnotations = optDanalysis.get().getEAnnotations();
            for (final DAnnotationEntry annotationEntry : sessionAnnotations) {
                if (annotationEntry.getSource().equals(colorsAnnotationSourceName)) {
                    return Optional.of(annotationEntry);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Sets the details of the {@link DAnnotationEntry} with the specified colorsList.
     * 
     * @param colorsAnnotationSourceName
     *            the source annotation used to retrieve the associated {@link DAnnotationEntry}.
     * @param colorsList
     *            the list of colors to set for the specified color category.
     */
    public void setColorsDetails(String colorsAnnotationSourceName, List<RGB> colorsList) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                DAnnotationEntry colorsEntry = getOrCreateColorAnnotationEntry(colorsAnnotationSourceName);
                List<String> stringColorsList = convertRGBToString(colorsList);
                List<String> colorDetails = colorsEntry.getDetails();
                colorDetails.clear();
                colorDetails.addAll(stringColorsList);

                if (!colorDetails.isEmpty()) {
                    addColorAnnotation(colorsEntry);
                }
            }
        });
    }

    /**
     * Gets the list of colors of a specified color category.
     * 
     * @param colorsAnnotationSourceName
     *            the source annotation used to retrieve the associated {@link DAnnotationEntry}.
     * @return the list of colors of the specified color category.
     */
    public List<RGB> getColorsDetails(String colorsAnnotationSourceName) {
        List<RGB> colors = new ArrayList<>();
        Optional<DAnnotationEntry> optAnnotationEntry = getColorAnnotationEntry(colorsAnnotationSourceName);
        if (optAnnotationEntry.isPresent()) {
            List<String> stringColors = optAnnotationEntry.get().getDetails();
            colors.addAll(convertStringToRGB(stringColors));
        }
        return colors;
    }

    /**
     * Converts a list of RGB colors to a list of colors as string. Used to persist strings value of a RGB colors list.
     * 
     * @param colorsList
     *            the list of RGB colors to convert.
     * @return the list of colors as string
     */
    private List<String> convertRGBToString(List<RGB> colorsList) {
        List<String> stringColorsList = new ArrayList<>();
        if (colorsList != null) {
            for (RGB rgb : colorsList) {
                String stringColor = rgb.red + RGB_VALUES_SEPARATOR + rgb.green + RGB_VALUES_SEPARATOR + rgb.blue;
                stringColorsList.add(stringColor);
            }
        }
        return stringColorsList;
    }

    /**
     * Converts a list of string rgb colors to a list of usable RGB colors. Mainly used to get a usable list of colors
     * from the {@link DAnnotationEntry}.
     * 
     * @param stringColorsList
     *            the list of colors as string
     * @return the list of RGB colors
     */
    private List<RGB> convertStringToRGB(List<String> stringColorsList) {
        List<RGB> colors = new ArrayList<>();
        if (stringColorsList != null) {
            for (String stringColor : stringColorsList) {
                String[] stringToParseArray = stringColor.split(RGB_VALUES_SEPARATOR);
                RGB color = new RGB(Integer.parseInt(stringToParseArray[0]), Integer.parseInt(stringToParseArray[1]), Integer.parseInt(stringToParseArray[2]));
                colors.add(color);
            }
        }
        return colors;
    }

    /**
     * Adds a {@link DAnnotationEntry} to the {@link DAnalysis}.
     * 
     * @param colorsEntry
     *            the entry to add.
     */
    private void addColorAnnotation(DAnnotationEntry colorsEntry) {
        if (this.optDanalysis.isPresent()) {
            this.optDanalysis.get().getEAnnotations().add(colorsEntry);
        }
    }
}
