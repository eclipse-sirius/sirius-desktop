/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.internal.navigation;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.ui.dialogs.OpenTypeSelectionDialog;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IJavaAwareInterpreter;
import org.eclipse.sirius.common.tools.internal.interpreter.ServiceInterpreter;
import org.eclipse.sirius.editor.Messages;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;

/**
 * This navigator opens the JAVA editor on all the classes containing the method corresponding to a service call in a
 * VSM expression. If two classes contains two method with the same name, we focus on the first one after opening.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
@SuppressWarnings("restriction")
public class ServiceNavigator implements INavigatorFromVSMExpression {
    /**
     * The JAVA class representations containing the service used in a VSM expression and pointing out by the cursor.
     */
    private Set<IJavaElement> navigationTargets;

    /**
     * Initialize data structures.
     */
    public ServiceNavigator() {
        navigationTargets = new HashSet<>();
    }

    @Override
    public void triggerNavigation(AbstractViewpointPropertySection propertySection, EObject targetEObject, ContentContext contentContext) {
        try {
            if (navigationTargets.isEmpty()) {
                initializeNavigationTarget(propertySection, targetEObject, contentContext);
            }
            if (navigationTargets.size() == 1) {
                JavaUI.openInEditor(navigationTargets.iterator().next());
            } else {
                IJavaSearchScope javaSearchScope = SearchEngine.createJavaSearchScope(navigationTargets.toArray(new IJavaElement[0]));
                OpenTypeSelectionDialog dialog = new OpenTypeSelectionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), true, PlatformUI.getWorkbench().getProgressService(),
                        javaSearchScope, IJavaSearchConstants.METHOD);
                dialog.setTitle(Messages.ServiceNavigator_serviceNavigationDialog_title);
                dialog.setMessage(Messages.ServiceNavigator_serviceNavigationDialog_description);
                dialog.setInitialPattern("?");
                int result = dialog.open();
                if (result == IDialogConstants.OK_ID) {
                    Object[] types = dialog.getResult();
                    Arrays.stream(types).forEach(type -> {
                        for (IJavaElement navigationTarget : navigationTargets) {
                            if (navigationTarget != null && navigationTarget.getParent().equals(type)) {
                                try {
                                    JavaUI.openInEditor(navigationTarget);
                                } catch (PartInitException | JavaModelException e) {
                                    SiriusEditorPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditorPlugin.PLUGIN_ID,
                                            MessageFormat.format(Messages.ServiceNavigator_targetInitialization_error, contentContext.getContents()), e));
                                }
                            }
                        }
                    });
                }
            }
        } catch (PartInitException | JavaModelException e) {
            SiriusEditorPlugin.getPlugin().getLog()
                    .log(new Status(IStatus.ERROR, SiriusEditorPlugin.PLUGIN_ID, MessageFormat.format(Messages.ServiceNavigator_targetInitialization_error, contentContext.getContents()), e));
        }
    }

    /**
     * Initialize the {@link IJavaElement} list that are implementations of a same Java service call from a VSM
     * expression.
     * 
     * @param propertySection
     *            the property section containing the VSM expression.
     * @param targetEObject
     *            the VSM object containing the expression from which we execute a navigation.
     * @param contentContext
     *            the {@link ContentContext} containing the expression, the cursor position and the
     *            {@link IInterpreterContext}.
     * @throws JavaModelException
     *             if the Java class containing the service cannot be retrieved as an {@link IJavaElement}.
     */
    @SuppressWarnings("static-access")
    private void initializeNavigationTarget(AbstractViewpointPropertySection propertySection, EObject targetEObject, ContentContext contentContext) throws JavaModelException {
        String vsmExpression = contentContext.getContents();
        String serviceCallNameWithParenthesis = extractServiceCall(contentContext);
        if (serviceCallNameWithParenthesis != null) {
            String serviceCallName = serviceCallNameWithParenthesis;
            int leftParenthesisIndex = serviceCallName.indexOf("(");
            int rightParenthesisIndex = serviceCallName.indexOf(")");
            if (leftParenthesisIndex != -1) {
                serviceCallName = serviceCallName.substring(0, leftParenthesisIndex);
            }
            serviceCallName = serviceCallName.trim();
            serviceCallName = serviceCallName.replaceAll("self.", "");
            if (serviceCallName.startsWith(ServiceInterpreter.PREFIX)) {
                serviceCallName.replaceFirst(ServiceInterpreter.PREFIX, "");
            }
            IInterpreter itp = CompoundInterpreter.INSTANCE.getInterpreterForExpression(vsmExpression);
            if (itp instanceof IJavaAwareInterpreter) {
                IInterpreterContext interpreterContext = contentContext.getInterpreterContext();
                Collection<String> imports = interpreterContext.getDependencies();
                for (String tempImport : imports) {
                    itp.addImport(tempImport);
                }
                Resource vsmResource = interpreterContext.getElement().eResource();
                if (vsmResource != null) {
                    itp.setProperty(IInterpreter.FILES, Lists.newArrayList(vsmResource.getURI().toPlatformString(true)));
                }
                Collection<Method> implementations = ((IJavaAwareInterpreter) itp).getImplementation(serviceCallName);
                Iterator<Method> methodIte = implementations.iterator();
                while (methodIte.hasNext()) {
                    Method m = methodIte.next();
                    if (m != null) {
                        Resource vsm = targetEObject.eResource();
                        // CHECKSTYLE:OFF
                        if (vsm.getURI().isPlatformResource()) {
                            String projectName = vsm.getURI().segment(1);
                            IProject rawProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
                            if (rawProject != null) {
                                IJavaProject javaProject = JavaCore.getJavaCore().create(rawProject);
                                if (javaProject != null) {
                                    IType serviceClass = javaProject.findType(m.getDeclaringClass().getName());
                                    if (serviceClass != null && serviceClass.exists()) {
                                        for (IMethod jm : serviceClass.getMethods()) {
                                            boolean onlySelfParameter = leftParenthesisIndex == -1 && rightParenthesisIndex == -1;

                                            if (jm.getElementName().equals(serviceCallName)) {
                                                int methodParametersNumber = jm.getParameters().length;
                                                long serviceParametersNumber = getServiceParametersNumber(vsmExpression, leftParenthesisIndex, rightParenthesisIndex);
                                                if (!onlySelfParameter && methodParametersNumber == serviceParametersNumber || (onlySelfParameter && methodParametersNumber == 1)) {
                                                    navigationTargets.add(jm);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                // CHECKSTYLE:ON
            }
        }
    }

    /**
     * Extract and return the service call part from given context if such call exists.
     * 
     * @param contentContext
     *            the context from which service call part must be extracted.
     * @return the service call part from given context if such call exists. Null otherwise.
     */
    private String extractServiceCall(ContentContext contentContext) {
        String serviceName = null;
        String expression = contentContext.getContents();
        boolean isServiceInterpreterUsed = expression.startsWith(ServiceInterpreter.PREFIX);
        int cursorPosition = contentContext.getPosition();
        if (isServiceInterpreterUsed) {
            expression = expression.replaceFirst(ServiceInterpreter.PREFIX, "");
            if (cursorPosition > ServiceInterpreter.PREFIX.length()) {
                cursorPosition = cursorPosition - ServiceInterpreter.PREFIX.length();
            }
        }
        char[] expressionArray = expression.toCharArray();

        int serviceCallStartPosition = -1;
        int i = initSearchPosition(expressionArray, cursorPosition);
        while (i >= 0 && serviceCallStartPosition == -1) {
            char tempChar = expressionArray[i];
            if (tempChar == '.' || tempChar == ':' || (tempChar == ' ')) {
                serviceCallStartPosition = i + 1;
            }
            i--;
        }
        if (i == -1 && isServiceInterpreterUsed) {
            serviceCallStartPosition = 0;
        }
        int serviceCallEndPosition = -1;
        int j = initSearchPosition(expressionArray, cursorPosition);
        while (j < expressionArray.length && serviceCallEndPosition == -1) {
            char tempChar = expressionArray[j];
            if (tempChar == ')') {
                serviceCallEndPosition = j + 1;
            }
            j++;
        }
        if (serviceCallStartPosition != -1 && serviceCallEndPosition != -1) {
            serviceName = expression.substring(serviceCallStartPosition, serviceCallEndPosition);
        } else if (serviceCallEndPosition != -1) {
            serviceCallStartPosition = expression.indexOf(".");
            if (serviceCallStartPosition != -1 && serviceCallStartPosition < serviceCallEndPosition) {
                serviceName = expression.substring(serviceCallStartPosition + 1, serviceCallEndPosition);
            }
        }
        return serviceName;
    }

    /**
     * Return the search position from cursor position. If the cursor position is out of range of the expression array
     * length, then we use as start search position the closer index. Otherwise it is the cursor position.
     * 
     * @param expressionArray
     *            the expression array from which we compute a starting search position.
     * @param cursorPosition
     *            the cursor position from which we compute a starting search position.
     * @return the search position from cursor position.
     */
    private int initSearchPosition(char[] expressionArray, int cursorPosition) {
        int searchPosition = cursorPosition;
        if (cursorPosition == -1) {
            searchPosition = 0;
        } else if (expressionArray.length == cursorPosition) {
            searchPosition = expressionArray.length - 1;
        }
        return searchPosition;
    }

    private long getServiceParametersNumber(String vsmExpression, int leftParenthesisIndex, int rightParenthesisIndex) {
        long serviceParametersNumber;
        if (rightParenthesisIndex == leftParenthesisIndex + 1) {
            serviceParametersNumber = 1;
        } else {
            long parameterSeparatorNumber = vsmExpression.chars().filter(ch -> ch == ',').count();
            if (parameterSeparatorNumber == 0) {
                serviceParametersNumber = 2;
            } else {
                serviceParametersNumber = parameterSeparatorNumber + 2;
            }
        }
        return serviceParametersNumber;
    }

    @Override
    public boolean doProvideNavigationFor(AbstractViewpointPropertySection propertySection, EObject targetEObject, ContentContext contentContext) {
        try {
            initializeNavigationTarget(propertySection, targetEObject, contentContext);
        } catch (JavaModelException e) {
            SiriusEditorPlugin.getPlugin().getLog()
                    .log(new Status(IStatus.ERROR, SiriusEditorPlugin.PLUGIN_ID, MessageFormat.format(Messages.ServiceNavigator_targetInitialization_error, contentContext.getContents()), e));
        }
        return !navigationTargets.isEmpty();
    }

}
