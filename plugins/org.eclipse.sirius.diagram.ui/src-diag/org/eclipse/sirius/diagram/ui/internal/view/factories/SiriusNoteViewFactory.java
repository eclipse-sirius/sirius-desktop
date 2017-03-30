/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.view.factories;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.NoteViewFactory;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.TextAlignment;
import org.eclipse.gmf.runtime.notation.TextStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;

import com.google.common.collect.Iterables;

/**
 * Specific view factory for Notes created from the Palette. Default label alignment of Note's shapes have been updated
 * in GMF runtime 1.8.0 (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=432387). This factory creates notes with the
 * old default alignment value.
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 *
 */
public class SiriusNoteViewFactory extends NoteViewFactory {

    /**
     * Create an {@link EAnnotation} that set the default vertical alignment (org.eclipse.draw2d.PositionConstants.TOP).
     * 
     * @return an {@link EAnnotation} with the default vertical alignment set.
     */
    public static EAnnotation createDefaultVerticalAlignmentEAnnotation() {
        EAnnotation specificStyles = EcoreFactory.eINSTANCE.createEAnnotation();
        specificStyles.setSource(ViewQuery.SPECIFIC_STYLES);
        EObject defaultVerticalAlignment = EcoreFactory.eINSTANCE.create(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY);
        if (defaultVerticalAlignment instanceof EStringToStringMapEntryImpl) {
            ((EStringToStringMapEntryImpl) defaultVerticalAlignment).setKey(ViewQuery.VERTICAL_ALIGNMENT);
            ((EStringToStringMapEntryImpl) defaultVerticalAlignment).setValue(String.valueOf(PositionConstants.TOP));
            specificStyles.getDetails().add((EStringToStringMapEntryImpl) defaultVerticalAlignment);
        }
        return specificStyles;
    }

    /**
     * Set the {@link TextStyle} with the default horizontal alignment.
     * 
     * @param styles
     *            the styles of the Note.
     */
    public void setDefaultHorizontalAlignment(Collection<Style> styles) {
        for (TextStyle textStyle : Iterables.filter(styles, TextStyle.class)) {
            textStyle.setTextAlignment(TextAlignment.CENTER_LITERAL);
            return;
        }
    }

    @Override
    protected List<?> createStyles(View view) {
        List<Style> styles = super.createStyles(view);
        EAnnotation verticalAlignment = createDefaultVerticalAlignmentEAnnotation();
        view.getEAnnotations().add(verticalAlignment);
        setDefaultHorizontalAlignment(styles);
        return styles;
    }
}
