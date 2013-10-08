/*******************************************************************************
 * Copyright (c) 2008, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.collect.Iterables;

import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * This class registers all {@link AcceleoInterpreter} for each viewpoint
 * specification file.
 * 
 * 
 * @author cbrun
 */
public class InterpreterRegistry {
    /**
     * Error message when the interpreter could not be found.
     * 
     * @since 2.1
     */
    public static final String ERROR_MSG_IMPOSSIBLE_TO_FIND_AN_INTERPRETER = "Impossible to find an interpreter";

    /** Map a TODO. */
    private Map<String, IInterpreter> root2ExPackage = new HashMap<String, IInterpreter>();

    /**
     * Return the resource that contains the current specification.
     * 
     * @param modelElement
     *            a model element.
     * @return the resource that contains the current specification.
     */
    private static Resource getModelerDescriptionResource(final EObject modelElement) {

        Resource res = null;
        final List<Resource> airResources = new ArrayList<Resource>();
        final Resource resource = modelElement.eResource();
        if (resource != null && resource.getResourceSet() != null) {
            final Iterator<Resource> it = resource.getResourceSet().getResources().iterator();
            while (it.hasNext()) {
                final Resource currentRes = it.next();
                final URI uri = currentRes.getURI();
                if (uri != null && new FileQuery(uri.fileExtension()).isVSMFile()) {
                    if (res == null) {
                        res = currentRes;
                    }
                    airResources.add(res);
                }
            }
        }
        return res;
    }

    /**
     * Return the model requests interpreter for the specified
     * <code>modelElement</code>. The model requests interpreter is retrieved
     * with the resourceSet of <code>modelElement</code>.
     * 
     * @param modelElement
     *            a model element.
     * @return the model requests interpreter for the specified
     *         <code>modelElement</code>.
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
                reason = "Model element is null";
            } else {
                reason = "Could not find a session for model element : " + modelElement;
            }
            SiriusPlugin.getDefault().error(ERROR_MSG_IMPOSSIBLE_TO_FIND_AN_INTERPRETER + " - " + reason, new RuntimeException());
            result = CompoundInterpreter.INSTANCE;
        }
        return result;
    }

    private String getMapKeyFromResource(final EObject modelElement) {
        String uri = "";
        if (modelElement != null) {
            final Resource modelerDescriptionResource = InterpreterRegistry.getModelerDescriptionResource(modelElement);
            if (modelerDescriptionResource != null && modelerDescriptionResource.getURI() != null) {
                uri = modelerDescriptionResource.getURI().toString();
            }
        }
        return uri;
    }

    /**
     * Dispose the registry. Clear the cache for the specified element.
     * 
     * @param modelElement
     *            a model element.
     * @deprecated this method is now useless because there is nothing to
     *             dispose.
     */
    public void disposeInterpreterRegistry(final EObject modelElement) {
        final String uri = getMapKeyFromResource(modelElement);
        if (root2ExPackage.containsKey(uri)) {
            root2ExPackage.remove(uri);
        }
    }

    /**
     * Prepare all imports of the specified model request interpreter and model
     * element.
     * 
     * @param inter
     *            the model requests interpreter to prepare.
     * @param modelElement
     *            a model element.
     * @deprecated
     * @see InterpreterRegistry#prepareImportsFromSession(IInterpreter, Session)
     */
    @Deprecated
    public static void prepareImportsFromModelElement(final IInterpreter inter, final EObject modelElement) {
        InterpreterRegistry.prepareImportsFromSession(inter, SessionManager.INSTANCE.getSession(modelElement));
    }

    /**
     * Prepare all imports of the specified model request interpreter and model
     * element.
     * 
     * @param inter
     *            the model requests interpreter to prepare.
     * @param session
     *            the current session
     * @since 2.6
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

    /**
     * Dispose all model requests interpreter.
     * 
     * @deprecated this method is now useless because there is nothing to
     *             dispose.
     */
    public void dispose() {
        root2ExPackage.clear();
    }

}
