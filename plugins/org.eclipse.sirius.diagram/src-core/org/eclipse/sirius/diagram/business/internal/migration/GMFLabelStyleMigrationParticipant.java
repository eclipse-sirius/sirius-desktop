/*******************************************************************************
 * Copyright (c) 2015, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.migration;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;

/**
 * A migration participant following the bug #477191.<br />
 * This migration participant copies the GMF underline and strike-through
 * properties toward the Sirius style.
 * 
 * @author Florian Barbin
 *
 */
public class GMFLabelStyleMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {
    private static final Version MIGRATION_VERSION = new Version("10.1.0.201509162000"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            for (DView dView : dAnalysis.getOwnedViews()) {
                for (DDiagram dDiagram : Iterables.filter(new DViewQuery(dView).getLoadedRepresentations(), DDiagram.class)) {
                    DiagramCreationUtil diagramCreationUtil = new DiagramCreationUtil(dDiagram);
                    if (diagramCreationUtil.findAssociatedGMFDiagram()) {
                        Diagram gmfDiagram = diagramCreationUtil.getAssociatedGMFDiagram();
                        addFontFormatsFromGMFStyle(gmfDiagram);
                    }
                }
            }
            super.postLoad(dAnalysis, loadedVersion);
        }
    }

    private void addFontFormatsFromGMFStyle(Diagram gmfDiagram) {
        UnmodifiableIterator<View> iterator = Iterators.filter(gmfDiagram.eAllContents(), View.class);
        while (iterator.hasNext()) {
            View view = iterator.next();
            EObject element = view.getElement();
            if (element instanceof DDiagramElement) {
                org.eclipse.sirius.viewpoint.Style siriusStyle = ((DDiagramElement) element).getStyle();
                Style style = view.getStyle(NotationPackage.eINSTANCE.getFontStyle());
                if (style instanceof FontStyle && siriusStyle instanceof BasicLabelStyle) {
                    Set<FontFormat> fontFormatToSet = new HashSet<FontFormat>(((BasicLabelStyle) siriusStyle).getLabelFormat());
                    int initialSize = fontFormatToSet.size();
                    if (((FontStyle) style).isStrikeThrough()) {
                        fontFormatToSet.add(FontFormat.STRIKE_THROUGH_LITERAL);
                    }
                    if (((FontStyle) style).isUnderline()) {
                        fontFormatToSet.add(FontFormat.UNDERLINE_LITERAL);
                    }
                    if (initialSize != fontFormatToSet.size()) {
                        FontFormatHelper.setFontFormat(((BasicLabelStyle) siriusStyle).getLabelFormat(), fontFormatToSet);

                        // If the GMF underline and/or strike-through were set
                        // to true, that means the user customized those
                        // features. That was not set in the customFeatures list
                        // since it was not handled by Sirius.
                        String labelFormatFeatureName = ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT.getName();
                        if (!((BasicLabelStyle) siriusStyle).getCustomFeatures().contains(labelFormatFeatureName)) {
                            ((BasicLabelStyle) siriusStyle).getCustomFeatures().add(labelFormatFeatureName);
                        }
                    }
                }
            }
        }
    }
}
