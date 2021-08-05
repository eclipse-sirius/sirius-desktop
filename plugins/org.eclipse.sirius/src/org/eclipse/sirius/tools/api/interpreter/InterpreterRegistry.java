/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.api.interpreter;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Iterables;

/**
 * This class registers all {@link AcceleoInterpreter} for each viewpoint specification file.
 * 
 * 
 * @author cbrun
 */
public class InterpreterRegistry {
    /**
     * Error message when the interpreter could not be found.
     * 
     * @since 0.9.0
     */
    public static final String ERROR_MSG_IMPOSSIBLE_TO_FIND_AN_INTERPRETER = Messages.InterpreterRegistry_ImpossibleToFindInterpreterErrorMsg;

    /**
     * Return the model requests interpreter for the specified <code>modelElement</code>. The model requests interpreter
     * is retrieved with the resourceSet of <code>modelElement</code>.
     * 
     * @param modelElement
     *            a model element.
     * @return the model requests interpreter for the specified <code>modelElement</code>.
     */
    public IInterpreter getInterpreter(final EObject modelElement) {
        IInterpreter result = null;
        Session session = new EObjectQuery(modelElement).getSession();
        if (session == null && modelElement instanceof DSemanticDecorator) {
            DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) modelElement;
            session = new EObjectQuery(dSemanticDecorator.getTarget()).getSession();
        }
        if (session != null) {
            result = session.getInterpreter();
        } else {

            String reason;
            if (modelElement == null) {
                reason = Messages.InterpreterRegistry_nullModelElementErrorMsg;
            } else {
                reason = MessageFormat.format(Messages.InterpreterRegistry_sessionNotFoundErrorMsg, modelElement);
            }
            SiriusPlugin.getDefault().error(ERROR_MSG_IMPOSSIBLE_TO_FIND_AN_INTERPRETER + " - " + reason, new RuntimeException()); //$NON-NLS-1$
            result = CompoundInterpreter.INSTANCE;
        }
        return result;
    }

    /**
     * Prepare all imports of the specified model request interpreter and session.
     * 
     * @param inter
     *            the model requests interpreter to prepare.
     * @param session
     *            the current session
     * @since 0.9.0
     */
    public static void prepareImportsFromSession(final IInterpreter inter, final Session session) {
        Collection<String> imports = inter.getImports();

        final LinkedHashSet<String> allImports = new LinkedHashSet<String>();
        if (session != null) {
            for (final Viewpoint vp : session.getSelectedViewpoints(false)) {
                if (vp.eResource() != null) {
                    for (JavaExtension javaExtension : vp.getOwnedJavaExtensions()) {
                        allImports.add(javaExtension.getQualifiedClassName());
                    }
                }
            }
        }

        if (allImports.size() != imports.size() || !Iterables.elementsEqual(allImports, imports)) {
            inter.clearImports();

            for (String dependency : allImports) {
                inter.addImport(dependency);
            }
        }
    }
}
