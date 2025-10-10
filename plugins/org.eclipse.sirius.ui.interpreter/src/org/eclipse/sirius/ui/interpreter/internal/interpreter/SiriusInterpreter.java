/*******************************************************************************
 * Copyright (c) 2011, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.interpreter.internal.interpreter;

import java.util.concurrent.Callable;

import org.eclipse.sirius.ui.interpreter.internal.SWTUtil;
import org.eclipse.sirius.ui.interpreter.internal.language.AbstractLanguageInterpreter;
import org.eclipse.sirius.ui.interpreter.internal.language.EvaluationContext;
import org.eclipse.sirius.ui.interpreter.internal.language.EvaluationResult;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * This implementation of a language interpreter adds the viewpoint interpreter to the list of languages available to
 * the interpreter view.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class SiriusInterpreter extends AbstractLanguageInterpreter {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.interpreter.internal.language.AbstractLanguageInterpreter#getEvaluationTask(org.eclipse.sirius.ui.interpreter.internal.language.EvaluationContext)
     */
    @Override
    public Callable<EvaluationResult> getEvaluationTask(EvaluationContext context) {
        return new SiriusEvaluationTask(context);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.interpreter.internal.language.AbstractLanguageInterpreter#createSourceViewer(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public SourceViewer createSourceViewer(Composite parent) {
        final SourceViewer viewer = new SiriusInterpreterSourceViewer(parent, null, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        final SiriusSourceViewerConfiguration config = new SiriusSourceViewerConfiguration();
        viewer.configure(config);
        SWTUtil.setUpScrollableListener(viewer.getTextWidget());
        viewer.setDocument(new Document());
        return viewer;
    }
}
